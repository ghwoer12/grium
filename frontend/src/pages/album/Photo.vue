<template>
  <div class="row">
    <card class="col-sm-6 col-md-3">
      <div class="text-center">
        <img
          id="filechange"
          src="@/assets/img/add.png"
          alt="PHOTO"
          v-on:click="fileopenclick"
        />

        <div>
          <ToggleButton
            id="onoff"
            :defaultState="true"
            v-on:change="triggerEvent"
          />
        </div>

        <div>
          <input
            style="display: none"
            type="text"
            placeholder="userid"
            v-bind:value="this.gallery.email"
            v-on:input="updateUserid"
            disabled
          />
          <input
            style="display: none"
            type="text"
            placeholder="writer"
            v-bind:value="this.gallery.writer"
            v-on:input="updateWriter"
            disabled
          />
          <input
            style="display: none"
            type="text"
            placeholder="secret"
            v-bind:value="this.gallery.secret"
            disabled
          />
        </div>
        <input
          style="display: none"
          type="file"
          @change="onFileSelected"
          ref="fileInput"
          id="upfile"
          multiple="multiple"
        />
        <div>
          <button @click="onUpload">Upload</button>
        </div>
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
          onmouseover="@click"
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
import ToggleButton from "../../components/ToggleButton.vue";

export default {
  name: "Album",
  components: {
    Card,
    ToggleButton
  },

  data() {
    return {
      selectedFile: null,
      active: false,
      gallery: {
        email: "",
        writer: "",
        secret: 0
      }
    };
  },
  methods: {
    updateUserid: function(event) {
      var updatedText = event.target.value;
      this.gallery.email = updatedText;
    },
    updateWriter: function(event) {
      var updatedText = event.target.value;
      this.gallery.writer = updatedText;
    },
    triggerEvent(value) {
      this.active = value;
      if (this.active) {
        this.gallery.secret = 0;
      } else {
        this.gallery.secret = 1;
      }
      // alert(this.active);
      // alert("secret값은 " + this.gallery.secret);
    },
    fileopenclick() {
      this.$refs.fileInput.click();
    },
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },

    onUpload() {
      // let gallery = this.gallery;
      // const fd = new FormData();
      // fd.append("image", this.selectedFile, this.selectedFile.name);
      // alert("selectedFile :" + this.selectedFile.name);
      // alert("USERID : " + gallery.userid);
      // alert("WRITER : " + gallery.writer);
      // alert("SECRET : " + gallery.secret);
      let gallery = this.gallery;
      const fd = new FormData();
      fd.append("image", this.selectedFile, this.selectedFile.name);
      axios.post("${SERVER_URL}/gallary/upload", fd, gallery).then(res => {
        console.log(res);
      });
    }
  },
  created() {
    this.gallery.email = this.$store.getters["getEmail"];
    this.gallery.writer = this.$store.getters["getName"];
  }
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.box {
  text-align: center;
  margin-bottom: 30px;
}
.toggle_container {
  margin: 0px auto;
  background: #efefef;
  width: 120px;
  padding: 10px 0;
  border-radius: 30px;
  transition: all 0.25s;
}
.toggle_container.active {
  background: #e9ffef;
}
</style>
