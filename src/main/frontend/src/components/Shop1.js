import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "../css/shop1.css";
import axios from "axios";
import Pagination from "./Pagination";

const { kakao } = window;

function Shop1() {
  let navigate = useNavigate();
  // const [shoes] = useState(Shop1Data);
  const [myShop, setMyShop] = useState("");
  const [imageFile, setImageFile] = useState([]);
  const [imgSrc, setImageSrc] = useState([]);
  const [shopAddress1, setShopAddress1] = useState("");
  const [shopAddress2, setShopAddress2] = useState("");
  const [shopTel, setShopTel] = useState("");

  const [limit, setLimit] = useState(4);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  // const myShopBtn = () => {
  //   myShop === "true" ? "visible" : "hidden";
  // };

  useEffect(() => {
    axios
      .get("/shop/shopDetail", { params: { id: 4, page: 0 } })
      .then((res) => {
        console.log(res);
        setMyShop(res.data.myshop);
        setShopAddress1(res.data.address.address1);
        setShopAddress2(res.data.address.address2);
        setShopTel(res.data.shopTel);
        setImageFile(res.data.mainItemDtoList.content);
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

  let copy = [];

  useEffect(() => {
    for (let i = 0; i < imageFile.length; i++) {
      axios
        .post(`/display/${imageFile[i]?.uploadFile.storeFileName}`)
        .then((res) => {
          copy = [...copy];
          copy.push(res.data);
          setImageSrc(copy, ...imgSrc);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [imageFile]);

  return (
    <div style={{ display: "flex", flexDirection: "row" }}>
      <div style={{ width: "600px" }}>
        <p
          style={{
            width: "400px",
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
              width: "400px",
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
              width: "400px",
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
            width: "400px",
            height: "300px",
            marginLeft: "90px",
            marginBottom: "20px",
            boxShadow: "inset 0px 4px 4px rgba(0, 0, 0, 0.25)",
            borderRadius: "20px",
          }}
        ></div>
        <div width="100%">
          <p
            style={{
              textAlign: "center",
              marginLeft: "60px",
              fontWeight: "400",
              fontSize: "16px",
              fontHeight: "29px",
            }}
          >
            주소: {shopAddress1} {shopAddress2}
          </p>
          <p
            style={{
              textAlign: "center",
              marginLeft: "60px",
              fontWeight: "400",
              fontSize: "16px",
              fontHeight: "29px",
            }}
          >
            전화번호: {shopTel}
          </p>
        </div>
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
            {imageFile?.slice(offset, offset + limit).map(function (a, i) {
              return (
                <ShopGoods
                  offset={offset + i}
                  key={imageFile[i].item_seq}
                  i={i}
                  imageFile={imageFile}
                  myShop={myShop}
                  imgSrc={imgSrc}
                  item_seq={imageFile[i].item_seq}
                ></ShopGoods>
              );
            })}
          </Row>
        </Container>
        <Pagination
          total={imageFile?.length}
          limit={4}
          page={page}
          setPage={setPage}
        />
      </div>
    </div>
  );
}

function ShopGoods(props) {
  let navigate = useNavigate();

  return (
    <Col md="4" style={{ textAlign: "start" }}>
      <img
        className="image"
        src={props.imgSrc[props.offset]}
        width="280px"
        height="250px"
        onClick={() => {
          navigate(`/shop/item/${props.offset + 1}`);
        }}
      ></img>
      <h4>{props.imageFile[props.offset].itemTitle}</h4>
      <h5>{props.imageFile[props.offset].price}원</h5>
      <div className="shop1Button">
        <button
          className="registerShopBtn"
          onClick={() => {
            navigate(`/changeGoods/${props.item_seq}`);
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
