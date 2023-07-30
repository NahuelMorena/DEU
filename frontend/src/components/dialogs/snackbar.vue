<template>
    <div class="alertsContainer" v-if="store">
        <v-alert
            v-for="(alert, i) in store.alerts"
            :value="store.show"
            :key="i"
            :color="alert.color"
            dark
            class="alert"
        >
            <div
                class="alert-content"
                :style="{
                    color: storeConfig.$state.colorPalette.text,
                }"
            >
                <b>{{ alert.text }}</b>
                <div class="close-button-container">
                    <v-btn icon text @click="store.close(alert)">
                        <v-icon
                            :style="{
                                color: storeConfig.$state.colorPalette.text,
                            }"
                            >mdi-close</v-icon
                        >
                    </v-btn>
                </div>
            </div>
        </v-alert>
    </div>
</template>

<script>
// import { mapActions } from "pinia";
import { SnackbarStore } from "@/store/snackbar";
import { StoreConfig } from "@/store/store";

export default {
    data: () => ({
        store: null,
        storeConfig: null,
    }),
    mounted() {
        this.store = SnackbarStore();
        this.storeConfig = StoreConfig();
    },
    methods: {},
};
</script>

<style scoped>
.alertsContainer {
    display: flex;
    flex-direction: column-reverse;
    position: fixed;
    bottom: 0;
    right: 0;
    margin: 16px;
    z-index: 8000;
}
.alert {
    width: 400px;
}
.alert-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.close-button-container {
    display: flex;
    align-items: center;
}
</style>
