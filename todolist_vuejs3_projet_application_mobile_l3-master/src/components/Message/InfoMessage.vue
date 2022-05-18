<template>
    <div class="alert alert-warning" role="alert">
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
    name: "InfoMessage",
    props : {
        message : String
    },
    methods : {

        CLOSE_ALERT()
        {
            this.$store.dispatch('todos/REQUEST_SET_INFO_MESSAGE','')
            this.$store.dispatch('accounts/REQUEST_SET_INFO_MESSAGE','')

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