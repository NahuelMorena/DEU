<template>
    <div>
        <v-app-bar
            app
            flat
            clipped-left
            border
            v-if="storeConfig"
            ref="appBar"
            :style="{
                backgroundColor: storeConfig.$state.colorPalette.background,
                color: storeConfig.$state.colorPalette.text,
            }"
        >
            <v-app-bar-nav-icon
                @click="drawer = !drawer"
                :style="{
                    color: storeConfig.$state.colorPalette.text,
                    marginTop: '0px',
                }"
                aria-label="Menu desplegable izquierdo"
            ></v-app-bar-nav-icon>
            <v-toolbar-title
                aria-label="Pagina principal"
                @click="redirectToHome()"
                :style="getTitleSizeStyle()"
                style="cursor: pointer"
            >
                Deportes App
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-menu offset-y>
                <template v-slot:activator="{ on }">
                    <v-btn
                        v-on="on"
                        class="black--text"
                        aria-label="Abrir Menú"
                        icon
                        :style="{
                            marginTop: '0px',
                        }"
                    >
                        <v-icon
                            :style="{
                                color: storeConfig.$state.colorPalette.text,
                            }"
                            aria-label="Icono de Puntos Verticales"
                            >mdi-dots-vertical
                        </v-icon>
                    </v-btn>
                </template>
                <v-list
                    :style="{
                        backgroundColor:
                            storeConfig.$state.colorPalette.background,
                    }"
                >
                    <v-list-item
                        v-for="(option, j) in items['General']"
                        :key="j"
                        link
                        :to="option.url"
                        :style="{
                            color: storeConfig.$state.colorPalette.text,
                        }"
                    >
                        <v-list-item-icon>
                            <v-icon
                                :style="{
                                    color: storeConfig.$state.colorPalette.text,
                                }"
                                >{{ option.icon }}
                            </v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title
                                class="menu-item-title"
                                :style="getFontSizeStyle()"
                            >
                                {{ option.name }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item
                        @click="logout()"
                        :style="{
                            color: storeConfig.$state.colorPalette.text,
                        }"
                    >
                        <v-list-item-icon>
                            <v-icon
                                :style="{
                                    color: storeConfig.$state.colorPalette.text,
                                }"
                                aria-label="Icono de cerrar sesión"
                            >
                                mdi-logout
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-title
                            class="menu-item-title"
                            :style="getFontSizeStyle()"
                            >Cerrar sesión
                        </v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
        </v-app-bar>

        <v-navigation-drawer
            v-model="drawer"
            ref="navigationDrawer"
            persistent
            v-if="storeConfig"
            :mini-variant="!drawer"
            app
            clipped
            dark
            :style="{
                backgroundColor: storeConfig.$state.colorPalette.background,
                color: storeConfig.$state.colorPalette.text,
            }"
        >
            <v-list dense nav v-for="subMenu in subMenuKeys" :key="subMenu">
                <v-subheader>{{ subMenu }}</v-subheader>
                <v-list-item
                    v-for="(option, j) in items[subMenu]"
                    :key="j"
                    link
                    :to="option.url"
                    :style="{
                        backgroundColor:
                            storeConfig.$state.colorPalette.background,
                        color: storeConfig.$state.colorPalette.text,
                    }"
                >
                    <v-list-item-icon>
                        <v-icon>{{ option.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title
                            class="menu-item-title"
                            :style="getFontSizeStyle()"
                        >
                            {{ option.name }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-main>
            <router-view />
        </v-main>
    </div>
</template>

<script>
import { AuthStore } from "@/store/auth";
import { localAxios } from "@/axios";
import { StoreConfig } from "@/store/store";
export default {
    data: () => ({
        items: [],
        drawer: null,
        storeConfig: null,
    }),
    computed: {
        currentRoute: function () {
            return this.$router.currentRoute.value.fullPath;
        },
        subMenuKeys: function () {
            //return Object.keys(this.items)
            //Esta ignorando la primera key "General"
            return Object.keys(this.items).slice(1);
        },
    },
    mounted() {
        this.storeConfig = StoreConfig();
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
        getTitleSizeStyle() {
            return {
                fontSize: this.storeConfig.$state.colorPalette.titleSize + "px",
            };
        },
        getFontSizeStyle() {
            return {
                fontSize: this.storeConfig.$state.colorPalette.fontSize + "px",
            };
        },
        redirectToHome() {
            this.$router.push("/");
        },
    },
};
///
</script>

<style>
.menu-item-title {
    white-space: normal;
    word-wrap: break-word;
    padding: 4px 0;
}
</style>
