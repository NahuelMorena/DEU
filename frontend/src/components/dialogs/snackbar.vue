<template>
    <div class="alertsContainer" v-if="store">
        <v-alert
            v-for="(alert, i) in store.alerts"
            :value="store.show"
            :key="i"
            border="left"
            :color="alert.color"
            dark
            class="alert"
        >
            {{ alert.text }}
            <v-btn icon text @click="store.close(alert)">
                <v-icon>mdi-close</v-icon>
            </v-btn>
        </v-alert>
    </div>
</template>

<script>
// import { mapActions } from "pinia";
import { SnackbarStore } from "@/store/snackbar";

export default {
    data: () => ({
        store: null,
    }),
    mounted() {
        this.store = SnackbarStore();
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
</style>
