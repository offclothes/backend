import "../css/myPage.css";
<<<<<<< HEAD
import { useState } from "react";
=======
import { useEffect, useState } from "react";
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SingUp() {
<<<<<<< HEAD
=======
  let [myData, setMyData] = useState([]);
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
=======
  let [gender, setGender] = useState("");
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650

  let navigate = useNavigate();

  function saveInformationButton() {
    axios
      .post("/Member/signup", {
<<<<<<< HEAD
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
=======
        memberId: id === undefined ? myData[0].memberId : id,
        password: password === undefined ? myData[0].password : password,
        nickname: nickName === undefined ? myData[0].nickname : nickName,
        phoneNm: phone === undefined ? myData[0]?.phoneNm : phone,
        address: {
          postcode: myData[0]?.address.postcode,
          address1: myData[0]?.address.address1,
          address2:
            address2 === undefined ? myData[0]?.address.address2 : address2,
        },
        length: height === undefined ? myData[0]?.length : height,
        weight: weight === undefined ? myData[0]?.weight : weight,
        gender: myData[0]?.gender,
      })
      .then((res) => {
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
        alert("회원가입 성공");
        navigate("/login");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const categoryRadio = (e) => {
    let gender = e.target.value;
<<<<<<< HEAD
    gender === "male" ? setCategory("남성") : setCategory("여성");
=======
    gender === "male"
      ? setCategory(0)
      : gender === "female"
      ? setCategory(1)
      : setCategory(2);
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
                placeholder="아이디를 입력해 주세요."
                onChange={(e) => {
                  setId(e.target.value);
                }}
              ></input>
=======
                onChange={(e) => {
                  setId(e.target.value);
                }}
              >
                {myData[0]?.memberId}
              </input>
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
                placeholder="비밀번호를 입력해 주세요."
=======
                defaultValue={myData[0]?.nickname}
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
                placeholder="닉네임을 입력해 주세요."
=======
                defaultValue={myData[0]?.nickname}
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
                placeholder="전화번호를 입력해 주세요."
=======
                defaultValue={myData[0]?.phoneNm}
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
              <input
                className="myPageAddressInput"
                placeholder="우편번호"
                onChange={(e) => {
                  setPostCode(e.target.value);
                }}
              ></input>
=======
              <input className="myPageAddressInput">
                {myData[0]?.address.postcode}
              </input>
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
              <input
                className="myPageAddress"
                placeholder="주소를 입력해 주세요."
                onChange={(e) => {
                  setAddress1(e.target.value);
                }}
              ></input>
=======
              <input className="myPageAddress">
                {myData[0]?.address.address1}
              </input>
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList"></th>
            <td>
              <input
                className="myPageAddress"
<<<<<<< HEAD
                placeholder="상세주소를 입력해 주세요."
=======
                defaultValue={myData[0]?.address.address2}
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
            <th className="myPageList">성별</th>
            <td style={{ paddingLeft: "120px" }}>
=======
            <th className="myPageList">나이</th>
            <td>
              <input className="myPageInput">{myData[0]?.gender}</input>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">카테고리</th>
            <td className="radioButton">
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
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
<<<<<<< HEAD
                placeholder="키를 입력해 주세요."
=======
                defaultValue={myData[0]?.length}
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650
                onChange={(e) => {
                  setHeight(e.target.value);
                }}
              ></input>
            </td>
          </tr>
<<<<<<< HEAD
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
=======
>>>>>>> 6d7c7736aeeb35cb758fec566679ec2577862650

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
