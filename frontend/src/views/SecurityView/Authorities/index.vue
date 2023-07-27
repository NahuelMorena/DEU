<template>
    <div>
        <v-card v-if="datatable.items">
            <v-card-title> Permisos </v-card-title>
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
                            aria-label="Agregar permiso"
                            @click="dialogs.addAuthority = true"
                        >
                            Agregar permiso
                        </v-btn>
                    </template>
                </v-text-field>
                <v-data-table
                    :headers="datatable.headers"
                    :items="datatable.items"
                    :search="datatable.search"
                    :items-per-page="5"
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
                                            aria-label="Borrar permiso"
                                            @click="deleteAuthority(item)"
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
        <AddAuthority
            v-model="dialogs.addAuthority"
            @saved="(newAuthority) => newAuthoritySaved(newAuthority)"
        />
        <v-dialog v-model="dialogs.deleteAuthority" max-width="500px">
            <v-card>
                <v-card-title>Confirmar eliminación</v-card-title>
                <v-card-text>
                    ¿Deseas eliminar la autorización seleccionada?
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        @click="dialogs.deleteAuthority = false"
                    >
                        <v-icon left>mdi-close</v-icon>
                        Cancelar</v-btn
                    >
                    <v-btn
                        color="rgba(34, 56, 67, 0.85)"
                        dark
                        @click="confirmDelete"
                    >
                        <v-icon left>mdi-delete</v-icon>
                        Eliminar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import AddAuthority from "./addAuthority.vue";
import { SnackbarStore } from "@/store/snackbar";

export default {
    components: {
        AddAuthority,
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
            addAuthority: false,
            deleteAuthority: false,
        },
    }),
    async mounted() {
        let response = await localAxios.get("/admin/authorities");
        this.datatable.items = response.data;
    },
    methods: {
        newAuthoritySaved(newAuthority) {
            this.datatable.items.push(newAuthority);
            this.snackbarStore.open(
                "Se creó el nuevo permiso: " + newAuthority.name + "."
            );
        },
        deleteAuthority(item) {
            this.dialogs.deleteAuthority = true;
            this.authorityToDelete = item;
        },
        async confirmDelete() {
            let response = await localAxios.delete("/admin/authorities", {
                data: this.authorityToDelete,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(
                    this.authorityToDelete
                );
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open(
                    "Se borró el permiso " + this.authorityToDelete.name + "."
                );
            }
            this.dialogs.deleteAuthority = false;
        },
    },
};
</script>
