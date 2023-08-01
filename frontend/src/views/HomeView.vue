<template>
    <div>
        <HeaderComponent title="Pantalla principal" />
        <v-container class="general-padding">
            <div class="card-container">
                <v-card
                    class="custom-card"
                    v-for="(option, j) in filteredItems"
                    :key="j"
                    link
                    :to="option.url"
                >
                    <v-card-title>{{ option.title }}</v-card-title>
                    <v-card-text>{{ option.description }}</v-card-text>
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
            </div>
        </v-container>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import HeaderComponent from "@/components/HeaderComponent.vue";
import { AuthStore } from "@/store/auth";

export default {
    data: () => ({
        items: [],
        authStore: AuthStore(),
    }),
    components: {
        HeaderComponent,
    },
    computed: {
        subMenuKeys: function () {
            return Object.keys(this.items);
        },
        filteredItems() {
            if (this.authStore.hasAuthority("USER")) {
                return this.items.filter(
                    (option) => option.subMenu !== "Entrenador"
                );
            }
            return this.items;
        },
    },
    mounted() {
        localAxios.get("/homeCardMenu").then((response) => {
            this.items = response.data;
        });
    },
    methods: {},
};
</script>

<style>
.card-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-auto-flow: row;
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

/* Estilos para pantallas grandes (hasta 3 cards por fila) */
@media (min-width: 1024px) {
    .card-container {
        grid-template-columns: repeat(3, 1fr);
    }
}

/* Estilos para pantallas medianas (hasta 2 cards por fila) */
@media (max-width: 1023px) {
    .card-container {
        grid-template-columns: repeat(2, 1fr);
    }
}

/* Estilos para pantallas pequeñas (1 card por fila) */
@media (max-width: 767px) {
    .card-container {
        grid-template-columns: repeat(1, 1fr);
    }
}
</style>
