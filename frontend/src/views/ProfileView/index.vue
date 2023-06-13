<template>
    <div>
        <v-card>
            <v-card-title>Perfil del Usuario</v-card-title>
            <v-card-text v-if="user">
                <v-card-text>
                    <v-form ref="form">
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
                                    :rules="rules.user_type"
                                    v-model="form.user_type"
                                    :items="allTypes"
                                    item-text="name"
                                    item-value="id"
                                    data-vv-name="select"
                                    label="Tipo de jugador"
                                    required
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
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        @click="
                            if ($refs.form.validate()) {
                                save();
                            }
                        "
                        >Guardar</v-btn
                    >
                </v-card-actions>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import { AuthStore } from "@/store/auth";
import { SnackbarStore } from "@/store/snackbar";
import Datepicker from "@/components/datepicker.vue";

export default {
    components: { Datepicker },
    data: () => ({
        user: null,
        authStore: null,
        allRoles: null,
        allTypes: null,
        showPassword: null,
        form: {
            name: "",
            surname: "",
            telephone: "",
            email: "",
            username: "",
            usertype: null,
            password: "",
            birthdate: null,
            roles: [],
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
        this.authStore = AuthStore();
        this.user = this.authStore.user.user;
        let response2 = await localAxios.get("/admin/users/get-types");
        this.allTypes = response2.data;
        this.form = { ...this.user };
        let response = await localAxios.get("/admin/roles");
        this.allRoles = response.data;

        // Buscar el tipo de usuario correspondiente en el array allTypes
        this.form.user_type = this.allTypes.find(
            (type) => type.id === this.user.usertype.id
        );
    },
    methods: {
        async save() {
            this.form.telephone = parseInt(this.form.telephone);
            const role = this.allRoles.find((item) => item.name === "USER");
            this.form.roles.push(role);
            let response = await localAxios.put("/admin/users", this.form);
        },
    },
};
</script>
