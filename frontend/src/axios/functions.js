import { LoadingStore } from "@/store/loading";

export function openLoading() {
    const loadingStore = LoadingStore();
    loadingStore.openLoading();
}

export function closeLoading() {
    const loadingStore = LoadingStore();
    loadingStore.closeLoading();
}
