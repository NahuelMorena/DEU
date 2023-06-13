<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px; height: 700px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Agregar usuarios a la planificacion</div>
                    <div>
                        <v-btn icon @click="closeAll()" aria-label="Cerrar">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col cols="12">
                            <multiselect
                                v-model="valueMultiselect"
                                placeholder="Seleccione jugadores a los que se les asignara"
                                label="name"
                                track-by="id"
                                :options="players"
                                :multiple="true"
                                :close-on-select="false"
                                @input="onSelect"
                                @remove="customRemoveTag"
                            >
                            </multiselect>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <li
                                v-for="(item, index) in selectedPlayers"
                                :key="item.id"
                                :class="{ deleting: item.deleting }"
                            >
                                <div class="item-container">
                                    <Datepicker
                                        :rules="rules.date"
                                        v-model="item.date"
                                        :label="'Fecha'"
                                    ></Datepicker>
                                    <span
                                        class="item-name"
                                        style="padding: 1%"
                                        >{{
                                            item.user.name +
                                            " " +
                                            item.user.surname
                                        }}</span
                                    >
                                </div>
                                <button
                                    class="delete-button"
                                    @click="borrarItem(index)"
                                    aria-label="Borrar"
                                >
                                    Borrar<v-icon color="red"
                                        >mdi-alpha-x</v-icon
                                    >
                                </button>
                            </li>
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
import Datepicker from "@/components/datepicker.vue";

export default {
    components: {
        Multiselect,
        Datepicker,
    },
    props: {
        value: { type: Boolean },
        planification: { type: Object },
    },
    data: () => ({
        localShow: false,
        selectedPlanification: [],
        selectedPlayers: [],
        players: [],

        valueMultiselect: [],
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
        planification: function (val) {
            this.selectedPlanification = val;
        },
    },
    async mounted() {
        let response2 = await localAxios.get("/admin/users/get-players");
        this.players = response2.data;
    },
    methods: {
        customRemoveTag(removedTag) {
            const index = this.selectedPlayers.findIndex((item) => {
                // Comparar los atributos del objeto actual con el objeto buscado
                // Por ejemplo, si deseas comparar los atributos "id" de los objetos:
                return item.user === removedTag;
            });
            this.selectedPlayers.splice(index, 1);
            console.log(this.selectedPlayers);
        },

        onSelect() {
            const nuevoObjeto = {
                planification: null,
                user: this.valueMultiselect[this.valueMultiselect.length - 1],
                minutes: null,
                orderNumber: this.valueMultiselect.length,
            };
            const existeObjeto = this.selectedPlayers.some((item) => {
                // Comparar los atributos del objeto actual con el objeto buscado
                return item.user === nuevoObjeto.user;
            });

            if (!existeObjeto) {
                this.selectedPlayers.push(nuevoObjeto);
            }
        },

        borrarItem(index) {
            this.selectedPlayers.splice(index, 1);
            this.valueMultiselect.splice(index, 1);
        },

        closeAll() {
            this.valueMultiselect = [];
            this.selectedPlayers = [];
            this.$emit("input", false);
        },

        async save() {
            if (this.selectedPlayers.length > 0) {
                for (const element of this.selectedPlayers) {
                    if (
                        element.date != null &&
                        this.selectedPlayers.indexOf(element) ===
                            this.selectedPlayers.length - 1
                    ) {
                        for (let i = 0; i < this.selectedPlayers.length; i++) {
                            this.selectedPlayers[i].planification =
                                this.selectedPlanification;
                        }

                        //Aca enviamos la planificacion asociada a los jugadores
                        let response = await localAxios
                            .post(
                                "/admin/users/planifications",
                                this.selectedPlayers
                            )
                            .then((response) => {
                                console.log("RESPONSE FINAL");
                                console.log(response);
                                // manejar la respuesta del servidor
                                this.$emit("saved", response.data);
                                this.closeAll();
                            })
                            .catch((error) => {
                                // manejar errores
                            });
                    } else {
                        if (
                            this.selectedPlayers.indexOf(element) ===
                            this.selectedPlayers.length - 1
                        ) {
                            alert(
                                "Debes ingresar las fechas de la planificacion para cada jugador"
                            );
                        }
                    }
                }
            } else {
                alert(
                    "Se requieren los jugadores y/o entrenamientos que asignara la planificacion"
                );
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
    text-align: center;
}

.item-minutes {
}
</style>
