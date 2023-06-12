<template>
    <div>
        <HeaderComponent
            title="Planificacion de entrenamientos para el usuario"
        />
        <v-card v-if="datatable.items && authStore">
            <v-card-title> Usuario </v-card-title>
            <v-card-text>
                <v-row>
                    <v-col cols="12" md="4">
                        <v-autocomplete
                            v-if="authStore.hasAuthority('TRAINER')"
                            :items="users"
                            :item-text="
                                (user) => `${user.name} ${user.surname}`
                            "
                            return-object
                            v-model="selectedUser"
                            @change="getUserTrainerPlanifications()"
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
                </v-text-field>
                <v-data-table
                    :headers="datatable.headers"
                    :items="datatable.items"
                    :search="datatable.search"
                    :items-per-page="10"
                    :sort-by="['date']"
                    :sort-asc="true"
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
                                            @click="AddCalification(item)"
                                        >
                                            <v-icon>mdi-pencil</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Agregar/Editar calificacion</span>
                                </v-tooltip>
                            </td>
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="deleteCalification(item)"
                                        >
                                            <v-icon>mdi-delete</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Borrar calificacion</span>
                                </v-tooltip>
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>
        <v-dialog
            v-model="dialogs.AddCalification.show"
            persistent
            max-width="600px"
        >
            <v-card>
                <v-card-title class="headline">
                    Agregar/Editar calificacion
                </v-card-title>
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field
                            v-model="calification"
                            label="Calificacion"
                            type="number"
                            required
                        ></v-text-field>
                    </v-form>
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn
                        type="submit"
                        @click="saveCalification()"
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        >Agregar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>

        <v-dialog
            v-model="dialogs.deleteCalification"
            persistent
            max-width="600px"
        >
            <v-card>
                <v-card-title class="headline">
                    ¿Deseas eliminar el entrenamiento seleccionado?
                </v-card-title>
                <v-card-actions>
                    <v-btn color="error" @click="confirmDelete">Eliminar</v-btn>
                    <v-btn text @click="dialogs.deleteCalification = false"
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
import { AuthStore } from "@/store/auth";

export default {
    components: {
        HeaderComponent,
    },
    data: () => ({
        dialogs: {
            AddCalification: { show: false, userPlan: null },
            deleteCalification: null,
        },
        esAdmin: false,
        calification: null,
        calificationToDelete: null,
        selectedUser: null,
        users: [],
        authStore: null,
        user_planifications: [],
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
                    value: "planification.name",
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
        this.authStore = AuthStore();
        let response = null;
        if (this.authStore.hasAuthority("TRAINER")) {
            response = await localAxios.post(
                "/admin/users/planifications/retrieve-by-trainer",
                this.authStore.user.user
            );
            let responseUsers = await localAxios.get(
                "/admin/users/get-players"
            );
            this.users = responseUsers.data;
            let responseTrainerPlanifications = await localAxios.post(
                "/admin/planifications/retrieve-by-trainer",
                this.authStore.user.user
            );
            this.datatable.items = responseTrainerPlanifications.data;
        } else if (this.authStore.hasAuthority("USER")) {
            response = await localAxios.post(
                "/admin/users/planifications/retrieve-by-user",
                this.authStore.user.user
            );
            let responseTrainerPlanifications = await localAxios.post(
                "/admin/planifications/retrieve-by-trainer",
                this.authStore.user.user
            );

            this.user_planifications = response.data;

            const filteredTrainerPlanifications =
                responseTrainerPlanifications.data
                    .filter((trainer_planification) =>
                        this.user_planifications.some(
                            (userPlanification) =>
                                userPlanification.planification.id ===
                                trainer_planification.planification.id
                        )
                    )
                    .map((filteredPlanification) => {
                        const userPlanification = this.user_planifications.find(
                            (userPlanification) =>
                                userPlanification.planification.id ===
                                filteredPlanification.planification.id
                        );
                        return {
                            ...filteredPlanification,
                            date: userPlanification.date,
                        };
                    });
            this.datatable.items = filteredTrainerPlanifications;
        }

        this.user_planifications = response.data;
    },
    methods: {
        getUserTrainerPlanifications() {
            //filtramos los user planifications por el usuario seleccionado
            const filteredUser = this.user_planifications.filter(
                (item) => item.user.id === this.selectedUser.id
            );

            //filtramos los trainer planifications del usuario seleccionado (por medio de los user planifications filtrados arriba)
            //tambien le agarramos el date a esos userplanifications y lo cargamos en el vector para poder mostrar
            const filteredTrainerPlanifications = this.datatable.items
                .filter((trainer_planification) =>
                    filteredUser.some(
                        (userPlanification) =>
                            userPlanification.planification.id ===
                            trainer_planification.planification.id
                    )
                )
                .map((filteredPlanification) => {
                    const userPlanification = filteredUser.find(
                        (userPlanification) =>
                            userPlanification.planification.id ===
                            filteredPlanification.planification.id
                    );
                    return {
                        ...filteredPlanification,
                        date: userPlanification.date,
                    };
                });
            this.datatable.filtered = filteredTrainerPlanifications;

            console.log("ITEMS: ");
            console.log(this.datatable.filtered);
            console.log("USUARIO: ");
            console.log(this.selectedUser);
        },

        saveCalification() {
            //aca hay que mandar el post a la base de datos
            //recibir el resultado y de alguna manera dejarlo mostrado en la tabla, actualizar tabla basicamente con ese resultado
        },
        requestBloqueado() {
            localAxios.get("/api/blocked").then(() => {});
        },
        deleteCalification(item) {
            this.dialogs.deleteCalification = true;
            this.calificationToDelete = item;
        },
        async confirmDelete() {
            this.dialogs.deleteCalification = false;
            // let response = await localAxios.delete(
            //     "/admin/users/planifications/calification",
            //     {
            //         data: this.calificationToDelete,
            //     }
            // );
            // if (response.status == 200) {
            //     const index = this.datatable.items.indexOf(
            //         this.calificationToDelete
            //     );
            //     if (index >= 0) this.datatable.items.splice(index, 1);
            // }
            this.calificationToDelete = null;
        },
        async AddCalification(item) {
            this.dialogs.AddCalification.show = true;
            this.dialogs.AddCalification.userPlan = item;
        },
    },
};
</script>
