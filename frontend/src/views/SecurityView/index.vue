<template>
    <div>
        <HeaderComponent title="Seguridad" />
        <v-container>
            <v-row>
                <v-col>
                    <Roles />
                </v-col>
                <v-col>
                    <Authorities />
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>
<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import Authorities from "./Authorities";
import Roles from "./Roles";
export default {
    components: { HeaderComponent, Roles, Authorities },
};
</script>
