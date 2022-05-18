<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
                <ion-button slot="start" href="/" fill="clear"> 
                    <ion-icon slot="icon-only" src="/assets/icon/arrow-undo-circle.svg"></ion-icon> 
               </ion-button>

                <ion-title class="titre">Mes cartes</ion-title>
            </ion-toolbar>
        </ion-header>



        <ion-content :fullscreen="true">
            <ion-header collapse="condense">
                <ion-toolbar>
                    <ion-title size="large">Tab 1</ion-title>
                </ion-toolbar>
            </ion-header>

            <ion-toolbar class="ion-toolbar-add-serie">
                <ion-button v-if="!showFormCreateSerie" color="dark" v-on:click="SHOW_CREATE_SERIE_FORM">Créer une série</ion-button>
                <ion-button v-else color="dark" v-on:click="SHOW_CREATE_SERIE_FORM">Cliquez-ici pour fermer</ion-button>

            </ion-toolbar>

            <ion-card class="ion-card-serie add-form" v-if="showFormCreateSerie">
                <ion-card-header>
                    <ion-card-subtitle>Créer une série</ion-card-subtitle>
                    <input v-model="serieName" type="text" placeholder="Nom de la série ...." />
                </ion-card-header>

                <ion-card-content>
                    <ion-button color="dark" v-on:click="CREATE_SERIE">Créer la série</ion-button>
                </ion-card-content>
            </ion-card>

            <ion-card class="ion-card-serie" v-for="serie in allSeries" v-bind:key="serie">
                <ion-card-header>
                    <ion-card-subtitle>Nombre de cartes : {{serie['cardsList'].length}}</ion-card-subtitle>
                    <ion-card-title>{{ serie['serieName'] }}</ion-card-title>
                </ion-card-header>

                <ion-card-content>
                    <ion-button v-bind:href="'/cardsManager/'+ serie['id']"  color="warning">Gérer les cartes</ion-button>
                    <ion-button color="danger" v-on:click="DELETE_SERIE(serie['id'])">Supprimer</ion-button>
                </ion-card-content>
            </ion-card>

        </ion-content>
    </ion-page>
</template>

<script>
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonIcon, IonButton} from '@ionic/vue';
import axios from 'axios';

export default  {
    name: 'Tab1',
    components: { IonHeader, IonToolbar, IonTitle, IonContent, IonPage, IonIcon, IonButton},
    data() {
        return {

            //Nos séries de cartes
            allSeries           : null,
            showFormCreateSerie : false,
            serieName    : ""
        }
    },
    methods : {

        DELETE_SERIE(id){

            //On supprime la série de l'API

            axios.delete("https://6060e5e2ac47190017a70428.mockapi.io/series/" + id)
                .then(response => {


                    window.location.reload();
                    console.log(response)

                })
                .catch(error => {

                    console.log(error);

                })

        },

        CREATE_SERIE(){


            if(this.serieName.length > 0){

                axios.post("https://6060e5e2ac47190017a70428.mockapi.io/series", {

                    serieName : this.serieName

                })
                    .then(response => {

                        console.log(response)
                        window.location.reload();

                    })
                    .catch(error => {

                        console.log(error);

                    })
            }


        },

        SHOW_CREATE_SERIE_FORM()
        {
            this.showFormCreateSerie = !this.showFormCreateSerie;
        }

    },
    mounted() {

        //On récupère toute nos séries depuis l'API
        axios.get("https://6060e5e2ac47190017a70428.mockapi.io/series")
            .then(response => {

                this.allSeries = response["data"].reverse();
                console.log(this.allSeries);

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
