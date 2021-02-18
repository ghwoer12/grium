<template>
  <div>
    <card title=" ">
      <div class="col-12" align="center">
        <div class="form-group" align="left">
          <h1>ALERT</h1>
        </div>
      </div>

      <div>
        <table class="b-table" >
          <bAlert
            v-for="blist in blists"
            :key="blist.board_id"
            :board_id="blist.board_id"
            :name="blist.name"
            :content="blist.content"
          />
        </table>
        <table class="b-table">
          <pAlert
            v-for="plist in plists"
            :key="plist.photo_id"
            :photo_id="plist.photo_id"
            :name="plist.name"
          />
        </table>
      </div>
    </card>
  </div>
</template>

<script>
import bAlert from "@/components/alert/bAlertRow.vue";
import pAlert from "@/components/alert/pAlertRow.vue"
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "Alert",
  components: {
    bAlert,
    pAlert
  },
  data: function() {
    return {
      blists: {
        name: "",
        board_id: 0,
        content: "",
      },
      plists: {
        name: "",
        photo_id: 0,
      },
      user_id: "",
      blength:"",
      plength:""
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/alert/getalarm`, {
        params: {
          user_id: "a9x2o6g2z2y6s"
        }
      })
      .then(res => {
        this.blists = res.data.blist;
        this.blength = res.data.blist.length;
        console.log(res.data.blist);
        console.log(this.blength);

        this.plists = res.data.plist;
        this.plength = res.data.plist.length;
        console.log(res.data.plist);
        console.log(this.plength);
      })
      .catch(() => {
        console.log("실패");
      });
  }
};
</script>
