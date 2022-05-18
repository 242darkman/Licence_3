export default function()
{
    return {

        is_data_loading        : false,     //Permet de savoir si nous devons afficher un écran de chargement
        success_message        : '',        //Permet de stocker un message à afficher en cas de success d'une requête
        error_message          : '',        //Permet de stocker un message à afficher en cas d'erreur d'une requête
        info_message           : '',        //Permet de stocker un message d'info à afficher à l'utilisateur
        default_list           : [],        //La liste de contenant toutes les lists de TODO récupérer depuis l'api
        current_list           : null,      //La liste courrante sur la quel nous travaillons
        filter                 : 'all'      //Le filtre à appliquer sur notre liste

    }
}