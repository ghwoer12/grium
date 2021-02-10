<template>
  <card title="글 수정">
    <div class="form-group">
      <label for="list.board_id">글 번호</label> &nbsp;
      <input
        type="number"
        class="form-control"
        id="list.board_id"
        ref="list.board_id"
        v-model="list.board_id"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.title">제목</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="list.title"
        ref="list.title"
        v-model="list.title"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.writer">작성자</label> &nbsp;
      <input
        type="text"
        class="form-control col-sm-5"
        id="list.writer"
        ref="list.writer"
        v-model="list.writer"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.board_dt">날짜</label> &nbsp;
      <input
        type="text"
        class="form-control col-sm-6"
        id="list.board_dt"
        ref="list.board_dt"
        v-model="list.board_dt"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.content">내용</label> &nbsp;
      <textarea
        type="context"
        class="form-control"
        id="list.content"
        ref="list.content"
        v-model="list.content"
      ></textarea>
    </div>
    <br />

    <div class="text-right">
      <label>
        <input type="checkbox" id="secret" v-model="secret" /> 비밀글
      </label>
      &nbsp;
      <button class="btn btn-dark" @click="checkHandler">
        수정
      </button>
      &nbsp;
      <button class="btn btn-dark" @click="claer">
        초기화
      </button>
      &nbsp;
      <button class="btn btn-dark" @click="moveList">목록</button>
      &nbsp;
    </div>
    <br />
  </card>
</template>

<script>
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardUpdate",
  // props: {
  //   board_id: { type: Number },
  //   title: { type: String },
  //   writer: { type: String },
  //   board_dt: { type: String },
  //   content: { type: String }
  // },
  data: function() {
    return {
      list: { board_id: "", title: "", writer: "", board_dt: "", content: "" },
      go: this.$route.query.board_id
      // URL 쿼리를 받아 오는 방식
      // go: this.$route.params.board_id

      // 보낸 쿼리를 받아오는 방식
    };
  },
  created() {
    // let god = this.$route.query.go
    // alert(go);
    axios
      // .get(`${SERVER_URL}/board/callmodi/${this.$route.query.board_id}`
      .get(`${SERVER_URL}/board/callmodi/${this.go}`)
      .then(res => {
        this.list = res.data.board;
        console.log(res.data);
      })
      .catch(() => {
        console.log(this.board_id);
        console.log("에러가 발생했습니다.");
      });
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },
    moveList() {
      this.$router.push("/boardlist");
    },
    clear() {
      this.content = "";
      this.title = "";
    },
    createHandler() {}
  }
};
</script>

<style scoped></style>
