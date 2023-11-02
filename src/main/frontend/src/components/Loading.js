import { FadeLoader } from "react-spinners";

const LoadingPage = () => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <h3>잠시만 기다려주세요.</h3>
      <FadeLoader color="#36d7b7" />
    </div>
  );
};

export default LoadingPage;
