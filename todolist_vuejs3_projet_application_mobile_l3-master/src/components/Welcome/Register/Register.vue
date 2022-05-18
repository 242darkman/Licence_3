<template>

    <div class="container shadow-lg p-3 mb-5 bg-body rounded">

        <div class="row mt-3 mb-3">
            <h1>Inscription</h1>
        </div>

        <InfoMessage    v-if="GET_INFO_MESSAGE    != '' " :message="GET_INFO_MESSAGE" />
        <SuccessMessage v-if="GET_SUCCESS_MESSAGE != '' " :message="GET_SUCCESS_MESSAGE" />
        <ErrorMessage   v-if="GET_ERROR_MESSAGE   != '' " :message="GET_ERROR_MESSAGE" />

        <div class="mb-3">
            <label for="register-email-input" class="form-label">Email</label>
            <input v-model="email" type="email" class="form-control" id="register-email-input" aria-describedby="register-email-help" placeholder="Votre adresse email ...">
            <div id="register-email-help" class="form-text">Ne partagez jamais vos données personnel.</div>
        </div>

        <div class="mb-3">
            <label for="register-pseudo-input" class="form-label">Pseudo</label>
            <input v-model="username" type="text" class="form-control" id="register-pseudo-input" aria-describedby="register-pseudo-help" placeholder="Votre pseudo ...">
            <div id="register-pseudo-help" class="form-text">3 caractère minimum / 15 caractère maximum</div>

        </div>

        <div class="mb-3">
            <label for="register-password-input" class="form-label">Mot de passe</label>
            <input v-model="password" type="password" class="form-control" id="register-password-input" aria-describedby="register-password-help" placeholder="Votre mot de passe ...">
            <div id="register-password-help" class="form-text">8 caractère minimum / 15 caractère maximum</div>

        </div>

        <div class="mb-3">
            <label for="register-password-confirmation-input" class="form-label">Confirmation mot de passe</label>
            <input v-model="password_confirmation" type="password" class="form-control" id="register-password-confirmation-input" placeholder="Confirmation mot de passe ...">
        </div>

        <button v-on:click="SIGNUP" class="btn btn-outline-primary mt-3">Inscription</button>

    </div>


</template>

<script>
import {mapGetters, mapActions} from "vuex";
import InfoMessage from "@/components/Message/InfoMessage";
import SuccessMessage from "@/components/Message/SuccessMessage";
import ErrorMessage from "@/components/Message/ErrorMessage";

export default {
    name: "Register",
    components : {
        InfoMessage,
        SuccessMessage,
        ErrorMessage
    },
    data() {
        return {
            email                 : null,
            username              : null,
            password              : null,
            password_confirmation : null
        }
    },
    computed : {
        ...mapGetters('accounts',['GET_SUCCESS_MESSAGE','GET_ERROR_MESSAGE','GET_INFO_MESSAGE']),
    },
    methods : {
        ...mapActions('accounts',['API_REQUEST_SIGNUP','REQUEST_SET_DEFAULT_MESSAGE','REQUEST_SET_INFO_MESSAGE']),

        SIGNUP()
        {

            if(this.email && this.username && this.password && this.password_confirmation) {


                let expression_email = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; //Nous utilisons une expréssion régulière pour vérifier la confiormité de l'adresse email

                if(!expression_email.test(this.email))
                {
                    this.REQUEST_SET_INFO_MESSAGE('Adresse email Invalide');
                    return
                }

                if(this.username.length < 3)
                {
                    this.REQUEST_SET_INFO_MESSAGE('Pseudo trop court')
                    return
                }

                if(this.username.length > 15)
                {
                    this.REQUEST_SET_INFO_MESSAGE('Pseudo trop long')
                    return

                }

                if(this.password.length < 8)
                {
                    this.REQUEST_SET_INFO_MESSAGE('Mot de passe trop court')
                    return
                }

                if(this.password.length > 15)
                {
                    this.REQUEST_SET_INFO_MESSAGE('Mot de passe trop long')
                    return
                }


                if(this.password != this.password_confirmation)
                {
                    this.REQUEST_SET_INFO_MESSAGE('Les mots de passe ne sont pas identique')
                    return
                }

                this.API_REQUEST_SIGNUP({
                    name     : this.username,
                    email    : this.email,
                    password : this.password
                })

            }else{
                this.REQUEST_SET_INFO_MESSAGE('Veuillez remplir tous les champs !')
            }
        }
    }

}
</script>

<style scoped>

</style>