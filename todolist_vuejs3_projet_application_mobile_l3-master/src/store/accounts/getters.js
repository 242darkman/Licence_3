/**
 * Permet de savoir si nous devons afficher un écran de chargement ou pas
 * @param state
 * @returns {boolean}
 */
export function GET_IS_DATA_LOADING(state)
{
    return state.is_data_loading;
}

/**
 * Permet de retourner le message de succees
 * @param state
 * @returns {string}
 */
export function GET_SUCCESS_MESSAGE(state)
{
    return state.success_message
}


/**
 * Permet de retourner le message d'erreur
 * @param state
 * @returns {string}
 */
export function GET_ERROR_MESSAGE(state)
{
    return state.error_message
}



/**
 * Permet de retourner le message d'erreur
 * @param state
 * @returns {string}
 */
export function GET_INFO_MESSAGE(state)
{
    return state.info_message
}


/**
 * Permet de récupérer les informations de l'utilisateur
 * @param state
 * @returns {[]|*}
 */
export function GET_USER_DATA(state)
{
    return state.user_data
}

/**
 * Permet de savoir si nous pouvons acceder à la page ou pas
 * @param state
 * @returns {boolean}
 */
export function GET_IS_ACCESS_AUTHORIZED(state)
{
    return state.is_access_authorized
}



