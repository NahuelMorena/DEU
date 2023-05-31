import { defineStore } from "pinia";
import { AuthStore } from "../auth";

export const Unexpected = defineStore("unexpected", {
    state: () => ({
        value: false,
    }),
    actions: {
        open() {
            this.value = true;
        },
        close() {
            let authStore = AuthStore();
            this.value = false;
            authStore.deleteSession();
        },
    },
});
