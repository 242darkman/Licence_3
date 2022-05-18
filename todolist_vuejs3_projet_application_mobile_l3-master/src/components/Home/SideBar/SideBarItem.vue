<template>
    <a v-on:click="REQUEST_SET_CURRENT_LIST(list_position_id)" class="list-group-item list-group-item-action" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="home">{{list_name}}</a>
</template>

<script>

import {mapActions} from "vuex";

export default {
    name: "SideBarItem",
    props : {
        list_position_id   : String,
        list_name          : String
    },
    methods : {
        ...mapActions('todos',['REQUEST_SET_CURRENT_LIST']),
    }
}
</script>

<style scoped>

</style>