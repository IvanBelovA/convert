<template>
  <v-sheet width="300" class="mx-auto" style="margin-top: 7.25rem">
    <v-form fast-fail @submit.prevent>
      <v-text-field
          v-model="name"
          label="Логин"
      ></v-text-field>

      <v-text-field
          v-model="password"
          label="Пароль"
      ></v-text-field>

      <v-btn @click="authenticate()" block class="mt-2">Войти</v-btn>
    </v-form>
  </v-sheet>
  <router-view/>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",

  data () {
    return {
      name: '',
      password: ''
    }
  },

  methods: {
    authenticate () {
      console.log(this.name)
      const user = {
        id: this.name,
        password: this.password
      }

      axios.post('http://localhost:8081/api/v1/users/login', user)
    }
  }
}
</script>

<style scoped>

</style>
