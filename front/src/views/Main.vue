<script setup>
import {RouterView} from "vue-router";

</script>

<template>
    <v-app-bar
        style=""
        color="black">
      <v-btn @click="this.$router.push('/converter')">Конвертировать</v-btn>
      <v-btn @click="this.$router.push('/history')">История</v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="this.$router.push('/login')">Вход</v-btn>
    </v-app-bar>

  <router-view />
</template>

<style scoped>
</style>
