<template>
    <div role="main">
        <v-layout fill-height>
            <v-col class="d-flex justify-center align-center">
                <v-card style="width: 500px" v-on:keyup.enter="logearse">
                    <v-card-title primary-title class="mb-7">
                        <div class="text-h4" tabindex="0">Iniciar sesion</div>
                    </v-card-title>
                    <v-card-text>
                        <v-text-field
                            v-model="username"
                            name="username"
                            label="Nombre de usuario"
                            id="username"
                        ></v-text-field>
                        <v-text-field
                            v-model="password"
                            type="password"
                            name="password"
                            label="Contraseña"
                            id="password"
                        ></v-text-field>
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            block
                            class="elevation-0"
                            @click="logearse"
                        >
                            Ingresar
                        </v-btn>
                        <div class="text-center mt-3">
                            ¿No tienes una cuenta?
                            <a
                                href="#"
                                class="register-link"
                                @click="registrarse()"
                                >Registrarse</a
                            >
                        </div>
                        <v-alert
                            class="mt-3"
                            v-model="loginError"
                            dismissible
                            type="error"
                        >
                            Error al iniciar sesion
                        </v-alert>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-layout>
    </div>
</template>
<script>
import { localAxios } from "@/axios";
import { AuthStore } from "../store/auth";
export default {
    data: () => ({
        username: "",
        password: "",
        loginError: false,
    }),
    methods: {
        logearse() {
            let formdata = new FormData();
            formdata.append("username", this.username);
            formdata.append("password", this.password);
            localAxios
                .post("/login", formdata)
                .then((response) => {
                    let authStore = AuthStore();
                    authStore.loggedIn(response.data);
                    this.$router.push({ name: "Home" });
                })
                .catch((err) => {
                    this.loginError = true;
                    console.log(err);
                });
        },
        registrarse() {
            this.$router.push("/register");
        },
    },
};
</script>

<style>
.register-link {
    color: #2196f3;
    text-decoration: none;
    font-weight: bold;
}

.register-link:hover {
    text-decoration: underline;
}

.v-btn {
    margin-top: 20px;
}
</style>
