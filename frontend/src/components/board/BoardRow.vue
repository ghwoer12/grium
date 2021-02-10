<template>
  <tr class="table-info">
    <td colspan="4" style="background-color: white">
      <router-link :to="`/boardread?board_id=${board_id}`">
        <strong>{{ board_id }}</strong>
      </router-link>
    </td>
    <td colspan="4">
      <router-link :to="`/boardread?board_id=${board_id}`">
        <strong>{{ title }}</strong>
      </router-link>
    </td>
    <td colspan="4" style="background-color: white">
      <strong>{{ writer }}</strong>
    </td>
    <td colspan="4">
      <strong>{{ board_dt }}</strong>
    </td>
  </tr>
</template>

<script>
export default {
  name: "boardrow",
  props: {
    board_id: { type: Number },
    title: { type: String },
    writer: { type: String },
    board_dt: { type: String },
    content: { type: String }
  }
};
</script>
