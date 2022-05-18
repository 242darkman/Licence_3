<template>


    <Loading v-if="GET_IS_DATA_LOADING" id="loading-screen"/>


    <div class="mt-4" v-else>

        <ul class="nav nav-tabs" id="myTab" role="tablist">

            <li class="nav-item" role="presentation">
                <button v-on:click="REQUEST_SET_DEFAULT_MESSAGE" class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Connexion</button>
            </li>
            <li class="nav-item" role="presentation">
                <button v-on:click="REQUEST_SET_DEFAULT_MESSAGE" class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Inscription</button>
            </li>

        </ul>

        <div class="tab-content" id="myTabContent">

            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <Login class="mt-5" />
            </div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <Register class="mt-5"/>
            </div>
        </div>
    </div>



</template>

<script>

import {mapGetters, mapActions} from "vuex";

import Login        from '@/components/Welcome/Login/Login.vue'
import Register     from '@/components/Welcome/Register/Register.vue'
import Loading      from '@/components/Loading/Loading.vue'

export default {
    name: "Welcome",
    components : {
        Loading,
        Login,
        Register
    },
    computed : {
        ...mapGetters('accounts',['GET_IS_DATA_LOADING'])
    },
    methods : {
        ...mapActions('accounts',['REQUEST_SET_DEFAULT_MESSAGE'])
    }
}
</script>

<style scoped>
#loading-screen
{
    margin-top: 250px;
}
</style>