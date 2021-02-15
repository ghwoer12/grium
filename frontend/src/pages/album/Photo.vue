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
          type="file"
          @change="onFileSelected"
          ref="files"
          id="files"
          name="upfile"
          multiple="multiple"
        />
        <div>
          <button v-on:click="onUpload()">Upload</button>
          <!-- <button v-on:click="submitFile()">Submit</button> -->
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
      files: [],
      // galleryDatas: [],
      // writer:"",
      // secret:0,
      gallery: {
        email: "",
        writer: "",
        secret: 0
      }
    };
  },  methods: {
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
    fileopenclick() {
      this.$refs.files.click();
    },
    onFileSelected() {
      this.files = this.$refs.files.files;
      console.log(this.files);
    },
    // fileopenclick() {
    //   this.$refs.fileInput.click();
    // },
    // onFileSelected(event) {
    //   // this.selectedFile = event.target.files[0];
    //   this.selectedFile = this.$refs.files.selectedFile;
    //   console.log(this.selectedFile);
    // },

    onUpload() {
      const formData = new FormData();
        //formData.append("title", this.title);
        // formData.append("gallery",this.gallery);
        formData.append("gallery",
          new Blob([JSON.stringify(this.gallery)], { type: "application/json" }));
      for (let i = 0; i < this.files.length; i++) {
        formData.append("files",this.files[i]);
        // formData.append("files", new Blob([JSON.stringify(this.files[i])],{type:"application/json"}));
      }
        axios
          .post(`${SERVER_URL}gallery/upload`, formData,
           {  headers: {
              "Content-Type": "multipart/form-data"
            }}
          )
          .then(function() {
            // console.log(gallery);
            console.log(formData);
            console.log("SUCCESS!!");
          })
          .catch(function() {
            console.log("바뀐거야안1221바뀐거야망할");
            console.log("FAILURE!!");
          });
      // }
    }
  },
  created() {
    //고인아이디도 보내줄래?
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