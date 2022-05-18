import { createStore } from 'vuex'

import todos    from './todos'      //Store permettant de gérer les TODOS
import accounts from './accounts'   //Store permettant de gérer le système de compte

/**
 * On va créer notre store avec les deux modules importé
 */
export default createStore({
  debug : true,
  modules : {
    todos,
    accounts
  }
})