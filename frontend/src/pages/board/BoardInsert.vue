<template>
  <card title="글 등록">
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
        class="form-control col-sm-5"
        id="writer"
        ref="writer"
        :readonly="true"
        v-model="writer"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="board_dt">날짜</label> &nbsp;
      <input
        type="text"
        class="form-control col-sm-6"
        id="board_dt"
        ref="board_dt"
        placeholder="자동으로 날짜가 들어가게 해"
        v-model="board_dt"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="content">내용</label> &nbsp;
      <textarea
        type="context"
        class="form-control"
        id="content"
        ref="content"
        placeholder="내용을 입력하세요"
        v-model="content"
      ></textarea>
    </div>
    <br />

    <div class="text-right">
      <label>
        <input type="checkbox" id="secret" v-model="secret" /> 비밀글
      </label>
      &nbsp;
      <button class="btn btn-dark" @click="checkHandler">
        등록
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
  name: "boardinsert",
  props: {
    type: { type: String }
  },
  data: function() {
    return {
      gone_id: "9t8e7s6t",
      board_id: "",
      title: "",
      writer: "",
      board_dt: "",
      content: "",
      secret: "",
        name: "",
        user_id: ""
    };
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
      //else this.type == 'create' ? this.createHandler() : this.updateHandler();
      else this.createHandler();
    },

    createHandler() {
      let {
        gone_id,
        board_id,
        title,
        writer,
        board_dt,
        content,
        secret
      } = this;
      let board = {
        gone_id,
        board_id,
        title,
        writer,
        board_dt,
        content,
        secret
      };

      axios
        .post(`${SERVER_URL}/board/insert`, board)
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
    },
    created() {
      this.writer = this.$store.getters["getName"];
    }
  }
};
</script>

<style scoped></style>
