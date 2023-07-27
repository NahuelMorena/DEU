<template>
    <v-container>
        <div class="googlefont" :style="getTitleSizeStyle()">
            {{ title }}
        </div>
    </v-container>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@350&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Didact+Gothic&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Red+Hat+Display:wght@350&display=swap");
.googlefont {
    font-size: 50px;
    font-family: "Didact Gothic", sans-serif;
}
</style>

<script>
import { defineComponent } from "vue";
import { StoreConfig } from "@/store/store";
export default {
    data: () => ({
        storeConfig: null,
    }),
    props: {
        title: {
            type: String,
            required: true,
        },
    },
    mounted() {
        this.storeConfig = StoreConfig();
    },
    methods: {
        getTitleSizeStyle() {
            let size = 30 + this.storeConfig.$state.colorPalette.titleSize;
            return {
                fontSize: size + "px",
            };
        },
    },
};
</script>
