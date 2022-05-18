package modele.util;



/**
 * Interface implémentée par les modèles. L'ajout et le retrait d'écouteur
 * doit être redéfini.
 *
 */
public interface Observable {

	/**
	 * Méthode ajoutant un observateur à la liste interne des observateurs.
	 * @param obs
	 */
	public void addObserver(Observer obs);
	
	
	/**
	 * Méthode ajoutant un observateur à la liste interne des observateurs.
	 */
	public void removeObserver();
	
	
	/**
	 * Méthode vérifiant l'indicateur interne pour voir si l'observable 
	 * a changé d'état et avertit tous les observateurs.
	 */
	public void notifyObservers();
}
