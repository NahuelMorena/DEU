<template>
    <v-app id="inspire">
        <v-app-bar app flat>
            <v-app-bar-nav-icon @click="toggleDrawer"></v-app-bar-nav-icon>

            <v-toolbar-title>AppStarter</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon @click="logout()">
                <v-icon>mdi-logout</v-icon>
            </v-btn>
        </v-app-bar>

        <v-navigation-drawer
            app
            theme="dark"
            :rail="!drawer"
            permanent
            style="background-color: rgb(29, 34, 40)"
        >
            <v-list nav>
                <v-list-subheader>General</v-list-subheader>
                <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                    :active="currentRoute == item.router"
                    :prepend-icon="item.icon"
                    :title="item.name"
                    :value="item.name"
                    @click="$router.push(item.router)"
                ></v-list-item>
            </v-list>
            <v-list nav>
                <v-list-subheader>Administracion</v-list-subheader>
                <v-list-item
                    v-for="(item, i) in adminItems"
                    :key="i"
                    :active="currentRoute == item.router"
                    :prepend-icon="item.icon"
                    :title="item.name"
                    :value="item.name"
                    @click="$router.push(item.router)"
                ></v-list-item>
            </v-list>
            <template v-slot:append>
                <div
                    class="text-subtitle-2 pa-2 text-center"
                    style="background-color: rgba(0, 0, 0, 0.7)"
                >
                    <div>v0.0.1</div>
                </div>
            </template>
        </v-navigation-drawer>

        <v-main style="background: #f4f6f9">
            <v-container grid-list-lg fluid class="px-10">
                <!-- <v-breadcrumbs :items="['Foo', 'Bar', 'Fizz']"></v-breadcrumbs> -->
                <router-view />
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import { mapState, mapActions } from "pinia";
import { UiStore } from "@/stores/ui";
import axios from "@/axios";
import { AuthStore } from "../stores/auth";
import router from "@/router";
export default {
    data: () => ({
        items: [
            { name: "Home", router: "/", icon: "mdi-home" },
            { name: "About", router: "/about", icon: "mdi-information" },
        ],
        adminItems: [
            {
                name: "Usuarios",
                router: "/admin/users",
                icon: "mdi-account-multiple",
            },
        ],
    }),
    mounted() {},
    computed: {
        ...mapState(UiStore, ["drawer"]),
        currentRoute() {
            return this.$router.currentRoute.value.fullPath;
        },
    },
    methods: {
        ...mapActions(UiStore, ["toggleDrawer"]),
        logout() {
            axios.post("/logout").then(() => {
                const authStore = AuthStore();
                authStore.deleteLoggedUser();
                router.push("/login");
            });
        },
    },
};
</script>

<style scoped>
.border {
    border: 2px dashed orange;
}
</style>
