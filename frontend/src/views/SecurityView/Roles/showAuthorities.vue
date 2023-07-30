<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent>
            <v-card style="width: 600px">
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Permisos para el rol: {{ roleName }}</div>
                    <div>
                        <v-btn
                            icon
                            @click="closeAll"
                            aria-label="Cerrar ventana"
                        >
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-text>
                    <ul>
                        <li
                            v-for="authority in authorities"
                            :key="authority.id"
                        >
                            {{ authority.name }}
                        </li>
                    </ul>
                </v-card-text>
                <v-card-actions class="d-flex justify-end">
                    <v-btn color="rgba(34, 56, 67, 0.85)" dark @click="closeAll"
                        >Cerrar</v-btn
                    >
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
export default {
    props: {
        value: { type: Boolean },
        roleName: { type: String },
        authorities: { type: Array },
    },
    data: () => ({
        localShow: false,
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    async mounted() {},
    methods: {
        closeAll() {
            this.$emit("input", false);
        },
    },
};
</script>
<style></style>
