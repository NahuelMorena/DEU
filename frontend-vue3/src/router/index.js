import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterViewVue from "@/views/RegisterView.vue";
import AuthGeneralView from "../views/AuthGeneralView.vue";
import { authGuard, loginAuthGuard } from "./guards/auth";
import { AuthStore } from "../stores/auth";
import { DialogsStore } from "../stores/dialogs";
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/login",
            name: "Login",
            component: LoginView,
            beforeEnter: loginAuthGuard,
        },
        {
            path: "/register",
            name: "Register",
            component: RegisterViewVue,
        },  
        {
            path: "/",
            name: "AuthRoutes",
            component: AuthGeneralView,
            beforeEnter: [authGuard],
            children: [
                {
                    path: "",
                    name: "Home",
                    component: HomeView,
                },
                {
                    path: "about",
                    name: "About",
                    component: () => import("../views/AboutView.vue"),
                },
                {
                    path: "admin",
                    name: "AdminRoutes",
                    children: [
                        {
                            path: "users",
                            name: "AdminUsers",
                            component: () =>
                                import("../views/admin/AdminUsers.vue"),
                        },
                    ],
                },
            ],
        },
    ],
});
router.beforeEach((to, from) => {
    const authStore = AuthStore();
    if (to.name != "Login" && !authStore.hasAuthority("CAN_VIEW_" + to.name)) {
        let dialogsStore = DialogsStore();
        dialogsStore.openForbiddenError(from.fullPath);
        return false;
    }
});
export default router;
