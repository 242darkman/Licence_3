<template>
    <div class="alert alert-success" role="alert">
        {{ message }}
        <button

            v-on:click="CLOSE_ALERT"

            id="close-button"
            type="button"
            class="btn-close"
            aria-label="Close">

        </button>
    </div>
</template>

<script>

export default {
    name: "SuccessMessage",
    props : {
        message : String
    },
    methods : {
        CLOSE_ALERT()
        {
            this.$store.dispatch('todos/REQUEST_SET_SUCCESS_MESSAGE','')
            this.$store.dispatch('accounts/REQUEST_SET_SUCCESS_MESSAGE','')

        }
    }
}
</script>

<style scoped>
#close-button
{
    float: right;
}
</style>