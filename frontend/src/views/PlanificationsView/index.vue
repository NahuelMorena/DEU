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
                            aria-label="Agregar planificacion"
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
                    class="elevation-0"
                >
                    <template v-slot:item="{ item }">
                        <tr>
                            <td>{{ item.name }}</td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            aria-label="Agregar usuarios a planificacion"
                                            @click="addUserPlanification(item)"
                                        >
                                            <v-icon>mdi-account-plus</v-icon>
                                        </v-btn>
                                    </template>
                                    <span
                                        >Agregar usuarios a esta
                                        planificacion</span
                                    >
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            aria-label="Editar asignacion de usuarios a planificacion"
                                            @click="editUserPlanification(item)"
                                        >
                                            <v-icon>mdi-account-edit</v-icon>
                                        </v-btn>
                                    </template>
                                    <span
                                        >Editar asignaciones de usuarios a esta
                                        planificacion</span
                                    >
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            aria-label="Editar planificacion"
                                            @click="editPlanification(item)"
                                        >
                                            <v-icon>mdi-pencil</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Editar planificacion</span>
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            aria-label="Borrar planificacion"
                                            @click="deletePlanification(item)"
                                        >
                                            <v-icon>mdi-delete</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Borrar planificacion</span>
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
        <EditUserPlanification
            v-model="dialogs.editUserPlanification.show"
            :planification="dialogs.editUserPlanification.planification"
            @saved="savededitUserPlanification"
        />
        <AddUserPlanification
            v-model="dialogs.addUserPlanification.show"
            :planification="dialogs.addUserPlanification.planification"
            @saved="savedaddUserPlanification"
        />
        <v-dialog
            v-model="dialogs.deletePlanification"
            persistent
            max-width="600px"
        >
            <v-card>
                <v-card-title class="headline">
                    ¿Deseas eliminar la planificacion seleccionado?
                </v-card-title>
                <v-card-actions>
                    <v-btn color="error" @click="confirmDelete">Eliminar</v-btn>
                    <v-btn text @click="dialogs.deletePlanification = false"
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
import AddUserPlanification from "./addUserPlanification.vue";
import EditUserPlanification from "./editUserPlanification.vue";

export default {
    components: {
        HeaderComponent,
        AddPlanification,
        AddUserPlanification,
        EditPlanification,
        EditUserPlanification,
    },
    data: () => ({
        dialogs: {
            addPlanification: false,
            editPlanification: { show: false, planification: null },
            editUserPlanification: { show: false, planification: null },
            addUserPlanification: { show: false, planification: null },
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
                    text: "Nombre",
                    value: "name",
                    sortable: null,
                },
                { text: "Agregar usuarios", value: "", sortable: null },
                { text: "Editar usuarios", value: "", sortable: null },
                { text: "Editar planificacion", value: "", sortable: null },
                { text: "Borrar", value: "", sortable: null },
            ],
            trainings: [],
            search: "",
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/planifications/basic");
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
        savedaddUserPlanification(newPlanification) {},
        savededitUserPlanification(newPlanification) {},
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
            this.dialogs.editPlanification.planification = item;
            this.dialogs.editPlanification.show = true;
        },
        async addUserPlanification(item) {
            this.dialogs.addUserPlanification.planification = item;
            this.dialogs.addUserPlanification.show = true;
        },
        async editUserPlanification(item) {
            this.dialogs.editUserPlanification.planification = item;
            this.dialogs.editUserPlanification.show = true;
        },
    },
};
</script>
