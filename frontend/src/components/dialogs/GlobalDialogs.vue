<template>
    <div>
        <SessionExpired />
        <ForbiddenDialog />
        <UnexpectedError />
        <Snackbar />
    </div>
</template>

<script>
import ForbiddenDialog from "./error/ForbiddenDialog.vue";
import SessionExpired from "./error/SessionExpired.vue";
import UnexpectedError from "./error/UnexpectedError.vue";
import Snackbar from "./snackbar.vue";

export default {
    components: {
        ForbiddenDialog,
        SessionExpired,
        UnexpectedError,
        Snackbar,
    },
};
</script>
