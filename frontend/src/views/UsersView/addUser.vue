<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Usuario</div>
                    <div>
                        <v-btn icon @click="closeAll()">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field
                            label="Nombre del usuario"
                            v-model="form.username"
                            :rules="rules.username"
                        >
                        </v-text-field>
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

export default {
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
        showPassword: false,
        allRoles: null,
        form: {
            username: "",
            password: "",
            roles: [],
        },
        rules: {
            username: [(v) => !!v || "Se requiere un nombre de usuario"],
            password: [(v) => !!v || "Se requiere una contraseña"],
        },
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {
        let response = await localAxios.get("/admin/roles");
        this.allRoles = response.data;
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
            let response = await localAxios.post("/admin/users", this.form);
            let newUser = response.data;
            this.$emit("saved", newUser);
            this.closeAll();
        },
    },
};
</script>
<style></style>
