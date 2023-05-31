<template>
    <v-dialog
        v-model="dialogLocal"
        persistent
        style="width: initial !important"
    >
        <v-card color="error" width="500px" dark>
            <v-card-title class="d-flex justify-space-between align-center">
                <div>Ocurrio un error inesperado!</div>
                <div>
                    <v-btn text icon @click="unexpectedStore.close()">
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
import { Unexpected } from "@/store/errors/unexpected";

export default {
    data: () => ({
        dialogLocal: false,
        unexpectedStore: null,
    }),
    mounted() {
        this.unexpectedStore = Unexpected();
    },
    computed: {
        dialog: function () {
            return this.unexpectedStore ? this.unexpectedStore.value : false;
        },
    },
    watch: {
        dialog: function (val) {
            this.dialogLocal = val;
        },
    },
};
</script>
