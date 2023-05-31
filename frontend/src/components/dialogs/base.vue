<template>
    <v-overlay :value="localShow">
        <v-dialog v-model="localShow" hide-overlay persistent width="800">
            <v-card>
                <v-card-title
                    class="d-flex justify-space-between align-center mb-4"
                >
                    <div>Titulo</div>
                    <div>
                        <v-btn icon @click="close">
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </div>
                </v-card-title>
                <v-card-subtitle class="mt-3"> subtitulo </v-card-subtitle>
                <v-card-text> cosas </v-card-text>
            </v-card>
        </v-dialog>
    </v-overlay>
</template>

<script>
export default {
    props: {
        value: { type: Boolean },
    },
    data: () => ({
        localShow: false,
    }),
    watch: {
        value: function (val) {
            this.localShow = val;
        },
    },
    mounted() {},
    methods: {
        close() {
            this.$emit("input", false);
        },
    },
};
</script>
<style></style>
