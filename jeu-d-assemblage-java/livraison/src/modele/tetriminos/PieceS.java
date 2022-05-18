package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;

import modele.game.Position;




@SuppressWarnings("unused")
public class PieceS extends Piece implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	



	public PieceS() {
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
		Piece pieceSClonee = new PieceS();
		pieceSClonee.setHeight(this.getHeight());
		pieceSClonee.setWidth(this.getWidth());
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				pieceSClonee.setElement(new Position(i, j), pieceSClonee.getElement(new Position(i, j)));
			}
		}
		return pieceSClonee;
	}

	
	/**
	 * Définition de la grille propre au tetriminos T
	 */
	@Override
	public void creerGrille() {
            
		float midHeight = this.getHeight() / 2;
		
	for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if (((i == 0) && (j>0)) || (j == midHeight) || (j<2 && i==this.getWidth()-1)) {
					this.grille[i][j] = true;
				}
			}
		}
		
//		this.grille[0][1] = true;
//		this.grille[0][2] = true;
//		
//		this.grille[1][1] = true;
//		
//		
//		
//		this.grille[2][0] = true;
//		this.grille[2][1] = true;
	}

}
