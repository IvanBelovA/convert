<script setup>
import {RouterView} from "vue-router";

</script>

<template>
  <v-container style="margin-top: 7.25rem" v-if="currencies">
    <v-row>
      <v-col cols="12" md="3"></v-col>
      <v-col cols="12" md="3">
        <v-select
            label="Выберите исходную"
            item-title="name"
            :items="currencies"
            v-model="selectedFrom"
            return-object
        ></v-select>
      </v-col>

      <v-col cols="12" md="3">
        <v-select
            item-title="name"
            label="Выберите целевую"
            v-model="selectedTo"
            :items="currencies"
            return-object
        ></v-select>
      </v-col>
      <v-col cols="12" md="3"></v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="3"></v-col>
      <v-col cols="12" md="3">
        <v-text-field
            v-model="quantity"
            :rules="nameRules"
            label="Количество"
            required
        ></v-text-field>
      </v-col>

      <v-col cols="12" md="3">
        <v-text-field
            disabled
            v-model="sum"
            :counter="10"
            label="Итог"
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="3"></v-col>
    </v-row>

    <v-row>
      <v-col cols="12" md="5"></v-col>
      <v-col cols="12" md="2">
        <v-btn color="black" @click="convert()">Конвертировать</v-btn>
      </v-col>
      <v-col cols="12" md="5"></v-col>
    </v-row>

  </v-container>
  <router-view />
</template>
<script>
import axios from "axios";
import {toRaw} from "vue";

export default {

  data () {
    return {
      selectedTo: null,
      selectedFrom: null,
      quantity: 1,
      sum: 15,
      currencies: [],
    }
  },

  mounted() {
    axios.get('http://localhost:8081/api/v1/currencies').then(resp => this.currencies = resp.data)
  },

  methods: {
    convert () {
      const to = toRaw(this.selectedTo)
      const from = toRaw(this.selectedFrom)

      let sum = 0;
      axios.get('http://localhost:8081/api/v1/exchanges',
          {params: {currencyFrom: from.id, currencyTo: to.id, quantity: this.quantity}}).then((resp) => {
            console.log(resp.data)
            sum = resp.data
      })
      sum = sum / 100

      this.sum = sum.toFixed(2)
    }
  }

}
</script>
<style scoped>

</style>
