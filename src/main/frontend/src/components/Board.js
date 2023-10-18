import { Link, useNavigate, useParams } from "react-router-dom";
import "../css/board.css";
import axios from "axios";
import { useEffect, useState } from "react";
import { changeLogOutStatus } from "./store";
import { increase } from "./store";
import { useSelector } from "react-redux";
import Pagination from "./Pagination";

function Board() {
  let navigate = useNavigate();
  let [data, setData] = useState([]);
  let [allData, setAllData] = useState([]);
  let [discountData, setDiscountData] = useState([]);
  let [closeData, setCloseData] = useState([]);
  let [myPost, setMyPost] = useState([]);
  let [visible, setVisible] = useState(false);
  let [clickedBtn, setClickedBtn] = useState("전체");
  let [render, setRender] = useState(0);

  const [limit, setLimit] = useState(2);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  let loginStatus = useSelector((state) => {
    return state;
  });

  let myBoard = () => {
    let error = () => {
      navigate("/event/all");
      alert("판매자가 아닙니다.");
    };
    setClickedBtn("내 글");
    setVisible(true);
    navigate("/event/myBoard");
    axios.get("/eventAll").then((res) => {
      let copy = [...allData];
      copy.push(...res.data.list);
      setData(copy);
      res.data.seller === true ? (
        <MyPostBoard
          data={data}
          setData={setData}
          myPost={myPost}
          visible={visible}
        />
      ) : (
        error()
      );
    });
  };

  let goToLogin = () => {
    alert("로그인을 해주세요.");
    navigate("/login");
  };

  useEffect(() => {
    axios.get("/eventAll", { params: { state: "A" } }).then((res) => {
      let copy = [...allData];
      console.log(res);
      copy.push(...res.data.list);
      setData(copy);
      return (
        <div>
          {data.map(function (a, i) {
            return (
              <BoardList
                visible={visible}
                key={data[i]?.eventId}
                i={i}
                data={data}
              />
            );
          })}
        </div>
      );
    });
  }, [render]);

  return (
    <div className="boardMain">
      <div className="boardTop">
        <button
          className={
            clickedBtn === "전체" ? "boardClicked" : "boardDiscountButton"
          }
          onClick={() => {
            setClickedBtn("전체");
            setVisible(false);
            axios.get("/eventAll", { params: { state: "A" } }).then((res) => {
              let copy = [...allData];
              console.log(res);
              copy.push(...res.data.list);
              setData(copy);
              return (
                <div>
                  {data.map(function (a, i) {
                    return (
                      <BoardList
                        visible={visible}
                        key={data[i]?.eventId}
                        i={i}
                        data={data}
                      />
                    );
                  })}
                </div>
              );
            });
          }}
        >
          전체
        </button>
        <button
          className={
            clickedBtn === "할인" ? "boardClicked" : "boardDiscountButton"
          }
          onClick={() => {
            setClickedBtn("할인");
            setVisible(false);
            navigate("/event/discount");
            axios.get("/eventAll", { params: { state: "D" } }).then((res) => {
              let copy = [...discountData];
              copy.push(...res.data.list);
              setData(copy);
              return (
                <div>
                  {data.map(function (a, i) {
                    return (
                      <BoardList
                        visible={visible}
                        key={data[i]?.eventId}
                        i={i}
                        data={data}
                      />
                    );
                  })}
                </div>
              );
            });
          }}
        >
          할인
        </button>
        <button
          className={
            clickedBtn === "폐점" ? "boardClicked" : "boardClosureButton"
          }
          onClick={() => {
            setClickedBtn("폐점");
            setVisible(false);
            navigate("/event/close");
            axios
              .get("/eventAll", { params: { state: "C" } })
              .then((res) => {
                console.log(res);
                let copy = [...closeData];
                copy.push(...res.data.list);
                setData(copy);
                return (
                  <div>
                    {data.map(function (a, i) {
                      return (
                        <BoardList
                          visible={visible}
                          key={data[i]?.eventId}
                          i={i}
                          data={data}
                        />
                      );
                    })}
                  </div>
                );
              })
              .catch((err) => {
                console.log(err);
              });
          }}
        >
          폐점
        </button>
        <button
          className={
            clickedBtn === "내 글" ? "boardMyButtonClicked" : "boardMyButton"
          }
          onClick={loginStatus.loginStatus === "true" ? myBoard : goToLogin}
        >
          내가 작성한 글 보기
        </button>
        <div className="boardRegister">
          <button
            className="boardRegisterButton"
            onClick={() => {
              navigate("/registerBoard");
            }}
          >
            작성하기
          </button>
        </div>
      </div>
      <div>
        {data?.slice(offset, offset + limit).map(function (a, i) {
          return (
            <BoardList
              offset={offset + i}
              visible={visible}
              key={data[i]?.eventId}
              i={i}
              data={data}
              render={render}
              setRender={setRender}
            />
          );
        })}
        <div className="paging">
          <Pagination
            total={data?.length}
            limit={2}
            page={page}
            setPage={setPage}
          />
        </div>
      </div>
    </div>
  );
}

function BoardList({ i, data, visible, offset, render, setRender }) {
  let navigate = useNavigate();

  return (
    <div>
      <div className="boardList">
        <div className="boardDetail">
          <p className="boardName">{data[offset]?.shopName}</p>
          <p>제목 : {data[offset]?.title}</p>
          <p>주소 : {data[offset]?.address.address1}</p>
          <p>내용 : {data[offset]?.content}</p>
          <p>
            기간 : {data[offset]?.startDay} ~ {data[offset]?.endDay}
          </p>
          <div
            className="boardGoShop"
            onClick={() => {
              navigate("/shop");
            }}
          >
            매장 페이지 이동하기
          </div>
        </div>
        <div style={{ marginBottom: "10px" }}></div>
      </div>
      <div>
        {visible && (
          <MyBoardButton
            data={data}
            i={i}
            render={render}
            setRender={setRender}
          />
        )}
        <hr />
      </div>
    </div>
  );
}

function MyBoardButton({ data, i, render, setRender }) {
  let navigate = useNavigate();
  let { eventId } = useParams();

  const deleteBtn = () => {
    axios
      .delete(`/myPost/${data[i].eventId}`)
      .then(() => {
        alert("삭제되었습니다.");
        setRender(render + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <div className="boardButton">
        <button
          className="boardChangeButton"
          onClick={() => {
            navigate(`/changeBoard/${data[i].eventId}`);
          }}
        >
          수정
        </button>
        <button className="boardDeleteButton" onClick={deleteBtn}>
          삭제
        </button>
      </div>
    </div>
  );
}

function MyPostBoard({ myPost, setData, visible, data }) {
  useEffect(() => {
    axios.get("/myPost").then((res) => {
      let copy = [...myPost];
      copy.push(...res.data);
      console.log(res.data);
      setData(copy);
    }, []);

    return (
      <div>
        {data.map(function (a, i) {
          return (
            <div>
              <div>
                <BoardList
                  visible={visible}
                  key={data[i]?.eventId}
                  i={i}
                  data={data}
                />
              </div>
            </div>
          );
        })}
      </div>
    );
  }).catch((err) => {
    console.log(err);
  });
}

export default Board;
