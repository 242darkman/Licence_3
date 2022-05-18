<template>
    <app-layout>
        <template #header>
            <h2 class="font-semibold text-xl text-gray-800 leading-tight">
                Jouer une partie
            </h2>
        </template>

        <div class="mt-5" v-for="serie in all_series" v-bind:key="serie" v-if="all_series.length != 0">
            <div class="max-w-2xl mx-auto sm:px-6 lg:px-8">
                <div class="overflow-hidden shadow-md text-gray-100">
                    <!-- card header -->
                    <div class="px-6 py-4 bg-gray-800 border-b border-gray-600 font-bold uppercase">
                        {{ serie.serie_name }}
                    </div>
                    <div class="p-6 bg-gray-800 border-gray-200 text-right">
                        <!-- button link -->
                        <a class="bg-blue-500 shadow-md text-sm m-2 text-white font-bold py-3 md:px-8 px-4 hover:bg-blue-400 rounded uppercase"
                           :href="'game/play/'+serie.id">Jouer</a>

                        <a class="bg-yellow-300 shadow-md text-sm m-2 text-white font-bold py-3 md:px-8 px-4 hover:bg-yellow-200 rounded uppercase"
                           :href="'manage/'+serie.id">Gérer</a>

                    </div>
                </div>
            </div>
        </div>


        <div class="container mt-5"  v-else>
            <div class="row text-center">

                <h1>Aucune partie</h1>

            </div>
        </div>




    </app-layout>
</template>

<script>
    import AppLayout from '@/Layouts/AppLayout'
    import Welcome from '@/Jetstream/Welcome'

    export default {
        components: {
            AppLayout,
            Welcome,
        },
        props : ['all_series']
    }
</script>
