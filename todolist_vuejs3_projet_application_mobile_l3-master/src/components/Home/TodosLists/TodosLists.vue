<template>

    <div class="container-fluid" v-if="current_list != null"> <!-- Si aucune liste n'a était selectionner alors on n'affiche pas la partie pour créer des todos et les gérer -->

        <h1>
            {{current_list.name}} <!--On afficher le nom de l'utilisateur -->
        </h1>

        <!-- Quand on clique sur le boutton on effectue une requête à axios pour supprimer la liste, on passe en paramètre l'id de la liste a supprimer -->
        <div class="mt-4 mb-4">
            <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                Supprimer la liste
            </button>

            <!-- Quand on appuie sur le boutton on ouvre une fenêtre modal pour avoir une confirmation de la supréssion -->

            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Supréssion de la liste</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Voulez-vous vraiment supprimer la liste ?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal" v-on:click="API_REQUEST_DELETE_TODO_LIST(current_list.id)" >Supprimer</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>


      <!-- LA BAR DE PROGRESSION POUR VOIR l'AVANCEMENT DES TODOS Rouge < 25% | Jaune < 60% | Vert > 60% -->

        <div class="progress mt-4">
          <div v-if="GET_POURCENTAGE_FINISH_BAR <= 25" class="progress-bar progress-bar-striped bg-danger" role="progressbar" v-bind:style="{width: GET_POURCENTAGE_FINISH_BAR + '%'}" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
          <div v-else-if="GET_POURCENTAGE_FINISH_BAR > 25 && GET_POURCENTAGE_FINISH_BAR <= 60" class="progress-bar progress-bar-striped bg-warning" role="progressbar" v-bind:style="{width: GET_POURCENTAGE_FINISH_BAR + '%'}" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
          <div v-else class="progress-bar progress-bar-striped bg-success" role="progressbar" v-bind:style="{width: GET_POURCENTAGE_FINISH_BAR + '%'}" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>

        </div>


      <!-- On affiche une fraction | Nombre de todos finis / Nombre de todos total -->

        <div class="row mt-3">

            <h3>{{GET_NB_TODOS_FINISH}} / {{GET_NB_TODOS}}</h3>
        </div>


        <!--
            Le formulaire pour créer une todo :

             le input va modifier la variable todo_name pour y stocker ce que l'utilisateur rentre
             quand on appuie sur la touche Enter du clavier, alors on apelle la méthode pour créer une TODO ( raccourci clavier )
        -->

        <div class="mb-3 mt-3">
            <label for="create-new-list-input" class="form-label">Créer une TODO</label>
            <input v-model="todo_name" type="text" class="form-control" id="create-new-list-input" placeholder="Nom de votre tâche ..." @keyup.enter="CREATE_TODO()">
            <div id="emailHelp" class="form-text">3 caractère minimum / 30 caractère maximum</div>
        </div>

        <!-- Quand nous cliquons sur le boutton nous apellons la méthodes pour créer une TODO -->
        <div class="mt-2 mb-4">
            <button v-on:click="CREATE_TODO()" type="submit" class="btn btn-outline-primary">Créer</button>
        </div>


        <!------------------------------------------   LES BOUTONS POUR GERER LES FILTRES   ------------------------------------------------>
        <div class="btn-group mt-4 mb-4" role="group" aria-label="Basic example">
            <button v-on:click="REQUEST_SET_ALL_TODOS"        type="button" class="btn btn-outline-primary">Toutes</button> <!-- Affiche toute les todos -->
            <button v-on:click="REQUEST_SET_NOT_FINISH_TODOS" type="button" class="btn btn-outline-primary">A faire</button> <!-- Affiche les todos à faire -->
            <button v-on:click="REQUEST_SET_FINISH_TODOS"     type="button" class="btn btn-outline-primary">Faites</button> <!--Affiche les todos finis -->
        </div>
      <!-------------------------------------------------------------------------------------------------------------->

        <!--
            Nous allons récupérer nos todos à l'aide de GET_CURRENT_FILTRED_LIST qui retourne la liste de todo en fonction du filtre selectrionner

            todo : Va contenir les differentes informations sur notre TODO ( son nom, son status, etc ... )
            key  : Nous permettera de savoir la position de la TODO dans notre liste

            Nous allons boucler sur les TODO de notre list et créer un TodosListsItem pour chaque todo
            et nous allons lui donner comme paramètre la TODO et ça position dans la liste

        --->
        {{GET}}
      <ul class="list-group" v-if="GET_CURRENT_FILTRED_LIST.length != 0">
            <TodosListsItem v-for="(todo, key) in GET_CURRENT_FILTRED_LIST" v-bind:key="key" :todo="todo" :todo_position_in_list="key"/>
      </ul>
        <p v-else>Aucune todo ne correspond à ce filtre</p>
    </div>

</template>



<script>

import TodosListsItem from "@/components/Home/TodosLists/TodosListsItem";
import {mapActions, mapGetters} from "vuex";

export default {
    name: "TodosLists",
    data() {
        return {
            todo_name : null,   //Va stocker le nom que l'utilisateur à entré pour la nouvelle TODO
        }
    },
    components: {
        TodosListsItem  //On utilise le components TodosListsItem
    },
    props : {
        current_list : Object,  //Lors de la création de notre composant TodosLists, nous demandons à avoir comme paramètre la liste de TODO sur la quel nous allons travailler
    },
    methods : { //importation des actions du STATE todos
        ...mapActions('todos',['API_REQUEST_CREATE_TODO','REQUEST_SET_ALL_TODOS','REQUEST_SET_FINISH_TODOS','REQUEST_SET_NOT_FINISH_TODOS','API_REQUEST_DELETE_TODO_LIST']),
        ...mapActions('todos',['REQUEST_SET_SUCCESS_MESSAGE','REQUEST_SET_ERROR_MESSAGE','REQUEST_SET_INFO_MESSAGE','REQUEST_SET_DEFAULT_MESSAGE']),

        /**
         * Va être appeler l'orsque l'utilisateur créer une todo, et va faire un apelle à l'api pour créer la todo
         */
        CREATE_TODO()
        {
            if(this.todo_name) { //On vérifie que l'utilisateur n'a pas créer une todo vide

                if(this.todo_name.length < 3) { //On vérifie si la taille de la todo est inférieur à 3
                    this.REQUEST_SET_INFO_MESSAGE('Nom de la todo trop court !') //On demande à afficher le message d'information suivant
                    return //Si jamais elle l'est alors nous sortons de notre méthode pour ne pas créer la TODO
                }

                if(this.todo_name.length > 30) { //Si jamais le nom de la todo dépasse 30 caractère
                    this.REQUEST_SET_INFO_MESSAGE('Nom de la todo trop long !') //On demande à afficher le message d'information suivant
                    return  //Nous sortons de notre méthode
                }

                //Si tous est bon nous demandons à L'API DE créer notre TODO

                /**
                 * name : le nom de la todo à créer
                 * todolist_id : l'id de la liste ou la todo sera créer
                 */
                this.API_REQUEST_CREATE_TODO({name : this.todo_name, todolist_id : this.current_list.id})
                this.todo_name = ''

            }
        }
    },
    computed : { //On importe les getters pour récupérer les variables dans le state de TODOS
        ...mapGetters('todos',['GET_CURRENT_FILTRED_LIST','GET_DEFAULT_LIST','GET_CURRENT_LIST','GET_NB_TODOS_FINISH','GET_NB_TODOS']),


        /**
         * //Va permettre de convertir une fraction en pourcentage pour faire fonctionner notre bar de progression
         * @returns {number}
         */
        GET_POURCENTAGE_FINISH_BAR()
        {

          return ( this.GET_NB_TODOS_FINISH / this.GET_NB_TODOS ) * 100

        }


    }
}
</script>

<style scoped>

</style>