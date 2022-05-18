package modele.tetriminos;

import java.util.Random;

/**
 * Énumération des directions utilisées pour jouer au jeu d'assemblage.
 * @author brandon
 *
 */
public enum Direction {
	/** Direction Haut */
	Haut,
	/** Direction Bas */
	Bas,
	/** Direction Droite */
	Droite,
	/** Direction Gauche */
	Gauche;
	
	
	/**
	 * Méthode renvoyant une direction aléatoirement
	 * @return : une direction aléatoire parmi celles qui ont été définies
	 */
	public static Direction getRandomlyADirection() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
	
	
	
	/**
	 * Méthode qui nous donne un symbole pour une direction
	 * @param dir : direction dont on veut avoir le symbole
	 * @return : le symbole d'une direction
	 */
	public static String getSymbolDirection(Direction dir) {
		
		switch (dir) {
		case Haut:
			return "↑";
		case Bas:
			return "↓";
		case Droite:
			return "→";
		case Gauche:
			return "←";
		default:
			return "*";
		}
	}
}
