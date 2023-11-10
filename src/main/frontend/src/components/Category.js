import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Routes, Route, useNavigate } from "react-router-dom";
import Container from "react-bootstrap/Container";
import { useEffect, useState } from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import "../css/category.css";
import Goods1 from "../components/Goods1";
import RegisterGoods from "../components/RegisterGoods";
import ChangeGoods from "../components/ChangeGoods";
import MyPage from "../components/MyPage";
import RegisterBoard from "../components/RegisterBoard";
import ChangeBoard from "../components/ChangeBoard";
import Board from "../components/Board";
import axios from "axios";
import LocationSearch from "./LocationSearch";
import Pagination from "./Pagination";
import LoadingPage from "./Loading";
import EntryStore from "./EntryStore";
import SingUp from "./SingUp";
import { useSelector } from "react-redux";
import MyShopPage from "./MyShop";

function Category({ categoryBtn, setCategoryBtn }) {
  const [gender, setGender] = useState("");
  const navigate = useNavigate();
  const [female, setFemale] = useState([]);
  const [male, setMale] = useState([]);
  const [both, setBoth] = useState([]);
  const [img, setImg] = useState([]);
  const [goodsData, setGoodsData] = useState();
  let loginStatus = useSelector((state) => {
    return state;
  });

  const [limit] = useState(4);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  let copyMale = [];
  let copyFemale = [];
  let copyBoth = [];

  const goodsGender =
    gender === "남성" ? male : gender === "여성" ? female : both;

  useEffect(() => {
    axios
      .get("/shop/shopDetail", { params: { id: 1, page: 0 } })
      .then((res) => {
        setGoodsData(res.data);
        for (let i = 0; i < res.data.mainItemDtoList.content.length; i++) {
          copyMale = [...copyMale];
          copyFemale = [...copyFemale];
          copyBoth = [...copyBoth];

          if (res.data.mainItemDtoList.content[i].category === 0) {
            copyMale.push(res.data.mainItemDtoList.content[i]);
            setMale(copyMale, ...male);
          } else if (res.data.mainItemDtoList.content[i].category === 1) {
            copyFemale.push(res.data.mainItemDtoList.content[i]);
            setFemale(copyFemale, ...female);
          } else {
            copyBoth.push(res.data.mainItemDtoList.content[i]);
            setBoth(copyBoth, ...both);
          }
        }
      })
      .catch((err) => {
        console.log(err);
      });
  }, [gender]);

  let copy = [];

  useEffect(() => {
    for (let i = 0; i < goodsGender.length; i++) {
      axios
        .post(`/display/${goodsGender[i]?.uploadFile.storeFileName}`)
        .then((res) => {
          copy = [...copy];
          copy.push(res.data);
          setImg(copy, ...img);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [gender]);

  return (
    <div>
      <Navbar
        style={{
          backgroundColor: "#E8F3DA",
          borderTop: "1px solid #789B78",
          borderBottom: "1px solid #789B78",
        }}
        variant="light"
      >
        <Nav className="me-auto">
          <Nav.Link
            className={
              categoryBtn === "여성" ? "genderFemaleClicked" : "genderFemale"
            }
            onClick={() => {
              setGender("여성");
              setCategoryBtn("여성");
              navigate("/category/female");
            }}
          >
            여성
          </Nav.Link>
          <Nav.Link
            className={categoryBtn === "남성" ? "genderClicked" : "genderMale"}
            onClick={() => {
              setGender("남성");
              setCategoryBtn("남성");
              navigate("/category/male");
            }}
          >
            남성
          </Nav.Link>
          <Nav.Link
            className={categoryBtn === "공용" ? "genderClicked" : "genderBoth"}
            onClick={() => {
              setGender("공용");
              setCategoryBtn("공용");
              navigate("/category/both");
            }}
          >
            공용
          </Nav.Link>
          <Nav.Link
            className={
              categoryBtn === "게시판" ? "genderClicked" : "genderBoth"
            }
            onClick={() => {
              setCategoryBtn("게시판");
              navigate("/event/all");
            }}
          >
            할인/폐점
          </Nav.Link>
          <Nav.Link
            className={
              categoryBtn === "지역" ? "genderClicked" : "goToLocation"
            }
            onClick={() => {
              setCategoryBtn("지역");
              navigate("/LocationSearch");
            }}
          >
            지역별 상품 보기
          </Nav.Link>
          {loginStatus.loginStatus === "true" ? (
            <Nav.Link
              className={
                categoryBtn === "입점" ? "genderClicked" : "goToLocation"
              }
              onClick={() => {
                setCategoryBtn("입점");
                navigate("/entry");
              }}
            >
              입점 신청
            </Nav.Link>
          ) : (
            ""
          )}
        </Nav>
      </Navbar>

      <Routes>
        <Route path="/category">
          <Route path="female" element={<Gender />} />
          <Route path="male" element={<Gender />} />
          <Route path="both" element={<Gender />} />
        </Route>
        <Route path="/event">
          <Route path="all" element={<Board />} />
          <Route path="discount" element={<Board />} />
          <Route path="close" element={<Board />} />
          <Route path="myBoard" element={<Board />} />
        </Route>
        <Route
          path="/shop/item/:id"
          element={<Goods1 goodsData={goodsData} />}
        />
        <Route path="/registerGoods" element={<RegisterGoods />} />
        <Route path="/changeGoods/:id" element={<ChangeGoods />} />
        <Route path="/myPage" element={<MyPage />} />
        <Route path="/registerBoard" element={<RegisterBoard />} />
        <Route path="/changeBoard/:eventId" element={<ChangeBoard />} />
        <Route path="/LocationSearch" element={<LocationSearch />} />
        <Route path="/entry" element={<EntryStore />} />
        <Route path="/signup" element={<SingUp />} />
        <Route path="/MyShop" element={<MyShopPage />} />
      </Routes>
    </div>
  );

  function Gender() {
    return (
      <div>
        <p
          style={{
            marginTop: "40px",
            marginLeft: "60px",
            marginRight: "60px",
            paddingBottom: "10px",
            fontSize: "20px",
            borderBottom: "1px solid #9F9F9F",
            fontWeight: "600",
          }}
        >
          {gender} | ALL
        </p>
        <Container>
          <Row style={{ rowGap: "50px" }}>
            {goodsGender?.slice(offset, offset + limit).map((a, i) => {
              return (
                <Goods
                  offset={offset + i}
                  key={goodsGender[i].item_seq}
                  i={i}
                  goodsGender={goodsGender}
                  img={img}
                ></Goods>
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
}

function Goods(props) {
  let navigate = useNavigate();
  return (
    <Col md="4" style={{ textAlign: "center" }}>
      {props.img ? (
        <img
          style={{ cursor: "pointer" }}
          src={props.img[props.offset]}
          width="250px"
          height="300px"
          referrerPolicy="no-referrer"
          onClick={() => {
            navigate(`/shop/item/${props.goodsGender[props.i].item_seq}`);
          }}
        ></img>
      ) : (
        <LoadingPage />
      )}
      <h4>{props.goodsGender[props.offset].itemTitle}</h4>
    </Col>
  );
}

export default Category;
