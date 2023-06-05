<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Editar Entrenamiento</div>
                    <div>
                        <v-btn icon @click="closeAll()">
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
                                    label="Nombre"
                                    required
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.repetitions_quantity"
                                    label="Cantidad de repeticiones"
                                    type="number"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
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
                                    v-model="form.date"
                                    :label="'Fecha'"
                                ></Datepicker>
                            </v-col>
                            <v-col cols="6">
                                <v-select
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
                                    v-model="form.description"
                                    label="Descripcion"
                                    required
                                ></v-textarea>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        type="submit"
                        @click="save()"
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        >Cargar</v-btn
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
            let response = await localAxios.put("/admin/trainings", this.form);
            let newTraining = response.data;
            this.$emit("saved", newTraining);
            console.log(this.form);
            this.closeAll();
        },
    },
    components: { Datepicker },
};
</script>
<style></style>
