<template>
  <div>
    <card title="예비게시판" sub-title="고인에게 전하고픈 말">
      <div class="text-right" style="margin-right : 20px">
        <button class="btn btn-dark" @click="insert">등록</button> &nbsp;
      </div>
      <div>
        <p></p>
        <div v-if="lists.length != 0">
          <table class="table table-active">
            <list-row
              v-for="list in lists"
              :key="list.board_id"
              :board_id="list.board_id"
              :title="list.title"
              :writer="list.writer"
              :board_dt="list.board_dt"
              id="my-table"
              :per-page="paginations.listSize"
              :current-page="currentPage"
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
      <b-pagination
        v-model="currentPage"
        @page-click="MovePage"
        :total-rows="rows"
        :per-page="paginations.listSize"
        aria-controls="my-table"
        align="center"
      >
      </b-pagination>
    </card>
  </div>
</template>
<script>
import ListRow from "@/components/board/BoardRow.vue";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardList",
  computed: {
    rows() {
      return this.paginations.listCnt;
    }
  },
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
      },
      paginations: {
        listSize: "",
        startPage: "",
        listCnt: ""
      },
      currentPage: 1,
      perPage: "",
      user_id:"",
      gone_id:""
    };
  },
  created() {
    this.user_id = this.$store.getters["getUserid"];
    this.gone_id = this.$store.getters["getGone"];

    axios
      .get(`${SERVER_URL}/board/makelist`, {
        params: {
          gone_id: this.gone_id,
          user_id: this.user_id
        }
      })
      .then(res => {
        this.lists = res.data.list;
        this.paginations = res.data.pagination;
        console.log(res.data.list);
        console.log(res.data.pagination);
      })
      .catch(() => {
        console.log("실패");
      });
  },
  methods: {
    insert() {
      this.$router.push("/boardinsert");
    },
    MovePage: function(page) {
      console.log("안녕안녕");
      console.log(this.currentPage);

      this.currentPage = page;
      console.log(page);

      axios
        .get(`${SERVER_URL}/board/list/${this.currentPage}`, {
          params: {
            gone_id: this.gone_id,
            user_id: this.user_id
          }
        })
        .then(res => {
          this.lists = res.data.list;
          console.log(res.data.list);
        })
        .catch(() => {
          console.log("실패");
        });
    }
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
