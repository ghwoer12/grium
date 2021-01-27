<template>
  <card title="login" :sub-title="`${message}`">
    <form @submit.prevent>
      <div class="row">
        <div class="col-md-5">
          <fg-input
          type="text"
            id="email"
            v-model="user.email"
            required
            placeholder="아이디 입력...."
          ></fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-5">
          <fg-input
            type="text"
            id="password"
            v-model="user.password"
            required
            placeholder="비밀번호 입력...."
            @keypress.enter="login"
          ></fg-input>
        </div>
      </div>
    </form>
    <b-button type="button" variant="primary" class="m-1" @click="login"
      >로그인</b-button
    >
    <b-button type="button" variant="success" class="m-1" @click="regist"
      >회원가입</b-button
    >
  </card>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import Card from "@/components/Cards/Card.vue";

Vue.use(VueRouter);

export default {
  components: { Card },
  name: "Login",
  data: function() {
    return {
      user: {
        email: "",
        password: ""
      },
      message: ""
    };
  },
  // computed: {
  //   nextRoute() {
  //     return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
  //   },
  // },
  methods: {
    regist: function() {
      this.$router.replace("/user/register");
    },
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.user)
        .then(() => this.$router.push(`/dashboard`))
        // .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(
          ({ message }) => (this.message = "아이디/비밀번호를 확인해주세요.")
        );
    }
  },
  created() {
    this.$store
      .dispatch("LOGOUT")
      .then(() => this.$router.replace("/login").catch(() => {}));
  }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
