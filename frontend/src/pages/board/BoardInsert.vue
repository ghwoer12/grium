<template>
  <card title="등록">
    <div class="form-group">
      <label for="title">제목</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="title"
        ref="title"
        placeholder="제목을 입력하세요"
        v-model="title"
      />
    </div>
    <br />

    <div class="form-group">
      <label for="writer">작성자</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="writer"
        ref="writer"
        placeholder="이름을 입력하세요"
        v-model="writer"
      />
    </div>
    <br />

    <div class="form-group">
      <label for="context">내용</label> &nbsp;
      <textarea
        type="context"
        class="form-control"
        id="context"
        ref="context"
        placeholder="내용을 입력하세요"
        v-model="context"
      ></textarea>
    </div>
    <br />


    <div class="text-right">
      <button
        class="btn btn-dark"
        @click="checkHandler"
      >
        등록
      </button>

      <button class="btn btn-dark" @click="moveList">목록</button>
    </div>
    <br />
  </card>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "board-Form",
  props: {
    type: { type: String }
  },
  data: function() {
    return {
      no: "",
      title: "",
      writer: "",
      date: "",
      context: "",
      click: "",
      flag:""
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.writer &&
        ((msg = "작성자를 입력해주세요"), (err = false), this.$refs.writer.focus());
      err &&
        !this.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.context &&
        ((msg = "내용을 입력해주세요"), (err = false), this.$refs.context.focus());
      if (!err) alert(msg);
      //else this.type == 'create' ? this.createHandler() : this.updateHandler();
      else this.createHandler();
    },

    createHandler() {
      axios
        .post(`${SERVER_URL}/board/insert`, {
        //   noq: this.noq,
        //   userid: this.userid,
        //   name: this.name,
        //   title: this.title,
        //   text: this.text,
        //   regDate: this.regDate
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data > 0) {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    },
    moveList() {
      this.$router.push("/boardlist");
    }
  }
};
</script>

<style scoped></style>
