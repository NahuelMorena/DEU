import { defineStore } from "pinia";
import { AuthStore } from "../auth";

export const SessionExpired = defineStore("sessionExpired", {
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
