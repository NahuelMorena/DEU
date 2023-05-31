<template>
    <v-dialog
        v-model="dialogLocal"
        persistent
        style="width: initial !important"
    >
        <v-card color="warning" width="500px" dark>
            <v-card-title class="d-flex justify-space-between align-center">
                <div>Se vencio la sesion</div>
                <div>
                    <v-btn text icon @click="sessionExpiredStore.close()">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </div>
            </v-card-title>
            <v-card-text class="d-flex justify-between align-center">
                <v-icon class="text-h1">mdi-alert-circle</v-icon>
                <div class="ml-4 white--text">
                    <div>
                        Es necesario volver a iniciar sesion en el sistema.
                    </div>
                </div>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import { SessionExpired } from "@/store/errors/sessionExpired";

export default {
    data: () => ({
        dialogLocal: false,
        sessionExpiredStore: null,
    }),
    mounted() {
        this.sessionExpiredStore = SessionExpired();
    },
    computed: {
        dialog: function () {
            return this.sessionExpiredStore
                ? this.sessionExpiredStore.value
                : false;
        },
    },
    watch: {
        dialog: function (val) {
            this.dialogLocal = val;
        },
    },
};
</script>
