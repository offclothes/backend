import { FadeLoader } from "react-spinners";

const ImgLoading = () => {
  return (
    <div
      style={{
        width: "200px",
        height: "150px",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <p>잠시만 기다려주세요.</p>
      <FadeLoader color="#36d7b7" />
    </div>
  );
};

export default ImgLoading;
