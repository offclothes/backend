import { useEffect, useState } from "react";
import axios from "axios";
import "../css/EntryStore.css";
import { useNavigate } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import { Form, Divider, Input, InputNumber, Button, Upload } from "antd";

function EntryStore() {
  let navigate = useNavigate();
  let [userName, setUserName] = useState("");
  let [storeName, setStoreName] = useState();
  let [storeNum, setStoreNum] = useState("");
  let [storeLocation, setStoreLocation] = useState("");
  let [file, setFile] = useState([]);
  let [contract, setContract] = useState("");
  let [thumb, setThumb] = useState([]);
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

  const saveContractFile = (e) => {
    setContract(URL.createObjectURL(e.target.files[0]));
    let copy = [...thumb];
    copy.push(e.target.files[0]);
    setThumb(copy);
    // console.log(e.target.files[0]);

    // formData.append("thumb", e.target.files[0]);
    for (let key of formData.entries()) {
      console.log(key);
    }
  };

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
            <th className="registerList">임대차 계약서</th>
            <th>
              {contract && (
                <img className="imageUpload" alt="sample" src={contract} />
              )}
              <div
                style={{
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <input
                  style={{ marginLeft: "1.5em" }}
                  name="imgUpload"
                  type="file"
                  accept="image/*"
                  onChange={saveContractFile}
                />
              </div>
            </th>
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
      {/* 
      <Routes>
        <Route path="/entry" element={<EntryStore />} />
      </Routes> */}
    </div>
  );
}

export default EntryStore;
