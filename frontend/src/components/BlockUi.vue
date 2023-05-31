<template>
    <div class="text-center" id="BlockUi">
        <v-layout
            id="loadingLayout"
            v-if="loading"
            class="d-felx flex-column align-center justify-center"
            fill-height
        >
            <v-progress-circular
                indeterminate
                size="64"
                color="primary"
            ></v-progress-circular>
            <p class="mt-4 font-weight-medium text-deep-purple-darken-2">
                Cargando . . .
            </p>
        </v-layout>
    </div>
</template>

<script>
import { LoadingStore } from "@/store/loading";

export default {
    data: () => ({
        loadingStore: null,
    }),
    mounted() {
        this.loadingStore = LoadingStore();
    },
    computed: {
        loading: function () {
            return this.loadingStore ? this.loadingStore.showLoading : false;
        },
    },
};
</script>
<style>
#loadingLayout {
    width: 100%;
    height: 100%;
    position: fixed !important;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    z-index: 9000 !important;
    background-color: rgba(255, 255, 255, 0.75);
    backdrop-filter: blur(6px);
    overflow: hidden;
}
</style>
