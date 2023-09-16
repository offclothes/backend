import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/NavBar";
import React from "react";
import axios from "axios";
import { useEffect } from "react";
import LogIn from "./components/LogIn";
import { useLocation } from "react-router-dom";

function App() {
  let location = useLocation();

  // useEffect(() => {
  //   axios
  //     .get("/api/test")
  //     .then((res) => console.log(res.data))
  //     .catch();
  // });
  return <div>{location.pathname === "/login" ? <LogIn /> : <NavBar />}</div>;
}

export default App;
