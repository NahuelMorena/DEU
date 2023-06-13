<template>
    <div>
        <HeaderComponent title="Usuarios" />
        <v-container class="general-padding">
            <v-card v-if="datatable.items">
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
                                @click="dialogs.addUser = true"
                            >
                                Agregar Usuario
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
                                    <span
                                        v-for="(role, i) in item.roles"
                                        :key="i"
                                        class="mr-2"
                                        >{{ role.name }}</span
                                    >
                                </td>
                                <td>
                                    <v-btn
                                        icon
                                        @click="confirmDelete(item)"
                                        aria-label="Borrar usuario"
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
            <AddUser
                v-model="dialogs.addUser"
                @saved="(newUser) => newUserSaved(newUser)"
            />
            <EditUser
                v-model="dialogs.editUser.show"
                :user="dialogs.editUser.user"
                @saved="savedEditUser"
            />
            <v-dialog
                v-model="dialogs.deleteVisit"
                persistent
                max-width="600px"
            >
                <v-card>
                    <v-card-title class="headline">
                        ¿Deseas eliminar el usuario seleccionado?
                    </v-card-title>
                    <v-card-actions>
                        <v-btn
                            color="error"
                            @click="deleteUser"
                            aria-label="Eliminar"
                            >Eliminar</v-btn
                        >
                        <v-btn
                            text
                            @click="dialogs.deleteVisit = false"
                            aria-label="Cancelar"
                            >Cancelar</v-btn
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
import AddUser from "./addUser.vue";
import EditUser from "./editUser.vue";
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        HeaderComponent,
        AddUser,
        EditUser,
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
                { text: "Permisos", value: "" },
                { text: "Borrar", value: "", sortable: null },
            ],
            search: "",
        },
        dialogs: {
            addUser: false,
            deleteVisit: false,
            editUser: { show: false, user: null },
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/users");
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
            this.datatable.items.push(newUser);
            this.snackbarStore.open(
                "Se creó el nuevo usuario: " + newUser.username + "."
            );
        },
        confirmDelete(item) {
            this.dialogs.deleteVisit = true;
            this.userToDelete = item;
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
        },
        async editUser(item) {
            this.dialogs.editUser.user = item;
            this.dialogs.editUser.show = true;
        },
    },
};
</script>
