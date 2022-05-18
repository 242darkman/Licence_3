<template>
    <li class="list-group-item">

        <input v-if="todo.completed" v-on:click="API_REQUEST_CHANGE_TODO_STATUS({name : todo.name, completed : !todo.completed, todolist_id : todo.todolist_id, todo_id : todo.id})"  class="form-check-input me-1" type="checkbox" value="" aria-label="..." checked>
        <input v-else v-on:click="API_REQUEST_CHANGE_TODO_STATUS({name : todo.name, completed : !todo.completed, todolist_id : todo.todolist_id, todo_id : todo.id})" class="form-check-input me-1" type="checkbox" value="" aria-label="..." >

        <h3 v-if="todo.completed" id="todo-name" class="text-decoration-line-through">{{todo.name}}</h3>
        <h3 v-else id="todo-name">{{todo.name}}</h3>

        <div>
            <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Modifier
            </button>
            <button class="btn btn-outline-danger button m-1" v-on:click="API_REQUEST_DELETE_TODO(todo.id)">Supprimer</button>
        </div>
    </li>


    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modification de la todo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3 mt-5">
                        <input v-model="new_todo_name" type="text" class="form-control" id="create-new-list-input" placeholder="Nouveau nom ...">
                        <div id="emailHelp" class="form-text">20 caractère maximum</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                    <button type="button" class="btn btn-primary" v-on:click="REQUEST_UPDATE_TODO" data-bs-dismiss="modal">Sauvegarder</button>
                </div>
            </div>
        </div>
    </div>

</template>

<script>

import {mapActions} from "vuex";

export default {
    name: "TodoListsItem",
    data() {
        return {
            new_todo_name : null
        }
    },
    props : {
        todo                  : Object,
        todo_position_in_list : Number
    },
    methods : {
        ...mapActions('todos',['API_REQUEST_DELETE_TODO','API_REQUEST_CHANGE_TODO_STATUS','API_REQUEST_UPDATE_TODO']),
        REQUEST_UPDATE_TODO()
        {

            if(this.new_todo_name) {

                if(this.new_todo_name.length >= 3 && this.new_todo_name.length <= 20) {

                    this.API_REQUEST_UPDATE_TODO({name : this.new_todo_name, todo_id : this.todo.id})

                }

            }

            this.new_todo_name = null

        }
    }
}
</script>

<style scoped>
p{
    display: inline-block;
}
input[type=checkbox] {
    margin-top: 0;
    height: 40px;
    width: 40px;
    border-radius: 50%;
}




.list-group-item{
    display: flex;
    justify-content: space-between;
}

#todo-name
{
    margin-top : 7px;
}
</style>