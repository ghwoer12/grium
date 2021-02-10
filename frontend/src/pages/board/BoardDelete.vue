<template>
    <div>삭제 중... </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
   data: function() {
    return {
        go: this.$route.query.board_id
      // URL 쿼리를 받아 오는 방식
    };
  },
    created(){
    axios
      .delete(`${SERVER_URL}/board/delete`,{
          data:{
              go
          }
      })
      .then(({data}) => {
          let msg = '삭제 처리시 문제가 발생했습니다.';
          if(data === 'success'){
              msg = '삭제가 완료되었습니다.';
          }
          alert(msg);
          this.$router.push('/boardlist')
      })
      .catch(() => {
        console.log("삭제 처리시 에러가 발생했습니다.");
      });
    }
}
</script>