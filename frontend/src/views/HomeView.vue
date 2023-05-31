<template>
    <div>
        <HeaderComponent title="Home view" />
        <v-btn color="error" @click="requestBloqueado">Request bloqueado</v-btn>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
    components: {
        HeaderComponent,
    },
    methods: {
        requestBloqueado() {
            localAxios.get("/api/blocked").then(() => {});
        },
    },
};
</script>
