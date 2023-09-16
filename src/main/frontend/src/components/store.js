import { useEffect, useState } from "react";
import { configureStore, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

let loginStatus = createSlice({
  name: "loginStatus",
  initialState: "false",

  reducers: {
    changeLoginStatus(state) {
      return "true";
    },
    changeLogOutStatus(state) {
      return "false";
    },
  },
});

// let memberSession = createSlice({
//   name: "memberId",
//   initialState: [{ id: 0, memberId: "" }],
// });

let count = createSlice({
  name: "count",
  initialState: 0,

  reducers: {
    increase(state) {
      return count++;
    },
  },
});

export let { changeLoginStatus, changeLogOutStatus } = loginStatus.actions;

export let { increase } = count.actions;

export default configureStore({
  reducer: {
    loginStatus: loginStatus.reducer,
    count: count.reducer,
  },
});
