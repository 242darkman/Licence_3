package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;

import modele.game.Position;

@SuppressWarnings("unused")
public class PieceJ extends Piece implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PieceJ() {
		// définition de la largeur de la pièce
				this.setWidth(3);
				
				// définition de la hauteur de la pièce
				this.setHeight(3);
				
				// initialisation de la grille où sera placé notre pièce
				this.grille = new boolean[this.getWidth()][this.getHeight()];
				
				creerGrille();
	}
	
	

	/**
	 * Méthode créant le tetriminos L
	 * @return : la pièce L clonée
	 */
	@Override
	public Piece creerPieceClone() {
		Piece pieceLClonee = new PieceL();
		pieceLClonee.setHeight(this.getHeight());
		pieceLClonee.setWidth(this.getWidth());
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				pieceLClonee.setElement(new Position(i, j), pieceLClonee.getElement(new Position(i, j)));
			}
		}
		
		return pieceLClonee;
	}

	
	/**
	 * Méthode qui définie la grille propre à la grille L
	 */
	@Override
	public void creerGrille() {
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if ( (i == (this.getHeight()) - 1) || (j == 2)) {
					this.grille[i][j] = true;
				}
			}
		}
	}

	



}
