<template>
    <v-container>
        <div class="googlefont">{{ title }}</div>
    </v-container>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@350&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Didact+Gothic&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Red+Hat+Display:wght@350&display=swap");
.googlefont {
    font-size: 50px;
    font-family: "Didact Gothic", sans-serif;
}
</style>

<script>
export default {
    props: {
        title: {
            type: String,
            required: true,
        },
    },
};
</script>
