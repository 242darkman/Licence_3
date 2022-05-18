import state          from './state'       //Va stocker nos différents variables
import * as getters   from './getters'     //Permet de récupérer les valeurs contenu dans state
import * as mutations from './mutations'   //Permet d'éffectuer des modifications dans le state
import * as actions   from './actions'     //Permet de faire des calcul et d'appeler des mutations


export default {
    namespaced: true,
    getters,
    mutations,
    actions,
    state
}