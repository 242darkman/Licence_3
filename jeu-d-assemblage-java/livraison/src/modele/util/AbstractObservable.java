package modele.util;

import java.util.ArrayList;
import java.util.List;





public abstract class AbstractObservable implements Observable {
	/**
	 * Liste d'écouteurs du modèle
	 */
	private List<Observer> ecouteurs;
	
	
	public AbstractObservable() {
		this.ecouteurs = new ArrayList<Observer>();
	}


	
	
	/**
	 * Ajout d'un écouteur sur le modèle
	 * @param obs : Ecouteur
	 */
	@Override
	public void addObserver(Observer obs) {
		this.ecouteurs.add(obs);
	}

	
	
	
	/**
	 * Retrait d'un écouteur sur le modèle
	 */
	@Override
	public void removeObserver() {
		this.ecouteurs = new ArrayList<Observer>();
	}

	
	
	/**
	 * Notifie tous les écouteurs qu'il y a eu un changement sur le modèle
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : ecouteurs) {
			observer.update();
		}
	}

}
