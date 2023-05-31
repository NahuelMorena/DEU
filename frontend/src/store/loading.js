import { defineStore } from "pinia";

export const LoadingStore = defineStore("loading", {
    state: () => ({
        loading: 0,
    }),
    getters: {
        showLoading: (state) => state.loading > 0,
    },
    actions: {
        openLoading() {
            this.loading++;
        },
        closeLoading() {
            this.loading--;
        },
    },
});
