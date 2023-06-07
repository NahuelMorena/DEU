<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px; height: 700px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Planificacion</div>
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
                            <v-col cols="12">
                                <multiselect
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
                        </v-row>
                        <v-row
                            ><v-col cols="12">
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
                                        <div class="item-container">
                                            <input
                                                type="number"
                                                v-model="item.minutes"
                                                class="item-minutes"
                                                min="0"
                                                placeholder="Minutos"
                                            />
                                            <span
                                                class="item-name"
                                                style="padding: 1%"
                                                >{{ item.training.name }}</span
                                            >
                                        </div>
                                        <button
                                            class="delete-button"
                                            @click="borrarItem(index)"
                                        >
                                            Borrar
                                        </button>
                                    </li>
                                </draggable>
                            </v-col></v-row
                        >
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

export default {
    components: {
        Multiselect,
        draggable,
    },
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
        trainings: [],
        players: [],
        form: {
            name: "",
        },
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
            date: [(v) => !!v || "Se requiere una fecha"],
        },

        valueMultiselect: [],
        selectedTrainings: [],
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
                planification: null,
                training: this.valueMultiselect[0],
                minutes: null,
                orderNumber: this.selectedTrainings.length,
            };
            this.selectedTrainings.push(nuevoObjeto);
            this.valueMultiselect = [];
            console.log(this.selectedTrainings);
        },

        actualizarOrden() {
            this.selectedTrainings.forEach((item, index) => {
                item.orderNumber = index;
            });
            console.log(this.selectedTrainings);
        },

        borrarItem(index) {
            this.selectedTrainings.splice(index, 1);
        },

        closeAll() {
            this.$refs.form.reset();
            this.valueMultiselect = [];
            this.selectedTrainings = [];
            this.$emit("input", false);
        },
        async save() {
            console.log(this.form);
            const isValid = await this.$refs.form.validate();
            if (isValid) {
                if (this.selectedTrainings.length > 0) {
                    for (const element of this.selectedTrainings) {
                        if (
                            element.minutes != null &&
                            this.selectedTrainings.indexOf(element) ===
                                this.selectedTrainings.length - 1
                        ) {
                            for (
                                let i = 0;
                                i < this.selectedTrainings.length;
                                i++
                            ) {
                                this.selectedTrainings[i].minutes = parseInt(
                                    this.selectedTrainings[i].minutes
                                );
                            }

                            console.log("TRAININGS: ", this.selectedTrainings);

                            //Aca enviamos la planificacion de los entrenamientos con sus duraciones, orden y minutos
                            let response = await localAxios
                                .post(
                                    "/admin/planifications/trainers",
                                    {
                                        trainerPlanificationList:
                                            this.selectedTrainings,
                                        name: this.form.name,
                                    },
                                    {
                                        headers: {
                                            "Content-Type": "application/json",
                                        },
                                    }
                                )
                                .then((response) => {
                                    console.log("RESPONSE FINAL");
                                    console.log(response);
                                    // manejar la respuesta del servidor
                                    this.$emit("saved", response.data);
                                    console.log(this.form);
                                    this.closeAll();
                                })
                                .catch((error) => {
                                    // manejar errores
                                });
                        } else {
                            if (
                                this.selectedTrainings.indexOf(element) ===
                                this.selectedTrainings.length - 1
                            ) {
                                alert(
                                    "Debes ingresar minutos en cada entrenamiento"
                                );
                            }
                        }
                    }
                } else {
                    alert(
                        "Se requieren los jugadores y/o entrenamientos que asignara la planificacion"
                    );
                }
            } else {
                alert("Escribe un nombre y fecha");
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

.item-container {
    display: flex;
    align-items: center;
}

.item-name {
    margin-right: 10px;
}

.item-minutes {
    width: 80px;
}
</style>
