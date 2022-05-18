<template>
    <app-layout>
    <template #header>

        <div class="container-fluid d-flex justify-content-center">
            <div class="col card-compteur">
                <p>Temps restant : {{compteur}}</p>
            </div>
            <div class="col card-compteur">
                <p>Score : {{score}}</p>
            </div>
            <div class="col card-compteur">
                <p>{{number_of_cards_do}} / {{all_cards.length}}</p>
            </div>
            <div class="col card-compteur">
                <p>Passer : {{next}}</p>
            </div>


        </div>

    </template>

        <div class="container w-25 shadow-lg rounded" v-if="!is_finish && begin">
            <div class="card text-center mt-5">
                <div class="card-header">
                    {{this.all_cards[current_card_index].card_name}}
                </div>
                <div class="card-body">
                    <h5 class="card-title">{{this.all_cards[current_card_index].first_forbidden_word}}</h5>
                    <h5 class="card-title">{{this.all_cards[current_card_index].second_forbidden_word}}</h5>
                    <h5 class="card-title">{{this.all_cards[current_card_index].third_forbidden_word}}</h5>
                    <h5 class="card-title">{{this.all_cards[current_card_index].fourth_forbidden_word}}</h5>
                </div>
                <div class="card-footer text-muted">
                    <div class="btn-group mb-3" role="group" aria-label="Basic mixed styles example">
                        <button v-on:click="TABOO" type="button" class="btn btn-danger m-1">Taboo</button>
                        <button v-if="next != 0" v-on:click="NEXT" type="button" class="btn btn-primary m-1">Passer</button>
                        <button v-on:click="CORRECT" type="button" class="btn btn-success m-1">Correct</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="main container mt-5 shadow-lg p-3 mb-5 bg-body rounded d-flex justify-content-center" v-else v-if="begin">

        <div class="row" >
            <h3>Bravo votre score est de : {{score}}</h3>
            <button v-on:click="GO_HOME" class="btn btn-outline-primary">Retourner à l'accueil </button>
        </div>


    </div>

    <div v-if="!begin" class="main container mt-5 shadow-lg p-3 mb-5 bg-body rounded d-flex justify-content-center" >

        <button v-on:click="START" class="btn btn-outline-primary">Commencer la partie </button>

    </div>

</app-layout>
</template>

<script>
import AppLayout from '@/Layouts/AppLayout'
import { Inertia } from '@inertiajs/inertia'

export default {
    name : "Game",
    components: {
        AppLayout,
    },
    data() {
        return {
            compteur           : 60,
            current_card_index : 0,
            score              : 0,
            next               : 3,
            number_of_cards_do : 0,
            is_finish          : false,
            begin              : false,
            data               : {
                score : null
            }

        }
    },
    props : ['all_cards'],
    methods : {

        TABOO()
        {
            this.score--;
            this.current_card_index++;
            this.number_of_cards_do++;
        },
        NEXT()
        {
            if(this.next != 0) {
                this.current_card_index++;
                this.next--;
                this.number_of_cards_do++;

            }

        },

        CORRECT()
        {
            this.score++;
            this.current_card_index++;
            this.number_of_cards_do++;

        },

        SCORE_CALCUL()
        {
            this.score += this.next + this.compteur;
        },
        START()
        {
            this.begin = true;
            this.compteur--;
        },
        GO_HOME()
        {
            this.$inertia.post('/game/update',this.data)
        }

    },
    watch : {

        number_of_cards_do : function()
        {
            if(this.number_of_cards_do === this.all_cards.length ){
                this.SCORE_CALCUL();
                this.compteur = 0;
                this.is_finish = true;
            }
        },

        compteur : function()
        {

            if(!this.is_finish) {
                setTimeout(() => {                                                                     //Nous redirigions après une seconde vers la page de gestion des todos

                    if(this.compteur > 0)
                    {
                        this.compteur--;
                    }
                }, 1000)
            }else{

               this.SCORE_CALCUL();
               this.is_finish = true;
               this.data.score = this.score

            }
        }
    }
}
</script>

