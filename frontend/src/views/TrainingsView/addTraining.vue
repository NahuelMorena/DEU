<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" v-if="authStore" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Entrenamiento</div>
                    <div>
                        <v-btn icon @click="closeAll()">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-form ref="form" @submit.prevent="addTraining">
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.breakfast"
                                    label="Desayuno"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.lunch"
                                    label="Almuerzo"
                                    required
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.snack"
                                    label="Merienda"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                    v-model="form.dinner"
                                    label="Cena"
                                    required
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                                <Datepicker
                                    v-model="form.date"
                                    :label="'Fecha'"
                                ></Datepicker>
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
                        >Agregar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
import { AuthStore } from "@/store/auth";
import { localAxios } from "@/axios";
import moment from "moment";
import Datepicker from "@/components/datepicker.vue";

export default {
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
        authStore: null,
        //showPassword: false,
        allTrainings: null,
        usuarioLogeado: null,
        form: {
            breakfast: "",
            snack: "",
            lunch: "",
            dinner: "",
            date: moment().format("YYYY-MM-DD"),
        },
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {
        this.authStore = AuthStore();
        this.form.id_user = this.authStore.user.user.id;

        let response = await localAxios.get("/admin/trainings");
        this.allTrainings = response.data;
    },
    methods: {
        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        async save() {
            let response = await localAxios.post("/admin/trainings", this.form);
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
