package modele.tetriminos;


public abstract class AbstractPieceCreator {
	
	
	/**
	 * La méthode buildPiece() permet de creer une piece suivant le type de tetriminos souhaité.
	 *
	 *  Cette méthode intègre le design pattern car la formation d'une piece lui est déléguée.
	 * 	En effet, on remarque que la méthode buildPiece() appel une autre méthode PieceBuilder() qui est elle retourne uniquement le type de piece souhaité.
     * 
     * 	Elle créee aussi la grille correspondant à la pièce
     * 
	 * 
	 * @param forme : forme que l'on souhaite construire
	 * @return : le type de pièce passé en @param
	 */
	public Piece buildPiece(TypeDeTetriminos forme) {
		Piece p = this.PieceBuilder(forme);
		p.creerGrille();
		return p;
	}
	
	
	
	/**
	 * Méthode abstraite permettant de créer une pièce en lui indiquant la forme voulu
	 * @param forme
	 * @return
	 */
	public abstract Piece PieceBuilder(TypeDeTetriminos forme);
}
