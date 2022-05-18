package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;

import modele.game.Position;


/**
 * Création du tetriminos I
 *
 */
@SuppressWarnings("unused")
public class PieceI extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;




		/**
		 * PieceL constructor
		 */

		public PieceI() {
			// définition de la largeur de la pièce
			this.setWidth(3);

			// définition de la hauteur de la pièce
			this.setHeight(3);



			// initialisation de la grille où sera placé notre pièce
			this.grille = new boolean[this.getWidth()][this.getHeight()];
			
			creerGrille();
			
			
		}

		

		

		/**
		 * Méthode créant le tetriminos I
		 * @return : la pièce L clonée
		 */
		@Override
		public Piece creerPieceClone() {
			Piece pieceIClonee = new PieceI();
			pieceIClonee.setHeight(this.getHeight());
			pieceIClonee.setWidth(this.getWidth());

			for (int i = 0; i < this.getWidth(); i++) {
				for (int j = 0; j < this.getHeight(); j++) {
					pieceIClonee.setElement(new Position(i, j), pieceIClonee.getElement(new Position(i, j)));
				}
			}

			return pieceIClonee;
		}

		

		/**
		 * Méthode qui définie la grille propre à la grille L
		 */
		@Override
		public void creerGrille() {
		    float midHeight = this.getHeight() / 2;

			for(int i = 0; i < this.grille.length; i++) {
				for (int j = 0; j < this.grille.length; j++) {
					if ((j == midHeight)) {
						this.grille[i][j] = true;
					}
				}
			}
	    }

		






}
