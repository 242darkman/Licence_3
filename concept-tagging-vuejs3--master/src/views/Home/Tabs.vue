<template>
    <ion-page>
        <ion-tabs>
           <ion-tab-bar slot="bottom">
                 <!--   <ion-tab-button tab="home" href="/app/home" slot="start">
                    <ion-icon :icon="gameController" />
                    <ion-label>Jouer</ion-label>
                </ion-tab-button> -->

                <ion-tab-button tab="cardsManager" href="/app/seriesManager" >
                    <ion-icon :icon="construct" />
                    <ion-label>Gérer mes cartes</ion-label>
                </ion-tab-button>

                <ion-tab-button tab="historyManager" href="/app/historyManager">
                    <ion-icon :icon="newspaper" />
                    <ion-label>Historique des parties</ion-label>
                </ion-tab-button>



            </ion-tab-bar>
        </ion-tabs>
    </ion-page>
</template>

<script lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage } from '@ionic/vue';
import { gameController, folderOpen, construct, newspaper, image} from 'ionicons/icons';

export default {
    name: 'Tabs',
    components: { IonLabel, IonTabs, IonTabBar, IonTabButton, IonIcon, IonPage },
    setup() {
        return {
            gameController, folderOpen, construct, newspaper, image
        }
    }
}
</script>