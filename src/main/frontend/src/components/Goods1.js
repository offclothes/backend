import { useNavigate, useParams } from "react-router-dom";
import "../css/goods.css";
import { useEffect, useState } from "react";
import axios from "axios";

function Goods1(props) {
  const [price, setPrice] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [myShop, setMyShop] = useState("");

  const [thumb, setThumb] = useState("");
  const [sub1, setSub1] = useState("");
  const [sub2, setSub2] = useState("");

  const [thumbImg, setThumbImg] = useState("");
  const [subImg1, setSubImg1] = useState("");
  const [subImg2, setSubImg2] = useState("");

  //파라미터의 값이랑 itemSeq값이랑 같으면 보여주기
  let { id } = useParams();
  let data = props.goodsData.mainItemDtoList.content.find(
    (x) => x.item_seq == id
  );
  let navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`/shop/item/${id}`)
      .then((res) => {
        console.log(res);
        setPrice(res.data.price);
        setTitle(res.data.itemTitle);
        setContent(res.data.content);
        setThumb(res.data.thumb);
        setSub1(res.data.imageFiles[0]);
        setSub2(res.data.imageFiles[1]);
        setMyShop(res.data.myshop);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    Promise.all([
      axios.post(`/display/${thumb?.storeFileName}`),
      axios.post(`/display/${sub1?.storeFileName}`),
      axios.post(`/display/${sub2?.storeFileName}`),
    ])
      .then((res) => {
        console.log(res);
        setThumbImg(res[0]?.data);
        setSubImg1(res[1]?.data);
        setSubImg2(res[2]?.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [sub2]);

  const goToChangeGoods = () => {
    navigate(`/changeGoods/${id}`);
  };

  return (
    <div className="GoodsMain">
      <div style={{ display: "flex", justifyContent: "center" }}>
        <div className="shopTitle">{props.goodsData.shopName}</div>
      </div>
      <div className="goodsTitle">{title}</div>
      <div height="100%" style={{ display: "flex" }}>
        <div className="imageInformation">
          <div className="asd">
            <img src={thumbImg} width="100%" height="70%" />
            <div className="subImg">
              <img
                className="subImage1"
                src={subImg1}
                width="200px"
                height="150px"
              />
              <img src={subImg2} width="200px" height="150px" />
            </div>
          </div>
        </div>
        <div className="information">
          <div className="goodsInformation">상품 정보</div>
          <div style={{ height: "100%" }}>{content}</div>
          <div className="priceData">
            <p className="priceInformation">가격 정보</p>
            <p className="price">{price}원</p>
            <div className="updateBtnSection">
              <button
                className="updateBtn"
                onClick={goToChangeGoods}
                style={{ visibility: myShop === true ? "visible" : "hidden" }}
              >
                수정
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Goods1;
