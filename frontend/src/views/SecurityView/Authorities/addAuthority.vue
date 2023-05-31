<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar permiso</div>
                    <div>
                        <v-btn icon @click="closeAll">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field
                            label="Nombre del permiso"
                            v-model="form.name"
                            :rules="rules.name"
                        >
                        </v-text-field>
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
        form: {
            name: "",
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
    async mounted() {},
    methods: {
        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        async save() {
            let response = await localAxios.post(
                "/admin/authorities",
                this.form
            );
            let newAuthority = response.data;
            this.$emit("saved", newAuthority);
            this.closeAll();
        },
    },
};
</script>
<style></style>
