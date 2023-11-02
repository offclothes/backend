import { useNavigate } from "react-router-dom";
import LoadingPage from "./Loading";
import Col from "react-bootstrap/Col";

export default function LocationSearchResultPage(props) {
  let navigate = useNavigate();

  return (
    <Col md="4" style={{ textAlign: "center" }}>
      {props.data ? (
        <img
          style={{ cursor: "pointer" }}
          src={props.img[props.offset]}
          width="250px"
          height="300px"
          referrerPolicy="no-referrer"
          onClick={() => {
            navigate(`/shop/item/${props.data[props.i].uploadFile.item_seq}`);
          }}
        ></img>
      ) : (
        <LoadingPage />
      )}
      <h4>{props.data[props.offset].itemTitle}</h4>
    </Col>
  );
}
