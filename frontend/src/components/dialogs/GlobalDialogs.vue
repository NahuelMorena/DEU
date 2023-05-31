<template>
    <div>
        <SessionExpired />
        <ForbiddenDialog />
        <UnexpectedError />
    </div>
</template>

<script>
import ForbiddenDialog from "./error/ForbiddenDialog.vue";
import SessionExpired from "./error/SessionExpired.vue";
import UnexpectedError from "./error/UnexpectedError.vue";

export default {
    components: {
        ForbiddenDialog,
        SessionExpired,
        UnexpectedError,
    },
};
</script>
