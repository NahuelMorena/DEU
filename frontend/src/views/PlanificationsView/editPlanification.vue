<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px; height: 700px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Editar Planificacion</div>
                    <div>
                        <v-btn icon @click="closeAll()" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field
                                v-model="name"
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

                    <v-row>
                        <v-col cols="12">
                            <draggable
                                v-model="selectedTrainings"
                                :element="'ul'"
                                class="sortable-list"
                                @end="actualizarOrden"
                            >
                                <li
                                    v-for="(
                                        item, index
                                    ) in selectedTrainingsSorted"
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
                        </v-col>
                    </v-row>
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        type="submit"
                        @click="save()"
                        aria-label="Agregar"
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
        planification: { type: Object },
    },
    data: () => ({
        localShow: false,
        selectedPlanification: null,
        //showPassword: false,
        trainings: [],
        players: [],
        name: "",
        valueMultiselect: [],
        selectedTrainings: [],
        trainingsBefore: [],
    }),
    created() {
        this.selectedPlanification = this.planification;
        // realizar otras acciones necesarias en el ciclo de vida created
    },
    watch: {
        value: function (val) {
            this.localShow = val;
        },
        planification: function (val) {
            this.selectedPlanification = val;
        },

        selectedPlanification: {
            handler: async function (val) {
                // Hacer una copia superficial del objeto observado
                const selectedPlanificationCopy = Object.assign({}, val);

                console.log(selectedPlanificationCopy);
                let response = await localAxios
                    .post(
                        "/admin/planifications/retrieve",
                        selectedPlanificationCopy
                    )
                    .then((response) => {
                        this.selectedTrainings = response.data;
                        this.name = this.selectedPlanification.name;
                        console.log("TRAININGS: ");
                        console.log(this.selectedTrainings);
                    })
                    .catch((error) => {
                        // Manejar el error
                        console.error(error);
                    });
            },
            deep: true, // Observa los cambios profundos en el objeto
        },
    },

    computed: {
        selectedTrainingsSorted() {
            return this.selectedTrainings
                .slice()
                .sort((a, b) => a.orderNumber - b.orderNumber);
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

        getElementsNotInArray(array1, array2) {
            const elementsNotInArray = [];

            array1.forEach((element) => {
                const id = element.id;
                if (!array2.some((el) => el.id === id)) {
                    elementsNotInArray.push(element);
                }
            });

            return elementsNotInArray;
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
            this.valueMultiselect = [];

            //al abrir de vuelta la misma ventana 2 veces si borro el selected trainings no aparecen los que tenias antes, asi que hay que recuperarlos...
            localAxios
                .post(
                    "/admin/planifications/retrieve",
                    this.selectedPlanification
                )
                .then((response) => {
                    this.selectedTrainings = response.data;
                })
                .catch((error) => {
                    // Manejar el error
                    console.error(error);
                });

            this.$emit("input", false);
        },
        async save() {
            if (this.name != "") {
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

                            await localAxios
                                .post(
                                    "/admin/planifications/retrieve",
                                    this.selectedPlanification
                                )
                                .then((response) => {
                                    this.trainingsBefore = response.data;
                                    //Aca enviamos la planificacion de los entrenamientos con sus duraciones, orden y minutos
                                    localAxios
                                        .put(
                                            "/admin/planifications/trainers",
                                            {
                                                trainerPlanificationList:
                                                    this.selectedTrainings,
                                                name: this.name,
                                                id: this.selectedPlanification
                                                    .id,
                                            },
                                            {
                                                headers: {
                                                    "Content-Type":
                                                        "application/json",
                                                },
                                            }
                                        )
                                        .then((responsePlanification) => {
                                            //Aca borro las cosas que saque de la lista de entrenamientos, es decir si teniamos el
                                            // entrenamientos 1 2 3 y deje solo 1 2, deberian cargarse esos y eliminarse el 3
                                            // aca se elimina el 3, arriba se hizo el put para cargar los cambios de esos 2, por ej en sus mins o el name de la planification

                                            let elementsToDelete = [];
                                            elementsToDelete =
                                                this.getElementsNotInArray(
                                                    this.trainingsBefore,
                                                    this.selectedTrainings
                                                );

                                            console.log(elementsToDelete);

                                            if (elementsToDelete.length > 0) {
                                                localAxios
                                                    .delete(
                                                        "/admin/planifications/trainers",
                                                        {
                                                            data: elementsToDelete,
                                                        }
                                                    )
                                                    .then((response2) => {
                                                        this.$emit(
                                                            "saved",
                                                            responsePlanification.data
                                                        );
                                                        this.closeAll();
                                                    })
                                                    .catch((error) => {
                                                        // Manejar el error
                                                        console.error(error);
                                                    });
                                            } else {
                                                this.$emit(
                                                    "saved",
                                                    responsePlanification.data
                                                );
                                                this.closeAll();
                                            }
                                        })
                                        .catch((error) => {
                                            // manejar errores
                                        });
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
