<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Rol</div>
                    <div>
                        <v-btn icon @click="closeAll" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field
                            label="Nombre del rol"
                            v-model="form.name"
                            :rules="rules.name"
                        >
                        </v-text-field>
                        <v-autocomplete
                            v-model="form.authorities"
                            :items="allAuthorities"
                            label="Permisos"
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
                        aria-label="Guardar"
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
        allAuthorities: null,
        form: {
            name: "",
            authorities: null,
        },
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
        },
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {
        let response = await localAxios.get("/admin/authorities");
        this.allAuthorities = response.data;
    },
    methods: {
        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        remove(authority) {
            const index = this.form.authorities.indexOf(authority);
            if (index >= 0) this.form.authorities.splice(index, 1);
        },
        async save() {
            let response = await localAxios.post("/admin/roles", this.form);
            let newRole = response.data;
            this.$emit("saved", newRole);
            this.closeAll();
        },
    },
};
</script>
<style></style>
