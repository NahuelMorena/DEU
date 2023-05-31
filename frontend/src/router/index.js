import { pinia } from "@/store";
import { AuthStore } from "@/store/auth";
import { ForbiddenError } from "@/store/errors/forbiddenError";
import AuthGeneralViewVue from "@/views/AuthGeneralView.vue";
import LoginViewVue from "@/views/LoginView.vue";
import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
// import { authGuard } from "./guards/auth";

Vue.use(VueRouter);

const routes = [
    {
        path: "/login",
        name: "Login",
        component: LoginViewVue,
    },
    {
        path: "/",
        component: AuthGeneralViewVue,
        children: [
            {
                path: "",
                name: "Home",
                component: HomeView,
            },
            {
                path: "/about",
                name: "About",
                component: () => import("@/views/AboutView.vue"),
            },
            {
                path: "/users",
                name: "Users",
                component: () => import("../views/UsersView"),
            },
            {
                path: "/security",
                name: "Security",
                component: () => import("@/views/SecurityView"),
            },
        ],
    },
];

const router = new VueRouter({
    // mode: "history",
    base: process.env.BASE_URL,
    routes,
});

router.beforeEach((to, from, next) => {
    const authStore = AuthStore(pinia);
    if (!authStore.isLogged() && to.name != "Login") {
        next({ name: "Login" });
    } else if (
        to.name != "Login" &&
        !authStore.hasAuthority("CAN_VIEW_" + to.name)
    ) {
        let forbiddenError = ForbiddenError();
        forbiddenError.open();
        return false;
    } else {
        next();
    }
});

export default router;