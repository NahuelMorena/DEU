<template>
    <div>
        <HeaderComponent title="Usuarios" />
        <v-container>
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
                                <td>{{ item.username }}</td>
                                <td>
                                    <span
                                        v-for="(role, i) in item.roles"
                                        :key="i"
                                        class="mr-2"
                                        >{{ role.name }}</span
                                    >
                                </td>
                                <td>
                                    <v-btn icon @click="deleteUser(item)">
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
        </v-container>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";
import AddUser from "./addUser.vue";
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        HeaderComponent,
        AddUser,
    },
    data: () => ({
        snackbarStore: SnackbarStore(),
        datatable: {
            items: null,
            headers: [
                { text: "Nombre", value: "name", width: "20%" },
                { text: "Permisos", value: "", width: "60%" },
                { text: "", value: "", width: "20%" },
            ],
            search: "",
        },
        dialogs: {
            addUser: false,
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/users");
        this.datatable.items = response.data;
    },
    methods: {
        newUserSaved(newUser) {
            this.datatable.items.push(newUser);
            this.snackbarStore.open(
                "Se creó el nuevo usuario: " + newUser.username + "."
            );
        },
        async deleteUser(item) {
            let response = await localAxios.delete("/admin/users", {
                data: item,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(item);
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open(
                    "Se borró el usuario " + item.username + "."
                );
            }
        },
    },
};
</script>
