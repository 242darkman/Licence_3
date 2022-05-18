import router from "@/router";

const axios = require('axios');

/**
 * Permet de faire une requête de connexion via l'API
 * @param commit
 * @param email    : L'adresse email de l'utilisateur
 * @param password : Mot de passe de l'utilisateur
 * @axios : POST
 */
export function API_REQUEST_LOGIN({commit,dispatch},{email, password})
{

    commit('SET_IS_DATA_LOADING_TRUE')                                                          //On lance l'écran de chargement

    axios
        .post('http://138.68.74.39/api/login',{                                        //Pour se connecter axios va prendre comme parametre l'email de l'utilisateur et son mot de passe
            email    : email,
            password : password
        })
        .then(response => {                                                                               //Si axios renvoie une requête positive avec la réponse

            commit('SET_AUTHENTIFICATION_TOKEN', response['data'].token),                                 //Nous stockons le token de l'utilisateur dans le store
            dispatch('REQUEST_SET_SUCCESS_MESSAGE', 'Connexion effectué, redirection en cours ...'),      //Nous modifions le state pour que le state reçoit le message de succees pour l'afficher
            commit('SET_IS_ACCESS_AUTHORIZED_TRUE')                                                       //Nous changons ce stauts pour dire que l'utilisateur est connecté

            /**
             *  Nous allons enregistrer nos données en local pour éviter de devoir refaire des appelle à l'API
             *  pour re récupérer le token d'authentification
             */

            localStorage.authentification_token = response['data'].token                                  //Nous stockons le token dans le localstorage

            setTimeout(() => {                                                                     //Nous redirigions après une seconde vers la page de gestion des todos
                router.push('/home')
            }, 1000)

        })
        .catch(error => {                                                                                  //Si jamais nous n'avons pas pus nous connecter

            commit('SET_IS_ACCESS_AUTHORIZED_FALSE')                                                       //La variable nous permettant de savoir que on est connecté passe à FALSE


            if(error.response) {                                                                           //Si le serveur nous renvoie une réponse d'erreur

                /**
                 * Nous mettons un IF car si dans le futur il y'a plusieurs code d'erreur à gérer
                 * il sera alors plus facile d'adapter pour gérer les différents code
                 */

                if(error.response.status == 401){   //401 = Impossible de se connecter
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Connexion impossible')
                }else{
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Connexion impossible')
                }

            }else{                                                                                          //Si jamais le serveur ne nous renvoie aucune réponse
                dispatch('REQUEST_SET_ERROR_MESSAGE','Service indisponible pour le moment ')
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE'))                                         //On arrête l'écran de chargement

}


/**
 * Permet de faire une requête à l'API pour s'inscrire
 * @param commit
 * @param name : Le pseudo de l'utilisateur
 * @param email : L'adresse email de l'utilisateur
 * @param password : Le mot de passe de l'utilisateur
 * @axios : POST
 */
export function API_REQUEST_SIGNUP({commit,dispatch},{name, email, password})
{
    commit('SET_IS_DATA_LOADING_TRUE');                                                         //On lance l'écran de chargement

    axios
        .post('http://138.68.74.39/api/register', {                                     //La requête axios pour s'inscrire prend 3 paramètre (Pseudo, Email, Mot de passe )
            name     : name,
            email    : email,
            password : password
        })
        .then(response => {                                                                      //Si le serveur nous renvoie une réponse positif

            commit('SET_AUTHENTIFICATION_TOKEN', response['data'].token),                        //Nous stockons le token de l'utilisateur dans le state
            dispatch('REQUEST_SET_SUCCESS_MESSAGE','Inscription effectué, redirection en cours ...')       //Nous demandons à afficher le message de success
            commit('SET_IS_ACCESS_AUTHORIZED_TRUE')                                              //Nous changons le state pour access autorise passe à TRUE

            /**
             *  Nous allons enregistrer nos données en local pour éviter de devoir refaire des appelle à l'API
             *  pour re récupérer le token d'authentification
             */

            localStorage.authentification_token = response['data'].token                        //On stock le token de l'utilisateur dans le local storage

            setTimeout(() => {                                                           //On effectue une redirection vers la page de gestion des todos
                router.push('/home')
            }, 1000)

        })
        .catch(error => {                                                                        //Si jamais il y'a une erreur

            commit('SET_IS_ACCESS_AUTHORIZED_FALSE')                                             //Nous n'autorisons pas la connexion

            if(error.response) {                                                                 //Si le serveur renvoie une erreur comme réponse

                /**
                 * Nous mettons un IF car si dans le futur il y'a plusieurs code d'erreur à gérer
                 * il sera alors plus facile d'adapter pour gérer les différents code
                 */

                if(error.response.status == 409){                                               //CODE 409 = Compte déja enregistrer
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Ce compte est déja enregistrer')                //On demande à afficher le message d'erreur
                }else{
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Inscription impossible')                        //Si c'est un autre code d'erreur on affiche ce message d'erreur
                }

            }else{
                dispatch('REQUEST_SET_ERROR_MESSAGE','Service indisponible pour le moment ')              //Si le serveur ne renvoie rien du tous affiche ce message d'erreur

            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE'))                             //On arrête l'écran de chargement

}

/**
 * Permet de récupérer les données de l'utilisateur
 * @param commit
 * @axios : GET
 */
export function API_REQUEST_GET_USER({commit, dispatch})
{

    commit('SET_IS_DATA_LOADING_TRUE');                               //On lance l'écran de chargement


    /**
     * Pour pouvoir effectuer l'action nous devons donner à Axios le token pour pouvoir l'autorisé
     */

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .get('http://138.68.74.39/api/user')        //On récupère les informations de l'utilisateur
        .then((response) => {          //Si le serveur nous renvoie une réponse positif

            commit('SET_USER_DATA',response['data'])    //Nous stockons les données de l'utilisateur dans le state

        })
        .catch((error) => {                             //Si le serveur renvoie une erreur

            commit('SET_IS_ACCESS_AUTHORIZED_FALSE')

            if(error.response)                          //Si il renvoie une erreur comme réponse
            {

                if(error.response.status == 429)                        //429 = Trop de requête à la fois
                {
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Service indisponible')  //Demande pour mettre un message d'erreur

                }else{
                    dispatch('REQUEST_SET_ERROR_MESSAGE','Erreur interne')         //Demande pour mettre un message d'erreur
                }

            }else{
                dispatch('REQUEST_SET_ERROR_MESSAGE','Erreur interne')             //Demande pour mettre un message d'erreur
            }


        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête l'écran de chargement

}


/**
 * On fait une requête pour se déconnecter
 */
export function REQUEST_LOGOUT({commit})
{
    commit('SET_AUTHENTIFICATION_TOKEN','')     //Nous remettons son token par défaut
    localStorage.clear()
    router.push('/')
}

/**
 * On fait une requête pour modifier le message d'informations
 * @param info_message
 */
export function REQUEST_SET_INFO_MESSAGE({commit},info_message)
{
    commit('SET_INFO_MESSAGE',info_message)
}

/**
 * On fait une requête pour remettre tous les message par défaut
 */
export function REQUEST_SET_ERROR_MESSAGE({commit}, error_message)
{
    commit('SET_ERROR_MESSAGE',error_message)
}

export function REQUEST_SET_SUCCESS_MESSAGE({commit}, success_message)
{
    commit('SET_SUCCESS_MESSAGE',success_message)
}




