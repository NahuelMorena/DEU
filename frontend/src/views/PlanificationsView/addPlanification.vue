<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar Planificacion</div>
                    <div>
                        <v-btn icon @click="closeAll()" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text style="max-height: 500px; overflow-y: auto">
                    <v-row>
                        <v-col cols="12">
                            <label for="name-field">
                                Nombre de la planificación</label
                            >
                            <v-text-field
                                v-model="name"
                                :rules="rules.name"
                                required
                                id="name-field"
                            ></v-text-field>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="12">
                            <b>
                                <label for="training-select">
                                    Seleccione entrenamiento
                                </label>
                            </b>
                            <multiselect
                                v-model="valueMultiselect"
                                id="training-select"
                                label="name"
                                placeholder="Seleccione entrenamiento....."
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
                            <li>
                                <b><span>Lista de entrenamientos</span></b>
                            </li>
                            <li>
                                <div class="item-container">
                                    <v-col style="margin-left: 10px">
                                        <b>
                                            <span for="minutes-input"
                                                >Minutos</span
                                            ></b
                                        >
                                    </v-col>
                                    <v-col style="margin-left: 100px">
                                        <b>
                                            <span for="name-input"
                                                >Nombre</span
                                            ></b
                                        >
                                    </v-col>
                                </div>
                            </li>
                            <draggable
                                v-model="selectedTrainings"
                                :element="'ul'"
                                class="sortable-list"
                                @end="actualizarOrden"
                            >
                                <li
                                    v-for="(item, index) in selectedTrainings"
                                    :key="item.id"
                                    :class="{ deleting: item.deleting }"
                                >
                                    <div
                                        class="item-container"
                                        style="display: flex"
                                    >
                                        <input
                                            type="number"
                                            v-model="item.minutes"
                                            class="item-minutes"
                                            min="0"
                                            id="minutes-input"
                                            placeholder="Seleccione minutos..."
                                        />
                                        <div class="item-training-name">
                                            <span>
                                                {{ item.training.name }}</span
                                            >
                                        </div>
                                    </div>
                                    <v-tooltip top>
                                        <template
                                            v-slot:activator="{ on, attrs }"
                                        >
                                            <v-btn
                                                class="delete-button"
                                                v-bind="attrs"
                                                v-on="on"
                                                icon
                                                aria-label="Quitar entrenamiento"
                                                @click="confirmDelete(index)"
                                            >
                                                <v-icon>mdi-delete</v-icon>
                                            </v-btn>
                                        </template>
                                        <span>Quitar entrenamiento</span>
                                    </v-tooltip>
                                </li>
                            </draggable>
                        </v-col></v-row
                    >
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        type="submit"
                        aria-label="Agregar"
                        @click="save()"
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        >Agregar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-dialog v-model="confirmDialog" max-width="500px">
            <v-card>
                <v-card-title>Confirmar eliminación</v-card-title>
                <v-card-text>
                    ¿Estás seguro de que deseas quitar el entrenamiento?
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        @click="cancelDelete"
                    >
                        <v-icon left>mdi-close</v-icon>
                        Cancelar</v-btn
                    >
                    <v-btn
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        @click="deleteItem"
                    >
                        <v-icon left>mdi-delete</v-icon>
                        Eliminar</v-btn
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
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        Multiselect,
        draggable,
    },
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        snackbarStore: SnackbarStore(),
        localShow: false,
        trainings: [],
        players: [],
        name: "",
        rules: {
            name: [(v) => !!v || "Se requiere un nombre"],
        },

        valueMultiselect: [],
        selectedTrainings: [],
        confirmDialog: false,
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
            this.snackbarStore.open(
                "Entrenamiento: " + nuevoObjeto.training.name + " seleccionado!"
            );
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

        confirmDelete(index) {
            this.deleteIndex = index;
            this.confirmDialog = true;
        },

        cancelDelete() {
            this.confirmDialog = false;
        },

        deleteItem() {
            this.confirmDialog = false;
            this.borrarItem(this.deleteIndex);
        },

        closeAll() {
            this.name = "";
            this.valueMultiselect = [];
            this.selectedTrainings = [];
            this.$emit("input", false);
        },
        async save() {
            console.log(this.form);
            if (this.name !== "") {
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
                                        name: this.name,
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
                alert("Escribe un nombre");
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
    width: 155px;
    box-sizing: border-box;
    padding: 5px;
}

.item-training-name {
    width: 100px;
    margin-left: 25px;
}
</style>
