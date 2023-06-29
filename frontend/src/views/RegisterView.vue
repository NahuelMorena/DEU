<template>
    <v-layout fill-height>
        <v-col class="d-flex justify-center align-center">
            <v-card style="width: 500px" v-on:keyup.enter="logearse">
                <v-card-title primary-title class="mb-7">
                    <div class="text-h4">Registrarse</div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form">
                        <v-row>
                            <v-col cols="12">
                                <v-select
                                    :rules="rules.trainer"
                                    v-model="form.trainer"
                                    :items="allTrainers"
                                    item-text="name"
                                    item-value="id"
                                    data-vv-name="select"
                                    label="Entrenador"
                                    required
                                    return-object
                                ></v-select>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    label="Nombre"
                                    v-model="form.name"
                                    :rules="rules.name"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    label="Apellido"
                                    v-model="form.surname"
                                    :rules="rules.surname"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <Datepicker
                                    v-model="form.birthdate"
                                    label="Fecha de nacimiento"
                                ></Datepicker>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    label="Telefono"
                                    v-model="form.telephone"
                                    :rules="rules.telephone"
                                    type="number"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    label="Email"
                                    v-model="form.email"
                                    :rules="rules.email"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-select
                                    :rules="rules.usertype"
                                    v-model="form.usertype"
                                    :items="allTypes"
                                    item-text="name"
                                    item-value="id"
                                    data-vv-name="select"
                                    label="Tipo de jugador"
                                    required
                                    return-object
                                ></v-select>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    label="Nombre del usuario"
                                    v-model="form.username"
                                    :rules="rules.username"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.password"
                                    :append-icon="
                                        showPassword ? 'mdi-eye' : 'mdi-eye-off'
                                    "
                                    :rules="rules.password"
                                    :type="showPassword ? 'text' : 'password'"
                                    label="Contraseña del usuario"
                                    counter
                                    @click:append="showPassword = !showPassword"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                    </v-form>
                    <v-card-actions class="d-flex justify-end">
                        <v-btn
                            block
                            color="primary"
                            class="elevation-0"
                            @click="register"
                        >
                            Ingresar
                        </v-btn>
                    </v-card-actions>
                    <div class="text-center mt-3">
                        ¿Ya tienes cuenta?
                        <a href="#" @click="login()" class="register-link"
                            >Ingresar</a
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
</template>
<script>
import { localAxios } from "@/axios";
import { AuthStore } from "../store/auth";
import Datepicker from "@/components/datepicker.vue";
export default {
    components: { Datepicker },
    data: () => ({
        loginError: false,
        showPassword: null,
        allTypes: [],
        allTrainers: [],
        form: {
            name: "",
            surname: "",
            telephone: "",
            email: "",
            username: "",
            usertype: null,
            password: "",
            birthdate: null,
            trainer: null,
        },
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
            surname: [(v) => !!v || "Se requiere un apellido"],
            telephone: [(v) => !!v || "Se requiere un telefono"],
            email: [
                (v) => !!v || "Se requiere un email",
                (v) =>
                    /^([\w-.]+@([\w-]+\.)+[\w-]{2,})?$/.test(v) ||
                    "El email debe tener un formato válido",
            ],
            birthdate: [(v) => !!v || "Se requiere una fecha de nacimiento"],
            username: [(v) => !!v || "Se requiere un nombre de usuario"],
            password: [(v) => !!v || "Se requiere una contraseña"],
        },
    }),
    async mounted() {
        let response2 = await localAxios.get("/admin/users/get-types");
        this.allTypes = response2.data;
        let responseTrainers = await localAxios.get(
            "/admin/users/get-trainers"
        );
        this.allTrainers = responseTrainers.data;
        console.log(responseTrainers.data);
    },
    methods: {
        register() {
            // let formdata = new FormData();
            // formdata.append("username", this.username);
            // formdata.append("password", this.password);
            localAxios
                .post("/register", this.form)
                .then((response) => {
                    console.log(response);
                    if (response.data) {
                        alert("Tu solicitud ha sido enviada al profesor");
                    } else {
                        alert(
                            "Este email/username ya hizo el pedido de registrarse o ya esta registrado"
                        );
                    }
                })
                .catch((err) => {
                    this.loginError = true;
                    console.log(err);
                });
        },
        login() {
            this.$router.push("/login");
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
