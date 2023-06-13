<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Editar Usuario</div>
                    <div>
                        <v-btn icon @click="closeAll()">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
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
                            <v-col cols="3">
                                <v-text-field
                                    label="Telefono"
                                    v-model="form.telephone"
                                    :rules="rules.telephone"
                                    type="number"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="3">
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
                                    label="Email"
                                    v-model="form.email"
                                    :rules="rules.email"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    label="Nombre del usuario"
                                    v-model="form.username"
                                    :rules="rules.username"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
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
                            <v-col cols="6">
                                <v-autocomplete
                                    v-model="form.roles"
                                    :items="allRoles"
                                    label="Roles"
                                    item-text="name"
                                    return-object
                                    filled
                                    rounded
                                    chips
                                    multiple
                                >
                                    <template v-slot:selection="data">
                                        <v-chip
                                            v-bind="data.attrs"
                                            :input-value="data.selected"
                                            close
                                            @click="data.select"
                                            @click:close="remove(data.item)"
                                        >
                                            {{ data.item.name }}
                                        </v-chip>
                                    </template>
                                    <template v-slot:item="data">
                                        <v-list-item-content>
                                            <v-list-item-title
                                                v-html="data.item.name"
                                            ></v-list-item-title>
                                        </v-list-item-content>
                                    </template>
                                </v-autocomplete>
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
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
import { localAxios } from "@/axios";
import Datepicker from "@/components/datepicker.vue";
import moment from "moment";

export default {
    props: {
        value: { type: Boolean },
        user: { type: Object },
    },
    data: () => ({
        localShow: false,
        showPassword: false,
        allRoles: null,
        allTypes: null,
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
    watch: {
        value: function (val) {
            this.localShow = val;
        },
        user: function (val) {
            this.form = { ...val };
            this.form.birthdate = moment(val.birthdate).format("YYYY-MM-DD");
        },
    },
    async mounted() {
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
            this.form.telephone = parseInt(this.form.telephone);
            let response = await localAxios.put("/admin/users", this.form);
            let newUser = response.data;
            this.$emit("saved", newUser);
            this.closeAll();
        },
    },
    components: { Datepicker },
};
</script>
<style></style>
