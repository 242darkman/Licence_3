<template>


    <app-layout>
        <template #header>
            <h2 class="font-semibold text-xl text-gray-800 leading-tight">
                Création de contenu
            </h2>
        </template>

            <div class="container-fluid">
                <Serie />
                <Card :all_series="all_series"/>
            </div>

    </app-layout>

</template>

<script>

import Card from '../../components/Create/Card';
import Serie from '../../components/Create/Serie';
import AppLayout from "@/Layouts/AppLayout";

export default {
    name: "Create",
    components : {
        Card,
        Serie,
        AppLayout
    },
    props : ["all_series"]
}
</script>

<style scoped>

</style>
