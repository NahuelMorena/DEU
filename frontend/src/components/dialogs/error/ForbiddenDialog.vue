<template>
    <v-dialog
        v-model="dialogLocal"
        persistent
        style="width: initial !important"
    >
        <v-card color="error" width="500px" dark>
            <v-card-title class="d-flex justify-space-between align-center">
                <div>Accion no autorizada</div>
                <div>
                    <v-btn text icon @click="forbiddenStore.close()">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </div>
            </v-card-title>
            <v-card-text class="d-flex justify-between align-center">
                <v-icon class="text-h1">mdi-alert-circle</v-icon>
                <div class="ml-4 white--text">
                    <div>No tienes permisos para realizar esta accion.</div>
                    <div>Contacta a un administrador.</div>
                </div>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
import { ForbiddenError } from "@/store/errors/forbiddenError";

export default {
    data: () => ({
        dialogLocal: false,
        forbiddenStore: null,
    }),
    mounted() {
        this.forbiddenStore = ForbiddenError();
    },
    computed: {
        dialog: function () {
            return this.forbiddenStore ? this.forbiddenStore.value : false;
        },
    },
    watch: {
        dialog: function (val) {
            this.dialogLocal = val;
        },
    },
};
</script>
