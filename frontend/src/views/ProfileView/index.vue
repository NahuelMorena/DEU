<template>
    <div>
        <h1>Perfil del Usuario</h1>
        <div v-if="user">
            <p>Nombre: {{ user.name }}</p>
            <p>Apellido: {{ user.surname }}</p>
            <p>Email: {{ user.email }}</p>
            <p>Teléfono: {{ user.telephone }}</p>
            <p>Fecha de Nacimiento: {{ user.birthdate }}</p>
            <p>Tipo: {{ user.usertype }}</p>
            <!-- Otros datos del usuario -->
        </div>
        <div v-else>
            <p>Cargando...</p>
        </div>
    </div>
</template>

<script>
import { localAxios } from "@/axios";
import { SnackbarStore } from "@/store/snackbar";

export default {
    data() {
        return {
            snackbarStore: SnackbarStore(),
            user: null,
        };
    },
    async mounted() {
        try {
            const response = await localAxios.get("/admin/users/get-user"); // Reemplaza "1" por el ID del usuario que deseas obtener
            this.user = response.data;
            console.log(this.user);
        } catch (error) {
            console.error(error);
        }
    },
};
</script>
