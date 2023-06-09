import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import VueCookies from 'vue-cookies';
import axios from "axios";

const vuetify = createVuetify({
    components,
    directives,
});

const app = createApp(App)

// eslint-disable-next-line no-unused-vars
let user = axios.get('http://localhost:8081/api/v1/users/current')
    .then((resp) => {return resp.data})

app.config.globalProperties.user = user

app.use(router).use(vuetify).use(VueCookies)

app.mount('#app')
