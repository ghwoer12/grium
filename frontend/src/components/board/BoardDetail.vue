<template>
<card title="글 상세">
  <div class="container" align="center">
    <div class="col-lg-6" align="center">
      <div class="form-group" align="left">
        <label for=""><strong>글번호 </strong></label>
        <p>{{ board_id }}</p>
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>작성자 </strong></label>
        <p>{{ writer }}</p>
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>작성일 </strong></label>
        <p>{{ board_dt }}</p>
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>제목 </strong></label>
        <p>{{ title }}</p>
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>내용 </strong></label>
        <p>{{ content }}</p>
      </div>
    </div>
  </div>
</card>
</template>

<script>
export default {
  name: "boarddetail",
  props: {
    board_id: { type: Number },
    title: { type: String },
    writer: { type: String },
    board_dt: { type: String },
    content: { type: String }
  }
  
};
</script>
<style>
.button {
  margin: 0 2pt;
}
</style>
