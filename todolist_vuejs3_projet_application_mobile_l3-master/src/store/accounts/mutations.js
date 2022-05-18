/**
 * Permet d'afficher l'écran de chargement
 * @param state
 */
export function SET_IS_DATA_LOADING_TRUE(state)
{
    state.is_data_loading = true;
}


/**
 * Permet d'enlever l'écran de chargement
 * @param state
 */
export function SET_IS_DATA_LOADING_FALSE(state)
{
    state.is_data_loading = false;
}

/**
 * Permet de charger le token d'authentification
 * @param state
 * @param authentification_token
 */
export function SET_AUTHENTIFICATION_TOKEN(state, authentification_token)
{
    state.authentification_token = authentification_token
}

/**
 * Permet de donner le message en cas de success
 * @param state
 * @param success_message
 */
export function SET_SUCCESS_MESSAGE(state, success_message)
{
    state.success_message = success_message
}

/**
 * Permet de donner le message en cas d'erreur
 * @param state
 * @param error_message
 */
export function SET_ERROR_MESSAGE(state, error_message)
{
    state.error_message = error_message
}

/**
 * Permet d'afficher un message pour prévenir l'utilisateur
 * @param state
 * @param info_message
 */
export function SET_INFO_MESSAGE(state, info_message)
{
    state.info_message = info_message;
}


/**
 * Permet de dire que nous sommes connecté
 * @param state
 */
export function SET_IS_ACCESS_AUTHORIZED_TRUE(state)
{
    state.is_access_authorized = true;
}

/**
 * Permet de changer is connected pour signifier que nous somme deconnecté
 * @param state
 */
export function SET_IS_IS_ACCESS_AUTHORIZED_FALSE(state)
{
    state.is_access_authorized = false;
}

/**
 * Permet de stocker les données de l'utilisateur récupérer le l'API dans le store
 * @param state
 * @param user_data
 */
export function SET_USER_DATA(state, user_data)
{
    state.user_data = user_data
}