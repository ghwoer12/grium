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
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <!-- <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="ti-panel"></i>
              <p>Stats</p>
            </a>
          </li> -->
          <drop-down
            class="nav-item"
            title="5 Notifications"
            title-classes="nav-link"
            icon="ti-bell"
          >
            <a class="dropdown-item" href="#">편지에 대한 신고 2건입니다.</a>
            <a class="dropdown-item" href="#">편지에 대한 신고 2건입니다.</a>
            <a class="dropdown-item" href="#">앨범에 대한 신고 2건입니다.</a>
            <a class="dropdown-item" href="#">앨범에 대한 신고 2건입니다.</a>
          </drop-down>
          <li class="nav-item">
            <drop-down
              class="nav-item"
              title="회원관리"
              title-classes="nav-link"
              icon="ti-user"
            >
              <router-link to="/login" class="nav-link" v-if="getName === ''">
                <p>
                  로그인
                </p>
              </router-link>
              <router-link to="/login" class="nav-link" v-else>
                <p>
                  로그아웃
                </p>
              </router-link>
            </drop-down>
          </li>
        </ul>
      </div>
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
