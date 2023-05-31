import router from "@/router";
import { defineStore } from "pinia";

export const ForbiddenError = defineStore("forbiddenError", {
    state: () => ({
        value: false,
        returnPath: null,
    }),
    actions: {
        open(url = null) {
            this.value = true;
            this.returnPath = url;
        },
        close() {
            this.value = false;
            if (this.returnPath) {
                router.push(this.returnPath);
                this.returnPath = null;
            }
        },
    },
});
