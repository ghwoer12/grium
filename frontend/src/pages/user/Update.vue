<template>
  <card class="card" title="regist" sub-title="내 정보 수정페이지입니다.">
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="text"
          label="email"
          :readonly="true"
          v-model="user.email"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="password"
          label="password"
          v-model="user.password"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="text"
          label="name"
          placeholder="name"
          v-model="user.name"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="phone"
          label="phone"
          placeholder="phone"
          v-model="user.phone"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-5">
        <b-button variant="primary" @click="update">수정완료</b-button>
      </div>
    </div>
  </card>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "update",
  data() {
    return {
      user: {
        email: "",
        name: "",
        password:"",
        phone: ""
      }
    };
  },
  methods: {
    update: function() {
      let user = this.user;
      console.log(user);
      axios
        .put(`${SERVER_URL}/user/edit`, user)
        .then(response => {
          // this.$router.replace("/user/profile");
          this.$store.dispatch("LOGOUT").then(() => {
            this.$router.replace("/login");
          });
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    }
  },
  created() {
    axios
      .get(`${SERVER_URL}/user/info`)
      .then(response => {
        this.user.email = response.data.user.email;
        this.user.name = response.data.user.name;
        this.user.password = response.data.user.password;
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
