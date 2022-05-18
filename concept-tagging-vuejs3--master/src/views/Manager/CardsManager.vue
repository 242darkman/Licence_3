<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
                <ion-title>Gérer mes cartes</ion-title>
            </ion-toolbar>
        </ion-header>



        <ion-content :fullscreen="true">
            <ion-header collapse="condense">
                <ion-toolbar>
                    <ion-title size="large">Tab 1</ion-title>
                </ion-toolbar>
            </ion-header>

            <ion-toolbar class="ion-toolbar-add-serie">

                <ion-button href="/app/home" color="light" v-on:click="SHOW_CREATE_CARD_FORM">Retourner au menu</ion-button><br />
                <ion-button v-if="!showFormCreateCard" color="dark" v-on:click="SHOW_CREATE_CARD_FORM">Créer une carte</ion-button>
                <ion-button v-else color="dark" v-on:click="SHOW_CREATE_CARD_FORM">Cliquez-ici pour fermer</ion-button>

            </ion-toolbar>

            <ion-card class="ion-card-serie add-form" v-if="showFormCreateCard">
                <ion-card-header>
                    <ion-card-subtitle>Créer une carte</ion-card-subtitle>

                    <input v-model="cardName" type="text" placeholder="Nom de la carte ...." />
                    <input v-model="firstForbiddenWord" type="text" placeholder="Premier mot interdit ...." />
                    <input v-model="secondForbiddenWord" type="text" placeholder="Deuxième mot interdit ...." />
                    <input v-model="thirdForbiddenWord" type="text" placeholder="Troisième mot interdit ...." />
                    <input v-model="fourthForbiddenWord" type="text" placeholder="Quatrième mot interdit ...." />

                </ion-card-header>

                <ion-card-content>
                    <ion-button color="dark" v-on:click="CREATE_CARD">Créer la carte</ion-button>
                </ion-card-content>
            </ion-card>

            <ion-card class="ion-card-serie" v-for="(card, index) in allCards" v-bind:key="card">
                <ion-card-header>
                    <ion-card-title>Nom de la carte : {{card['cardName']}}</ion-card-title>
                    <ion-card-subtitle>1er mot interdit: {{card['firstForbiddenWord']}}</ion-card-subtitle>
                    <ion-card-subtitle>2ème mot interdit: {{card['secondForbiddenWord']}}</ion-card-subtitle>
                    <ion-card-subtitle>3ème mot interdit: {{card['thirdForbiddenWord']}}</ion-card-subtitle>
                    <ion-card-subtitle>4ème mot interdit: {{card['fourthForbiddenWord']}}</ion-card-subtitle>

                </ion-card-header>

                <ion-card-content>
                    <ion-button color="danger" v-on:click="DELETE_CARD(card['id'], index)">Supprimer</ion-button>
                </ion-card-content>
            </ion-card>

        </ion-content>
    </ion-page>
</template>

<script>
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/vue';
import axios from 'axios';


export default  {
    name: 'Tab1',
    components: { IonHeader, IonToolbar, IonTitle, IonContent, IonPage },
    data() {
        return {

            //Nos séries de cartes
            allCards           : null,
            showFormCreateCard : false,
            cardName    : "",
            firstForbiddenWord    : "",
            secondForbiddenWord    : "",
            thirdForbiddenWord    : "",
            fourthForbiddenWord    : "",
            serieId : this.$route.params.serieId
        }
    },
    methods : {

        DELETE_CARD(id){

            //On supprime la série de l'API

            axios.delete("https://6060e5e2ac47190017a70428.mockapi.io/series/" + this.serieId + "/cardsList/" + id)
                .then(response => {
                    console.log(response)
                    window.location.reload();

                })
                .catch(error => {

                    console.log(error);

                })

        },

        CREATE_CARD(){


            if(this.cardName.length > 0 && this.firstForbiddenWord.length > 0 && this.secondForbiddenWord.length > 0 && this.thirdForbiddenWord.length > 0 && this.fourthForbiddenWord.length > 0) {

                axios.post("https://6060e5e2ac47190017a70428.mockapi.io/series/" + this.serieId + "/cardsList", {
                    cardName: this.cardName,
                    firstForbiddenWord: this.firstForbiddenWord,
                    secondForbiddenWord: this.secondForbiddenWord,
                    thirdForbiddenWord: this.thirdForbiddenWord,
                    fourthForbiddenWord: this.fourthForbiddenWord
                    })
                    .then(response => {

                        console.log(response);
                        window.location.reload();

                    })
                    .catch(error => {

                        console.log(error);

                    })

                }
        },

        SHOW_CREATE_CARD_FORM()
        {
            this.showFormCreateCard = !this.showFormCreateCard;
        }

    },
    mounted() {

        //On récupère toute nos séries depuis l'API
        axios.get("https://6060e5e2ac47190017a70428.mockapi.io/series/" + this.serieId + "/cardsList")
            .then(response => {

                this.allCards = response["data"].reverse();

                console.log(this.allCards);

            })
            .catch(error => {

                console.log(error);

            })
    }

}
</script>


<style scoped>

.add-form input{
    margin-top: 30px;
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

</style>
