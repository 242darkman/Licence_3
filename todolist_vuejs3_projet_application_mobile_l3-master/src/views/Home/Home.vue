<template>

    <div class="container-fluid mt-3">

        <div class="row">
          <TopRow :user_data="GET_USER_DATA"/>
        </div>
        <div class="mt-3">
          <InfoMessage    v-if="GET_INFO_MESSAGE    != ''"  :message="GET_INFO_MESSAGE" />
          <SuccessMessage v-if="GET_SUCCESS_MESSAGE != '' " :message="GET_SUCCESS_MESSAGE" />
          <ErrorMessage   v-if="GET_ERROR_MESSAGE   != '' " :message="GET_ERROR_MESSAGE" />
        </div>

        <div class="row">
            <div class="col-3 mt-5">
                <SideBar :default_list="GET_DEFAULT_LIST"/>
            </div>
            <div v-if="!GET_IS_DATA_LOADING" id="todos-lists" class="col mt-5 mb-3">
                <TodosLists v-if="GET_CURRENT_LIST != null" :current_list="GET_CURRENT_LIST" />
                <div v-else >
                    <h3>Cliquez sur une liste pour pouvoir commencer !</h3>
                </div>
            </div>

            <div v-else>
                <Loading/>
            </div>

        </div>


    </div>

</template>

<script>


import {mapActions, mapGetters} from "vuex";
import SideBar                  from "@/components/Home/SideBar/SideBar";
import TodosLists               from "@/components/Home/TodosLists/TodosLists";
import TopRow                   from "@/components/Home/TopRow/TopRow";
import InfoMessage              from "@/components/Message/InfoMessage.vue";
import SuccessMessage           from '@/components/Message/SuccessMessage.vue'
import ErrorMessage             from '@/components/Message/ErrorMessage.vue'
import Loading                   from "@/components/Loading/Loading";
export default {
    name: "Home",
    components: {TopRow, InfoMessage, TodosLists, SideBar, SuccessMessage, ErrorMessage, Loading},
    methods : {
        ...mapActions('accounts',['API_REQUEST_GET_USER']),
        ...mapActions('todos',['API_REQUEST_GET_TODOS_LISTS']),
        ...mapActions('todos',['REQUEST_SET_SUCCESS_MESSAGE','REQUEST_SET_INFO_MESSAGE','REQUEST_SET_ERROR_MESSAGE']),


    },
    computed : {
        ...mapGetters('accounts',['GET_USER_DATA','GET_IS_ACCESS_AUTHORIZED']),
        ...mapGetters('todos',['GET_DEFAULT_LIST','GET_CURRENT_LIST']),
        ...mapGetters('todos',['GET_SUCCESS_MESSAGE','GET_ERROR_MESSAGE','GET_INFO_MESSAGE','GET_IS_DATA_LOADING']),



    },
    mounted() {
        this.API_REQUEST_GET_TODOS_LISTS()
        this.API_REQUEST_GET_USER()
    },
    watch : {

      GET_SUCCESS_MESSAGE : function()
      {
        this.REQUEST_SET_INFO_MESSAGE('')
        this.REQUEST_SET_ERROR_MESSAGE('')
      },
      GET_ERROR_MESSAGE : function()
      {
        this.REQUEST_SET_INFO_MESSAGE('')
        this.REQUEST_SET_SUCCESS_MESSAGE('')
      },
      GET_INFO_MESSAGE : function()
      {
        this.REQUEST_SET_ERROR_MESSAGE('')
        this.REQUEST_SET_SUCCESS_MESSAGE('')
      }

    }
}

</script>

<style scoped>

</style>