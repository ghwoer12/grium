<template>
  <card title="예비 게시판" sub-title="고인에게 글을 남겨주세요" >
      <div class="text-right" style="margin-right : 20px">
      <button class="btn btn-dark" @click="movePage">등록</button>
      <p></p>
    </div>
    <div>
      <b-table
        striped
        hover
        :items="items"
        :fields="fields"
        @row-clicked="rowClick"
      >
      </b-table>
    </div>
  </card>
</template>
<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: function() {
    return {
      fields: ["글 번호", "제목", "작성자", "날짜"],
      items: []
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/board/list`)
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        // console.log("catch");
      });
  },
  methods: {
    movePage() {
      this.$router.push("/boardinsert");
    },
    // rowClick(items) {
    //   this.$router.push("/qna/read?noq=" + items.noq);
    // }
  }
  // components: {
  //   QLine,
  // },
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
