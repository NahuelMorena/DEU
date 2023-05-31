import Vue from "vue";
import App from "./App.vue";

// import store from "./store";
import vuetify from "./plugins/vuetify";

import { PiniaVuePlugin } from "pinia";
import { pinia } from "@/store";
import router from "./router";

import "./assets/app.css";

// import VueCompositionAPI from "@vue/composition-api";

Vue.use(PiniaVuePlugin);

// Vue.use(VueCompositionAPI);

Vue.config.productionTip = false;

new Vue({
    vuetify,
    pinia,
    router,
    render: (h) => h(App),
}).$mount("#app");
