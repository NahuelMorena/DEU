import { defineStore } from "pinia";

export const StoreConfig = defineStore("store", {
    state: () => ({
        colorPalette: {
            background: "#fffff",
            text: "#000000",
            textSize: 12,
        },
    }),
    mutations: {
        updateColorPalette(state, { background, text }) {
            state.colorPalette.background = background;
            state.colorPalette.text = text;
        },
    },
    actions: {
        setColorPalette(background, text) {
            this.colorPalette.background = background;
            this.colorPalette.text = text;
        },
    },
});
