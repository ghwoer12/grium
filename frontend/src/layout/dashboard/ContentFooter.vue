<template>
  <footer class="footer">
    <div class="container-fluid d-flex flex-wrap justify-content-between">
      <nav>
        <ul>
          <li>
            <router-link :to="{ path: '/admin' }">그리움</router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright d-flex flex-wrap">
        <p>
          with by FOUR TEAM
          <i class="fa fa-heart heart"></i>
        </p>
      </div>
    </div>
  </footer>
</template>
<script>
export default {};
</script>
<style></style>
