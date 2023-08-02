<template>
    <div>
        <HeaderComponent title="Mis jugadores" />
        <v-container class="general-padding">
            <v-card v-if="datatable.items">
                <v-card-title> Jugadores </v-card-title>
                <v-card-text>
                    <v-text-field
                        v-model="datatable.search"
                        label="Buscador"
                        hide-details
                    >
                        <template v-slot:append-outer>
                            <v-btn
                                class="custom-button"
                                color="rgba(34, 56, 67, 0.85)"
                                dark
                                aria-label="Agregar jugador"
                                @click="dialogs.addUser = true"
                            >
                                Agregar Jugador
                            </v-btn>
                            <v-btn
                                class="custom-button"
                                color="rgba(34, 56, 67, 0.85)"
                                dark
                                aria-label="Solicitudes pendientes"
                                @click="redirectToRequests"
                            >
                                Solicitudes pendientes
                            </v-btn>
                        </template>
                    </v-text-field>
                    <v-data-table
                        :headers="datatable.headers"
                        :items="datatable.items"
                        :search="datatable.search"
                        class="elevation-0"
                    >
                        <template v-slot:item="{ item }">
                            <tr>
                                <td>{{ item.name }}</td>
                                <td>{{ item.surname }}</td>
                                <td>{{ item.email }}</td>
                                <td>{{ item.telephone }}</td>
                                <td>{{ item.birthdate }}</td>
                                <td v-if="item.usertype">
                                    {{ item.usertype.name }}
                                </td>
                                <td v-else>-</td>
                                <td>
                                    <v-btn
                                        icon
                                        :style="{
                                            marginTop: '0px',
                                        }"
                                        @click="editPlayer(item)"
                                        aria-label="Editar"
                                    >
                                        <v-icon>mdi-pencil</v-icon>
                                    </v-btn>
                                </td>

                                <td>
                                    <v-btn
                                        icon
                                        :style="{
                                            marginTop: '0px',
                                        }"
                                        @click="confirmDelete(item)"
                                        aria-label="Borrar"
                                    >
                                        <v-icon>mdi-delete</v-icon>
                                    </v-btn>
                                </td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-card-text>
            </v-card>

            <!-- Dialogs -->

            <AddPlayer
                v-model="dialogs.addUser"
                @saved="(newUser) => newUserSaved(newUser)"
            />

            <EditPlayer
                v-model="dialogs.editPlayer.show"
                :user="dialogs.editPlayer.user"
                @saved="savedEditUser"
            />

            <v-dialog v-model="dialogs.deleteUser" max-width="500px">
                <v-card>
                    <v-card-title>Confirmar eliminación</v-card-title>
                    <v-card-text>
                        ¿Deseas eliminar el jugador seleccionado?
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="dialogs.deleteUser = false"
                        >
                            <v-icon left>mdi-close</v-icon>
                            Cancelar</v-btn
                        >
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="deleteUser"
                        >
                            <v-icon left>mdi-delete</v-icon>
                            Eliminar</v-btn
                        >
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-container>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";
import AddPlayer from "./addPlayer.vue";
import EditPlayer from "./editPlayer.vue";
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        HeaderComponent,
        AddPlayer,
        EditPlayer,
    },
    data: () => ({
        snackbarStore: SnackbarStore(),
        datatable: {
            items: null,
            headers: [
                { text: "Nombre", value: "name" },
                { text: "Apellido", value: "surname" },
                { text: "Email", value: "email" },
                { text: "Telefono", value: "telephone" },
                {
                    text: "Fecha de Nacimiento",
                    value: "birthdate",
                    width: "20%",
                },
                { text: "Tipo", value: "usertype" },
                { text: "Editar", value: "", sortable: null },
                { text: "Borrar", value: "", sortable: null },
            ],
            search: "",
        },
        dialogs: {
            addUser: false,
            deleteUser: false,
            editPlayer: { show: false, user: null },
        },
    }),
    async mounted() {
        //Esto trae todos los jugadores hayan sido aceptados por el entrenador o no
        //let response = await localAxios.get("/admin/users/get-players");
        //Esto trae solo a los jugadores que hayan sido aceptados
        let response = await localAxios.get(
            "/admin/users/get-players-accepted"
        );
        this.datatable.items = response.data;
        console.log(response);
    },
    methods: {
        savedEditUser(newUser) {
            const index = this.datatable.items.findIndex(
                (i) => i.id === newUser.id
            );
            this.datatable.items.splice(index, 1, newUser);
        },
        newUserSaved(newUser) {
            if (newUser != "" && newUser != null) {
                this.datatable.items.push(newUser);
                this.snackbarStore.open(
                    "Se creó el nuevo usuario: " + newUser.username + "."
                );
            }
        },
        confirmDelete(item) {
            this.dialogs.deleteUser = true;
            this.userToDelete = item;
        },
        redirectToRequests() {
            this.$router.push("/players/requests");
        },
        async deleteUser() {
            let response = await localAxios.delete("/admin/users", {
                data: this.userToDelete,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(this.userToDelete);
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open(
                    "Se borró el usuario " + this.userToDelete.username + "."
                );
            }
            this.dialogs.deleteUser = false;
        },
        async editPlayer(item) {
            this.dialogs.editPlayer.user = item;
            this.dialogs.editPlayer.show = true;
        },
    },
};
</script>
<style>
.custom-button {
    margin-right: 8px;
}
</style>
