import { defineStore } from "pinia";
import { StoreConfig } from "@/store/store";

export const SnackbarStore = defineStore("snackbar", {
    state: () => ({
        alerts: [],
    }),
    actions: {
        open(text) {
            let storeConfig = StoreConfig();
            let alert = {
                text,
                color: storeConfig.$state.colorPalette.background,
            };
            this.alerts.push(alert);
            setTimeout(() => {
                this.close(alert);
            }, 2000);
        },
        close(alert) {
            const index = this.alerts.indexOf(alert);
            if (index >= 0) this.alerts.splice(index, 1);
        },
    },
});
