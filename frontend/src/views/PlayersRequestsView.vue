<template>
    <div>
        <HeaderComponent title="Mis jugadores" />
        <v-container class="general-padding">
            <v-card>
                <v-card-title> Jugadores </v-card-title>
                <v-card-text>
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
                                        color="green"
                                        @click="acceptPlayer(item)"
                                        aria-label="Aceptar"
                                        >Aceptar
                                    </v-btn>
                                </td>

                                <td>
                                    <v-btn
                                        icon
                                        color="red"
                                        @click="rejectPlayer(item)"
                                        aria-label="Rechazar"
                                        >Rechazar
                                    </v-btn>
                                </td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-card-text>
            </v-card>

            <!-- Dialogs -->

            <v-dialog v-model="dialogs.rejectUser" persistent max-width="600px">
                <v-card>
                    <v-card-title class="headline">
                        ¿Deseas rechazar al usuario seleccionado?
                    </v-card-title>
                    <v-card-actions>
                        <v-btn
                            color="error"
                            @click="rejectUser()"
                            aria-label="Eliminar"
                            >Eliminar</v-btn
                        >
                        <v-btn
                            text
                            @click="dialogs.rejectUser = false"
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
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        HeaderComponent,
    },
    data: () => ({
        snackbarStore: SnackbarStore(),
        datatable: {
            items: [],
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
            rejectUser: false,
        },
    }),
    async mounted() {
        let response = await localAxios.get(
            "/admin/users/get-players-requests"
        );
        this.datatable.items = response.data;
        console.log(response);
    },
    methods: {
        async acceptPlayer(user) {
            let response = await localAxios.put("/admin/users/register", user);
            console.log(response);
            const index = this.datatable.items.indexOf(user);
            if (index >= 0) this.datatable.items.splice(index, 1);
        },
        rejectPlayer(item) {
            this.dialogs.rejectUser = true;
            this.userToReject = item;
        },
        async rejectUser() {
            let response = await localAxios.delete("/admin/users", {
                data: this.userToReject,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(this.userToReject);
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open(
                    "Se borró el usuario " + this.userToReject.username + "."
                );
            }
            this.dialogs.rejectUser = false;
        },
    },
};
</script>
