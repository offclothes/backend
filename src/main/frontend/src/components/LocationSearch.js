import Form from "react-bootstrap/Form";
import "../css/locationSearch.css";
import { useEffect, useState } from "react";
import axios from "axios";
import LocationSearchResultPage from "./LocationSearchResult";
import Pagination from "./Pagination";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";

export default function LocationSearch() {
  //시, 구, 동 axios로 받아와서 top, middle, dong에 넣기
  //state에 넣은 값을 각각 select안에 있는 option에 넣기
  //검색 버튼 클릭 시 선택된 값을 axios로 보내주기
  //보내졌을 때 받아온 매장 정보를 화면에 띄워주기

  let [top, setTop] = useState("");
  let [middle, setMiddle] = useState("");
  let [dong, setDong] = useState("");
  let [topSelect, setTopSelect] = useState("");
  let [middleSelect, setMiddleSelect] = useState("");
  let [dongSelect, setDongSelect] = useState("");
  let [data, setData] = useState([]);
  let [img, setImg] = useState([]);

  const [limit] = useState(4);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  useEffect(() => {
    axios.get("/top").then((res) => {
      setTop(res.data);
    });
  }, []);

  useEffect(() => {
    axios.get("/mid", { params: { topRegion: topSelect } }).then((res) => {
      setMiddle(res.data);
    });
  }, [topSelect]);

  useEffect(() => {
    axios
      .get("/dong", {
        params: { topRegion: topSelect, midRegion: middleSelect },
      })
      .then((res) => {
        setDong(res.data);
      });
  }, [middleSelect]);

  let copy = [];

  useEffect(() => {
    for (let i = 0; i < data.length; i++) {
      axios
        .post(`/display/${data[i]?.uploadFile.storeFileName}`)
        .then((res) => {
          copy = [...copy];
          copy.push(res.data);
          setImg(copy, ...img);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [data]);

  // const onClickSearch = () => {
  //   axios.get("/research").then((res) => {
  //     console.log(res);
  //   });
  // };

  return (
    <div className="wrapper">
      <div className="wrapper__top">
        <Form.Select
          size="lg"
          onChange={(e) => {
            setTopSelect(e.target.value);
          }}
        >
          <option>시/도 선택</option>
          {top &&
            top.map((a, i) => {
              return (
                <option value={a} key={a}>
                  {top[i]}
                </option>
              );
            })}
        </Form.Select>
        <br />
        <Form.Select
          size="lg"
          onChange={(e) => {
            setMiddleSelect(e.target.value);
          }}
        >
          <option>구/군 선택</option>
          {middle &&
            middle.map((a, i) => {
              return (
                <option value={a} key={a}>
                  {middle[i]}
                </option>
              );
            })}
        </Form.Select>
        <br />
        <Form.Select
          size="lg"
          onChange={(e) => {
            setDongSelect(e.target.value);
          }}
        >
          <option>동 선택</option>
          {dong &&
            dong.map((a, i) => {
              return (
                <option value={a} key={a}>
                  {dong[i]}
                </option>
              );
            })}
        </Form.Select>
        <button
          style={{
            width: "300px",
            marginLeft: "10px",
            background: "#C9E79D",
            border: "1px solid #000000",
            borderRadius: "7px",
          }}
          onClick={() => {
            // URL에 쿼리 매개변수를 추가하고 Axios를 사용하여 서버에 요청을 보냅니다.
            const url = `/research?top=${topSelect}&mid=${middleSelect}&dong=${dongSelect}`;
            axios
              .get(url)
              .then((res) => {
                console.log(res);
                setData(res.data.content);
              })
              .catch((err) => {
                console.log(err);
              });
          }}
        >
          검색
        </button>
      </div>
      <Container>
        <Row style={{ rowGap: "50px", marginTop: "20px" }}>
          {data?.slice(offset, offset + limit).map((a, i) => {
            return (
              <LocationSearchResultPage
                offset={offset + i}
                key={data[i].uploadFile.item_seq}
                i={i}
                data={data}
                img={img}
              />
            );
          })}
        </Row>
        <div className="page">
          <Pagination
            total={img?.length}
            limit={6}
            page={page}
            setPage={setPage}
          />
        </div>
      </Container>
    </div>
  );
}
