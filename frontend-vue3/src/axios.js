import axios from "axios";
import router from "@/router";
import { AuthStore } from "./stores/auth";
import { UiStore } from "./stores/ui";
import { DialogsStore } from "./stores/dialogs";

function deleteSession() {
    const authStore = AuthStore();
    authStore.deleteLoggedUser();
    router.push("/login");
}

function openForbiddenError() {
    let dialogsStore = DialogsStore();
    dialogsStore.openForbiddenError();
}

function openLoading() {
    const uiStore = UiStore();
    uiStore.openLoading();
}

function closeLoading() {
    const uiStore = UiStore();
    uiStore.closeLoading();
}

const axiosInstance = axios.create({
    baseURL: "http://localhost:8081",
    withCredentials: true,
});

axiosInstance.interceptors.request.use((config) => {
    openLoading();
    return config;
});

axiosInstance.interceptors.response.use(
    (response) => {
        closeLoading();
        return response;
    },
    (error) => {
        if (error.response && error.response.status == 401) {
            deleteSession();
            closeLoading();
            return;
        }
        if (error.response && error.response.status == 403) {
            openForbiddenError();
            closeLoading();
            return;
        }
        if (error.code == "ERR_NETWORK") deleteSession();
        closeLoading();
        throw error;
    }
);

export default axiosInstance;
