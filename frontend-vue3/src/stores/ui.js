import { defineStore } from "pinia";

export const UiStore = defineStore("ui", {
    state: () => ({
        drawer: true,
        loading: 0,
    }),
    actions: {
        toggleDrawer() {
            this.drawer = !this.drawer;
        },
        openLoading() {
            this.loading++;
        },
        closeLoading() {
            this.loading--;
        },
    },
});
