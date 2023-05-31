<template>
    <v-dialog v-model="dialogLocal" persistent content-class="align-center">
        <v-card color="error" width="500px">
            <v-card-title class="d-flex justify-space-between align-center">
                <div>Se vencio la sesion</div>
                <div>
                    <v-btn
                        flat
                        color="error"
                        icon="mdi-close"
                        @click="dialogStore.closeForbiddenError(false)"
                    ></v-btn>
                </div>
            </v-card-title>
            <v-card-text class="d-flex justify-between align-center">
                <v-icon icon="mdi-alert-circle" class="text-h1"></v-icon>
                <div class="ml-4">
                    <div>
                        Es necesario volver a iniciar sesion en el sistema.
                    </div>
                </div>
            </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script setup>
import { computed, ref, watch } from "vue";
import { DialogsStore } from "@/stores/dialogs";

let dialogStore = DialogsStore();
let dialogLocal = ref(false);
let dialog = computed(() => dialogStore.forbiddenError);
watch(dialog, (newVal) => {
    dialogLocal.value = newVal;
});
</script>
