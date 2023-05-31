import { defineStore } from "pinia";
import router from "@/router";
export const AuthStore = defineStore("auth", {
    state: () => ({
        user: null,
    }),
    getters: {},
    actions: {
        isLogged() {
            return this.user != null;
        },
        loggedIn(user) {
            this.user = user;
        },
        deleteSession() {
            this.user = null;
            router.push("Login");
        },
        hasAuthority(authority) {
            return (
                this.user &&
                this.user.authorities
                    .map((a) => a.authority)
                    .find((a) => a == authority.toUpperCase()) != null
            );
        },
    },
});
