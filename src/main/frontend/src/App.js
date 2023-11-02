import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/NavBar";
import React from "react";
import axios from "axios";
import { useEffect } from "react";
import LogIn from "./components/LogIn";
import { useLocation } from "react-router-dom";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import EntryStore from "./components/EntryStore";

function App() {
  let location = useLocation();

  // useEffect(() => {
  //   axios
  //     .get("/api/test")
  //     .then((res) => console.log(res.data))
  //     .catch();
  // });
  return (
    // <Router>
    <div>{location.pathname === "/login" ? <LogIn /> : <NavBar />}</div>
    // <Routes>
    // <Route path="/entry" element={<EntryStore />} />
    // </Routes>
    // </Router>
  );
}

export default App;
