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
        <div>
          <input
            type="file"
            v-on:change="handleFileUpload"
            ref="upfile"
            id="upfile"
            multiple
          />
          <button v-on:click="submitFile()">담기</button>
        </div>

        <!-- <div>
          <button @click="onUpload">Upload</button>
        </div> -->
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
    <!-- <div>
      이미지 리스트 받아오기
      <div v-for="(galleryData, index) in galleryDatas" :key="index">
        {{ galleryData.title }}
        <img
          style="width: 120px;"
          :src="galleryData.file_path"
          alt="로드실패"
        />
      </div>
    </div> -->
  </div>
</template>

<script>
import axios from "axios";
import Card from "../../components/Cards/Card.vue";
import ToggleButton from "../../components/ToggleButton.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "Album",
  components: {
    Card,
    ToggleButton
  },

  data() {
    return {
      active: false,
      title: "",
      upfile: [],
      galleryDatas: [],
      gallery: {
        email: "",
        writer: "",
        secret: 0
      }
    };
  },
  // mounted() {
  //   axios
  //     .get(`${SERVER_URL}/gallary/upload`)
  //     .then(res => {
  //       console.log(res.data);
  //       this.galleryDatas = res.data;
  //     })
  //     .catch(error => console.log(error));
  // },
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
    },
    // fileopenclick() {
    //   this.$refs.fileInput.click();
    // },
    // onFileSelected(event) {
    //   // this.selectedFile = event.target.files[0];
    //   this.selectedFile = this.$refs.files.selectedFile;
    //   console.log(this.selectedFile);
    // },

    // onUpload() {
    //   for (let i = 0; i < this.selectedFile.length; i++) {
    //     let gallery = this.gallery;
    //     const fd = new FormData();
    //     fd.append("image", this.selectedFile, this.selectedFile.name);
    //     axios
    //       .post(`${SERVER_URL}/gallary/upload`, fd, gallery, {
    //         headers: {
    //           "Content-Type": "multipart/form-data"
    //         }
    //       })
    //       .then(res => {
    //         console.log(res);
    //         alert("axios성공");
    //       })
    //       .catch(() => {
    //         console.log("err");
    //         // alert("액시오스 전");
    //       });
    //   }
    // },
    submitFile() {
      for (let i = 0; i < this.upfile.length; i++) {
        let formData = new FormData();
        formData.append("title", this.title);
        formData.append("upfile", this.upfile[i]);
        axios
          .post(`${SERVER_URL}/gallary/upload`, formData, {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          })
          .then(function() {
            console.log("SUCCESS!!");
          })
          .catch(function() {
            console.log("FAILURE!!");
          });
      }
    },
    handleFileUpload() {
      this.upfile = this.$refs.upfile.upfile;
      console.log(this.upfile);
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