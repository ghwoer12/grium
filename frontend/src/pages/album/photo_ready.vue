<template>
  <div>
    <input
      style="display:none"
      type="file"
      @change="onFileSelected"
      ref="fileInput"
    />
    <button @click="$refs.fileInput">Pick File</button>
    <button @click="onUpload">Upload</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HelloWorld",
  data() {
    return {
      selectedFile: null
    };
  },
  methods: {
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload() {
      const fd = new FormData();
      axios
        .post("${SERVER_URL}user/signup", fd, {
          onUploadProgress: uploadEvent => {
            console.log(
              "Upload Progress: " +
                Math.round((uploadEvent.loaded / uploadEvent.total) * 100) +
                "%"
            );
          }
        })
        .then(res => {
          console.log(res);
        });
    }
  },
  // axios
  //       .post(`${SERVER_URL}user/signup`, user)
  //       .then(response => {
  //         this.$router.push("/login");
  //       })
  //       .catch(({ message }) => {
  //         this.msg = message;
  //       });
  created() {
    //   this.user.email = this.$store.getters["getEmail"];
    //   this.user.name = this.$store.getters["getName"];
    //   this.user.phone = this.$store.getters["getPhone"];
    //   this.user.password = this.$store.getters["getPassword"];
    //
  }
};
</script>
