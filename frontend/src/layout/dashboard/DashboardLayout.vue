<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/dashboard" name="Main" icon="ti-panel" />
        <sidebar-link to="/user/profile" name="My page" icon="ti-user" />
        <sidebar-link
          to="/search/house"
          name="Online Feed"
          icon="ti-view-list-alt"
        />
        <!-- <sidebar-link to="/typography" name="Typography" icon="ti-text" /> -->
        <!-- <sidebar-link to="/icons" name="Icons" icon="ti-pencil-alt2" /> -->
        <!-- <sidebar-link to="/maps" name="지도" icon="ti-map" /> -->
        <sidebar-link to="/qna/list" name="질문" icon="ti-bell" />
      </template>
      <mobile-menu>
        <!-- <li class="nav-item">
          <a class="nav-link">
            <i class="ti-panel"></i>
            <p>Stats</p>
          </a>
        </li> -->
        <drop-down
          class="nav-item"
          title="알림"
          title-classes="nav-link"
          icon="ti-bell"
        >
          <a class="dropdown-item" href="#"></a>
          <a class="dropdown-item" href="#">...</a>
          <a class="dropdown-item" href="#">...</a>
          <a class="dropdown-item" href="#">...</a>
        </drop-down>
        <li class="nav-item">
          <a href="#" class="nav-link">
            <i class="ti-user"></i>
            <p>로그인</p>
          </a>
        </li>
        <li class="divider"></li>
      </mobile-menu>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar"> </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    }
  }
};
</script>
