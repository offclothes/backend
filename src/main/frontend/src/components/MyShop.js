import { useEffect, useState } from "react";
import "../css/myShop.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function MyShopPage() {
  let navigate = useNavigate();
  let [role, setRole] = useState("");
  let [shopInfo, setShopInfo] = useState([]);
  let [pageOrShop, setPageOrShop] = useState("myShop");

  useEffect(() => {
    axios
      .get("/Member/myPage")
      .then((res) => {
        console.log(res);
        setRole(res.data.role);
        setShopInfo(...shopInfo, res.data.myPageShoppingMal);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onClickMyPage = () => {
    navigate("/myPage");
    setPageOrShop("myPage");
  };

  const onClickMyShop = () => {
    navigate("/myShop");
    setPageOrShop("myShop");
  };

  return (
    <div className="myPageMain">
      <table width="100%">
        <tr>
          <th className="myPageTitle">OffClothes</th>
        </tr>
        <tr className="MyPageShop">
          <th
            className={
              pageOrShop === "myPage" ? "myPageTopClicked" : "myPageTop"
            }
            onClick={onClickMyPage}
          >
            My Page
          </th>
          {role === "SELLER" ? (
            <th
              className={
                pageOrShop === "myShop" ? "myShopTopClicked" : "myShopTop"
              }
              onClick={onClickMyShop}
            >
              My Shop
            </th>
          ) : (
            ""
          )}
        </tr>
        <hr />
        <tr>
          <div className="wrapperTop">
            <div>번호</div>
            <div>이름</div>
            <div>페이지 바로가기</div>
          </div>
          {shopInfo?.map((a, i) => {
            return (
              <div className="myShopWrapper">
                <div className="wrapperMid">
                  <div className="shopNum">{i + 1}</div>
                  <div>{shopInfo[i].shopName}</div>
                  <button
                    className="goToMyShopBtn"
                    onClick={() => {
                      navigate(`/shop/${shopInfo[i].shopId}`);
                    }}
                  >
                    바로가기
                  </button>
                </div>
              </div>
            );
          })}
        </tr>
      </table>
    </div>
  );
}
