<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px; height: 700px">
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
                    <v-form ref="form" @submit.prevent="addPlanification">
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
                                <multiselect
                                    :rules="rules.selectedTrainings"
                                    v-model="valueMultiselect"
                                    placeholder="Seleccione entrenamiento"
                                    label="name"
                                    :options="trainings"
                                    :multiple="true"
                                    :close-on-select="false"
                                    @input="onSelect"
                                >
                                </multiselect>
                            </v-col>
                            <v-col cols="6">
                                <draggable
                                    v-model="selectedTrainings"
                                    :element="'ul'"
                                    class="sortable-list"
                                    @end="actualizarOrden"
                                >
                                    <li
                                        v-for="(
                                            item, index
                                        ) in selectedTrainings"
                                        :key="item.id"
                                        :class="{ deleting: item.deleting }"
                                    >
                                        {{ item.training.name }}
                                        <button
                                            class="delete-button"
                                            @click="borrarItem(index)"
                                        >
                                            Borrar
                                        </button>
                                    </li>
                                </draggable>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                                <multiselect
                                    :rules="rules.selectedPlayers"
                                    v-model="valuePlayer"
                                    placeholder="Seleccione jugadores a los que se les asignara"
                                    label="name"
                                    track-by="id"
                                    :options="players"
                                    :multiple="true"
                                    :close-on-select="false"
                                    :taggable="true"
                                >
                                </multiselect>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                                <Datepicker
                                    :rules="rules.date"
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
import { localAxios } from "@/axios";
import Multiselect from "vue-multiselect";
import moment from "moment";
import draggable from "vuedraggable";
import Datepicker from "@/components/datepicker.vue";

export default {
    components: {
        Multiselect,
        Datepicker,
        draggable,
    },
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
        trainings: [],
        players: [],
        selectedPlayers: [],
        valuePlayer: [],
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
            selectedPlayers: [
                (v) => !!v || "Se requieren jugadores a los que asignar",
            ],
            selectedTrainings: [(v) => !!v || "Se requieren entrenamientos"],
            date: [(v) => !!v || "Se requiere una fecha"],
        },

        valueMultiselect: [],
        selectedTrainings: [],
        options: [
            {
                city: "San Martin",
            },
            {
                city: "San Nicolas",
            },
            {
                city: "San Francisco",
            },
        ],
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {
        let response = await localAxios.get("/admin/trainings");
        this.trainings = response.data;
        let response2 = await localAxios.get("/admin/users/get-players");
        this.players = response2.data;
    },
    methods: {
        onSelect() {
            const nuevoObjeto = {
                date: null,
                training: this.valueMultiselect[0],
                orden: this.selectedTrainings.length,
            };
            this.selectedTrainings.push(nuevoObjeto);
            this.valueMultiselect = [];
            console.log(this.selectedTrainings);
        },

        actualizarOrden() {
            this.selectedTrainings.forEach((item, index) => {
                item.orden = index;
            });
            console.log(this.selectedTrainings);
        },

        borrarItem(index) {
            this.selectedTrainings.splice(index, 1);
        },

        closeAll() {
            this.$refs.form.reset();
            this.$emit("input", false);
        },
        getEntrenamientoNombre(id) {
            const entrenamiento = this.trainings.find((e) => e.id === id);
            return entrenamiento ? entrenamiento.name : "";
        },
        async save() {
            const isValid = await this.$refs.form.validate();
            if (isValid) {
                this.form.training_type = this.allTypes.find(
                    (type) => type.id === this.form.training_type
                );
                let response = await localAxios.post(
                    "/admin/planifications",
                    this.form
                );
                let newPlanification = response.data;
                this.$emit("saved", newPlanification);
                console.log(this.form);
                this.closeAll();
            } else {
                alert("Completa todos los campos");
            }
        },
    },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
.sortable-list {
    list-style-type: none;
    padding: 0;
}
li {
    display: flex;
    align-items: center;
    padding: 10px;
    border-bottom: 1px solid #ccc;
    transition: background-color 0.3s ease;
}

li.deleting {
    background-color: #ffeaea;
}

.delete-button {
    margin-left: auto;
    color: #ff5252;
    border: none;
    background: none;
    cursor: pointer;
}

.delete-button:hover {
    color: #d32f2f;
}
</style>
