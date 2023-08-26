<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    tabindex="0"
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Editar Entrenamiento</div>
                    <div>
                        <v-btn icon @click="closeAll()" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form" @submit.prevent="addTraining">
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                    v-model="form.name"
                                    :rules="rules.name"
                                    label="Nombre"
                                    required
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    :rules="rules.repetitions_quantity"
                                    v-model="form.repetitions_quantity"
                                    label="Cantidad de repeticiones"
                                    type="number"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    :rules="rules.warmup_time"
                                    v-model="form.warmup_time"
                                    label="Minutos de tiempo de calentamiento"
                                    type="number"
                                    required
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <Datepicker
                                    :rules="rules.date"
                                    v-model="form.date"
                                    :label="'Fecha'"
                                ></Datepicker>
                            </v-col>
                            <v-col cols="6">
                                <v-select
                                    :rules="rules.training_type"
                                    v-model="form.training_type"
                                    :items="allTypes"
                                    item-text="name"
                                    item-value="id"
                                    data-vv-name="select"
                                    label="Tipo de entrenamiento"
                                    required
                                ></v-select>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                                <v-textarea
                                    :rules="rules.description"
                                    v-model="form.description"
                                    label="Descripcion"
                                    required
                                    rows="3"
                                ></v-textarea>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        type="submit"
                        aria-label="Guardar"
                        @click="save()"
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        class="custom-add-button"
                        >Confirmar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
import { localAxios } from "@/axios";
import moment from "moment";
import Datepicker from "@/components/datepicker.vue";

export default {
    props: {
        value: { type: Boolean },
        training: { type: Object },
    },
    data: () => ({
        localShow: false,
        //showPassword: false,
        allTypes: null,
        form: {
            name: "",
            description: "",
            warmup_time: null,
            training_type: null,
            repetitions_quantity: null,
            training: null,
            date: moment().format("YYYY-MM-DD"),
        },
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
            description: [(v) => !!v || "Se requiere una descripcion"],
            warmup_time: [
                (v) => !!v || "Se requiere un tiempo de calentamiento",
            ],
            training_type: [
                (v) => !!v || "Se requiere un tipo de entrenamiento",
            ],
            repetitions_quantity: [
                (v) => !!v || "Se requiere la cantidad de repeticiones",
            ],
            date: [(v) => !!v || "Se requiere una fecha"],
        },
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
        training: function (val) {
            this.form = { ...val };
            this.form.date = moment(val.date).format("YYYY-MM-DD");
        },
    },
    async mounted() {
        let response2 = await localAxios.get("/admin/trainings/get-types");
        this.allTypes = response2.data;
    },
    methods: {
        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        async save() {
            const isValid = await this.$refs.form.validate();
            if (isValid) {
                let response = await localAxios.put(
                    "/admin/trainings",
                    this.form
                );
                let newTraining = response.data;
                this.$emit("saved", newTraining);
                console.log(this.form);
                this.closeAll();
            } else {
                alert("Completa todos los campos");
            }
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
