package modele.tetriminos;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import modele.game.Plateau;
import modele.game.Position;
import modele.util.AbstractObservable;
import modele.util.Observer;



@SuppressWarnings("unused")
public abstract class Piece extends AbstractObservable implements PieceInterface, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int width;

	private int height;
	private int[] positionPiece = new int[2];
	
	
	protected boolean[][] grille;
	
	
	public void setGrille(boolean[][] grille) {
		this.grille = grille;
	}





	/**
	 * Position courante d'une pièce dans la grille
	 */
	private Position posPiece;
	
	/**
	 * Observer qui va effectuer une mise à jour de notre pièce dans la grille
	 */
	protected Observer observer;
	
	
	
	private Plateau state;
	
	




	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public boolean getElement(Position position) {
		return this.grille[position.getX()][position.getY()];
	}

	@Override
	public Position getCenterPosition() {
		return new Position(this.height/2, this.width/2);
	}

	
	public Position getPosPiece() {
		return posPiece;
	}
	
	public int[] getPositionPiece() {
		return this.positionPiece;
	}
	
	/**
	 * Méthode permettant de récupérer un élément d'une cellule dans la grille principale de jeu
	 * @param state
	 * @param pos
	 * @return
	 */
	public boolean getCellElementInMainGrid(Plateau state, Position pos) {
		boolean[][] plateauPrincipal = state.getGrillePrincipale();
		if(pos.getX() <10 && pos.getY() < 10) {
			return plateauPrincipal[pos.getX()][pos.getY()];
		}
		return false;
		
		
	}


	@Override
	public void setWidth(int width) {
		this.width = width;

	}

	@Override
	public void setHeight(int height) {
		this.height = height;

	}

	
	
	public void setPiecePosition(int x,int y) {
		this.positionPiece[0] = x;
		this.positionPiece[1] = y;
	}
	
	
	public void setElement(Position pos, boolean element) {
		this.grille[pos.getX()][pos.getY()] = element;
	}
	
	
	public abstract Piece creerPieceClone();
	
	public abstract void creerGrille();



	
	@Override
	public void applyTranslation(Direction direction) {
		this.state = new Plateau();
		
		this.makeTranslation(direction);
		this.notifyObservers();
	}
	
	
	/**
	 * Méthode qui suivant la direction souhaitée, applique les modifications aux coordonnées de la pièce
	 * @param direction : direction dans laquelle on veut déplacé notre pièce
	 */
	public void makeTranslation(Direction direction) {

		switch (direction) {
		case Haut: 
			this.setPiecePosition(this.getPositionPiece()[0]-1,this.getPositionPiece()[1]);
				break;
		case Bas: 
			this.setPiecePosition(this.getPositionPiece()[0]+1,this.getPositionPiece()[1]);
				break;
		case Droite: 
				this.setPiecePosition(this.getPositionPiece()[0],this.getPositionPiece()[1] +1);
				break;
		case Gauche:
				this.setPiecePosition(this.getPositionPiece()[0],this.getPositionPiece()[1] -1);
				break;
		}
				this.notifyObservers();
			
	}

	
	public boolean[][] getGrille() {
		return grille;
	}

	/**
	 * Méthode qui applique une rotation sur notre grille
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void applyRotation(Direction dir) {
		
		switch (dir) {
		case Droite:
			this.makeRotationToRight();
			break;
		case Gauche:
			this.makeRotationToLeft();
			break;
		}
	}
	
	
	/**
	 * Méthode permettant d'effectuer sur une pièce une rotation vers la droite
	 */
	public void makeRotationToRight() {
		int ligne = this.grille.length;
		int colonne = this.grille[0].length;
		boolean nouvelle_Grille [][] = new boolean [colonne][ligne];
		
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				nouvelle_Grille[j][i] = this.grille[ligne-i-1][j];
			}
		}
		setGrille(nouvelle_Grille);
	}
	

	/**
	 * Méthode permettant d'effectuer sur une pièce une rotation vers la gauche
	 */
   public void makeRotationToLeft() {
		int ligne = this.grille.length;
		int colonne = this.grille[0].length;
		boolean nouvelle_Grille [][] = new boolean [colonne][ligne];
		
		for(int i=0; i<ligne; i++) {
			for(int j=0; j<colonne; j++) {
				nouvelle_Grille[j][i] = this.grille[i][colonne -j -1];
			}
		}
		setGrille(nouvelle_Grille);
	}
	
	
	
	/**
	 * Redéfinition de la méthode d'affichage
	 */
	@Override
	public String toString() {
		String chaine = "";
				
		
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				chaine += this.getElement(new Position(i, j));
				chaine += ("  ");
			}
			chaine     += ("\n");
		}
		chaine += ("\n");
		
		return chaine;
	}

	
}
