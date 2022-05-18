<template>
    <ion-page>
        <ion-content fullscreen="true" id="ion-content">
            <header class="ion-content-header">
                <h1> CONCEPT TAGGING </h1>
            </header>

            <div class="ion-content-image">
                <img src="assets/images/homeIcon.svg" alt="Image de l'icone" />
            </div>

            <footer class="ion-content-footer">
                <ion-button href="/app/home" color="light">Commencer</ion-button> <br />
                <ion-button color="light" v-on:click="SHOW_RULE">Règle du jeu</ion-button>
            </footer>


            <ion-card class="card-rule" v-if="showGameRule">
                <ion-card-header>
                    <ion-card-subtitle>CONCEPT TAGGING</ion-card-subtitle>
                    <ion-card-title>Règle du jeu</ion-card-title>
                </ion-card-header>

                <ion-card-content>
                    Le but du jeu est de faire deviner un mot à ses coéquipiers sans utiliser certains mots interdits ou tabous.
                    Il se joue avec un paquet de cartes sur lesquelles sont inscrits des mots à faire deviner et la liste
                    des cinq mots interdits.<br />
                    <ion-button color="dark" v-on:click="SHOW_RULE">Fermer</ion-button>

                </ion-card-content>
            </ion-card>


        </ion-content>




    </ion-page>
</template>

<script>
export default {
    name: "Welcome",
    data() {
        return {
            showGameRule : false

        }
    },
    methods : {
        SHOW_RULE(){
            this.showGameRule = !this.showGameRule;
        }
    }
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap');

.card-rule{
    transition-duration: 1s;
    opacity: 0.95;
    --ion-background-color : white;
    position: absolute;
    top: 30%;
}

.ion-content-header{
    margin-top: 8.5%;
    display: flex;
    justify-content: center;
}

.ion-content-footer{
    margin-top: 20%;
    display: grid;
    grid-template-columns: 1fr;
}

.ion-content-image{
    margin-top: 20%;
}

.ion-content-header h1{
    font-family : 'Roboto', sans-serif;
    color       : white;
    font-size: 50px;
    text-align: center;
}

ion-content{
    position: relative;
    --ion-background-color : linear-gradient(348.02deg, #9C1176 6.53%, #F93C42 90.02%);
}


</style>