const axios = require('axios');

/**
 * Requête vers l'API pour récupérer la liste des todos
 * @param commit
 * @param authentification_token
 */
export function API_REQUEST_GET_TODOS_LISTS({commit,dispatch})
{

    commit('SET_IS_DATA_LOADING_TRUE')

    /**
     * On met le token en paramètre pour autoriser l'action
     */

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .get('http://138.68.74.39/api/todolists') //On recupère la liste contient les différentes liste de TODO
        .then(response => {                           //Si le serveir renvoie une réponse positif

            commit('SET_DEFAULT_LIST', response['data']) //On modifie le state pour y placer la liste que nous avons récupérer en réponse


        })
        .catch(error => { //Si il y'a une erreur

            if(error.response)
            {
                commit('SET_DEFAULT_LIST', [])
                dispatch('REQUEST_SET_ERROR_MESSAGE','Erreur lors de la récupération de la liste')
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement
}


/**
 * Permet de créer une todo dans la liste
 * @param commit
 * @param name
 * @param todolist_id
 * @constructor
 */
export function API_REQUEST_CREATE_TODO({commit, dispatch},{name , todolist_id})
{

    commit('SET_IS_DATA_LOADING_TRUE')

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .post('http://138.68.74.39/api/todo',{
            name        : name,
            completed   : 0,
            todolist_id : todolist_id
        })

        .then(response => {

            commit('ADD_TODO_IN_LIST',{
                id          : response['data'].id,
                todolist_id : response['data'].todolist_id,
                name        : response['data'].name,
                completed   : response['data'].completed,
                created_at  : response['data'].created_at,
                update_at   : response['data'].update_at
            })

        })
        .catch(error => {

            if(error.response)
            {
                dispatch('REQUEST_SET_ERROR_MESSAGE',"Erreur lors de l'ajout dans la liste")
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement


}


/**
 * Va permettre de supprimer une todo d'une liste
 * @param commit
 * @param todo_id
 * @constructor
 */
export function API_REQUEST_DELETE_TODO({commit, dispatch}, todo_id)
{

    commit('SET_IS_DATA_LOADING_TRUE')

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .delete('http://138.68.74.39/api/todo/' + todo_id)

        .then(response => {

            commit('REMOVE_TODO_IN_LIST',response['data'].id)
            dispatch('REQUEST_SET_SUCCESS_MESSAGE','Supréssion de la todo réussie')
        })
        .catch(error => {

            if(error.response)
            {
                dispatch('REQUEST_SET_ERROR_MESSAGE',"Erreur lors de la supréssion de la liste")
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement
}


/**
 * Permet de changer le status d'une TODO ( Faite / pas Faite )
 * @param commit
 * @param name
 * @param completed
 * @param todolist_id
 * @param todo_id
 * @constructor
 */
export function API_REQUEST_CHANGE_TODO_STATUS({commit, dispatch},{name, completed, todolist_id, todo_id})
{

    commit('SET_IS_DATA_LOADING_TRUE')

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .post('http://138.68.74.39/api/completeTodo/' + todo_id, {
            name        : name,
            completed   : completed,
            todolist_id : todolist_id
        })
        .then(response => {

            commit('UPDATE_TODO_STATUS_IN_LIST',response['data'].id)

        })
        .catch(error => {

            if(error.response)
            {
                dispatch('REQUEST_SET_ERROR_MESSAGE',"Erreur lors du changement de status de la todo")
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement

}


/**
 * Permet de mettre à jour une TODO
 * @param commit
 * @param name
 * @param completed
 * @param todolist_id
 * @param todo_id
 */
export function API_REQUEST_UPDATE_TODO({commit, dispatch},{name, todo_id})
{

    commit('SET_IS_DATA_LOADING_TRUE')

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .patch('http://138.68.74.39/api/todo/' + todo_id, {
            name : name,
        })
        .then(response => {

            dispatch('REQUEST_SET_SUCCESS_MESSAGE','Changement effectué ')

            commit('UPDATE_TODO_NAME_IN_LIST',{
                todo_id  : response['data'].id,
                new_name : name
            })

        })
        .catch(error => {

            if(error.response)
            {
                dispatch('REQUEST_SET_ERROR_MESSAGE',"Erreur lors de la mise à jour de la TODO")
            }

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement
}


/**
 * Permet de supprimer une liste de TODO
 * @param commit
 * @param todo_list_id
 * @param authentification_token
 * @constructor
 */
export function API_REQUEST_DELETE_TODO_LIST({commit, dispatch}, todo_list_id)
{

    commit('SET_IS_DATA_LOADING_TRUE')


    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .delete('http://138.68.74.39/api/todolist/' + todo_list_id)

        .then((response) => {

            dispatch('REQUEST_SET_SUCCESS_MESSAGE','Liste parfaitement supprimer'),
            commit('DELETE_TODO_LIST',todo_list_id)
            console.log(response)

        })
        .catch((error) => {

            dispatch('REQUEST_SET_ERROR_MESSAGE','Erreur lors de la supréssion de la liste'),
            console.log(error)

        })
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement


}


/**
 * Permet de créer une liste de TODO
 * @axios : POST
 */
export function API_REQUEST_CREATE_TODO_LIST({commit, dispatch}, name)
{
    commit('SET_IS_DATA_LOADING_TRUE')

    axios.create({
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.authentification_token
        }
    })
        .post('http://138.68.74.39/api/todolist', {
            name : name
        })

        .then((response) => {

            dispatch('REQUEST_SET_SUCCESS_MESSAGE','Liste parfaitement créer'),
            commit('CREATE_TODO_LIST', {

                id          : response['data'].id,
                name        : response['data'].name,
                user_id     : response['data'].user_id,
                created_at  : response['data'].created_at,
                updated_at  : response['data'].updated_at,
                nb_todos    : response['data'].nb_todos,
                todos       : []

            })


        })
        .catch((error) => (

            dispatch('REQUEST_SET_ERROR_MESSAGE','Erreur lors de la création de la liste'),
            console.log(error)

        ))
        .finally(() => commit('SET_IS_DATA_LOADING_FALSE')) //On arrête le chargement

}

/**
 * Permet choisir la liste en cours
 * @constructor
 */
export function REQUEST_SET_CURRENT_LIST({commit},list_position)
{
    commit('SET_CURRENT_LIST',list_position)
}


/**
 * Permet de changer le filtre sur toute les TODO
 * @param commit
 * @constructor
 */
export function REQUEST_SET_ALL_TODOS({commit})
{
    commit('SET_ALL_TODOS')
}

/**
 * Permet de changer le filtre sur les todos finis
 * @param commit
 * @constructor
 */
export function REQUEST_SET_FINISH_TODOS({commit})
{
    commit('SET_FINISH_TODOS')
}

/**
 * Permet de changer le filtre sur les todos non finis
 * @param commit
 * @constructor
 */
export function REQUEST_SET_NOT_FINISH_TODOS({commit})
{
    commit('SET_NOT_FINISH_TODOS')
}


/**
 * Permet de modifier le message d'informations
 * @param commit
 * @param info_message
 */
export function REQUEST_SET_INFO_MESSAGE({commit},info_message)
{
    commit('SET_INFO_MESSAGE',info_message)
}

/**
 * Requête pour modifier le message de success
 * @param commit
 * @param success
 */
export function REQUEST_SET_SUCCESS_MESSAGE({commit},success)
{
    commit('SET_SUCCESS_MESSAGE',success)
}

/**
 * Permet de modifier le message d'erreur
 * @param commit
 * @param error_message
 */
export function REQUEST_SET_ERROR_MESSAGE({commit},error_message)
{
    commit('SET_ERROR_MESSAGE',error_message)
}


export function REQUEST_SET_DEFAULT_MESSAGE({dispatch})
{
    dispatch('REQUEST_SET_SUCCESS_MESSAGE','')
    dispatch('REQUEST_SET_INFO_MESSAGE','')
    dispatch('REQUEST_SET_ERROR_MESSAGE','')
}