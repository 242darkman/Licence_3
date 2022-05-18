/**
 * Permet d'affecter la liste de TODO en cours
 * @param state
 * @param list_position
 */
export function SET_CURRENT_LIST(state, list_position)
{
    state.current_list = state.default_list[list_position]
}

/**
 * Permet de stocker la liste récupérer depuis l'API
 * @param state
 * @param default_list
 */
export function SET_DEFAULT_LIST(state, default_list)
{
    state.default_list = default_list
}

/**
 * On remet le message de success par défaut
 * @param state
 */
export function SET_DEFAULT_SUCCESS_MESSAGE(state)
{
    SET_SUCCESS_MESSAGE(state,'')
}

/**
 * On remet le message d'erreur par défaut
 * @param state
 */
export function SET_DEFAULT_ERROR_MESSAGE(state)
{
    SET_ERROR_MESSAGE(state,'')
}

/**
 * On remet le message d'information par défaut
 * @param state
 */
export function SET_DEFAULT_INFO_MESSAGE(state)
{
    SET_INFO_MESSAGE(state,'')
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
 * Permet de rajouter une TODO dans notre liste en LOCAL
 * @param state
 * @param id            : ID de la todo
 * @param todolist_id   : ID de la liste dans la quel nous devons ajouter la todo
 * @param name          : Le nom de la todo
 * @param completed     : Son status ( finis ou pas )
 * @param created_at    : Date de création
 * @param update_at     : Date de mise à jour
 * @constructor
 */
export function ADD_TODO_IN_LIST(state,{ id, todolist_id, name, completed, created_at, update_at})
{
    //On ajoute une todo dans notre liste sur la quel nous travaillons
    state.current_list.todos.push({
        id          : id,
        todolist_id : todolist_id,
        name        : name,
        completed   : completed,
        created_at  : created_at,
        update_at   : update_at,
    })

}

/**
 * Permet de retirer une todo d'une liste en local
 * @param state
 * @param todo_id : L'id de LA TODO à retirer
 */
export function REMOVE_TODO_IN_LIST(state, todo_id)
{
    let i = get_index(state.current_list.todos, todo_id) //On trouve la position de la todo dans la liste
    state.current_list.todos.splice(i, 1)                //On supprime la todo de la liste
}

/**
 * Permet de changer le status de la liste
 * @param state
 * @param todo_id : L'id de la todo
 */
export function UPDATE_TODO_STATUS_IN_LIST(state, todo_id)
{
    let i = get_index(state.current_list.todos, todo_id)    //On trouve la position de la todo dans la liste
    state.current_list.todos[i].completed = !state.current_list.todos[i].completed //Le status de la todo ( TRUE = FALSE et FALSE = TRUE )
}

/**
 * Met à jour le nom de la liste
 * @param state
 * @param todo_id : l'id de la todo
 * @param new_name : le nouveau nom de la todo
 */
export function UPDATE_TODO_NAME_IN_LIST(state, {todo_id, new_name})
{
    let i = get_index(state.current_list.todos, todo_id)
    state.current_list.todos[i].name = new_name

}

/**
 * Permet de supprimer une liste de todos
 * @param state
 * @param todo_list_id : L'id de la liste de la todo
 * @constructor
 */
export function DELETE_TODO_LIST(state, todo_list_id)
{
    let i = get_index(state.default_list, todo_list_id) //On trouve la position de la liste de todo dans la la liste contenant toute les listes de TODO
    state.default_list.splice(i, 1)           //On supprimer notre liste
    SET_CURRENT_LIST(state, null)             //On remet la liste sur la quel on travaille à NULL
}

/**
 * Permet de créer une liste de TODO
 * @param state
 * @param id            : l'id de la liste
 * @param name          : le nom de la liste
 * @param user_id       : l'id de l'utilisateur qui a créer la liste
 * @param created_at    : la date de création de la liste
 * @param updated_at    : la date de la dernière mise à jour de la liste
 * @param nb_todos      : Le nombre de todos dans la liste
 * @param todos         : la liste contenant toutes les todos
 */
export function CREATE_TODO_LIST(state, {id, name, user_id, created_at, updated_at, nb_todos, todos})
{

    console.log(todos)

    /**
     * On créer notre liste
     */
    state.default_list.push({
        id          : id,
        name        : name,
        user_id     : user_id,
        created_at  : created_at,
        updated_at  : updated_at,
        nb_todos    : nb_todos,
        todos       : todos
    })

}

/**
 * Permet de mettre le filtre sur les todo non finis
 * @param state
 * @constructor
 */
export function SET_NOT_FINISH_TODOS(state)
{
    state.filter = 'todo'
}

/**
 * Permet de mettre le filtre sur les todos finis
 * @param state
 * @constructor
 */
export function SET_FINISH_TODOS(state)
{
    state.filter = 'do'
}

/**
 * Permet de mettre le filtre sur toutes les todos
 * @param state
 * @constructor
 */
export function SET_ALL_TODOS(state)
{
    state.filter = 'all'
}


/**
 * Permet de récupérer la position de la todo dans une liste
 * @param todo_id
 */
function get_index(liste,todo_id)
{
    return liste.map(item => item.id).indexOf(todo_id)
}



