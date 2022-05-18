export default function()
{
    return {

        is_data_loading        : false,     //Permet de savoir si nous devons afficher un écran de chargement
        success_message        : '',        //Permet de stocker un message à afficher en cas de success d'une requête
        error_message          : '',        //Permet de stocker un message à afficher en cas d'erreur d'une requête
        info_message           : '',        //Permet de stocker un message d'info à afficher à l'utilisateur
        authentification_token : '',        //Permet de stocker le token d'authentification
        is_access_authorized   : false,     //Permet de savoir si l'utilisateur est identifier ou non
        user_data              : []         //Permet de stocker les données de l'utilisateur

    }
}