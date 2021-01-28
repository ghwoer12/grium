import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    email: "",
    name: "",
    password: '',
    phone: "",
    photo: "",
    // roll : "",

  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getEmail(state) {
      return state.email;
    },
    getName(state) {
      return state.name;
    },
    getPassword(state) {
      return state.password;
    },
    getPhone(state) {
      return state.phone;
    },
    getPhoto(state) {
      return state.photo;
    },
    // getRoll(state) {
    //   return state.roll;
    // }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.password = payload['password'];
      state.phone = payload["phone"];
      state.photo = payload["photo"];
      // state.roll = payload["roll"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.email = "";
      state.name = "";
      state.password = '';
      state.phone = "";
      state.photo = "";
      // state.roll = "";
    },
    FINDPW(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.password = payload['password'];
      // state.roll = payload["roll"];
    },
    UPDATE(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.phone = payload['phone'];
      // state.roll = payload["roll"];
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/confirm/login`, user)
        .then((response) => {
          context.commit("LOGIN", response.data);
          if(`${response.data["auth-token"]}` == "undefined") reject();
          axios.defaults.headers.common["auth-token"] = `${response.data["auth-token"]}`;
          sessionStorage.setItem('auth-token', `${response.data["auth-token"]}`)
          sessionStorage.setItem("phone", `${response.data["phone"]}`
          );
        })
        .catch(() => {
          reject();
        });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
      sessionStorage.removeItem('auth-token');
    },

    FINDPW(context, user) {
      return axios
        .post(`${SERVER_URL}/user/findpw`, user)
        .then((response) => {
          context.commit("FINDPW", response.data);
          axios.defaults.headers.common["auth-token"] = `${response.data["auth-token"]}`;
          sessionStorage.setItem('auth-token', `${response.data["auth-token"]}`)
        })
        .catch(() => {
          reject();
        });
    },
    UPDATE(context, user) {
      return axios
        .post(`${SERVER_URL}/user/update`, user)
        .then((response) => {
          context.commit("UPDATE", response.data);
          if (`${response.data["auth-token"]}` == "undefined") reject();
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          sessionStorage.setItem(
            "auth-token",
            `${response.data["auth-token"]}`
          );
        })
        .catch(() => {
          reject();
        });
    },
  },
  modules: {}
});
