<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent>
        <div v-if="!successful">
          <!-- userid; username; userpwd; email; address; -->

          <div class="form-group">
            <label for="email">E-mail</label>
            <input
              type="text"
              class="form-control"
              name="email"
              placeholder="Enter your Email"
              v-model="email"
              v-bind:class="{
                error: error.email,
                complete: !error.email && email.length !== 0
              }"
            />
            <div class="alert-danger" v-if="submitted && errors.has('email')">
              {{ errors.first("email") }}
            </div>
            <div class="error-text" v-if="error.email">{{ error.email }}</div>
            <!-- v-bind, div error text추가 -->
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input
              type="password"
              class="form-control"
              name="password"
              placeholder="Enter your Password"

              v-model="user.password"
            />
            <div
              class="alert-danger"
              v-if="submitted && errors.has('password')"
            >
              {{ errors.first("password") }}
            </div>
            <div class="error-text" v-if="error.password">
              {{ error.password }}
            </div>
            <!-- v-bind, div error text추가 -->
          </div>

          <div class="form-group">
            <label for="name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              placeholder="Enter your Name"
              v-model="name"
            />
            <div class="alert-danger" v-if="submitted && errors.has('name')">
              {{ errors.first("name") }}
            </div>
          </div>
          <div class="form-group">
            <label for="phone">PhoneNumber</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              placeholder="'-' 없이 입력하세요"
              v-model="phone"
            />
            <div class="alert-danger" v-if="submitted && errors.has('phone')">
              {{ errors.first("phone") }}
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="regist">
              Sign Up
            </button>
          </div>
        </div>
      </form>

      <div
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
        v-if="message"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
// 이메일과 비밀번호를 검사하는 npm 프로그램 import
import PV from "password-validator";
import * as EmailValidator from "email-validator";

// import func from "../../../vue-temp/vue-editor-bridge";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "register",
  created() {
    this.passwordSchema
      // 최소 길이 2
      .is()
      .min(2)
      // 최대 길이 100
      .is()
      .max(100)
      // 1 자리 이상
      .has()
      .digits(1)
      // 스페이스바 금지
      .has()
      .not()
      .spaces();
    // 대문자가 있어야 함
    // .has().uppercase()
    // 소문자가 있어야 함
    // .has().lowercase()
    // 만들면 안 되는 비밀번호 - 블랙리스트 등록
    // .is().not().oneOf(['블랙리스트비번', '블랙리스트비번']);
  },

  data() {
    return {
      email: "",
      password: "",
      name: "",
      phone: "",

      submitted: false,
      successful: false,
      message: "",

      // 비밀번호 검사, error 검사
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  },
  // mounted() {
  //   if (this.loggedIn) {
  //     this.$router.push('/');
  //   }
  // },

  // 계속 지켜보는 게 watch : 바뀔 때마다 확인함
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },

  methods: {
    // checkForm() 추가
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문, 숫자 포함 8자리 이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },

    regist: function() {
      let { email, password, name, phone } = this;
      let user = { email, password, name, phone };

      axios
        .post(`${SERVER_URL}/user/signup`, user)
        .then(response => {
          this.$router.push("/login");
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    }
    // checkpass: function() {
    //   var pw = $("#password").val();
    //   var num = pw.search(/[0-9]/g);
    //   var eng = pw.search(/[a-z]/gi);
    //   var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    //   if (pw.length < 10 || pw.length > 20) {
    //     alert("10자리 ~ 20자리 이내로 입력해주세요.");
    //     return false;
    //   } else if (pw.search(/\s/) != -1) {
    //     alert("비밀번호는 공백 없이 입력해주세요.");
    //     return false;
    //   } else if (
    //     (num < 0 && eng < 0)
    //     (eng < 0 && spe < 0)
    //     (spe < 0 && num < 0)
    //   ) {
    //     alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
    //     return false;
    //   } else {
    //     console.log("통과");
    //   }
    // }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
