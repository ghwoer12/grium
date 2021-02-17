<template>
  <div class="row">
    <div class="col-8">
      <card>
        <p>장례 정보 들어갈 곳</p>
      </card>
    </div>
    <div class="col-4">
      <card>
        <ChatList :gone_id="gone_id" />
        <ChatInput :gone_id="gone_id" />
      </card>
    </div>
    <div class="col-12">
      <br>
      <br>
      <br>
      <card>
        <p>이미지 들어갈 곳</p>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

import ChatInput from "@/components/chat/ChatInput"
import ChatList from "@/components/chat/ChatList"

export default {
  name: "GoneDetail",
  components: {
    ChatInput,
    ChatList,
  },
  data: function() {
    return {
      goneData: {},
      gone_id: "",
    }
  },
  created() {
    axios
      .get(`${SERVER_URL}gone/list/${this.$route.query.gone_id}`)
      .then(res => {
        console.log(res.data)
        this.goneData = res.data
        this.gone_id = res.data.gone.gone_id
      })
      .catch(() => {
          console.log("실패")
      })
  },
}
</script>

<style>

</style>