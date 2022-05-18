/**
 * Permet de retourner la liste en cours
 * @param state
 * @returns {null|*}
 * @constructor
 */
export function GET_CURRENT_LIST(state)
{
    return state.current_list
}


export function GET_CURRENT_FILTRED_LIST(state)
{

    if(state.filter == 'all') {

        return state.current_list.todos

    }else if(state.filter == 'todo'){

        return state.current_list.todos.filter(t => !t.completed)

    }else if(state.filter == 'do'){

        return state.current_list.todos.filter(t => t.completed)

    }else{

        return state.default_list.todos

    }

}

/**
 * Permet de récupérer le filtre en cours sur les todos
 * @param state
 * @returns {*}
 * @constructor
 */
export function GET_FILTER(state)
{
    return state.filter
}


/**
 * Permet de retourner la liste contenant toutes les listes de TODO
 * @param state
 * @returns {[]}
 * @constructor
 */
export function GET_DEFAULT_LIST(state)
{
    return state.default_list
}


/**
 * Permet de savoir si nous devons afficher l'écran de chargement ou pas
 * @param state
 * @returns {boolean|*}
 * @constructor
 */
export function GET_IS_DATA_LOADING(state)
{
    return state.is_data_loading
}

/**
 * Permet de récupérer le message à afficher en cas de success
 * @param state
 * @returns {string|*}
 * @constructor
 */
export function GET_SUCCESS_MESSAGE(state)
{
    return state.success_message
}

/**
 * Permet de récupérer le message à afficher en cas d'erreur
 * @param state
 * @returns {string|*}
 * @constructor
 */
export function GET_ERROR_MESSAGE(state)
{
    return state.error_message
}

/**
 * Permet de récupérer le message d'information à afficher
 * @param state
 * @returns {string|*}
 * @constructor
 */
export function GET_INFO_MESSAGE(state)
{
    return state.info_message
}



/**
 * Permet de savoir le nombre de todo de notre liste
 */
export function GET_NB_TODOS(state)
{
    return state.current_list.todos.length
}

/**
 * Permet de savoir le nombre de todo terminé dans notre liste
 */
export function GET_NB_TODOS_FINISH(state)
{
    return state.current_list.todos.filter(t => t.completed).length
}

