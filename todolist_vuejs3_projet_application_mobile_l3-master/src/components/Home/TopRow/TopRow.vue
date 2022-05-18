<template>
  <div class="container d-flex">
    <div class="">
      <div class="mr-auto">
          <h1>Bienvenue : {{user_data.name}}</h1>
          <button v-on:click="REQUEST_LOGOUT" type="button" class="btn btn-outline-danger">Deconnexion</button>
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "TopRow",
  props : {
    user_data : Object
  },
  methods : {
      ...mapActions('accounts',['REQUEST_LOGOUT'])
  }
}
</script>

<style scoped>

</style>