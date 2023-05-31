<template>
    <v-layout fill-height>
        <v-row>
            <v-col class="d-flex justify-center align-center">
                <v-card style="width: 500px" v-on:keyup.enter="logearse">
                    <v-card-title primary-title class="mb-7">
                        <div class="text-h4">Iniciar sesion</div>
                    </v-card-title>
                    <v-card-text>
                        <v-text-field
                            v-model:model-value="username"
                            variant="underlined"
                            name="username"
                            label="Nombre de usuario"
                            id="username"
                        ></v-text-field>
                        <v-text-field
                            v-model="password"
                            variant="underlined"
                            type="password"
                            name="password"
                            label="Contraseña"
                            id="password"
                        ></v-text-field>
                        <v-btn block flat color="primary" @click="logearse">
                            Ingresar
                        </v-btn>
                        <v-alert
                            class="mt-3"
                            v-model="loginError"
                            closable
                            text="Error al iniciar sesion"
                            type="error"
                        ></v-alert>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-layout>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { AuthStore } from "../stores/auth";
import { useRouter } from "vue-router";
import axios from "@/axios";

let authStore = null;
let router = null;

let username = ref("");
let password = ref("");
let loginError = ref(false);

onMounted(() => {
    authStore = AuthStore();
    router = useRouter();
});
function logearse() {
    let formdata = new FormData();
    formdata.append("username", username.value);
    formdata.append("password", password.value);
    axios
        .post("/login", formdata)
        .then((response) => {
            authStore.loggedIn(response.data);
            router.push({ name: "Home" });
        })
        .catch((err) => {
            loginError.value = true;
            console.log(err);
        });
}
</script>
