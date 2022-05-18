<template>

    <ion-page>

        <!--------------------------------------------------------------------------------------->

        <ion-header v-if="!isGameLaunch">
            <ion-toolbar>
               <ion-button slot="start" href="/" fill="clear"> 
                    <ion-icon slot="icon-only" src="/assets/icon/arrow-undo-circle.svg"></ion-icon> 
               </ion-button>
                
                <ion-title class="titre"> Accueil </ion-title>
            </ion-toolbar>
        </ion-header>


        <ion-content :fullscreen="true" v-if="!isGameLaunch && !isGameFinish">
            <div class="ion-content-start-screen">
                <div>
                    <h1>Au tour de l'équipe {{turnNumber}}</h1>
                </div>
                <div class="ion-content-button">
                    <ion-button color="light" v-on:click="LAUNCH_GAME">Commencer</ion-button>
                </div>
            </div>

        </ion-content>


        <!-- Si la partie est finie -->
        <ion-content :fullscreen="true" v-if="isGameFinish">

            <div class="ion-content-end-game">
                <h1>Partie terminée</h1>
                <h5 v-if="scoreFirstTeam > scoreSecondTeam">L'équipe 1 remporte la partie</h5>
                <h5 v-else-if="scoreFirstTeam == scoreSecondTeam">Egalité entre les deux équipes</h5>
                <h5 v-else-if="scoreFirstTeam < scoreSecondTeam">L'équipe 2 remporte la partie</h5>

                <ion-router-link href="/">
                    <ion-button color="light" v-on:click="LAUNCH_GAME">Retourner à l'accueil</ion-button>
                </ion-router-link>
            </div>


        </ion-content>


        <!--------------------------------------------------------------------------------------->

        <!--On affiche ce contenu si la partie a été lancé -->
        <ion-content :fullscreen="true" v-if="isGameLaunch">
            <!--Header de notre page-->
            <ion-header>
                <ion-toolbar>
                    <ion-title>{{ serie["serieName"] }}</ion-title>
                </ion-toolbar>
                <div class="ion-toolbar-info">
                    <p>Joker : {{joker}}</p>
                    <p v-if="turnNumber === 1">Score : {{scoreFirstTeam}}</p>
                    <p v-else>Score :  {{scoreSecondTeam}}</p>
                    <p>Compteur : {{compteur}}</p>
                </div>
            </ion-header>

            <ion-card class="ion-game-card">
                <ion-card-header>
                    <ion-card-subtitle>{{serieCardsListIndex + 1}} / {{serieCardsList.length}}</ion-card-subtitle>
                    <ion-card-title>{{ currentCard['cardName'] }}
                    </ion-card-title>
                </ion-card-header>

                <ion-card-content>
                    <p> - {{ currentCard["firstForbiddenWord"] }}</p>
                    <p> - {{ currentCard["secondForbiddenWord"] }}</p>
                    <p> - {{ currentCard["thirdForbiddenWord"] }}</p>
                    <p> - {{ currentCard["fourthForbiddenWord"] }}</p>
                </ion-card-content>
            </ion-card>



            <!--Les bouttons de jeu-->
            <footer class="ion-content-footer">
                <ion-button color="success" v-on:click="CARD_FOUND">Trouver</ion-button>
                <ion-button color="warning" v-on:click="CARD_PASS">Passer</ion-button>
                <ion-button color="danger"  v-on:click="CARD_TABOO">Taboo</ion-button>
            </footer>

        </ion-content>


        <!--------------------------------------------------------------------------------------->





    </ion-page>
</template>

<script>

import axios from 'axios';

export default {
    name: "SerieManagerCreate",
    data() {
        return {

            /**
             * Pour chaque équipe une serie sera pris aléatoirement
             */

            allSeries           : null,  //Toutes les séries
            serie               : null,  //La série de carte qui a été choisie
            serieCardsList      : null,  //Les cartes de cette série
            serieCardsListIndex : 0,     //L'index permettant de savoir à quel carte nous somme
            currentCard         : null,

            compteur            : 60,    //Le temps restant pour le joueur
            joker               : 3,     //Le nombre de joker restant

            scoreFirstTeam      : 0,     //Le score de l'équipe 1
            scoreSecondTeam     : 0,     //Le score de l'équipe 2

            isGameLaunch        : false, //Permet de savoir si la partie est lancé
            isGameFinish        : false, //Permet de savoir si la partie est finis
            turnNumber          : 1,     //Permet de savoir à quel tour de jeu on est

        }
    },
    methods : {

        /**
         * Permet de lancer la partie
         * @constructor
         */
        LAUNCH_GAME(){

            //On recupère l'index de la serie choisie
            const serieIndex    = Math.floor(Math.random() * this.allSeries.length)

            //On recupère la liste
            this.serie          = this.allSeries[serieIndex];

            //On recupère toute les carte de notre liste
            this.serieCardsList = this.serie["cardsList"];

            //On recupère la première carte
            this.currentCard    = this.serieCardsList[this.serieCardsListIndex]

            //On lance le compteur



            /**
             *  Si la taille de la liste est supérieur à 1 alors
             *  on supprime celle que nous allons jouer pour que
             *  la deuxième équipe ne puisse pas la jouer
             *
             *  sinon les deux jourons la même série de carte
             */
            if(this.serie.length > 1){

                this.allSeries.splice(serieIndex, 1);

            }


            this.isGameLaunch = true;
            this.COUNTDOWN();

        },


        /**
         * Si la carte à était trouver
         * @constructor
         */
        CARD_FOUND(){


            //En fonction du tour on ajoute 1 points à la bonne équipe si la carte est trouvé
            if(this.turnNumber === 1){
                this.scoreFirstTeam++;
            }else{
                this.scoreSecondTeam++;
            }

            this.CARD_COMMUN();

        },


        /**
         * Si un mot TABOO a été prononcer
         * @constructor
         */
        CARD_TABOO() {

            //En fonction du tour on enlève un point pour le TABOO à la bonne équipe
            if(this.turnNumber === 1){
                this.scoreFirstTeam--;
            }else{
                this.scoreSecondTeam--;
            }

            this.CARD_COMMUN();

        },

        /**
         * Permet d'utiliser un joker et passé la carte
         */
        CARD_PASS() {

            //On vérifie qu'il nous reste des jokers de disponible
            if(this.joker > 0){
                this.joker--;
                this.CARD_COMMUN();

            }


        },

        /**
         * Permet de rassembler les instructions commune entre nos fonctions
         * @constructor
         */
        CARD_COMMUN() {

            this.serieCardsListIndex++; //On passe à la carte suivante
            this.currentCard    = this.serieCardsList[this.serieCardsListIndex]

            //On vérifie si le tour est finis

            if(this.serieCardsListIndex === this.serieCardsList.length){

                this.isGameLaunch        = false; //On remet la fenêtre de lancement

                this.serieCardsListIndex = 0;     //On remet les variables par défaut pour l'équipe 2
                this.compteur            = 60;
                this.joker               = 3;

                console.log(this.scoreFirstTeam);
                console.log(this.scoreSecondTeam);

                //Si ces le tour 2 qui vient de se finir alors la partie est terminé
                if(this.turnNumber === 2){
                    this.isGameFinish = true;
                    this.scoreSecondTeam += this.compteur;
                    this.GAME_FINISH(); //Une fois la partie fini on sauvegarde la partie dans l'historique
                }else{
                    this.scoreFirstTeam += this.compteur;
                }

                this.turnNumber++; //On pass au tour suivant

            }

        },

        /**
         * Permet de gérer le compteur
         * @constructor
         */
        COUNTDOWN() {
            if(this.isGameLaunch){
                if(this.compteur > 0) {
                    setTimeout(() => {

                        this.compteur -= 1
                        this.COUNTDOWN()
                    }, 1000)
                }else{

                    this.isGameLaunch = false;

                    if(this.turnNumber === 2){
                        this.isGameFinish = true;
                        this.GAME_FINISH(); //Une fois la partie fini on sauvegarde la partie dans l'historique

                    }


                }

            }
        },

        /**
         * Permet de sauvegarder le résultat de la partie dans l'historique
         * @constructor
         */
        GAME_FINISH(){

            let winner = "Equipe 1";

            if(this.scoreFirstTeam > this.scoreSecondTeam){
                winner = "Equipe 1";

            }else if(this.scoreFirstTeam < this.scoreSecondTeam){
                winner = "Equipe 2";
            }else{
                winner = "Egalité";
            }

            //On sauvegarde dans l'api

            axios.post("https://6060e5e2ac47190017a70428.mockapi.io/gameHistory", {
                winner          : winner,
                scoreFirstTeam  : this.scoreFirstTeam,
                scoreSecondTeam : this.scoreSecondTeam,
                playAt          : this.currentDate()
            })
                .then(response => {

                    this.allSeries = response["data"];

                })
                .catch(error => {

                    console.log(error);

                })

        },

        /**
         * Permet d'obtenir la date en cours
         * source : https://reactgo.com/vue-get-current-date/
         * @returns {string}
         */
        currentDate() {
            const current = new Date();
            const date = `${current.getDate()}/${current.getMonth()+1}/${current.getFullYear()}`;
            return date;
        }

    },
    mounted() {

        //On va récupérer toutes nos séries depuis notre API

        axios.get("https://6060e5e2ac47190017a70428.mockapi.io/series")
            .then(response => {

                this.allSeries = response["data"];

            })
            .catch(error => {

                console.log(error);

            })


    }
}
</script>



<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap');

.ion-content-end-game{
    margin-top: 150px;
    text-align: center;
    text-transform: uppercase;
}

.ion-content-end-game h1, .ion-content-end-game h5{
    color: white;
}

.ion-content-start-screen{
    margin-top: 300px;
    color: white;
    text-align: center;

}


.ion-game-card{
    margin-top: 150px;
    --ion-background-color : white;

}



.ion-content-footer{
    margin-top: 50px;
    width: 100vw;
    display: flex;
    justify-content: space-around;
}



.ion-toolbar-info{
    display : flex;
    justify-content : space-around;

}



ion-toolbar{
    --ion-background-color : white !important;
    text-align: center;
}

ion-content{
    position: relative;
    --ion-background-color : linear-gradient(348.02deg, #9C1176 6.53%, #F93C42 90.02%);
}


.titre {
    text-align: center;
    margin-top: 10%;
    margin-right: 20.5%;
}

ion-icon {
    color: #9C1176;
}

</style>