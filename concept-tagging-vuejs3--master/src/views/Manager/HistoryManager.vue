<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
                <ion-button slot="start" href="/" fill="clear"> 
                    <ion-icon slot="icon-only" src="/assets/icon/arrow-undo-circle.svg"></ion-icon> 
               </ion-button>

                <ion-title class="titre">Historique des parties</ion-title>
            </ion-toolbar>
        </ion-header>



        <ion-content :fullscreen="true">
            <ion-header collapse="condense">
                <ion-toolbar>
                    <ion-title size="large">Tab 1</ion-title>
                </ion-toolbar>
            </ion-header>

            <ion-card class="ion-card-serie" v-for="game in allGames" v-bind:key="game">
                <ion-card-header>
                    <ion-card-subtitle>{{game['playAt']}}</ion-card-subtitle>
                    <ion-card-title>Score Equipe 1 : {{game['scoreFirstTeam']}}</ion-card-title>
                    <ion-card-title>Score Equipe 2 : {{game['scoreSecondTeam']}}</ion-card-title>

                </ion-card-header>

                <ion-card-content>
                    Gagnant : {{game['winner']}}
                </ion-card-content>
            </ion-card>

        </ion-content>
    </ion-page>
</template>

<script>
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonIcon, IonButton} from '@ionic/vue';
import axios from 'axios';

export default  {
    name: 'HistoryManager',
    components: { IonHeader, IonToolbar, IonTitle, IonContent, IonPage, IonIcon, IonButton},
    data() {
        return {

            //Nos séries de cartes
            allGames           : null,
        }
    },

    mounted() {

        //On récupère toute nos séries depuis l'API
        axios.get("https://6060e5e2ac47190017a70428.mockapi.io/gameHistory")
            .then(response => {

                this.allGames = response["data"].reverse();

                console.log(this.allGames);

            })
            .catch(error => {

                console.log(error);

            })
    }

}
</script>


<style scoped>

.add-form input{
    margin-top: 10px;
    width: 50vw;
    text-align: center;
    border: 0px;
    border-bottom: 1px solid #2a2a2a;
}

.ion-toolbar-add-serie{
    margin-top: 50px;
    text-align: center;
    --ion-background-color : rgba(0,0,0,0);
}

ion-content{
    position: relative;
    --ion-background-color : linear-gradient(348.02deg, #9C1176 6.53%, #F93C42 90.02%);
}

.ion-card-serie{
    text-align: center;
    margin-top: 20px;
    --ion-background-color : white;
}


ion-icon {
    color:#9C1176;
    margin: 10%;
}

.titre {
    text-align: center;
    margin-top: 10%;
    margin-right: 20.5%;
}

</style>
