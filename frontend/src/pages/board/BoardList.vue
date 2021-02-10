<template>
  <card title="예비게시판" sub-title="고인에게 글">
    <div class="text-right" style="margin-right : 20px">
      <button class="btn btn-dark" @click="insert">등록</button> &nbsp;
    </div>
    <div>
      <p></p>
      <div v-if="lists.length != 0">
        <table class="table table-active">
          <colgroup>
            <col :style="{ width: '5%' }" />
            <col :style="{ width: '50%' }" />
            <col :style="{ width: '10%' }" />
            <col :style="{ width: '25%' }" />
          </colgroup>
          <tr>
            <th colspan="4">번호</th>
            <th colspan="4">제목</th>
            <th colspan="4">작성자</th>
            <th colspan="4">작성날짜</th>
          </tr>
          <list-row
            v-for="list in lists"
            :key="list.board_id"
            :board_id="list.board_id"
            :title="list.title"
            :writer="list.writer"
            :board_dt="list.board_dt"
          />
        </table>
      </div>
      <div v-else>
        <table class="table table-active">
          <tbody>
            <tr class="table-info" align="center">
              <td>작성된 글이 없습니다.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </card>
</template>
<script>
import ListRow from "@/components/board/BoardRow.vue";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardList",
  components: {
    ListRow
  },
  data: function() {
    return {
      lists: {
        board_id: "",
        title: "",
        writer: "",
        board_dt: ""
      }
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/board/makelist`, {
        params: {
          gone_id: "9t8e7s6t",
          user_id: "h4r5z5u9o6p6m"
        }
      })
      .then(res => {
        this.lists = res.data.list;
        console.log(res.data.list);
      })
      .catch(() => {
        console.log("실패");
      });
  },
  methods: {
    insert() {
      this.$router.push("/boardinsert");
    },

  }
};
</script>
<style>
table {
  text-align: center;
  width: 94%;
  margin: 0, 3%;
}
tr {
  height: 30px;
}
#thead {
  height: 50px;
}
</style>
