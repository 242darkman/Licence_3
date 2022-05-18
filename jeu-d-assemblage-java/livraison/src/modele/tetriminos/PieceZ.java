package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;

import modele.game.Position;


@SuppressWarnings("unused")
public class PieceZ extends Piece implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public PieceZ() {
		
		// définition de la largeur de la pièce
		this.setWidth(3);
		
		// définition de la hauteur de la pièce
		this.setHeight(3);
		
		// définition du sens d'orientation de la pièce (position initiale)

		
		// initialisation de la grille où sera placé notre pièce
		this.grille = new boolean[this.getWidth()][this.getHeight()];
	
		
		creerGrille();

	}



	@Override
	public Piece creerPieceClone() {
		Piece pieceZClonee = new PieceZ();
		pieceZClonee.setHeight(this.getHeight());
		pieceZClonee.setWidth(this.getWidth());
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				pieceZClonee.setElement(new Position(i, j), pieceZClonee.getElement(new Position(i, j)));
			}
		}
		return pieceZClonee;
	}

	
	
	@Override
	public void creerGrille() {
		float midHeight = this.getHeight()/2;
		
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille[i].length; j++) {
				if ( ((i == 0) && (j < 2)) || (j == midHeight) || (j>0 && i == this.getWidth()-1)) {
					this.grille[i][j] = true;
				}
			}
		}

	}
	
	
	

}
