<template>
    <app-layout>
        <template #header>
            <a :href="'delete/serie/'+serie.id"><button class="btn btn-outline-danger">Supprimer la série</button></a>
        </template>

        <div class="container w-25">
            <div class="card text-center mt-5" v-for="card in all_cards">
                <div class="card-header">
                    {{card.card_name}}
                </div>
                <div class="card-body">
                    <h5 class="card-title">{{card.first_forbidden_word}}</h5>
                    <h5 class="card-title">{{card.second_forbidden_word}}</h5>
                    <h5 class="card-title">{{card.third_forbidden_word}}</h5>
                    <h5 class="card-title">{{card.fourth_forbidden_word}}</h5>
                </div>
                <div class="card-footer text-muted">
                    <a :href="'delete/card/'+card.id"><button class="btn btn-outline-danger">Supprimer la carte</button></a>

                </div>
            </div>
        </div>

    </app-layout>
</template>

<script>
import AppLayout from '@/Layouts/AppLayout'
import Welcome from '@/Jetstream/Welcome'
import Button from "@/Jetstream/Button";

export default {
    name : "Manage",
    components: {
        Button,
        AppLayout,
        Welcome,
    },
    props : ['serie','all_cards']
}
</script>


