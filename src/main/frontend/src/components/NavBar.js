import axios from "axios";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Navbar from "react-bootstrap/Navbar";
import { Routes, Route, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Category from "../components/Category";
import Shop1 from "../components/Shop1";
import MainImage from "../assets/image/clothes.jpg";
import "../css/navBar.css";
import { useDispatch, useSelector } from "react-redux";
import { changeLogOutStatus } from "./store";
import { increase } from "./store";
import { Map, MapMarker, CustomOverlayMap } from "react-kakao-maps-sdk";
import { cookie } from "react-cookie";

const { kakao } = window;

function NavBar() {
  let navigate = useNavigate();
  let [categoryBtn, setCategoryBtn] = useState("");
  let loginStatus = useSelector((state) => {
    return state;
  });
  let dispatch = useDispatch();

  return (
    <div>
      <Navbar expand="lg">
        <Container fluid>
          <Navbar.Brand
            style={{
              paddingLeft: "1em",
              color: "#A8DBA8",
              cursor: "pointer",
              fontWeight: "700",
              fontSize: "25px",
            }}
            onClick={() => {
              setCategoryBtn("");
              navigate("/");
            }}
          >
            OffClothes
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">
            <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                style={{ width: "50em", marginLeft: "10em" }}
              />
              <Button variant="outline-success">Search</Button>
            </Form>
          </Navbar.Collapse>
          {loginStatus.loginStatus === "true" ? (
            <div className="logInOut">
              <div></div>
              <button
                className="logInOutButton"
                onClick={() => {
                  axios
                    .post("/Member/logout")
                    .then((res) => {
                      alert(res.data.message);
                      cookie.remove("id", { path: "/" });
                    })
                    .catch((err) => {
                      console.log(err);
                    });
                  dispatch(changeLogOutStatus());
                  navigate("/");
                  setCategoryBtn("");
                }}
              >
                로그아웃
              </button>
              <div
                className="myPageButton"
                onClick={() => {
                  navigate("/myPage");
                  // axios.get("/{id}", {}).then((res) => console.log(res));
                }}
              ></div>
            </div>
          ) : (
            <button
              className="logInOutButton"
              onClick={() => {
                setCategoryBtn("");
                navigate("/login");
              }}
            >
              로그인
            </button>
          )}
        </Container>
      </Navbar>
      <Category
        categoryBtn={categoryBtn}
        setCategoryBtn={setCategoryBtn}
      ></Category>

      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/shop/" element={<Shop1 />} />
        {/* /shop/shopid값추가 */}
      </Routes>
    </div>
  );
}

function Main() {
  let navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [shopName, setShopName] = useState("");
  const [addr1, setAddr1] = useState("");
  const [addr2, setAddr2] = useState("");
  const [lat, setLat] = useState("");
  const [lng, setLng] = useState("");

  const location = function () {
    navigator.geolocation.getCurrentPosition((position) => {
      setLat(position?.coords.latitude);
      setLng(position?.coords.longitude);
    });
  };

  const markerPosition = {
    lat: 37.3807323,
    lng: 126.9284,
  };

  useEffect(() => {
    axios
      .get("/map")
      .then((res) => {
        console.log(res.data[0]);
        setShopName(res.data[0].shopName);
        setAddr1(res.data[0].addr1);
        setAddr2(res.data[0].addr2);
      })
      .catch((err) => {
        console.log(err);
      });
    location();
  }, [lng]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <div
        style={{
          width: "80%",
          display: "flex",
          flexDirection: "row",
          justifyContent: "space-around",
          marginTop: "15px",
          marginBottom: "15px",
          background: "#F5F5F5",
          boxShadow: "inset 0px 4px 4px rgba(0, 0, 0, 0.25)",
          borderRadius: "20px",
          paddingTop: "20px",
          paddingBottom: "20px",
        }}
      >
        <p
          style={{
            textAlign: "center",
            fontSize: "20px",
            fontWeight: "550",
          }}
        >
          OffClothes는 내 주변 오프라인 의류 매장을
          <br /> 한 눈에 확인해 볼 수 있는 웹사이트입니다.
          <br /> 소개소개
          <br /> 소개소개
          <br /> 소개소개
        </p>
        <img style={{ width: "300px", height: "200px " }} src={MainImage}></img>
      </div>
      <Map // 지도를 표시할 Container
        id={`map`}
        center={{
          // 지도의 중심좌표
          lat: lat,
          lng: lng,
        }}
        style={{
          // 지도의 크기
          width: "80%",
          height: "450px",
          borderRadius: "20px",
        }}
        level={3} // 지도의 확대 레벨
      >
        <MapMarker position={markerPosition} onClick={() => setIsOpen(true)} />
        {isOpen && (
          <CustomOverlayMap position={markerPosition}>
            <div className="wrap">
              <div className="info">
                <div className="title">
                  {shopName}
                  <div
                    className="close"
                    onClick={() => setIsOpen(false)}
                    title="닫기"
                  ></div>
                </div>
                <div className="body">
                  <div className="img">
                    <img
                      src="//t1.daumcdn.net/thumb/C84x76/?fname=http://t1.daumcdn.net/cfile/2170353A51B82DE005"
                      width="73"
                      height="70"
                      alt={shopName}
                    />
                  </div>
                  <div className="desc">
                    <div className="ellipsis">{addr1}</div>
                    <div className="jibun ellipsis">{addr2}</div>
                    <div>
                      <div
                        onClick={() => {
                          navigate("/shop");
                        }}
                        target="_blank"
                        className="link"
                        rel="noreferrer"
                      >
                        홈페이지
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            ;
          </CustomOverlayMap>
        )}
      </Map>
    </div>
  );
}

export default NavBar;
