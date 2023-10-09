import Form from "react-bootstrap/Form";
import "../css/locationSearch.css";
import { useEffect, useState } from "react";
import axios from "axios";

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
      </div>
      <div>
        <button
          onClick={() => {
            axios
              .get("/research", {
                params: { top: topSelect, mid: middleSelect, dong: dongSelect },
              })
              .then((res) => {
                console.log(res);
              });
          }}
        >
          검색
        </button>
      </div>
    </div>
  );
}
