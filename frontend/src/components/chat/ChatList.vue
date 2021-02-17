<template>
  <div class="row">
    <tr class="b-table" v-for="(chat, idx) in chatList" :key="idx">
      <td>
        <b-col sm>
          <img class="" v-if="chat.flower == 1" src="@/assets/img/condol_basic.png" alt="">
          <img class="" v-else-if="chat.flower == 2" src="@/assets/img/black-ribbon.png" alt="">
          <img class="" v-else src="@/assets/img/wreath.png" alt="">
        </b-col>
      </td>
      <td>
        <b-col sm>
          {{ chat.writer }}
        </b-col>
      </td>
      <td>
        <b-col sm>
          {{ chat.content }}
        </b-col>
      </td>
    </tr>
    <!-- <div class="col-12 d-flex" v-for="(chat, idx) in chatList" :key="idx">
       <div class="">
        <img class="" v-if="chat.flower == 1" src="@/assets/img/condol_basic.png" alt="">
        <img class="" v-else-if="chat.flower == 2" src="@/assets/img/black-ribbon.png" alt="">
        <img class="" v-else src="@/assets/img/wreath.png" alt="">
      </div>
      <div class="">{{ chat.writer }} | </div> 
      <div class="">{{chat.content }}</div>
    </div> -->
  </div>
</template>

<script>
import axios from "axios";
import Card from '../Cards/Card.vue';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: { Card },
  name: "ChatList",
  props: {
    gone_id: String,
  },
  data() {
    return {
      chatList: {},
    }
  },
  created() {
    setTimeout(() => {
      axios
      .get(`${SERVER_URL}chat/list`, {
          params: {gone_id:this.gone_id}
      })
      .then(res => {
        console.log(res)
        this.chatList = res.data.chatList
      })
      .catch(() => {
        console.log("채팅 목록 가져오기 실패")
      })
    }, 100);
  }
}
</script>

<style>
  .parent{
    display:flex;
  }
  .child{
    flex: 1;
  }
</style>