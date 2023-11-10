import { useState } from "react";
import axios from "axios";
import "../css/EntryStore.css";
import { useNavigate } from "react-router-dom";

function EntryStore() {
  let navigate = useNavigate();
  let [shopName, setShopName] = useState();
  let [postCode, setPostCode] = useState("");
  let [address1, setAddress1] = useState("");
  let [address2, setAddress2] = useState("");
  let [email, setEmail] = useState("");
  let [shopTel, setShopTel] = useState("");

  function EntryRegisterButton() {
    axios
      .post("/shop/signup", {
        memberId: null,
        shopName: shopName,
        shopTel: shopTel,
        address: {
          postcode: postCode,
          address1: address1,
          address2: address2,
        },
        email: email,
      })
      .then(() => {
        alert("입점 신청 성공");
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <div className="registerMain">
      <table width="100%">
        <tr>
          <th className="title1">OffClothes</th>
        </tr>
        <tr>
          <th className="top">입점 신청</th>
        </tr>
        <tr>
          <td>
            <th className="registerList">이름</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setShopName(e.target.value);
                }}
                placeholder="이름을 입력해 주세요."
              ></input>
            </th>
          </td>
        </tr>

        <tr>
          <td>
            <th className="registerList">상호명</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setShopName(e.target.value);
                }}
                placeholder="상호명을 입력해 주세요."
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">매장 전화번호</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setShopTel(e.target.value);
                }}
                placeholder="매장 전화번호를 입력해 주세요."
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">매장 위치</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setPostCode(e.target.value);
                }}
                placeholder="우편번호를 입력해 주세요."
              ></input>
            </th>
            <td className="addressInput">
              <th>
                <input
                  className="input"
                  onChange={(e) => {
                    setAddress1(e.target.value);
                  }}
                  placeholder="도로명 주소를 입력해 주세요."
                ></input>
              </th>
              <th>
                <input
                  className="input"
                  onChange={(e) => {
                    setAddress2(e.target.value);
                  }}
                  placeholder="상세 주소를 입력해 주세요."
                ></input>
              </th>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">이메일</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                placeholder="이메일을 입력해 주세요."
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td className="button">
            <button className="registerButton" onClick={EntryRegisterButton}>
              신청하기
            </button>
          </td>
        </tr>
      </table>
    </div>
  );
}

export default EntryStore;
