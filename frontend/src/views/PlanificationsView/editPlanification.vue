<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px; height: 700px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Editar Planificacion</div>
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
        planification: { type: Object },
    },
    data: () => ({
        localShow: false,
        selectedPlanification: null,
        //showPassword: false,
        trainings: [],
        players: [],
        form: {
            name: "",
            date: moment().format("YYYY-MM-DD"),
        },
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
            date: [(v) => !!v || "Se requiere una fecha"],
        },
        valueMultiselect: [],
        selectedTrainings: [],
        trainingsToDelete: [],
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
                        this.form.name = this.selectedPlanification.name;
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
                            console.log("ID: ", this.selectedPlanification.id);

                            //Aca enviamos la planificacion de los entrenamientos con sus duraciones, orden y minutos
                            await localAxios
                                .put(
                                    "/admin/planifications/trainers",
                                    {
                                        trainerPlanificationList:
                                            this.selectedTrainings,
                                        name: this.form.name,
                                        id: this.selectedPlanification.id,
                                    },
                                    {
                                        headers: {
                                            "Content-Type": "application/json",
                                        },
                                    }
                                )
                                .then((response) => {
                                    //Aca borro las cosas que saque de la lista de entrenamientos, es decir si teniamos el
                                    // entrenamientos 1 2 3 y deje solo 1 2, deberian cargarse esos y eliminarse el 3
                                    // aca se elimina el 3, arriba se hizo el put para cargar los cambios de esos 2, por ej en sus mins o el name de la planification

                                    localAxios
                                        .post(
                                            "/admin/planifications/retrieve",
                                            this.selectedPlanification
                                        )
                                        .then((response) => {
                                            this.trainingsToDelete =
                                                response.data;

                                            let elementsToDelete = [];
                                            elementsToDelete =
                                                this.selectedTrainings.filter(
                                                    (element) =>
                                                        !this.trainingsToDelete.includes(
                                                            element
                                                        )
                                                );

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
                                                            response.data
                                                        );
                                                        this.closeAll();
                                                    })
                                                    .catch((error) => {
                                                        // Manejar el error
                                                        console.error(error);
                                                    });
                                            }
                                        })
                                        .catch((error) => {
                                            // Manejar el error
                                            console.error(error);
                                        });
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
