<template>
    <div>
        <HeaderComponent title="Configuraciones" />
        <v-container class="general-padding">
            <v-card>
                <v-card-title> Configuraciones </v-card-title>
                <v-card-text>
                    <v-form
                        @submit.prevent="updateColors"
                        v-if="storeConfig && snackbarStore"
                    >
                        <v-row align="center">
                            <v-col cols="6">
                                <div
                                    class="color-square"
                                    :style="{ backgroundColor: background }"
                                    @click="
                                        showBackgroundPicker =
                                            !showBackgroundPicker
                                    "
                                ></div>
                                <v-color-picker
                                    v-model="background"
                                    v-if="showBackgroundPicker"
                                ></v-color-picker>
                            </v-col>
                            <v-col cols="6">
                                <div
                                    class="color-square"
                                    :style="{ backgroundColor: text }"
                                    @click="showTextPicker = !showTextPicker"
                                ></div>
                                <v-color-picker
                                    v-model="text"
                                    v-if="showTextPicker"
                                ></v-color-picker>
                            </v-col>
                        </v-row>
                        <v-btn color="primary" type="submit">Guardar</v-btn>
                    </v-form>
                </v-card-text>
            </v-card>
        </v-container>
    </div>
</template>
<script>
import { StoreConfig } from "@/store/store";
import { SnackbarStore } from "@/store/snackbar";
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
    components: {
        HeaderComponent,
    },
    data: () => ({
        background: "",
        text: "",
        storeConfig: null,
        snackbarStore: null,
        showBackgroundPicker: false,
        showTextPicker: false,
    }),

    mounted() {
        this.snackbarStore = SnackbarStore();
        this.storeConfig = StoreConfig();
        console.log(this.storeConfig.$state.colorPalette.background);
        console.log(this.storeConfig.$state.colorPalette.text);
        this.background = this.storeConfig.$state.colorPalette.background;
        this.text = this.storeConfig.$state.colorPalette.text;
    },
    methods: {
        updateColors() {
            console.log(this.background);
            if (this.background && this.text) {
                this.storeConfig.setColorPalette(this.background, this.text);
                this.snackbarStore.open("Guardado", "green");
            }
        },
    },
};
</script>

<style>
.color-square {
    width: 50px;
    height: 50px;
    margin-top: 20px;
    border-radius: 4px;
    cursor: pointer;
    border: 2px solid black;
}
</style>
