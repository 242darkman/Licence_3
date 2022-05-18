<template>

    <div id="bar-loading" class="container shadow-lg p-3 mb-5 bg-body rounded">
        <h1>{{text_to_show}}</h1>
        <div class="progress">
            <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
        </div>
    </div>

</template>

<script>
export default {
    name: "BarLoading",
    props : {
        text_to_show : String
    }
}
</script>

<style scoped>
    #bar-loading
    {
        margin-top: 15%;
    }
</style>