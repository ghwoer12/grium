<template>
  <card class="card" title="Profile" sub-title="my data" style="width:80%">
    <div class="row">
      <div class="col-md-6">
        <fg-input
          type="text"
          label="E-mail"
          :readonly="true"
          v-model="user.email"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <fg-input type="text" label="Name" :readonly="true" v-model="user.name">
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <fg-input
          type="text"
          label="Phone"
          :readonly="true"
          placeholder="-를 빼고 입력해주세요."
          v-model="user.phone"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <b-button variant="primary" @click="checkform" class="col-md-6"
          >정보수정</b-button
        >

        <b-button variant="danger" @click="withdraw" class="col-md-6"
          >회원탈퇴</b-button
        >
      </div>
    </div>
  </card>
</template>

<script>
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: {
        email: "",
        name: "",
        phone: ""
      }
    };
  },
  methods: {
    withdraw: function() {
      axios
        .delete(`${SERVER_URL}/user/withdraw`)
        .then(response => {
          console.log(response);
          this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/"));
        })
        .catch(() => {
          console.log("err");
        });
    },
    // updateform: function() {
    //   this.$router.push("/user/update");
    // },
    checkform: function() {
      this.$router.replace("/user/passcheck");
    }
  },
  created() {
    axios
      .get(`${SERVER_URL}/user/info`)
      .then(response => {
        this.user.email = response.data.user.email;
        this.user.name = response.data.user.name;
        this.user.phone = response.data.user.phone;
      })
      .catch(() => {
        this.$store.dispatch("LOGOUT").then(() => {
          this.$router.replace("/");
        });
      });
  }
};
</script>

<style></style>
