<template>
    <div>
        <HeaderComponent title="Listado de entrenamientos" />
        <v-card v-if="datatable.items && authStore">
            <v-card-title> Entrenamientos </v-card-title>
            <v-card-text>
                <v-row>
                    <v-col cols="12" md="4">
                        <v-autocomplete
                            v-if="authStore.hasAuthority('ADMIN')"
                            :items="users"
                            :item-text="
                                (user) => `${user.name} ${user.surname}`
                            "
                            item-value="id"
                            v-model="selectedUser"
                            @change="getUserTrainings()"
                            label="Seleccionar usuario"
                            outlined
                        />
                    </v-col>
                </v-row>
                <v-text-field
                    v-model="datatable.search"
                    label="Buscador"
                    hide-details
                >
                    <template v-slot:append-outer>
                        <v-btn
                            v-if="!esAdmin"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="dialogs.addTraining = true"
                        >
                            Agregar entrenamiento
                        </v-btn>
                    </template>
                </v-text-field>
                <v-data-table
                    v-if="selectedUser"
                    :headers="datatable.headers"
                    :items="datatable.filtered"
                    :search="datatable.search"
                    :items-per-page-options="[10, 20, 50]"
                    :items-per-page="10"
                    :sort-by="['date']"
                    :sort-asc="true"
                    class="elevation-0"
                >
                    <template v-slot:item="{ item }">
                        <tr>
                            <td>{{ item.date }}</td>
                            <td>{{ item.breakfast }}</td>
                            <td>{{ item.lunch }}</td>
                            <td>{{ item.snack }}</td>
                            <td>{{ item.dinner }}</td>
                            <td>
                                <v-tooltip
                                    top
                                    v-if="!authStore.hasAuthority('ADMIN')"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="editTraining(item)"
                                        >
                                            <v-icon>mdi-pencil</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Editar entrenamiento</span>
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip
                                    top
                                    v-if="!authStore.hasAuthority('ADMIN')"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="deleteTraining(item)"
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
        <AddTraining v-model="dialogs.addTraining" @saved="newTrainingSaved" />
        <EditTraining
            v-model="dialogs.editTraining.show"
            :training="dialogs.editTraining.training"
            @saved="savededitTraining"
        />
        <v-dialog v-model="dialogs.deleteTraining" persistent max-width="600px">
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
import AddTraining from "./addTraining.vue";
import EditTraining from "./editTraining.vue";
import { AuthStore } from "@/store/auth";

export default {
    components: {
        HeaderComponent,
        AddTraining,
        EditTraining,
    },
    data: () => ({
        dialogs: {
            addTraining: false,
            editTraining: { show: false, training: null },
            deleteTraining: false,
        },
        esAdmin: false,
        trainingToDelete: null,
        authStore: null,
        selectedUser: null,
        users: [],
        datatable: {
            items: null,
            filtered: null,
            headers: [
                {
                    text: "Fecha",
                    align: "start",
                    sortable: true,
                    value: "date",
                    sortIcon: "mdi-arrow-up-down",
                },
                {
                    text: "Desayuno",
                    value: "breakfast",
                    sortable: null,
                },
                {
                    text: "Almuerzo",
                    value: "lunch",
                    sortable: null,
                },
                {
                    text: "Merienda",
                    value: "snack",
                    sortable: null,
                },
                {
                    text: "Cena",
                    value: "dinner",
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
        this.authStore = AuthStore();
        this.esAdmin = this.authStore.hasAuthority("ADMIN");
        let response = await localAxios.get("/admin/trainings");
        console.log(response);
        this.datatable.items = response.data;

        if (
            !this.authStore.hasAuthority("ADMIN") &&
            this.authStore.hasAuthority("USER")
        ) {
            this.selectedUser = this.authStore.user.user.id;
            this.datatable.filtered = this.datatable.items.filter(
                (item) => item.user.id === this.selectedUser
            );
            this.datatable.items = this.datatable.items.filter(
                (item) => item.user.id === this.selectedUser
            );
            console.log(this.datatable.items);
        }

        if (this.authStore.hasAuthority("ADMIN")) {
            let responseUsers = await localAxios.get("/admin/users");
            this.users = responseUsers.data;
            this.datatable.headers[5].text = "";
            this.datatable.headers[6].text = "";
        }
    },
    methods: {
        getUserTrainings() {
            console.log("ENTRO");
            const filteredTrainings = this.datatable.items.filter(
                (item) => item.user.id === this.selectedUser
            );
            this.datatable.filtered = filteredTrainings;
        },
        newTrainingSaved(newTraining) {
            this.datatable.items.push(newTraining);
            this.getUserTrainings();
            //this.snackbarStore.open("Se agrego nueva comida: " + newTraining + ".");
        },
        savededitTraining(newTraining) {
            const index = this.datatable.items.findIndex(
                (i) => i.id === newTraining.id
            );
            this.datatable.items.splice(index, 1, newTraining);
            this.getUserTrainings();
        },
        requestBloqueado() {
            localAxios.get("/api/blocked").then(() => {});
        },
        deleteTraining(item) {
            this.dialogs.deleteTraining = true;
            this.trainingToDelete = item;
        },
        async confirmDelete() {
            this.dialogs.deleteTraining = false;
            let response = await localAxios.delete("/admin/trainings", {
                data: this.trainingToDelete,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(
                    this.trainingToDelete
                );
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.getUserTrainings();
            }
            this.trainingToDelete = null;
        },
        async editTraining(item) {
            // this.$router.push({
            //     name: "Edit_Training",
            //     params: {
            //         training: item,
            //     },
            // });
            this.dialogs.editTraining.training = item;
            this.dialogs.editTraining.show = true;
        },
    },
};
</script>
