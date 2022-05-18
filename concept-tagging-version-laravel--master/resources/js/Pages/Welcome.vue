<template>
    <!--
    <div class="relative flex items-top justify-center min-h-screen bg-gray-100 dark:bg-gray-900 sm:items-center sm:pt-0">
        <div v-if="canLogin" class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
            <inertia-link v-if="$page.props.user" href="/dashboard" class="text-sm text-gray-700 underline">
                Dashboard
            </inertia-link>

            <template v-else>
                <inertia-link :href="route('login')" class="text-sm text-gray-700 underline">
                    Connexion
                </inertia-link>

                <inertia-link v-if="canRegister" :href="route('register')" class="ml-4 text-sm text-gray-700 underline">
                    Inscription
                </inertia-link>
            </template>
        </div>
    </div>-->

    <div class="px-4 py-5 my-5 text-center">
        <img class="d-block mx-auto mb-4" src="../../../public/images/logo.png" alt="" width="100" height="100">
        <h1 class="display-5 fw-bold">Concept Tapping</h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">Le but du jeu est de faire deviner un mot à ses coéquipiers sans utiliser certains mots interdits ou tabous. Il se joue avec un paquet de cartes sur lesquelles sont inscrits des mots à faire deviner et la liste des cinq mots interdits.</p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center" v-if="!$page.props.user">
                <inertia-link :href="route('register')" class="text-sm text-gray-700 underline">
                    <button type="button" class="btn btn-primary btn-lg px-4 me-sm-3">S'inscrire</button>
                </inertia-link>

                <inertia-link :href="route('login')" class="text-sm text-gray-700 underline">
                    <button type="button" class="btn btn-outline-primary btn-lg px-4">Se connecter</button>
                </inertia-link>
            </div>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center" v-else>
                <inertia-link v-if="$page.props.user" href="/dashboard" class="text-sm text-gray-700 underline">
                    <button type="button" class="btn btn-outline-primary btn-lg px-4">Dashboard</button>
                </inertia-link>
            </div>
        </div>
    </div>


    <div class="b-example-divider mb-0"></div>

</template>

<style scoped>
    #main-container
    {
        margin-top: 150px;
    }
</style>

<script>
    export default {
        props: {
            canLogin: Boolean,
            canRegister: Boolean,
            laravelVersion: String,
            phpVersion: String,
        }
    }
</script>
