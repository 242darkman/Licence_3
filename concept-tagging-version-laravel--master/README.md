#Jeu de TABOO 



#Repértoire : 

##Controlleurs :
    app/Http/Controllers
##Models : 
    app/Models
##Views : 
    resources/js

#Technologie utilisé 

##Front-End : 
    
    Vue 3 - JS
    Bootstrap 5

##Back-End : 

    Laravel
    Plugin Laravel : JetStream ( Base pour la création et la gestion des comptes )

##Autre : 

    Inertia ( Pour faire fonctionner Laravel et Vue3 - JS ensemble )

#Commandes : 

Lancement du serveur : 
    
    php artisan serve 

Compiler les fichiers Vue JS

    npm run dev

Compiler les fichiers Vue JS quand il y'a un changement de detecté 

    npm run watch


Créer un controlleur

    php artisan make:controller NomDuController

Créer un models 

    php artisan make:model NomDuModel -m


Faire une migration des données de la base 

    php artisan migrate


Réinitialiser la base de données 

    php artisan migrate:refresh


