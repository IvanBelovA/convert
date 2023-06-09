<script setup>

import {RouterView} from "vue-router";
</script>
<template>
  <v-container class="mb-6" style="margin-top: 7.25rem">

  <v-row style="">
    <v-col cols="5"></v-col>
    <v-col cols="2"><h1>История</h1></v-col>
    <v-col cols="5"></v-col>
  </v-row>

  <v-row>

    <v-col cols="1" md="0"></v-col>

    <v-col cols="10">
      <v-table table-border-color="black">
        <thead>
        <tr>
          <th class="text-left">
            Целевая валюта
          </th>
          <th class="text-left">
            Исходная валюта
          </th>
          <th class="text-left">
            Исходная сумма
          </th>
          <th class="text-left">
            Получаемая сумма
          </th>
          <th class="text-left">
            Дата
          </th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-if="histories.length > 0"
            v-for="item in histories"
            :key="item.id"
        >
          <td>{{ item.rateFrom.name }}</td>
          <td>{{ item.rateTo.name }}</td>
          <td>{{ item.quantity }}</td>
          <td>{{ item.sum }}</td>
          <td>{{ item.date }}</td>
        </tr>
        </tbody>
      </v-table>
    </v-col>

    <v-col cols="1" md="0"></v-col>
  </v-row>
  </v-container>

  <router-view />
</template>

<script>
import axios from "axios";

export default {

  data () {
    return {
      histories: []
    }
  },

  mounted() {
    axios.get('http://localhost:8081/api/v1/changes/history').then(resp => this.histories = resp.data)
  },

}
</script>

<style scoped>

</style>
