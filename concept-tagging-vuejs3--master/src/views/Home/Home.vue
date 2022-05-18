<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
               <ion-button slot="start" href="/" fill="clear"> 
                    <ion-icon slot="icon-only" src="/assets/icon/arrow-undo-circle.svg"></ion-icon> 
               </ion-button>
                
                <ion-title class="titre"> Accueil </ion-title>
            </ion-toolbar>
        </ion-header>

        <ion-content :fullscreen="true">
            <ion-header collapse="condense">
                <ion-toolbar>
                    <ion-title size="large"></ion-title>
                </ion-toolbar>
            </ion-header>
        </ion-content>



            <ion-tabs>
                <ion-tab-bar class="launch-game-button">
                    <ion-tab-button tab="game" href="/game" >
                        <ion-icon :icon="gameController" />
                            <ion-label>Jouer</ion-label>
                    </ion-tab-button>
                </ion-tab-bar>
            </ion-tabs>

<!--
            <div class="launch-game-button">
                <ion-router-link href="/game">
                    <ion-button color="light">Lancer la partie</ion-button>
                </ion-router-link>
            </div>
-->
        

        
    </ion-page>
</template>


<script lang="ts">
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonIcon, IonTabBar, IonTabButton, IonTabs, IonLabel} from '@ionic/vue';
import { gameController} from 'ionicons/icons';

//import ExploreContainer from '@/components/ExploreContainer.vue';
//import axios from 'axios';

export default  {
    name: 'Tab1',

    components: {  IonHeader, IonToolbar, IonTitle, IonContent, IonPage, IonIcon, IonTabBar, IonTabButton, IonTabs, IonLabel},

    setup(){
        return { gameController }
    }

}
</script>





<style scoped>

.launch-game-button{
    width: 50%;
    display: flex;
    justify-content: center;
    position: absolute;
    top: 50%;
    left: 25%;
    right: 25%;
}


ion-tab-button {
    background-color: #BE2163;
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


ion-button {
    margin-top: 10%;
}


ion-icon {
    color:#9C1176;
}


ion-tab-button ion-icon {
    color: #37364C;
}

ion-label {
    color: black;
}

</style>