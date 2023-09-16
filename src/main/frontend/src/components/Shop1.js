import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Shop1Data from "../Shop1Data";
import "../css/shop1.css";
import axios from "axios";

const { kakao } = window;

function Shop1() {
  let navigate = useNavigate();
  const [shoes] = useState(Shop1Data);
  const [myShop, setMyShop] = useState("");

  // const myShopBtn = () => {
  //   myShop === "true" ? "visible" : "hidden";
  // };

  useEffect(() => {
    axios
      .get("/shop/shopDetail", { params: { id: 4, page: 0 } })
      .then((res) => {
        console.log(res);
        setMyShop(res.data.myshop);
      })
      .catch((err) => {
        console.log(err);
      });
    let container = document.getElementById("map");
    let options = {
      center: new kakao.maps.LatLng(37.5004, 127.028),
      level: 3,
    };
    let map = new kakao.maps.Map(container, options);

    let markerPosition = new kakao.maps.LatLng(37.5004, 127.028);
    let marker = new kakao.maps.Marker(
      {
        position: markerPosition,
      },
      []
    );

    marker.setMap(map);

    var content =
      '<div class="wrap">' +
      '    <div class="info">' +
      '        <div class="title">' +
      "            테스트 온" +
      '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
      "        </div>" +
      '        <div class="body">' +
      '            <div class="img">' +
      '                <img src="/logo192.png" width="73" height="70">' +
      "           </div>" +
      '            <div class="desc">' +
      '                <div class="ellipsis">서울특별시 강남구 역삼동 817-31</div>' +
      '                <div class="jibun ellipsis">(우) 06129 (우편) 서울특별시 강남구 강남대로98길 12-5</div>' +
      '                <div><a href="http://localhost:3000/shop" target="_blank" class="link">홈페이지</a></div>' +
      "            </div>" +
      "        </div>" +
      "    </div>" +
      "</div>";
  }, []);
  return (
    <div style={{ display: "flex" }}>
      <div style={{ width: "600px" }}>
        <p
          style={{
            marginLeft: "90px",
            color: "#194819",
            fontFamily: "Inter",
            fontStyle: "normal",
            fontWeight: "800",
            fontSize: "48px",
            display: "flex",
            justifyContent: "center",
            textAlign: "center",
            marginTop: "30px",
          }}
        >
          MARITHÉ
        </p>
        <div>
          <p
            style={{
              textAlign: "center",
              marginLeft: "90px",
              fontWeight: "400",
              fontSize: "18px",
              fontHeight: "29px",
            }}
          >
            스타일: 캐주얼
          </p>
        </div>
        {myShop === true ? (
          <textarea
            style={{
              width: "400px",
              height: "280px",
              marginTop: "15px",
              marginBottom: "15px",
              background: "#F5F5F5",
              boxShadow: "inset 0px 4px 4px rgba(0, 0, 0, 0.25)",
              borderRadius: "20px",
              paddingTop: "20px",
              paddingBottom: "20px",
              marginLeft: "90px",
              textAlign: "center",
              color: "black",
              fontWeight: "700",
              fontSize: "20px",
            }}
            placeholder="  마리떼프랑소와저버는 BACHEELLERIE와 FRANÇOIS GIRBAUD가 만든
          브랜드로, 1972년 프랑스에서 론칭한 이후, 스톤워싱, 배기진,
          엔지니어드진 등을 세계 최초로 개발하였습니다."
          />
        ) : (
          <div
            style={{
              height: "280px",
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
              marginLeft: "90px",
            }}
          >
            <a
              style={{
                paddingLeft: "24px",
                paddingRight: "24px",
                textAlign: "center",
                lineHeight: "40px",
                fontSize: "20px",
                fontWeight: "500",
              }}
            >
              마리떼프랑소와저버는 BACHEELLERIE와 FRANÇOIS GIRBAUD가 만든
              브랜드로, 1972년 프랑스에서 론칭한 이후, 스톤워싱, 배기진,
              엔지니어드진 등을 세계 최초로 개발하였습니다.
            </a>
          </div>
        )}

        <div className="shopInformation">
          {/* 수정 버튼 클릭 시 마이페이지에서 수정하는 것처럼 바로 수정이 가능하도록 */}
          <button
            className="shopInformationBtn"
            style={{ visibility: myShop === true ? "visible" : "hidden" }}
          >
            수정
          </button>
        </div>
        <div
          id="map"
          style={{
            height: "300px",
            marginLeft: "90px",
            marginBottom: "20px",
            boxShadow: "inset 0px 4px 4px rgba(0, 0, 0, 0.25)",
            borderRadius: "20px",
          }}
        ></div>
        <p
          style={{
            textAlign: "center",
            marginLeft: "90px",
            fontWeight: "400",
            fontSize: "16px",
            fontHeight: "29px",
          }}
        >
          주소: 서울특별시 강남구 역삼동 817-31 10층
        </p>
        <p
          style={{
            textAlign: "center",
            marginLeft: "90px",
            fontWeight: "400",
            fontSize: "16px",
            fontHeight: "29px",
          }}
        >
          전화번호: 010-1234-5678
        </p>
      </div>
      <div style={{ marginLeft: "100px" }}>
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
          }}
        >
          <p
            style={{
              display: "flex",
              height: "72px",
              fontWeight: "800",
              fontSize: "25px",
              lineHeight: "39px",
              marginTop: "30px",
              alignItems: "center",
            }}
          >
            전체
          </p>
          <button
            className="goodsRegisterButton"
            onClick={() => {
              navigate("/registerGoods");
            }}
            style={{ visibility: myShop === true ? "visible" : "hidden" }}
          >
            상품 등록하기
          </button>
        </div>
        <Container>
          <Row className="container">
            {shoes.map(function (a, i) {
              return (
                <ShopGoods
                  key={shoes[i].id}
                  i={i}
                  shoes={shoes}
                  myShop={myShop}
                ></ShopGoods>
              );
            })}
          </Row>
        </Container>
      </div>
    </div>
  );
}

function ShopGoods(props) {
  let navigate = useNavigate();
  let [btn, setBtn] = useState("seller"); //setBtn에 서버에서 가져온 정보(판매자, 일반회원)를 btn에 넣어서 판매자면 버튼이 보이고 일반회원이면 버튼이 안보이도록
  return (
    <Col md="5" style={{ textAlign: "start" }}>
      <img
        src={
          "https://codingapple1.github.io/shop/shoes" + (props.i + 1) + ".jpg"
        }
        width="80%"
        height="60%"
      ></img>
      <h4>{props.shoes[props.i].title}</h4>
      <h5>100,000원</h5>
      <div className="shop1Button">
        <button
          className="registerShopBtn"
          onClick={() => {
            navigate("/changeGoods");
          }}
          style={{ visibility: props.myShop === true ? "visible" : "hidden" }}
        >
          수정
        </button>
        <button
          style={{ visibility: props.myShop === true ? "visible" : "hidden" }}
          className="cancelShopBtn"
        >
          삭제
        </button>
      </div>
    </Col>
  );
}

export default Shop1;
