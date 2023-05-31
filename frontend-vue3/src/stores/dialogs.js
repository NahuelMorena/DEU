import { defineStore } from "pinia";
import router from "../router";
export const DialogsStore = defineStore("dialogs", {
    state: () => ({
        forbiddenError: false,
        forbiddenErrorReturnPath: null,
    }),
    actions: {
        openForbiddenError(url = null) {
            this.forbiddenError = true;
            if (url) this.forbiddenErrorReturnPath = url;
        },
        closeForbiddenError() {
            this.forbiddenError = false;
            if (this.forbiddenErrorReturnPath) {
                router.push(this.forbiddenErrorReturnPath);
                this.forbiddenErrorReturnPath = null;
            }
        },
    },
});
