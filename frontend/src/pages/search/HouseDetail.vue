<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col>name</b-col>
      <b-col>{{ house.name }}</b-col>
    </b-row>
    <b-row>
      <b-col>area</b-col>
      <b-col>{{ house.area }}</b-col>
    </b-row>
    <b-row>
      <b-col>floor</b-col>
      <b-col>{{ house.floor }}</b-col>
    </b-row>
    <b-row>
      <b-col>amount</b-col>
      <b-col>{{ house.amount }}</b-col>
    </b-row>
    <b-row>
      <b-col>bunzi</b-col>
      <b-col>{{ house.bunzi }}</b-col>
    </b-row>
    <img class="housepic" v-if="house.kind === 'a'" :src="aptimg" fluid alt="Responsive image" />
    <img class="housepic" v-if="house.kind === 'v'" :src="villaimg" alt="Responsive image" />
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      aptimg: require('../../assets/img/apt.jpg'),
      villaimg: require('../../assets/img/villa.jpg'),
    };
  },
  props: {
    house: { type: Object },
  },
};
</script>
<style>
.housepic {
  width: 300px;
  height: 300px;
}
</style>
