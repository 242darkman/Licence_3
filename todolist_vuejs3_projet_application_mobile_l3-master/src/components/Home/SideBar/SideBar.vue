<template>

    <div class="mb-3">
        <label for="create-new-list-input" class="form-label">Créer une liste</label>
        <input v-model="new_list_name" type="text" class="form-control" id="create-new-list-input" placeholder="Nom de votre liste ...">
        <div id="emailHelp" class="form-text">20 caractère maximum</div>
    </div>
    <button v-on:click="REQUEST_CREATE_TODO_LIST" type="submit" class="btn btn-outline-primary">Créer</button>

    <div class="list-group mt-3" id="list-tab" role="tablist">
        <SideBarItem  v-for="(todo, key) in default_list" v-bind:key="key" :list_name="todo.name" :list_position_id="key"/>
    </div>

</template>

<script>

import SideBarItem from "@/components/Home/SideBar/SideBarItem";
import {mapActions} from "vuex";

export default {
    name: "SideBar",
    components : {
        SideBarItem
    },
    data() {
        return {
            new_list_name : null
        }
    },
    props : {
        default_list : Object
    },
    methods : {
        ...mapActions('todos',['API_REQUEST_CREATE_TODO_LIST']),
        ...mapActions('todos',['REQUEST_SET_INFO_MESSAGE']),

      REQUEST_CREATE_TODO_LIST()
        {

            if(this.new_list_name) {

                if(this.new_list_name.length <3 ) {
                    this.REQUEST_SET_INFO_MESSAGE('Nom de liste trop court')
                    return
                }

                  if(this.new_list_name.length > 20 ) {
                    this.REQUEST_SET_INFO_MESSAGE('Nom de liste trop long')
                    return
                  }


                 this.API_REQUEST_CREATE_TODO_LIST(this.new_list_name)



            }


        }
    },
    computed : {

    }
}
</script>

<style scoped>

</style>