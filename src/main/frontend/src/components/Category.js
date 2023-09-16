import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Routes, Route, useNavigate } from "react-router-dom";
import Container from "react-bootstrap/Container";
import data from "../constants/Data";
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

function Category({ categoryBtn, setCategoryBtn }) {
  let [gender, setGender] = useState("");
  let navigate = useNavigate();
  let [shoes, setShoes] = useState(data);

  // useEffect(() => {
  //   axios
  //     .get("/map")
  //     .then((res) => {
  //       console.log(res);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

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
              navigate("/eventAll");
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
        </Nav>
      </Navbar>

      <Routes>
        <Route path="/category">
          <Route path="female" element={<Gender />} />
          <Route path="male" element={<Gender />} />
          <Route path="both" element={<Gender />} />
        </Route>
        <Route path="/eventAll" element={<Board />} />
        <Route path="/shop/item" element={<Goods1 />} />
        <Route path="/registerGoods" element={<RegisterGoods />} />
        <Route path="/changeGoods" element={<ChangeGoods />} />
        <Route path="/myPage" element={<MyPage />} />
        <Route path="/registerBoard" element={<RegisterBoard />} />
        <Route path="/changeBoard/:eventId" element={<ChangeBoard />} />
        <Route path="/LocationSearch" element={<LocationSearch />} />
      </Routes>
      {/* <button
        className="registerButton"
        onClick={() => {
          axios
            .get("/myPost")
            .then((res) => {
              console.log(res);
            })
            .catch((err) => {
              console.log(err);
            });
        }}
      >
        mypost
      </button>
      <button
        className="registerButton"
        onClick={() => {
          axios
            .get("/Member/myPage")
            .then((res) => {
              console.log(res);
            })
            .catch((err) => {
              console.log(err);
            });
        }}
      >
        저장
      </button> */}
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
            {shoes.map((a, i) => {
              return <Goods key={shoes[i].id} i={i} shoes={shoes}></Goods>;
            })}
          </Row>
        </Container>
      </div>
    );
  }
}

function Goods(props) {
  let navigate = useNavigate();
  return (
    <Col md="4" style={{ textAlign: "center" }}>
      <img
        style={{ cursor: "pointer" }}
        src={
          "https://codingapple1.github.io/shop/shoes" + (props.i + 1) + ".jpg"
        }
        width="60%"
        onClick={() => {
          navigate("/shop/item/"); //아이템id추가
        }}
      ></img>
      <h4>{props.shoes[props.i].title}</h4>
    </Col>
  );
}

export default Category;
