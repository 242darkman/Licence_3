<template>
    <app-layout>
        <template #header>
            <h2 class="font-semibold text-xl text-gray-800 leading-tight">
                Classement des meilleurs utilisateurs
            </h2>



        </template>

        <div>
            <table class="rounded-t-lg m-5 w-5/6 mx-auto bg-gray-200 text-gray-800">
                <tr class="text-left border-b-2 border-gray-300">
                    <th class="px-4 py-3">Position</th>
                    <th class="px-4 py-3">Pseudo</th>
                    <th class="px-4 py-3">Score</th>
                </tr>

                <tr class="bg-gray-100 border-b border-gray-200" v-for="(user, key) in all_user" v-bind:key="key">
                    <td class="px-4 py-3">{{ key + 1 }}</td>
                    <td class="px-4 py-3">{{ user.name }}</td>
                    <td class="px-4 py-3">{{ user.user_score }}</td>

                </tr>

            </table>

        </div>

    </app-layout>
</template>

<script>

import AppLayout from "@/Layouts/AppLayout";


export default {
    name: "LeaderBoard",
    components : {
        AppLayout
    },
    props : ['user_score','all_user'],

    mounted() {
        console.log(this.user_score)
    }

}
</script>

<style scoped>

</style>
