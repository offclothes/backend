import "../css/myPage.css";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SingUp() {
  let [id, setId] = useState("");
  let [password, setPassword] = useState("");
  let [nickName, setNickName] = useState("");
  let [phone, setPhone] = useState("");
  let [category, setCategory] = useState("");
  let [postCode, setPostCode] = useState("");
  let [address1, setAddress1] = useState("");
  let [address2, setAddress2] = useState("");
  let [height, setHeight] = useState("");
  let [weight, setWeight] = useState("");

  let navigate = useNavigate();

  function saveInformationButton() {
    axios
      .post("/Member/signup", {
        memberId: id,
        password: password,
        nickname: nickName,
        phoneNm: phone,
        address: {
          postcode: postCode,
          address1: address1,
          address2: address2,
        },
        length: height,
        weight: weight,
        gender: category,
      })
      .then(() => {
        alert("회원가입 성공");
        navigate("/login");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const categoryRadio = (e) => {
    let gender = e.target.value;
    gender === "male" ? setCategory("남성") : setCategory("여성");
  };

  return (
    <div className="myPageMain">
      <table width="100%">
        <tr>
          <th className="myPageTitle">OffClothes</th>
        </tr>
        <tr>
          <th className="myPageTop">회원가입</th>
        </tr>
        <hr />
        <tr>
          <td>
            <th className="myPageEssential">필수 정보</th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">아이디</th>
            <td>
              <input
                className="myPageInput"
                placeholder="아이디를 입력해 주세요."
                onChange={(e) => {
                  setId(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">비밀번호</th>
            <td>
              <input
                className="myPageInput"
                type="password"
                placeholder="비밀번호를 입력해 주세요."
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">닉네임</th>
            <td>
              <input
                className="myPageInput"
                placeholder="닉네임을 입력해 주세요."
                onChange={(e) => {
                  setNickName(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">전화번호</th>
            <td>
              <input
                className="myPageInput"
                placeholder="전화번호를 입력해 주세요."
                onChange={(e) => {
                  setPhone(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">주소</th>
            <td>
              <input
                className="myPageAddressInput"
                placeholder="우편번호"
                onChange={(e) => {
                  setPostCode(e.target.value);
                }}
              ></input>
            </td>
            <td>
              <button className="myPagePostCodeButton">우편번호 찾기</button>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList"></th>
            <td>
              <input
                className="myPageAddress"
                placeholder="주소를 입력해 주세요."
                onChange={(e) => {
                  setAddress1(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList"></th>
            <td>
              <input
                className="myPageAddress"
                placeholder="상세주소를 입력해 주세요."
                onChange={(e) => {
                  setAddress2(e.target.value);
                }}
              ></input>
            </td>
          </td>
        </tr>
        <hr />
        <tr>
          <td>
            <th className="myPageSelection">선택 정보</th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">성별</th>
            <td style={{ paddingLeft: "120px" }}>
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
            </td>
          </td>
        </tr>
        <tr>
          <tr>
            <th className="myPageList">키</th>
            <td>
              <input
                className="myPageInput"
                placeholder="키를 입력해 주세요."
                onChange={(e) => {
                  setHeight(e.target.value);
                }}
              ></input>
            </td>
          </tr>
          <tr>
            <th className="myPageList">몸무게</th>
            <td>
              <input
                className="myPageInput"
                placeholder="몸무게를 입력해 주세요."
                onChange={(e) => {
                  setWeight(e.target.value);
                }}
              ></input>
            </td>
          </tr>

          <td className="button">
            <button
              className="registerButton"
              onClick={() => {
                saveInformationButton();
              }}
            >
              저장하기
            </button>
          </td>
        </tr>
      </table>
    </div>
  );
}

export default SingUp;
