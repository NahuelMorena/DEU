import { AuthStore } from "../../stores/auth";

export function authGuard(to) {
    const authStore = AuthStore();
    if (to.name != "Login" && authStore.user == null) {
        return {
            name: "Login",
        };
    }
}

export function loginAuthGuard(to, from) {
    const authStore = AuthStore();
    if (from.name != "Login" && authStore.user != null) {
        console.log(from, to.name);
        return {
            name: from.name != undefined ? from.name : "Home",
        };
    }
}
