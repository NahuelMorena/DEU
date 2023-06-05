<template>
    <div>
        <HeaderComponent title="Listado de entrenamientos" />
        <v-card>
            <v-card-title> Planificaciones </v-card-title>
            <v-card-text>
                <v-text-field
                    v-model="datatable.search"
                    label="Buscador"
                    hide-details
                >
                    <template v-slot:append-outer>
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="dialogs.addPlanification = true"
                        >
                            Agregar planificacion
                        </v-btn>
                    </template>
                </v-text-field>
                <v-data-table
                    :headers="datatable.headers"
                    :items="datatable.items"
                    :search="datatable.search"
                    :items-per-page="10"
                    :sort-by="['date']"
                    :sort-asc="true"
                    item-key="id"
                    show-select
                    class="elevation-0"
                >
                    <template v-slot:item="{ item }">
                        <tr>
                            <td>{{ item.date }}</td>
                            <td>{{ item.name }}</td>
                            <td>{{ item.description }}</td>
                            <td>{{ item.training_type.name }}</td>
                            <td>{{ item.warmup_time }}</td>
                            <td>{{ item.repetitions_quantity }}</td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="editPlanification(item)"
                                        >
                                            <v-icon>mdi-pencil</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Editar entrenamiento</span>
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="deletePlanification(item)"
                                        >
                                            <v-icon>mdi-delete</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Borrar entrenamiento</span>
                                </v-tooltip>
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>
        <AddPlanification
            v-model="dialogs.addPlanification"
            @saved="newPlanificationSaved"
        />
        <EditPlanification
            v-model="dialogs.editPlanification.show"
            :planification="dialogs.editPlanification.planification"
            @saved="savededitPlanification"
        />
        <v-dialog
            v-model="dialogs.deletePlanification"
            persistent
            max-width="600px"
        >
            <v-card>
                <v-card-title class="headline">
                    ¿Deseas eliminar el entrenamiento seleccionado?
                </v-card-title>
                <v-card-actions>
                    <v-btn color="error" @click="confirmDelete">Eliminar</v-btn>
                    <v-btn text @click="dialogs.deleteTraining = false"
                        >Cancelar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";
import AddPlanification from "./addPlanification.vue";
import EditPlanification from "./editPlanification.vue";

export default {
    components: {
        HeaderComponent,
        AddPlanification,
        EditPlanification,
    },
    data: () => ({
        dialogs: {
            addPlanification: false,
            editPlanification: { show: false, planification: null },
            deletePlanification: false,
        },
        esAdmin: false,
        planificationToDelete: null,
        selectedUser: null,
        users: [],
        datatable: {
            items: [],
            headers: [
                {
                    text: "Fecha",
                    align: "start",
                    sortable: true,
                    value: "date",
                    sortIcon: "mdi-arrow-up-down",
                },
                {
                    text: "Nombre",
                    value: "name",
                    sortable: null,
                },
                {
                    text: "Descripcion",
                    value: "description",
                    sortable: null,
                },
                {
                    text: "Tipo de entrenamiento",
                    value: "training_type.name",
                    sortable: null,
                },
                {
                    text: "Tiempo de calentamiento",
                    value: "warmup_time",
                    sortable: null,
                },
                {
                    text: "Cantidad de repeticiones",
                    value: "repetitions_quantity",
                    sortable: null,
                },
                { text: "Editar", value: "", sortable: null },
                { text: "Borrar", value: "", sortable: null },
            ],
            trainings: [],
            search: "",
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/planifications");
        this.datatable.items = response.data;
    },
    methods: {
        newPlanificationSaved(newPlanification) {
            this.datatable.items.push(newPlanification);
        },
        savededitPlanification(newPlanification) {
            const index = this.datatable.items.findIndex(
                (i) => i.id === newPlanification.id
            );
            this.datatable.items.splice(index, 1, newPlanification);
        },
        requestBloqueado() {
            localAxios.get("/api/blocked").then(() => {});
        },
        deletePlanification(item) {
            this.dialogs.deletePlanification = true;
            this.planificationToDelete = item;
        },
        async confirmDelete() {
            this.dialogs.deletePlanification = false;
            let response = await localAxios.delete("/admin/planifications", {
                data: this.planificationToDelete,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(
                    this.planificationToDelete
                );
                if (index >= 0) this.datatable.items.splice(index, 1);
            }
            this.planificationToDelete = null;
        },
        async editPlanification(item) {
            // this.$router.push({
            //     name: "Edit_Planification",
            //     params: {
            //         Planification: item,
            //     },
            // });
            this.dialogs.editPlanification.planification = item;
            this.dialogs.editPlanification.show = true;
        },
    },
};
</script>
