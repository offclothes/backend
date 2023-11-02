import { useEffect, useState } from "react";
import axios from "axios";
import "../css/EntryStore.css";
import { useNavigate } from "react-router-dom";
import { Routes, Route } from "react-router-dom";

function EntryStore() {
  let navigate = useNavigate();
  let [userName, setUserName] = useState("");
  let [storeName, setStoreName] = useState();
  let [storeNum, setStoreNum] = useState("");
  let [storeLocation, setStoreLocation] = useState("");
  let [file, setFile] = useState([]);
  let [email, setEmail] = useState("");

  const formData = new FormData();

  formData.append("userName", userName);
  formData.append("storeName", storeName);
  formData.append("storeNum", storeNum);
  formData.append("storeLocation", storeLocation);

  formData.append("email", email);

  for (let i = 0; i < file.length; i++) {
    formData.append("imageFiles", file[i]);
  }
  for (let key of formData.entries()) {
    console.log(key);
  }

  function EntryRegisterButton() {
    axios
      .post("/shop/saveItem", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        navigate("/shop");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  // const categoryRadio = (e) => {
  //   let gender = e.target.value;
  //   gender === "male"
  //     ? setCategory(0)
  //     : gender === "female"
  //     ? setCategory(1)
  //     : setCategory(2);
  // };

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
                  setUserName(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>

        {/* <tr>
          <td>
            <th className="registerList">카테고리</th>
            <td className="radioButton">
              <input
                type="radio"
                id="female"
                name="gender"
                value="female"
                onChange={categoryRadio}
              />
              <label for="">여성</label>
              <input
                type="radio"
                id="male"
                name="gender"
                value="male"
                onChange={categoryRadio}
              />
              <label for="">남성</label>
              <input
                type="radio"
                id="both"
                name="gender"
                value="both"
                onChange={categoryRadio}
              />
              <label for="">공용</label>
            </td>
          </td>
        </tr> */}
        <tr>
          <td>
            <th className="registerList">상호명</th>
            <th>
              <input
                className="input"
                onChange={(e) => {
                  setStoreName(e.target.value);
                }}
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
                  setStoreNum(e.target.value);
                }}
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
                  setStoreLocation(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        {/* 임대차 계약서 */}
        <tr>
          <td>
            <th className="registerList">이메일</th>
            <th>
              <input
                className="informationInput"
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
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

      <Routes>
        <Route path="/entry" element={<EntryStore />} />
      </Routes>
    </div>
  );
}

export default EntryStore;
