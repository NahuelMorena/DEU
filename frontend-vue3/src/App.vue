<template>
    <v-app id="inspire" style="background-color: #eee">
        <BlockUiVue />
        <ForbiddenDialog />
        <router-view />
    </v-app>
</template>

<script>
import BlockUiVue from "./components/BlockUi.vue";
import ForbiddenDialog from "./components/dialogs/error/ForbiddenDialog.vue";

export default {
    components: {
        BlockUiVue,
        ForbiddenDialog,
    },
};
</script>
