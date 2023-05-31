import axios from "axios";
import { ErrorManagment } from "./ErrorManagment";
import { closeLoading, openLoading } from "./functions";

let localAxios = axios.create({
    baseURL: "http://localhost:8081",
    withCredentials: true,
});

localAxios.interceptors.request.use((config) => {
    openLoading();
    return config;
});

localAxios.interceptors.response.use((response) => {
    closeLoading();
    return response;
}, ErrorManagment);

export { localAxios };
// export default axiosInstance;
