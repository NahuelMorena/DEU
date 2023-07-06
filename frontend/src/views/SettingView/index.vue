<template>
    <div>
        <h1>Configuraciones</h1>
        <form
            @submit.prevent="updateColors"
            v-if="storeConfig && snackbarStore"
        >
            <label>
                Fondo:
                <input type="color" v-model="background" />
            </label>
            <label>
                Texto:
                <input type="color" v-model="text" />
            </label>
            <button type="submit">Guardar</button>
        </form>
    </div>
</template>
<script>
import { StoreConfig } from "@/store/store";
import { SnackbarStore } from "@/store/snackbar";

export default {
    data: () => ({
        background: "",
        text: "",
        storeConfig: null,
        snackbarStore: null,
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
            if (this.background) {
                this.storeConfig.setColorPalette(this.background, this.text);
                this.snackbarStore.open("Se creo");
            }
        },
    },
};
</script>
