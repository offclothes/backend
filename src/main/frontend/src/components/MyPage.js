import "../css/myPage.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function MyPage() {
  let [myData, setMyData] = useState([]);
  let [nickName, setNickName] = useState("");
  let [phone, setPhone] = useState("");
  let [postCode, setPostCode] = useState("");
  let [address1, setAddress1] = useState("");
  let [address2, setAddress2] = useState("");
  let [height, setHeight] = useState("");
  let [weight, setWeight] = useState("");
  let [gender, setGender] = useState("");

  let navigate = useNavigate();

  // let count = useSelector((state) => {
  //   return state.count;
  // });

  function saveInformationButton() {
    axios
      .patch("/Member/myPage", {
        nickname: nickName === undefined ? myData[0].nickname : nickName,
        phoneNm: phone === undefined ? myData[0]?.phoneNm : phone,
        postcode: myData[0]?.address.postcode,
        address1: myData[0]?.address.address1,
        address2:
          address2 === undefined ? myData[0]?.address.address2 : address2,
        length: height === undefined ? myData[0]?.length : height,
        weight: weight === undefined ? myData[0]?.weight : weight,
        gender: myData[0]?.gender,
      })
      .then((res) => {
        alert(res.data.message);
        console.log(nickName, phone, address2);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  useEffect(() => {
    axios
      .get("/Member/myPage")
      .then((res) => {
        setMyData([...myData, res.data]);
        setNickName(myData[0]?.nickname);
        setPhone(myData[0]?.phoneNm);
        setPostCode(myData[0]?.address.postcode);
        setAddress1(myData[0]?.address.address1);
        setAddress2(myData[0]?.address.address2);
        setHeight(myData[0]?.length);
        setWeight(myData[0]?.weight);
        setGender(myData[0]?.gender);
      })
      .catch(() => {
        alert("로그인을 해주세요.");
        navigate("/login");
      });
  }, []);

  return (
    <div className="myPageMain">
      <table width="100%">
        <tr>
          <th className="myPageTitle">OffClothes</th>
        </tr>
        <tr>
          <th className="myPageTop">My Page</th>
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
              <p className="myPageInput" disabled="true">
                {myData[0]?.memberId}
              </p>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList">닉네임</th>
            <td>
              <input
                className="myPageInput"
                placeholder={myData[0]?.nickname}
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
                placeholder={myData[0]?.phoneNm}
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
              <p className="myPageAddressInput">
                {myData[0]?.address.postcode}
              </p>
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
              <p className="myPageAddress">{myData[0]?.address.address1}</p>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="myPageList"></th>
            <td>
              <input
                className="myPageAddress"
                placeholder={myData[0]?.address.address2}
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
            <td>
              <p className="myPageInput">{myData[0]?.gender}</p>
            </td>
          </td>
        </tr>
        <tr>
          <tr>
            <th className="myPageList">키</th>
            <td>
              <input
                className="myPageInput"
                placeholder={myData[0]?.length}
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
                placeholder={myData[0]?.weight}
                onChange={(e) => {
                  setWeight(e.target.value);
                }}
              ></input>
            </td>
          </tr>
        </tr>
        <tr>
          <td className="button">
            <button
              className="registerButton"
              onClick={() => {
                saveInformationButton();
              }}
            >
              저장
            </button>
            <button className="cancelButton">취소</button>
          </td>
        </tr>
      </table>
    </div>
  );
}

export default MyPage;
