<template>
    <div>
        <v-text-field
            @change="checkFormat"
            v-model="localValue"
            :label="label"
            :append-icon="append_icon"
            :append-outer-icon="append_outer_icon"
            :autofocus="autofocus"
            :background-color="background_color"
            :clear-icon="clear_icon"
            :clearable="clearable"
            :color="color"
            :counter="counter"
            :dark="dark"
            :dense="dense"
            :disabled="disabled"
            :error="error"
            :error-count="error_count"
            :error-messages="error_messages"
            :filled="filled"
            :flat="flat"
            :full-width="full_width"
            :height="height"
            :hide-details="hide_details"
            :hide-spin-buttons="hide_spin_buttons"
            :hint="hint"
            :id="id"
            :light="light"
            :loader-height="loader_height"
            :loading="loading"
            :messages="messages"
            :outlined="outlined"
            :persistent-hint="persistent_hint"
            :persistent-placeholder="persistent_placeholder"
            :placeholder="placeholder"
            :prefix="prefix"
            :prepend-icon="prepend_icon"
            :prepend-inner-icon="prepend_inner_icon"
            :readonly="readonly"
            :reverse="reverse"
            :rounded="rounded"
            :rules="rules"
            :shaped="shaped"
            :single_line="single_line"
            :solo="solo"
            :solo_inverted="solo_inverted"
            :success="success"
            :success-messages="success_messages"
            :suffix="suffix"
            :type="type"
            :validate-on_blur="validate_on_blur"
        >
        </v-text-field>
    </div>
</template>

<script>
import { Utils } from "@/Utils";

export default {
    props: {
        value: {
            required: false,
            type: Number,
            default: undefined,
        },
        label: {
            required: false,
            type: String,
            default: undefined,
        },
        append_icon: {
            required: false,
            type: String,
            default: undefined,
        },
        append_outer_icon: {
            required: false,
            type: String,
            default: undefined,
        },
        autofocus: {
            required: false,
            type: Boolean,
            default: false,
        },
        background_color: {
            required: false,
            type: String,
            default: undefined,
        },
        clear_icon: {},
        clearable: {
            required: false,
            type: Boolean,
            default: false,
        },
        color: {
            required: false,
            type: String,
            default: undefined,
        },
        counter: {
            required: false,
            type: String,
            default: undefined,
        },
        dark: {
            required: false,
            type: Boolean,
            default: false,
        },
        dense: {
            required: false,
            type: Boolean,
            default: false,
        },
        disabled: {
            required: false,
            type: Boolean,
            default: false,
        },
        error: {
            required: false,
            type: Boolean,
            default: false,
        },
        error_count: {
            required: false,
            type: Number,
            default: 1,
        },
        error_messages: {
            required: false,
            type: Array,
        },
        filled: {
            required: false,
            type: Boolean,
            default: false,
        },
        flat: {
            required: false,
            type: Boolean,
            default: false,
        },
        full_width: {
            required: false,
            type: Boolean,
            default: false,
        },
        height: {
            required: false,
            type: String,
            default: undefined,
        },
        hide_details: {
            required: false,
            type: Boolean,
            default: false,
        },
        hide_spin_buttons: {
            required: false,
            type: Boolean,
            default: false,
        },
        hint: {
            required: false,
            type: String,
            default: undefined,
        },
        id: {
            required: false,
            type: String,
            default: undefined,
        },
        light: {
            required: false,
            type: Boolean,
            default: false,
        },
        loader_height: {
            required: false,
            type: Number,
            default: 2,
        },
        loading: {
            required: false,
            type: Boolean,
            default: false,
        },
        messages: {
            required: false,
            type: Array,
        },
        outlined: {
            required: false,
            type: Boolean,
            default: false,
        },
        persistent_hint: {
            required: false,
            type: Boolean,
            default: false,
        },
        persistent_placeholder: {
            required: false,
            type: Boolean,
            default: false,
        },
        placeholder: {
            required: false,
            type: String,
            default: undefined,
        },
        prefix: {
            required: false,
            type: String,
            default: undefined,
        },
        prepend_icon: {
            required: false,
            type: String,
            default: undefined,
        },
        prepend_inner_icon: {
            required: false,
            type: String,
            default: undefined,
        },
        readonly: {
            required: false,
            type: Boolean,
            default: false,
        },
        reverse: {
            required: false,
            type: Boolean,
            default: false,
        },
        rounded: {
            required: false,
            type: Boolean,
            default: false,
        },
        rules: {
            required: false,
            type: Array,
        },
        shaped: {
            required: false,
            type: Boolean,
            default: false,
        },
        single_line: {
            required: false,
            type: Boolean,
            default: false,
        },
        solo: {
            required: false,
            type: Boolean,
            default: false,
        },
        solo_inverted: {
            required: false,
            type: Boolean,
            default: false,
        },
        success: {
            required: false,
            type: Boolean,
            default: false,
        },
        success_messages: {
            required: false,
            type: Array,
        },
        suffix: {
            required: false,
            type: String,
            default: undefined,
        },
        type: {
            required: false,
            type: String,
            default: "text",
        },
        validate_on_blur: {
            required: false,
            type: Boolean,
            default: false,
        },
    },
    data: () => ({
        localValue: 0,
    }),
    mounted() {
        this.localValue = this.value ? Utils.numberFormat(this.value) : null;
    },
    watch: {
        localValue: function (val) {
            this.$emit("input", val ? Utils.numberFormatEn(val) : null);
        },
    },
    methods: {
        checkFormat(value) {
            let parsedValue = Utils.numberFormatEn(value);
            this.localValue = Utils.numberFormat(parsedValue);
        },
    },
};
</script>
