import "../css/goods.css";

function Goods1() {
  return (
    <div className="GoodsMain">
      <div style={{ display: "flex", justifyContent: "center" }}>
        <div className="shopTitle">MARITHÉ</div>
      </div>
      <div className="goodsTitle">VINTAGE COLLEGE LOGO SWEAT TEE</div>
      <div style={{ display: "flex" }}>
        <div className="imageInformation">
          <div>
            <img
              src={"https://codingapple1.github.io/shop/shoes1.jpg"}
              width="100%"
              height="70%"
            />
            <div>
              <img
                src={"https://codingapple1.github.io/shop/shoes1.jpg"}
                width="30%"
              />
              <img
                src={"https://codingapple1.github.io/shop/shoes1.jpg"}
                width="30%"
              />
              <img
                src={"https://codingapple1.github.io/shop/shoes1.jpg"}
                width="30%"
              />
            </div>
          </div>
        </div>
        <div style={{ width: "45%", marginLeft: "20px" }}>
          <div className="goodsInformation">상품 정보</div>
          <p>
            마리떼프랑소와저버는 BACHEELLERIE와
            <br />
            FRANÇOIS GIRBAUD가 만든 브랜드로, 1972년 프랑스에서
            <br />
            론칭한 이후, 스톤워싱, 배기진, 엔지니어드진 등을
            <br />
            세계 최초로 개발하였습니다. 데님을 기반으로 독창적이고
            <br />
            실험적인 컬렉션을 계속하여 선보이며, 40여 년간 <br />
            그 명성을 이어오고 있는 브랜드입니다. 1990년대 국내에서 <br />
            큰 인기를 끌었던 데님 브랜드를 현대식 라이프 <br />
            스타일에 맞추어 캐쥬얼하게 재해석하여 2019년 새로운
            <br /> ‘마리떼프랑소와저버’를 선보입니다.
          </p>
          <p className="priceInformation">가격 정보</p>
          <p className="price">100,000원</p>
          <div className="updateBtnSection">
            <button className="updateBtn">수정</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Goods1;
