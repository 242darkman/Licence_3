package modele.util;


/**
 * Interface Ecouteur modèle
 *
 */
public interface Observer {

	
	/**
	 * Méthode appelée lorsqu'un changement est produit dans l'état de l'observable.
	 * @param obs
	 */
	public void update();
}
