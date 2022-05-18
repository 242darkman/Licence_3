package modele.tetriminos;

import java.io.Serializable;

import modele.game.Position;



@SuppressWarnings("unused")
public class PieceO extends Piece implements Serializable{

	private static final long serialVersionUID = 1L;






	/**
	 * Constructeur de  la Classe 
	 * 
	 */
	public PieceO() {
		// définition de la largeur de la pièce
				this.setWidth(3);
				
				// définition de la hauteur de la pièce
				this.setHeight(3);
				
				// initialisation de la grille où sera placé notre pièce
				this.grille = new boolean[this.getWidth()][this.getHeight()];
				
			
				creerGrille();

			}
	
	
	
	
	
	
	
	
	/**
	 * Création de la Piece O
	 */
	@Override
	public void creerGrille() {
		float midHeight = this.getHeight()/2; 
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if ((i == 0) || (j==0) || (i == this.getHeight()-1 || j == this.getWidth()-1)) {
					this.grille[i][j] = true;
				}
			}
		}
	}
	
	
	/*
	 * Piece Clonée
	 */
	@Override
	public Piece creerPieceClone() {
		Piece pieceOClonee = new PieceZ();
		pieceOClonee.setHeight(this.getHeight());
		pieceOClonee.setWidth(this.getWidth());
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				pieceOClonee.setElement(new Position(i, j), pieceOClonee.getElement(new Position(i, j)));
			}
		}
		
		return pieceOClonee;
	}
	
	

	

}
