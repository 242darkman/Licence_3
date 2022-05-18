<template>
    <div class="alert alert-success" role="alert">
        {{ message }}
    </div>
</template>

<script>
export default {
    name: "SuccessMessage",
    props : {
        message : String
    }
}
</script>

<style scoped>

</style>
