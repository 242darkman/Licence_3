package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;

import modele.game.Position;



@SuppressWarnings("unused")
public class PieceT extends Piece implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public PieceT() {
		// définition de la largeur de la pièce
				this.setWidth(3);
				
				// définition de la hauteur de la pièce
				this.setHeight(3);
				
				// initialisation de la grille où sera placé notre pièce
				this.grille = new boolean[this.getWidth()][this.getHeight()];
				
			
	
				creerGrille();
			}
	

	
	
	@Override
	public Piece creerPieceClone() {
		Piece pieceTClonee = new PieceT();
		pieceTClonee.setHeight(this.getHeight());
		pieceTClonee.setWidth(this.getWidth());
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				pieceTClonee.setElement(new Position(i, j), pieceTClonee.getElement(new Position(i, j)));
			}
		}
		return pieceTClonee;
	}

	
	/**
	 * Définition de la grille propre au tetriminos T
	 */
	@Override
	public void creerGrille() {
		float midHeight = this.getHeight() / 2;
		
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if ( (i == 0) || (j == midHeight)) {
					this.grille[i][j] = true;
				}
			}
		}
	}

}
