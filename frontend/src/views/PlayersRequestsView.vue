<template>
    <div>
        <HeaderComponent title="Solicitudes pendientes" />
        <v-container class="general-padding">
            <v-card>
                <v-card-title tabindex="0"> Jugadores </v-card-title>
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
                                        @click="acceptPlayer(item)"
                                        aria-label="Aceptar"
                                        color="rgba(34, 56, 67, 0.85)"
                                        dark
                                        :style="{
                                            marginTop: '0px',
                                        }"
                                        ><v-icon>mdi-check</v-icon>
                                    </v-btn>
                                </td>

                                <td>
                                    <v-btn
                                        icon
                                        @click="rejectPlayer(item)"
                                        aria-label="Rechazar"
                                        color="rgba(34, 56, 67, 0.85)"
                                        dark
                                        :style="{
                                            marginTop: '0px',
                                        }"
                                        ><v-icon>mdi-close</v-icon>
                                    </v-btn>
                                </td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-card-text>
            </v-card>

            <!-- Dialogs -->

            <v-dialog v-model="dialogs.rejectUser" max-width="500px">
                <v-card>
                    <v-card-title>Rechazar jugador</v-card-title>
                    <v-card-text>
                        ¿Deseas rechazar al jugador seleccionado?
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="dialogs.rejectUser = false"
                        >
                            <v-icon left>mdi-close</v-icon>
                            Cancelar</v-btn
                        >
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="rejectUser()"
                        >
                            <v-icon left>mdi-delete</v-icon>
                            Rechazar</v-btn
                        >
                    </v-card-actions>
                </v-card>
            </v-dialog>
            <v-dialog v-model="dialogs.acceptUser" max-width="500px">
                <v-card>
                    <v-card-title>Aceptar jugador</v-card-title>
                    <v-card-text>
                        ¿Deseas aceptar al jugador seleccionado?
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="dialogs.acceptUser = false"
                        >
                            <v-icon left>mdi-close</v-icon>
                            Cancelar</v-btn
                        >
                        <v-btn
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            @click="acceptUser()"
                        >
                            <v-icon left>mdi-check</v-icon>
                            Aceptar</v-btn
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
                { text: "Aceptar", value: "", sortable: null },
                { text: "Rechazar", value: "", sortable: null },
            ],
            search: "",
        },
        dialogs: {
            rejectUser: false,
            acceptUser: false,
        },
        user: null,
    }),
    async mounted() {
        let response = await localAxios.get(
            "/admin/users/get-players-requests"
        );
        this.datatable.items = response.data;
        console.log(response);
    },
    methods: {
        acceptPlayer(item) {
            this.dialogs.acceptUser = true;
            this.user = item;
        },
        async acceptUser() {
            let response = await localAxios.put("/register", this.user);
            console.log(response);
            const index = this.datatable.items.indexOf(this.user);
            if (index >= 0) this.datatable.items.splice(index, 1);
            this.dialogs.acceptUser = false;
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
