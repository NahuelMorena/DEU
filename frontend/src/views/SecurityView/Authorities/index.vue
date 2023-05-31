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
        async deleteAuthority(item) {
            let response = await localAxios.delete("/admin/authorities", {
                data: item,
            });
            if (response.status == 200) {
                const index = this.datatable.items.indexOf(item);
                if (index >= 0) this.datatable.items.splice(index, 1);
                this.snackbarStore.open(
                    "Se borró el permiso " + item.name + "."
                );
            }
        },
    },
};
</script>
