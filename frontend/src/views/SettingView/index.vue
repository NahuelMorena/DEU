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
                        <v-row>
                            <v-col cols="6">
                                <div class="color-square-container">
                                    <v-card-subtitle>
                                        Color fondo
                                    </v-card-subtitle>
                                    <div
                                        ref="colorSquare"
                                        class="color-square"
                                        :style="{ backgroundColor: background }"
                                        @click="toggleColorPicker('background')"
                                    ></div>
                                    <v-color-picker
                                        v-model="background"
                                        v-if="showBackgroundPicker"
                                    ></v-color-picker>
                                </div>
                            </v-col>
                            <v-col cols="6">
                                <div class="color-square-container">
                                    <v-card-subtitle>
                                        Color letra
                                    </v-card-subtitle>
                                    <div
                                        ref="textSquare"
                                        class="color-square"
                                        :style="{ backgroundColor: text }"
                                        @click="toggleColorPicker('text')"
                                    ></div>
                                    <v-color-picker
                                        v-model="text"
                                        v-if="showTextPicker"
                                    ></v-color-picker>
                                </div>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-select
                                    v-model="titleSize"
                                    label="Tamaño de titulo"
                                    :items="allSizes"
                                >
                                </v-select>
                            </v-col>
                            <v-col cols="6">
                                <v-select
                                    v-model="fontSize"
                                    label="Tamaño de letras de menu"
                                    :items="allSizes"
                                >
                                </v-select>
                            </v-col>
                        </v-row>
                        <v-card-actions class="d-flex justify-end">
                            <v-btn
                                type="submit"
                                aria-label="Guardar"
                                color="rgba(34, 56, 67, 0.85)"
                                dark
                                >Guardar</v-btn
                            >
                        </v-card-actions>
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
        titleSize: 0,
        fontSize: 0,
        allSizes: [
            { text: "Pequeño", value: 12 },
            { text: "Mediano", value: 16 },
            { text: "Grande", value: 20 },
        ],
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
        this.titleSize = this.storeConfig.$state.colorPalette.titleSize;
        this.fontSize = this.storeConfig.$state.colorPalette.fontSize;
        // Agregar el listener para el evento click en el documento
        document.addEventListener("click", this.handleClickOutside);
    },
    beforeDestroy() {
        // Eliminar el listener del evento click en el documento antes de destruir el componente
        document.removeEventListener("click", this.handleClickOutside);
    },
    methods: {
        updateColors() {
            if (
                this.background &&
                this.text &&
                this.titleSize &&
                this.fontSize
            ) {
                this.storeConfig.setColorPalette(
                    this.background,
                    this.text,
                    this.titleSize,
                    this.fontSize
                );
                this.snackbarStore.open("Guardado", "green");
            }
        },
        toggleColorPicker(type) {
            if (type === "background") {
                this.showBackgroundPicker = !this.showBackgroundPicker;
                this.showTextPicker = false;
            } else if (type === "text") {
                this.showTextPicker = !this.showTextPicker;
                this.showBackgroundPicker = false;
            }
        },
        hideColorPicker(type) {
            if (type === "background") {
                this.showBackgroundPicker = false;
            } else if (type === "text") {
                this.showTextPicker = false;
            }
        },
        handleClickOutside(event) {
            // Comprobar si el clic se produce fuera del cuadro de etiqueta y el selector de color
            const colorSquare = this.$refs.colorSquare;
            const textSquare = this.$refs.textSquare;

            if (
                !colorSquare.contains(event.target) &&
                !textSquare.contains(event.target)
            ) {
                this.showBackgroundPicker = false;
                this.showTextPicker = false;
            }
        },
    },
};
</script>

<style>
.color-square-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%; /* Asegura que el contenedor tenga una altura definida */
}
.color-square {
    width: 50px;
    height: 50px;
    margin-top: 10px;
    border-radius: 4px;
    cursor: pointer;
    border: 2px solid black;
}
</style>
