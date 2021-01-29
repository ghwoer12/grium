<template>
  <div>
    <card title="ddd" sub-title="ddd">
      <form>
        <hr />
        <div class="select" style="width:15%">
          <b-form-group id="select2" description="dd" label="dd">
            <b-form-select
              v-model="selected2"
              :options="option2"
            ></b-form-select>
          </b-form-group>
        </div>
        <div class="select" style="width:15%">
          <b-form-group id="kind" description="bbb" label="bb">
            <b-form-select
              v-model="selected1"
              :options="option1"
            ></b-form-select>
          </b-form-group>
        </div>
        <br />
        <div class="searchbtn" style="width:20%">
          <b-form-input
            ref="keyword"
            v-model="keyword"
            placeholder="검색어"
          ></b-form-input>
        </div>
        <div class="searchbtn" style="width:10%">
          <b-button variant="primary" @click="search">검색</b-button>
        </div>
      </form>
      <br />
    </card>
    <card>
      <b-table
        class="sub"
        striped
        hover
        :items="items"
        :fields="fields"
        @row-clicked="rowClick"
        style="width:45%"
      />
      <div class="sub" style="width:45%">
        <house-detail :house="selhouse"></house-detail>
      </div>
    </card>
  </div>
</template>

<script>
import axios from "axios";
import HouseDetail from "./HouseDetail.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data() {
    return {
      keyword: "",
      selected1: "a",
      selected2: "",
      option1: [
        { value: "a", text: "vv" },
        { value: "v", text: "vv" }
      ],
      option2: [
        { value: "", text: "vv" },
        { value: "n", text: "vv" },
        { value: "c", text: "vv" }
      ],
      items: [],
      selhouse: {
        kind: "",
        no: "",
        dong: "",
        bunzi: "",
        name: "",
        area: "",
        amount: "",
        floor: ""
      },
      fields: ["name", "amount"]
    };
  },
  components: {
    HouseDetail
  },
  methods: {
    search: function() {
      if (this.keyword == "") {
        this.$refs.keyword.focus();
      } else {
        axios
          .get(
            `${SERVER_URL}house/${this.selected1}${this.selected2}/${this.keyword}`
          )
          .then(({ data }) => {
            this.items = data;
            console.log(data);
          })
          .catch(() => {
            console.log("catch");
          });
      }
    },
    rowClick(item) {
      if (this.selected1 == "a") {
        this.selhouse.no = item.aptid;
        this.selhouse.kind = "a";
      } else {
        this.selhouse.no = item.villaid;
        this.selhouse.kind = "v";
      }
      this.selhouse.dong = item.dong;
      this.selhouse.bunzi = item.bunzi;
      this.selhouse.name = item.name;
      this.selhouse.area = item.area;
      this.selhouse.amount = item.amount;
      this.selhouse.floor = item.floor;
    }
  }
};
</script>

/**<style>
.select {
  display: inline-block;
  margin-right: 3%;
  vertical-align: top;
}
.searchbtn {
  display: inline-block;
  margin-right: 3%;
  vertical-align: top;
}
.sub {
  display: inline-block;
  margin: 2.5%;
  vertical-align: top;
}
</style>
