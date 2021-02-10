<template>
  <div>
    <board-detail
      :board_id="list.board_id"
      :title="list.title"
      :writer="list.writer"
      :board_dt="list.board_dt"
      :content="list.content"
    />
    <div class="form-group" align="center">
      <!-- <router-link :to="`/boardupdate?board_id=${board_id}`"> -->
      <button class="btn btn-dark button" @click="moveUpdate">
        <!--  -->
        게시글 수정
      </button>
      <!-- </router-link> -->
      <!-- <router-link :to="'/boarddelete?board_id=' + board_id"> -->
        <button class="btn btn-dark button" @click="movedelete">
          게시글 삭제
        </button>
        <!-- </router-link> -->
      <button class="btn btn-dark button" @click="moveList">
        목록으로
      </button>
    </div>
  </div>
</template>
<script>
import BoardDetail from "@/components/board/BoardDetail.vue";
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  props: {
    board_id: { type: Number },
    title: { type: String },
    writer: { type: String },
    board_dt: { type: String },
    content: { type: String }
  },
  name: "boardread",
  components: {
    BoardDetail
  },
  data: function() {
    return {
      list: { board_id: "", title: "", writer: "", board_dt: "", content: "" }
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/board/getboard/${this.$route.query.board_id}`, {
        params: {
          gone_id: "9t8e7s6t",
          user_id: "h4r5z5u9o6p6m"
        }
      })
      .then(res => {
        this.list = res.data.board;
        console.log(res.data.board);
      });
  },
  methods: {
    moveList() {
      this.$router.push("/boardlist");
    },
    moveUpdate() {
      console.log(this.list.board_id);
      let go = this.list.board_id;
      //  단순 URL 파라미터 전달방식
      this.$router.push(`/boardupdate?board_id=${go}`);
      // router.push({name: "BoardUpdate", params: {"board_id": this.list.board_id}});
      // this.$router.push(`/boardupdate?board_id=${go}`);
      //쿼리를 보내는 방식
    },
    moveDelete() {
      console.log(this.list.board_id);
      let go = this.list.board_id;
      //  단순 URL 파라미터 전달방식
      this.$router.push(`/boarddelete?board_id=${go}`);
    }
  }
};
</script>
