import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'; // <-- aliased path


/*

    LOCAL STORAGE : TOKEN

 */

const routes = [
    {
        path: '/',
        name: 'Welcome',
        component: () => import('../views/Welcome/Welcome.vue'),

        beforeEnter(to, from, next) {

            if(localStorage.getItem('authentification_token')) {
                next({
                    name : "Home"
                });
            }else{
                next();
            }

        }

    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('../views/Home/Home.vue'),
        beforeEnter(to, from, next) {


            if(localStorage.getItem('authentification_token'))
            {
                next();
            }else{
                if(store.getters['accounts/GET_IS_ACCESS_AUTHORIZED']) {
                    next()
                }else{
                    next({
                        name : "Welcome"
                    });
                }
            }

        }

    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router



/*
beforeEnter: () => {


let authentification_token = ''

if(!localStorage.authentification_token) {
  authentification_token = store.getters['accounts/GET_AUTHENTIFICATION_TOKEN'];
}else{
  authentification_token = localStorage.authentification_token
}

console.log(authentification_token)

axios.create({
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer '+ authentification_token
  }
})
    .get('http://138.68.74.39/api/user')
    .catch((error) => (

        router.push('/'),
            console.log(error)

    ))

},
 */