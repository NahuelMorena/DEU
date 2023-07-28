<template>
    <v-app id="inspire" style="background-color: #eee">
        <h1>Deportes App</h1>
        <BlockUiVue />
        <GlobalDialogs />
        <router-view />
    </v-app>
</template>

<script>
import { localAxios } from "./axios";
import BlockUiVue from "./components/BlockUi.vue";
import GlobalDialogs from "./components/dialogs/GlobalDialogs.vue";
import { AuthStore } from "./store/auth";

export default {
    name: "App",
    components: {
        BlockUiVue,
        GlobalDialogs,
    },
    data: () => ({
        authStorage: null,
    }),
    mounted() {
        this.authStorage = AuthStore();
        localAxios.get("/api/loggedUser").then((response) => {
            this.authStorage.loggedIn(response.data);
            this.$router.replace("/");
        });
    },
};
</script>

<style>
.container {
    padding-right: 40px !important;
    padding-left: 40px !important;
    max-width: 100%;
}
.w-50 {
    width: 50% !important;
}
.w-100 {
    width: 100% !important;
}
.column-gap {
    column-gap: 25px;
}
</style>
