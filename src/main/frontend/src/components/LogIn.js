import { useNavigate } from "react-router-dom";
import "../css/logIn.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { changeLoginStatus } from "./store";

function LogIn() {
  let navigate = useNavigate();
  let [loginId, setLoginId] = useState("");
  let [loginPw, setLoginPw] = useState("");
  let dispatch = useDispatch();
  let loginStatus = useSelector((state) => {
    return state;
  });

  return (
    <div className="loginMain">
      <div className="loginPage">
        <div
          className="loginTitle"
          onClick={() => {
            navigate("/");
          }}
        >
          OffClothes
        </div>
        <input
          type="text"
          className="idInput"
          placeholder="아이디"
          value={loginId}
          onChange={(e) => {
            setLoginId(e.target.value);
          }}
        ></input>
        <input
          type="password"
          className="pwInput"
          placeholder="비밀번호"
          value={loginPw}
          onChange={(e) => {
            setLoginPw(e.target.value);
          }}
        ></input>
        <div className="loginButtonMain">
          <button
            className="loginButton"
            onClick={() => {
              {
                axios
                  .post("/Member/login", {
                    id: loginId,
                    pwd: loginPw,
                  })
                  .then((res) => alert(res.data.message))
                  .then(() => navigate("/"))
                  .then(() => {
                    dispatch(changeLoginStatus());
                  })
                  .catch(function (err) {
                    alert("아이디 또는 비밀번호가 일치하지 않습니다.");
                    console.log(err);
                  });
              }
            }}
          >
            로그인
          </button>
          <button
            className="signUpButton"
            onClick={() => {
              navigate("/registerGoods");
            }}
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
}

export default LogIn;
