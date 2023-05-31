<template>
    <div>
        <HeaderComponent title="Listado de roles" />
        <v-container>
            <v-card>
                <v-card-text>
                    <v-data-table
                        :headers="headers"
                        :items="items"
                        :items-per-page="5"
                        class="elevation-0"
                    >
                        <template v-slot:item="{ item }">
                            <tr>
                                <td>{{ item.name }}</td>
                                <td>
                                    <v-btn icon>
                                        <v-icon>mdi-key``</v-icon>
                                    </v-btn>
                                </td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-card-text>
            </v-card>
        </v-container>
    </div>
</template>
<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
    components: {
        HeaderComponent,
    },
    data: () => ({
        headers: [
            { text: "Nombre", value: "name", width: "90%" },
            {
                text: "",
                value: "id",
                width: "10%",
            },
        ],
        items: [],
    }),
    mounted() {
        localAxios.get("/admin/roles").then((response) => {
            this.items = response.data;
        });
    },
};
</script>
