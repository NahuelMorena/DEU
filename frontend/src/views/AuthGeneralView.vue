<template>
    <div>
        <v-app-bar app flat clipped-left border>
            <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>Deportes App</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn
                class="black--text"
                aria-label="Cerrar sesion"
                icon
                @click="logout()"
            >
                <v-icon>mdi-logout</v-icon>
            </v-btn>
        </v-app-bar>

        <v-navigation-drawer persistent :mini-variant="drawer" app clipped dark>
            <v-list dense nav v-for="subMenu in subMenuKeys" :key="subMenu">
                <v-subheader>{{ subMenu }}</v-subheader>
                <v-list-item
                    v-for="(option, j) in items[subMenu]"
                    :key="j"
                    link
                    :to="option.url"
                >
                    <v-list-item-icon>
                        <v-icon>{{ option.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ option.name }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-main>
            <router-view />
        </v-main>
    </div>
    <!-- <v-app id="inspire"> -->
    <!-- </v-app> -->
</template>

<script>
import { AuthStore } from "@/store/auth";
import { localAxios } from "@/axios";
export default {
    data: () => ({
        items: [],
        drawer: null,
    }),
    computed: {
        currentRoute: function () {
            return this.$router.currentRoute.value.fullPath;
        },
        subMenuKeys: function () {
            return Object.keys(this.items);
        },
    },
    mounted() {
        localAxios.get("/menu").then((response) => {
            this.items = this.splitInSubGroups(response.data);
        });
    },
    methods: {
        logout() {
            localAxios.post("/logout").then(() => {
                const authStore = AuthStore();
                authStore.deleteSession();
            });
        },
        splitInSubGroups(menuOptions) {
            let menuInSubGroups = [];
            menuOptions.forEach((mp) => {
                if (!menuInSubGroups[mp.subMenu])
                    menuInSubGroups[mp.subMenu] = [];
                menuInSubGroups[mp.subMenu].push(mp);
            });
            return menuInSubGroups;
        },
    },
};
</script>
