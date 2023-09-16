import axios from "axios";
import "../css/registerBoard.css";
import React, { useEffect, useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import moment from "moment";
import { useNavigate, useParams } from "react-router-dom";

function RegisterBoard() {
  let [shopName, setShopName] = useState("");
  let [shopAddress, setShopAddress] = useState("");
  let [shopStartPeriod, setShopStartPeriod] = useState("");
  let [shopEndPeriod, setShopEndPeriod] = useState("");
  let [shopDetail, setShopDetail] = useState("");
  let [event, setEvent] = useState("");
  let [boardData, setBoardData] = useState("");

  let navigate = useNavigate();

  let { eventId } = useParams();

  let updateBoard = () => {
    axios
      .patch(`/event/update/${eventId}`, {
        title: shopName === undefined ? boardData[0].title : shopName,
        eventType: event === undefined ? boardData[0].eventType : event,
        startDate:
          shopStartPeriod === undefined
            ? boardData[0].startDate
            : shopStartPeriod,
        endDate:
          shopEndPeriod === undefined ? boardData[0].endDate : shopEndPeriod,
        content: shopDetail === undefined ? boardData[0].content : shopDetail,
      })
      .then((res) => {
        navigate("/eventAll");
      });
  };

  useEffect(() => {
    axios
      .get(`/event/update/${eventId}`)
      .then((res) => {
        setBoardData([...boardData, res.data]);
        setShopName(boardData[0]?.title);
        setShopAddress(boardData[0]?.shoppingmall.address.address1);
        setShopDetail(boardData[0]?.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

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
                placeholder={boardData[0]?.title}
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
              <label for=""></label>폐점
              <input
                onClick={() => {
                  setEvent("D");
                }}
                type="radio"
                id="discount"
                name="board"
                value="discount"
              />
              <label for=""></label>할인
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
                placeholder={boardData[0]?.content}
                onChange={(e) => {
                  setShopDetail(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td className="registerBoardButton">
            <button className="registerBoardBtn" onClick={updateBoard}>
              수정
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
