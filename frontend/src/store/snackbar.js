import { defineStore } from "pinia";

export const SnackbarStore = defineStore("snackbar", {
    state: () => ({
        alerts: [],
    }),
    actions: {
        open(text, color = "indigo") {
            let alert = { text, color };
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
