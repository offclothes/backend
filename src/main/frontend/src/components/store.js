import { configureStore, createSlice } from "@reduxjs/toolkit";

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
