<template>
    <div class="alert alert-warning" role="alert">
        {{ message }}
    </div>
</template>

<script>
export default {
    name: "ErrorMessage",
    props : {
        message : String
    }
}
</script>

<style scoped>

</style>
