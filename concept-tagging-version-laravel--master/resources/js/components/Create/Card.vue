<template class="">


    <div id="create-card-form" class="w-1/2 shadow-2xl p-4 bg-white rounded-lg">



        <div class="row mb-2">
            <h1>Création d'une carte </h1>
        </div>

        <ErrorMessage   v-if="error_message != ''" :message="error_message" />
        <SuccessMessage v-if="success_message != ''" :message="success_message" />

        <form @submit.prevent="submit">

            <div class="col-span-6 sm:col-span-3 mb-3">
                <label for="country" class="block text-sm font-medium text-gray-700">Série de la carte</label>
                <select v-model="card.serie_id" id="country" autocomplete="country" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <option v-for="serie in all_series" :value="serie.id">{{serie.serie_name}}</option>
                </select>
            </div>

            <div>
                <jet-label for="email" value="Nom de la carte" />
                <jet-input v-model="card.card_name" id="card_name" type="text" class="mt-1 block w-full"  autofocus />
            </div>

            <div class="mt-4">
                <jet-label for="first_forbidden_word" value="1er mot interdit" />
                <jet-input v-model="card.first_forbidden_word" id="first_forbidden_word" type="text" class="mt-1 block w-full"/>
            </div>

            <div class="mt-4">
                <jet-label for="second_forbidden_word" value="2ème mot interdit" />
                <jet-input v-model="card.second_forbidden_word"  id="second_forbidden_word" type="text" class="mt-1 block w-full"  />
            </div>


            <div class="mt-4">
                <jet-label for="third_forbidden_word" value="3ème mot interdit" />
                <jet-input v-model="card.third_forbidden_word" id="third_forbidden_word" type="text" class="mt-1 block w-full"  />
            </div>

            <div class="mt-4">
                <jet-label for="fourth_forbidden_word" value="4ème mot interdit" />
                <jet-input v-model="card.fourth_forbidden_word" id="fourth_forbidden_word" type="text" class="mt-1 block w-full"  />
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
    name: "Card",
    data() {
        return {
            card : {
                serie_id              : null,
                card_name             : null,
                first_forbidden_word  : null,
                second_forbidden_word : null,
                third_forbidden_word  : null,
                fourth_forbidden_word : null,
            },
            error_message   : '',
            success_message : '',
        }
    },
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
    methods : {
        submit() {

            if(!this.card.card_name) {
                this.error_message = 'Veuillez entrez un nom de carte';
                return
            }

            if(!this.card.serie_id) {
                this.error_message = 'Veuillez choisir la liste où la carte sera ajouter';
                return
            }

            if(!this.card.first_forbidden_word)
            {
                this.error_message = 'Premier mot interdit requis';
                return
            }

            if(!this.card.second_forbidden_word)
            {
                this.error_message = 'Deuxième mot interdit requis';
                return
            }

            if(!this.card.third_forbidden_word)
            {
                this.error_message = 'Troisième mot interdit requis';
                return
            }

            if(!this.card.fourth_forbidden_word)
            {
                this.error_message = 'Quatrième mot interdit requis';
                return
            }

            this.success_message = 'Carte bien créer !';
            this.$inertia.post('/create/card',this.card)



            this.card.serie_id = null
            this.card.card_name = null
            this.card.first_forbidden_word = null
            this.card.second_forbidden_word = null
            this.card.third_forbidden_word = null
            this.card.fourth_forbidden_word = null


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
