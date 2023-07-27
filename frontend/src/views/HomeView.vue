<template>
    <div>
        <HeaderComponent title="Pantalla principal" />
        <v-container class="general-padding">
            <!--
                <v-btn color="error" @click="requestBloqueado">Request bloqueado</v-btn>
            -->
            <div class="card-container">
                <v-card class="custom-card">
                    <v-card-title>Organiza tus entrenamientos</v-card-title>
                    <v-card-text>
                        Administra los entrenamientos existentes, crea nuevos, o
                        elimine los que ya no necesite.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                </v-card>
                <v-card class="custom-card">
                    <v-card-title>Coordina a tus jugadores</v-card-title>
                    <v-card-text>
                        Administra los jugadores existentes, registra nuevos
                        jugadores, o elimine a los que ya no cuenten en el
                        equipo.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                    <!-- Contenido de la tarjeta 2 -->
                </v-card>
                <v-card class="custom-card">
                    <v-card-title>Estadisticas de rendimiento</v-card-title>
                    <v-card-text>
                        Observe los rendimientos de sus jugadores.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                    <!-- Contenido de la tarjeta 3 -->
                </v-card>
                <v-card class="custom-card">
                    <v-card-title>Entrenadores</v-card-title>
                    <v-card-text>
                        Administra los entrenadores existentes, agregue nuevos,
                        o elimine a los que ya no ejerzan.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                    <!-- Contenido de la tarjeta 4 -->
                </v-card>
                <v-card class="custom-card">
                    <v-card-title>Ver mis datos</v-card-title>
                    <v-card-text>
                        Administre sus propios datos de usuario.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                    <!-- Contenido de la tarjeta 5 -->
                </v-card>
                <v-card class="custom-card">
                    <v-card-title>Configuración</v-card-title>
                    <v-card-text>
                        Modifique la interfaz, como la paleta de colores o el
                        tamaño de las fuentes del sistema.
                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            class="bottom-left-btn"
                            type="submit"
                            aria-label="Ver mas"
                            color="rgba(34, 56, 67, 0.85)"
                            dark
                            >Ver mas</v-btn
                        >
                    </v-card-actions>
                    <!-- Contenido de la tarjeta 6 -->
                </v-card>
            </div>
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
    methods: {
        requestBloqueado() {
            localAxios.get("/api/blocked").then(() => {});
        },
    },
};
</script>

<style>
.card-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap: 20px;
}

.custom-card {
    position: relative;
    padding-bottom: 30px;
    margin-bottom: 20px;
}

.bottom-left-btn {
    position: absolute;
    left: 10px;
    bottom: 10px;
}
</style>
