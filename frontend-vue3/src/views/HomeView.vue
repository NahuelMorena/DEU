<template>
    <div>
        <div class="mb-5">
            <div class="text-h2">Home</div>
            <div class="text-subtitle-1">Subtitulo de ejemplo</div>
            <v-divider></v-divider>
        </div>
        <v-card>
            <v-card-title primary-title>titulo?</v-card-title>
            <v-card-actions>
                <v-btn flat color="primary" @click="test"
                    >Request de prueba</v-btn
                >
                <v-btn flat color="error" @click="bloqueado"
                    >Request bloqueado</v-btn
                >
            </v-card-actions>
        </v-card>
    </div>
</template>

<script setup>
import { onMounted } from "vue";
import axios from "@/axios";
import { AuthStore } from "../stores/auth";

onMounted(() => {
    axios.get("/api/loggedUser").then((response) => {
        const authStore = AuthStore();
        authStore.loggedIn(response.data);
    });
});

function test() {
    axios.get("/api/test").then(() => {});
}

function bloqueado() {
    axios.get("/api/blocked").then(() => {});
}
</script>
