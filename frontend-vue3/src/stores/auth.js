import { defineStore } from "pinia";
import { useStorage } from "@vueuse/core";
export const AuthStore = defineStore("auth", {
    state: () => ({
        _user: useStorage("user", null),
    }),
    getters: {
        user: (state) => JSON.parse(state._user),
    },
    actions: {
        loggedIn(user) {
            this._user = JSON.stringify(user);
        },
        deleteLoggedUser() {
            this._user = null;
        },
        hasAuthority(authority) {
            return (
                this.user.authorities
                    .map((a) => a.authority)
                    .find((a) => a == authority.toUpperCase()) != null
            );
        },
    },
});
