<template>
    <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        transition="scale-transition"
        offset-y
        min-width="auto"
    >
        <template v-slot:activator="{ on, attrs }">
            <v-text-field
                v-model="dateFormatted"
                :label="label"
                :prepend-icon="prependicon"
                :prepend-inner-icon="prependinnericon"
                v-bind="attrs"
                v-on="on"
                :dark="dark"
                :filled="filled"
                :disabled="disabled"
                hide-details
            ></v-text-field>
        </template>
        <v-date-picker
            :disabled="disabled"
            :type="type"
            v-model="date"
            :min="min"
            locale="es-es"
            @change="save"
            @input="menu = false"
        ></v-date-picker>
    </v-menu>
</template>

<script>
import moment from "moment";
export default {
    props: {
        value: { type: String },
        label: { type: String },
        prependicon: { type: String },
        prependinnericon: { type: String },
        type: { type: String, default: "date" },
        dark: Boolean,
        filled: Boolean,
        min: { type: String, default: "1922-01-01" },
        disabled: Boolean,
    },
    data() {
        return {
            date: this.value,
            dateFormatted: null,
            menu: false,
        };
    },
    watch: {
        value: function (val) {
            this.save(val);
        },
    },
    mounted() {
        moment.locale("es");
        this.save(this.value);
    },
    methods: {
        save(date) {
            if (!date) return null;
            this.$refs.menu.save(date);
            this.date = date;
            if (this.type == "month") {
                this.dateFormatted = moment(date, "YYYY-MM").format(
                    "MMMM YYYY"
                );
            } else {
                this.dateFormatted = moment(date, "YYYY-MM-DD").format(
                    "DD/MM/YYYY"
                );
            }
            this.$emit("input", date);
        },
    },
};
</script>

<style></style>
