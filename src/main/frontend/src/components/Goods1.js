import { useParams } from "react-router-dom";
import "../css/goods.css";
import { useEffect, useState } from "react";
import axios from "axios";

function Goods1(props) {
  const [img, setImg] = useState("");
  //파라미터의 값이랑 itemSeq값이랑 같으면 보여주기
  let { id } = useParams();
  let data = props.goodsData.mainItemDtoList.content.find(
    (x) => x.item_seq == id
  );

  useEffect(() => {
    axios
      .post(`/display/${data?.uploadFile.storeFileName}`)
      .then((res) => {
        setImg(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="GoodsMain">
      <div style={{ display: "flex", justifyContent: "center" }}>
        <div className="shopTitle">{props.goodsData.shopName}</div>
      </div>
      <div className="goodsTitle">{data.itemTitle}</div>
      <div height="100%" style={{ display: "flex" }}>
        <div className="imageInformation">
          <div className="asd">
            <img src={img} width="100%" height="70%" />
            <div className="subImg">
              <img
                className="subImage1"
                src={img}
                width="200px"
                height="150px"
              />
              <img src={img} width="200px" height="150px" />
            </div>
          </div>
        </div>
        <div className="information">
          <div className="goodsInformation">상품 정보</div>
          <div style={{ height: "100%" }}>{data.content}</div>
          <div className="priceData">
            <p className="priceInformation">가격 정보</p>
            <p className="price">{data.price}원</p>
            <div className="updateBtnSection">
              <button className="updateBtn">수정</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Goods1;
