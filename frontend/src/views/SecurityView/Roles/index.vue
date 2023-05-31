<template>
    <div>
        <v-card v-if="datatable.items">
            <v-card-title> Roles </v-card-title>
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
                            @click="dialogs.addRole = true"
                        >
                            Agregar rol
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
                            <td>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="showAuthorities(item)"
                                        >
                                            <v-icon>mdi-eye</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Ver permisos</span>
                                </v-tooltip>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            v-bind="attrs"
                                            v-on="on"
                                            icon
                                            @click="deleteRole(item)"
                                        >
                                            <v-icon>mdi-delete</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Borrar rol</span>
                                </v-tooltip>
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-card>

        <!-- Dialogs -->
        <AddRole
            v-model="dialogs.addRole"
            @saved="(newRole) => newRoleSaved(newRole)"
        />
        <ShowAuthorities
            v-model="dialogs.authorities.show"
            v-bind:roleName="dialogs.authorities.roleName"
            v-bind:authorities="dialogs.authorities.items"
        />
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import AddRole from "./addRole.vue";
import ShowAuthorities from "./showAuthorities.vue";
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        AddRole,
        ShowAuthorities,
    },
    data: () => ({
        snackbarStore: SnackbarStore(),
        datatable: {
            items: null,
            headers: [
                { text: "Nombre", value: "name", width: "75%" },
                { text: "", value: "", width: "25%" },
            ],
            search: "",
        },
        dialogs: {
            addRole: false,
            authorities: {
                show: false,
                roleName: null,
                items: [],
            },
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/roles");
        this.datatable.items = response.data;
    },
    methods: {
        newRoleSaved(newRole) {
            this.datatable.items.push(newRole);
            this.snackbarStore.open("Se creó el nuevo rol: " + newRole.name);
        },
        showAuthorities(item) {
            this.dialogs.authorities.show = true;
            this.dialogs.authorities.roleName = item.name;
            this.dialogs.authorities.items = item.authorities;
        },
        async deleteRole(item) {
            let response = await localAxios.delete("/admin/roles", {
                data: item,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(item);
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open("Se borró el rol " + item.name + ".");
            }
        },
    },
};
</script>
