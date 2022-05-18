<template class="">


    <div id="create-card-form" class="w-1/2 shadow-2xl p-4 bg-white rounded-lg">

        <div class="row mb-2">
            <h1>Création d'une série </h1>
        </div>

        <SuccessMessage v-if="success_message != ''" :message="success_message" />
        <ErrorMessage v-if="error_message != ''" :message="error_message" />

        <form @submit.prevent="submit">

            <div class="mt-4">
                <jet-label for="first_forbidden_word" value="Nom de la série" />
                <jet-input v-model="serie.serie_name" id="first_forbidden_word" type="text" class="mt-1 block w-full" required autocomplete="current-password" />
            </div>

            <jet-button class="mt-5 bg-blue-500" type="submit">
                Créer
            </jet-button>


        </form>

    </div>





</template>

<script>
import JetInput from '@/Jetstream/Input'
import JetLabel from '@/Jetstream/Label'
import JetButton from "@/Jetstream/Button";
import ErrorMessage from "@/components/Message/ErrorMessage";
import SuccessMessage from "@/components/Message/SuccessMessage";

export default {
    name: "Serie",
    props : {
        all_series : Array
    },
    components : {
        ErrorMessage,
        JetInput,
        JetLabel,
        JetButton,
        SuccessMessage
    },
    data() {
        return {
            serie : {
                serie_name : null
            },
            error_message : '',
            success_message : ''
        }
    },
    methods : {
        submit() {

            if(!this.serie.serie_name){
                this.error_message = 'Veuillez entrez un nom de série'
                return
            }
            this.success_message = 'Série parfaitement créer'
            this.$inertia.post('/create/serie',this.serie)
            this.serie.serie_name = null
        }
    }
}
</script>

<style scoped>
#create-card-form{
    margin-top : 100px;
    margin-left: auto;
    margin-right: auto;
}
</style>
