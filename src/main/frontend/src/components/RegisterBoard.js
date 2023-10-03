import axios from "axios";
import "../css/registerBoard.css";
import React, { useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import moment from "moment";
import { useNavigate } from "react-router-dom";

function RegisterBoard() {
  let [shopName, setShopName] = useState("");
  let [shopAddress, setShopAddress] = useState("");
  let [shopStartPeriod, setShopStartPeriod] = useState("");
  let [shopEndPeriod, setShopEndPeriod] = useState("");
  let [shopDetail, setShopDetail] = useState("");
  let [event, setEvent] = useState("");
  let navigate = useNavigate();

  return (
    <div className="registerBoardMain">
      <table width="100%">
        <tr>
          <th className="registerBoardTitle">OffClothes</th>
        </tr>
        <tr>
          <th className="registerBoardTop">폐점/할인 등록하기</th>
        </tr>
        <tr>
          <td>
            <th className="registerBoardList">제목</th>
            <th>
              <input
                className="registerBoardInput"
                placeholder="제목을 입력해주세요."
                onChange={(e) => {
                  setShopName(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <td className="RegisterBoardRadioButton">
              <input
                onClick={() => {
                  setEvent("C");
                }}
                type="radio"
                id="close"
                name="board"
                value="close"
              />
              <label></label>폐점
              <input
                onClick={() => {
                  setEvent("D");
                }}
                type="radio"
                id="discount"
                name="board"
                value="discount"
              />
              <label></label>할인
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerBoardList">기간</th>
            <th>
              <th>
                <StartCalendar
                  shopStartPeriod={shopStartPeriod}
                  setShopStartPeriod={setShopStartPeriod}
                />
              </th>
              <th>
                <EndCalendarApp
                  setShopEndPeriod={setShopEndPeriod}
                  shopEndPeriod={shopEndPeriod}
                />
              </th>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerBoardList">내용</th>
            <th>
              <input
                className="detailInput"
                onChange={(e) => {
                  setShopDetail(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td className="registerBoardButton">
            <button
              className="registerBoardBtn"
              onClick={() => {
                axios
                  .post("/event/post", {
                    title: shopName,
                    eventType: event,
                    startDate: shopStartPeriod,
                    endDate: shopEndPeriod,
                    content: shopDetail,
                  })
                  .then(() => {
                    navigate("/event/all");
                  })
                  .catch((err) => console.log(err));
              }}
            >
              등록
            </button>
            <button
              className="cancelBoardBtn"
              onClick={() => {
                navigate("/eventAll");
              }}
            >
              취소
            </button>
          </td>
        </tr>
      </table>
    </div>
  );
}

function StartCalendar(props) {
  const [value, onChange] = useState(new Date());

  return (
    <div style={{ textAlign: "center" }}>
      <Calendar onChange={onChange} value={value} />
      <div className="text-gray-500 mt-4">
        {moment(value).format("YYYY-MM-DD")}
      </div>
      <button
        className="shopPeriodButton"
        onClick={() => {
          props.setShopStartPeriod(moment(value).format("YYYY-MM-DD"));
          console.log(props.shopStartPeriod);
        }}
      >
        시작 날짜
      </button>
    </div>
  );
}

function EndCalendarApp(props) {
  const [value, onChange] = useState(new Date());

  return (
    <div style={{ textAlign: "center" }}>
      <Calendar onChange={onChange} value={value} />
      <div className="text-gray-500 mt-4">
        {moment(value).format("YYYY-MM-DD")}
      </div>
      <button
        className="shopPeriodButton"
        onClick={() => {
          props.setShopEndPeriod(moment(value).format("YYYY-MM-DD"));
          console.log(props.shopEndPeriod);
        }}
      >
        끝 날짜
      </button>
    </div>
  );
}

export default RegisterBoard;
