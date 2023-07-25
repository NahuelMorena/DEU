<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Jugador</div>
                    <div>
                        <v-btn icon @click="closeAll()" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form" @submit.prevent="addPlayer">
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
                        aria-label="Guardar"
                        class="custom-add-button"
                        @click="
                            if ($refs.form.validate()) {
                                save();
                            }
                        "
                        >Guardar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
import { localAxios } from "@/axios";
import Datepicker from "@/components/datepicker.vue";
import { AuthStore } from "@/store/auth";
import moment from "moment";

export default {
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
        showPassword: false,
        allRoles: null,
        allTypes: null,
        authStore: null,
        form: {
            name: "",
            surname: "",
            telephone: "",
            email: "",
            trainer: "",
            username: "",
            usertype: null,
            password: "clave",
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
            usertype: [(v) => !!v || "Se requiere la seleccion del tipo"],
            password: [(v) => !!v || "Se requiere una contraseña"],
        },
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {
        this.authStore = AuthStore();
        let response = await localAxios.get("/admin/roles");
        this.allRoles = response.data;
        let response2 = await localAxios.get("/admin/users/get-types");
        this.allTypes = response2.data;
    },
    methods: {
        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        remove(role) {
            const index = this.form.roles.indexOf(role);
            if (index >= 0) this.form.roles.splice(index, 1);
        },
        async save() {
            if (Number.isInteger(this.form.usertype)) {
                this.form.usertype = this.allTypes.find(
                    (usertype) => usertype.id === this.form.usertype
                );
            }
            this.form.trainer = this.authStore.user.user;
            this.form.telephone = parseInt(this.form.telephone);
            let response = await localAxios
                .post("/admin/users", this.form)
                .then((response) => {
                    if (response.data != null) {
                        alert("Se creo con exito el jugador");
                    } else {
                        alert("Ya existia una cuenta con ese username/email");
                    }
                });
            let newUser = response.data;
            this.$emit("saved", newUser);
            this.closeAll();
        },
    },
    components: { Datepicker },
};
</script>
<style>
.custom-add-button {
    margin-top: -10px;
}
</style>
