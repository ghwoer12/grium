<template>
  <div>
    <card title=" ">
      <div class="col-12" align="center">
        <div class="form-group" align="left">
          <h1>ALERT</h1>
        </div>
      </div>

      <div>
        <table class="b-table">
          <list-row
            v-for="blist in blists"
            :key="blist.board_id"
            :board_id="blist.board_id"
            :name="blist.name"
            :content="blist.content"
            @click="movealert"
          />
        </table>
      </div>
    </card>
  </div>
</template>

<script>
import ListRow from "@/components/alert/AlertRow.vue";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "Alert",
  components: {
    ListRow
  },
  data: function() {
    return {
      blists: {
        name: "",
        board_id: "",
        alert_id: "",
        content: ""
      },
      user_id: ""
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/alert/getalarm`, {
        params: {
          user_id: "h4r5z5u9o6p6m"
        }
      })
      .then(res => {
        this.blists = res.data.blist;
        console.log(res.data.blist);
        console.log(res.data);
      })
      .catch(() => {
        console.log("실패");
      });
  },
  methods: {
    movealert() {
      this.$router.push("/boardlist");
    }
  }
};
</script>
