<template>
  <div class="row">
    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img
          src="@/assets/img/add.png"
          alt="PHOTO"
          v-on:click="fileopenclick"
        />
        <input
          style="display: none"
          type="file"
          @change="onFileSelected"
          ref="fileInput"
          id="upfile"
          multiple="multiple"
        />
        <button @click="onUpload">Upload</button>
      </div>
      <div class="text-center" style="margin-top: 25px">
        <p><strong>SHARE YOUR PHOTOS</strong></p>
        <p><mark>It'll be beautiful</mark></p>
      </div>
      <hr />

      <div class="text-center">
        <img
          src="@/assets/img/report_basic.png"
          alt="PHOTO"
          style="margin-right: 25px; margin-bottom: 10px"
        />
        <img
          src="@/assets/img/condol_basic.png"
          alt="PHOTO"
          style="margin-bottom: 10px"
        />
      </div>
    </card>

    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img src="@/assets/img/photo_basic.png" alt="PHOTO" />
      </div>
      <div class="text-center" style="margin-top: 25px">
        <p><strong>SHARE YOUR PHOTOS</strong></p>
        <p><mark>It'll be beautiful</mark></p>
      </div>
      <hr />
      <div class="text-center">
        <img
          src="@/assets/img/report_basic.png"
          alt="PHOTO"
          style="margin-right: 25px; margin-bottom: 10px"
        />
        <img
          src="@/assets/img/condol_basic.png"
          alt="PHOTO"
          style="margin-bottom: 10px"
        />
      </div>
    </card>

    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img src="@/assets/img/photo_basic.png" alt="PHOTO" />
      </div>
      <div class="text-center" style="margin-top: 25px">
        <p><strong>SHARE YOUR PHOTOS</strong></p>
        <p><mark>It'll be beautiful</mark></p>
      </div>
      <hr />
      <div class="text-center">
        <img
          src="@/assets/img/report_basic.png"
          alt="PHOTO"
          style="margin-right: 25px; margin-bottom: 10px"
        />
        <img
          src="@/assets/img/condol_basic.png"
          alt="PHOTO"
          style="margin-bottom: 10px"
        />
      </div>
    </card>

    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img src="@/assets/img/photo_basic.png" alt="PHOTO" />
      </div>
      <div class="text-center" style="margin-top: 25px">
        <p><strong>SHARE YOUR PHOTOS</strong></p>
        <p><mark>It'll be beautiful</mark></p>
      </div>
      <hr />
      <div class="text-center">
        <img
          src="@/assets/img/report_basic.png"
          alt="PHOTO"
          style="margin-right: 25px; margin-bottom: 10px"
        />
        <img
          src="@/assets/img/condol_basic.png"
          alt="PHOTO"
          style="margin-bottom: 10px"
        />
      </div>
    </card>

    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img src="@/assets/img/photo_basic.png" alt="PHOTO" />
      </div>
      <div class="text-center" style="margin-top: 25px">
        <p><strong>SHARE YOUR PHOTOS</strong></p>
        <p><mark>It'll be beautiful</mark></p>
      </div>
      <hr />
      <div class="text-center">
        <img
          src="@/assets/img/report_basic.png"
          alt="PHOTO"
          style="margin-right: 25px; margin-bottom: 10px"
        />
        <img
          src="@/assets/img/condol_basic.png"
          alt="PHOTO"
          style="margin-bottom: 10px"
        />
      </div>
    </card>
  </div>
</template>

<script>
import axios from "axios";
import Card from "../../components/Cards/Card.vue";

export default {
  name: "Album",
  components: {
    Card,
  },

  data() {
    return {
      selectedFile: null,
      user: {
        email: "",
      },
    };
  },
  methods: {
    fileopenclick() {
      // alert("dddd");
      this.$refs.fileInput.click();
    },
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },

    onUpload() {
      let user = this.user;

      const fd = new FormData();
      fd.append("image", this.selectedFile, this.selectedFile.name);
      axios.post("${SERVER_URL}/gallary/upload", fd, user).then((res) => {
        console.log(res);
      });
    },
  },
  created() {
    this.user.email = this.$store.getters["getEmail"];
  },
};
</script>
<style></style>
