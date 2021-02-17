<template>
  <div class="row">
    <div class="col-8">
      <card class="height">
        <div class="text-center">
          <img :src="goneData.gone.photo_path" alt="" class="mainphoto">
          <br>
          <h3><strong>故 {{ goneData.gone.gone_nm }} 님</strong></h3>
        </div>
        <div class="d-flex text-center">
          <card class="col-4">
            별세 일시 : {{$moment(goneData.gone.death_dt).format('YYYY-MM-DD')}}
          </card>
          <card class="col-4">
            생년 월일 : {{$moment(goneData.gone.birth_dt).format('YYYY-MM-DD')}}
          </card>
          <card class="col-4">
            상주 : {{ goneData.user.name }}
          </card>
        </div>
        <div class="d-flex">
          <div class="col-6">
            <span>장례식장 이름 : {{ goneData.funeral.funeral_nm }}</span>
            <br>
            <span>장레식장 주소 : {{ goneData.funeral.funeral_adr }}</span>
          </div>
          <div class="col-6">
            <span>입금 은행 : {{ goneData.condolance.bank }}</span>
            <br>
            <span>계좌 번호 : {{ goneData.condolance.account }}</span>
            <br>
            <span>예금주 : {{ goneData.condolance.holder }}</span>
          </div>
        </div>
      </card>
    </div>
    <div class="col-4">
      <card class="height">
        <ChatList class="chatheight" :gone_id="gone_id" />
        <ChatInput :gone_id="gone_id" />
      </card>
    </div>
    <div class="col-12">
      <br>
      <card class="text-center">
        <div v-if="goneData.gallery.length == 0">
          <h3>아직 등록된 사진이 없습니다.</h3>
        </div>
        <div v-else>
          <card class="left mr-2" v-for="(gall, idx) in goneData.gallery" :key="idx">
              <img :src="gall.gaddress[0].server_path" alt="" width="150px" height="150px">
              <p>{{ gall.writer }}</p>
          </card>
        </div>
        <router-link :to="`/gallery/list?gone_id=${gone_id}`">
          <button class="btn btn-dark button">
            갤러리 보기
          </button>
        </router-link>
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
    ChatList
  },
  data: function() {
    return {
      goneData: {},
      gone_id: "",
    }
  },
  created() {
    let gone = this.$route.query.gone_id
    this.$store.dispatch('createGone', gone)
    axios
      .get(`${SERVER_URL}gone/list/${this.$store.state.gone_id}`)
      .then(res => {
        console.log(res.data)
        this.goneData = res.data
        this.gone_id = res.data.gone.gone_id
      })
      .catch(() => {
          console.log("실패")
      })
      // $route.query.gone_id
  },
}
</script>

<style>
  .height{
    height: 500px;
  }
  .chatheight{
    height: 400px;
  }
  .left{
    float: left;
  }
  .mainphoto{
    height: 200px;
    width: 200px;
  }
</style>