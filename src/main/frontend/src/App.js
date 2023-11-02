import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/NavBar";
import React from "react";
import LogIn from "./components/LogIn";
import { useLocation } from "react-router-dom";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import EntryStore from "./components/EntryStore";

function App() {
  let location = useLocation();
  return <div>{location.pathname === "/login" ? <LogIn /> : <NavBar />}</div>;
}

export default App;
