<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">{{ routeName }}</a>
      <button
        class="navbar-toggler navbar-burger"
        type="button"
        @click="toggleSidebar"
        :aria-expanded="$sidebar.showSidebar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
      </button>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <drop-down
            class="nav-item"
            title=" "
            title-classes="nav-link"
            icon="ti-bell"
          >
            <router-link to="/alert" class="nav-link">
              신고 관리
            </router-link>
          </drop-down>
        </li>
        <li class="nav-item">
          <drop-down
            class="nav-item"
            title=" "
            title-classes="nav-link"
            icon="ti-user"
          >
            <router-link to="/login" class="nav-link" v-if="getName === ''">
              로그인
            </router-link>
            <router-link @click.native="logout" to="/" class="nav-link" v-else>
              로그아웃
            </router-link>
          </drop-down>
        </li>
      </ul>
    </div>
  </nav>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserid",
      "getName",
      "getNumber",
      "getAddress",
      "getRoll"
    ]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  data() {
    return {
      activeNotifications: false
    };
  },
  methods: {
    logout: function() {
      this.$store.dispatch("LOGOUT").then(() => {
        this.$router.replace("/login");
      });
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    }
  }
};
</script>
<style></style>
