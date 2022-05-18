package modele.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import modele.tetriminos.Direction;
import modele.tetriminos.Piece;
import modele.tetriminos.PieceI;
import modele.tetriminos.PieceJ;
import modele.tetriminos.PieceL;
import modele.tetriminos.PieceO;
import modele.tetriminos.PieceS;
import modele.tetriminos.PieceT;
import modele.tetriminos.PieceZ;
import modele.util.AbstractObservable;
import modele.util.Observer;

public class Plateau extends AbstractObservable implements Observer {
	/**
	 * fichier de sauvegarde de la configuration du plateau
	 */
	private static final String BACKUP_FILE_NAME = "sauvegarde.txt";
	/**
	 * fichier de sauvegarde des meilleures scores
	 */
	private static final String MAX_SCORE_FILE_NAME = "max_score.txt";
	
	/**
	 * hauteur de la grille
	 */
	private int hauteur = 20;
	/**
	 * largeur de la grille
	 */
	private int largeur = 20;
	/**
	 * piece courante du jeu dans la grille
	 */
	private Piece currentPiece;
	/**
	 * grille(plateau) de jeu
	 */
	private boolean[][] grillePrincipale;
	/**
	 * liste des pièces contenues dans le plateau
	 */
	private ArrayList<Piece> listePieces;
	private int score;
	





	/**
	 * Plateau constructor
	 */
	public Plateau() {
		this.grillePrincipale = new boolean[hauteur][largeur];
		try {
			this.restoreConfigGame();
		}catch (Exception e) {
			// TODO: handle exception
			// initialise si sauvegarde non trouvee
			initGame();
			
		}
	
	}
	


	
	/**
	 * Initialise une nouvelle partie.
	 */
	public void initGame() {
		listePieces = new ArrayList<Piece>();
		this.addPieceInList();
		//afficher les pi�ces dans une grille de jeux
		this.placerPiecesDansGrille();
	}
	
	
	
	/**
	 * Ajout de la piece dans la liste
	 */
	public void addPieceInList() {
		//tant que la liste ne contient pas 8 piece continue on continue a generer les pieces a des positions aleatoires
		while(this.listePieces.size() !=7 ) {
			Piece pieceAdd = makePiece();
			Random rand = new Random();
			int xPostion = rand.nextInt(17-pieceAdd.getHeight());
			int yPostion = rand.nextInt(17-pieceAdd.getWidth());
			pieceAdd.setPiecePosition(xPostion,yPostion);
			if (estPlacable(pieceAdd)) {
				this.listePieces.add(pieceAdd);
			}
		}
	}
	

	
	/**
	 * Méthode de placement des pieces
	 * @param piece : la pièce à placer
	 * @return true si la pièce peut etre placé, false sinon
	 */
	public boolean estPlacable(Piece piece) {
		int xi,yi;
		if(this.listePieces.size() == 0) {
			return true;
		}else{
			if(piece.getPositionPiece()[0] >=0 && piece.getPositionPiece()[1] >= 0 && piece.getPositionPiece()[0]+piece.getHeight() <= this.hauteur && piece.getPositionPiece()[1]+piece.getWidth() <= this.largeur){
				//on parcour en verifiant 
				for (int i = 0; i <piece.getHeight(); i++) {
					for (int j = 0; j < piece.getWidth(); j++) {
						if(piece.getGrille()[i][j] == true) {
							xi = piece.getPositionPiece()[0] + i;
							yi = piece.getPositionPiece()[1] +j;
							if(!colision(xi, yi)) {
								
								return false;
							}
						}
					}
				}
				return true;
			}
			return false;
		}
	}
	
	
	/**
	 * méthode vérifiant les possibles colisions dans le jeu 
	 * @param x abscisse
	 * @param y ordonnées
	 * @return true si il y a colision, false sinon
	 */
	public boolean colision(int x,int y) {
		int xi,yi;
		//on parcour en verifiant 
		for(Piece currentPiece : this.listePieces) {
			
			for(int i = 0;i<currentPiece.getHeight();i++) {
				for(int j = 0;j<currentPiece.getWidth();j++) {
					if(currentPiece.getGrille()[i][j] == true) {
						xi = currentPiece.getPositionPiece()[0] + i;
						yi = currentPiece.getPositionPiece()[1] + j;
						
						if(x == xi && y == yi) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	
	
	/**
	 * Redefinition de la methode d'affichage de notre plateau de jeu
	 * @return le plateau de jeu
	 */
	public String toString() {
		String chaine = "";

		for (int ligne = 0; ligne < this.grillePrincipale.length; ligne++) {
			for (int colonne = 0; colonne < this.grillePrincipale[ligne].length; colonne++) {
				/**
				 * on representera les cases occupee par un et les cases vides par un tiret
				 */
				chaine += this.grillePrincipale[ligne][colonne] ? "1" : "-";
				chaine += " ";
			}
			chaine     += "\n";
		}
		return chaine;
	}
	
	
	
	/**
	 * Methode qui place une piece dans la grille
	 */
	public void placerPiecesDansGrille() {
		this.cleanGrille();
			for(Piece piece : this.listePieces) {
				
				for(int i = 0;i<piece.getHeight();i++) {
					for(int j = 0 ;j<piece.getWidth();j++) {
						//cette condtion permetra de pas explosé le 1 pour metre 0
						if(this.grillePrincipale[piece.getPositionPiece()[0]+i][piece.getPositionPiece()[1]+j] == true && piece.getGrille()[i][j] == false) {
							this.grillePrincipale[piece.getPositionPiece()[0]+i][piece.getPositionPiece()[1]+j] = true;
						}else {
							this.grillePrincipale[piece.getPositionPiece()[0]+i][piece.getPositionPiece()[1]+j] = piece.getGrille()[i][j];
						}
						
					}
				}
			}
	}
	
	
	
	/**
	 * Methode permettant de vider notre plateau de jeu
	 */
	public void cleanGrille() {
		for (int i = 0; i < grillePrincipale.length; i++) {
			for (int j = 0; j < grillePrincipale.length; j++) {
				this.grillePrincipale[i][j] = false;
			}
		}
	}
	
	
	
	/**
	 * Methode permettant de generer une piece aléatoirement
	 * @return la pièce crée
	 */
	public Piece makePiece() {
		Random rand = new Random();
		int numeroPiece = rand.nextInt(7);
		if(numeroPiece == 0) { 
			Piece pieceL = new PieceL();
			return pieceL;
		}else if(numeroPiece == 1) {
			Piece pieceO= new PieceO();
			return pieceO;
		}else if(numeroPiece == 2) {
			Piece pieceS = new PieceS();
			return pieceS;
		}else if(numeroPiece == 3) { 
			Piece pieceZ = new PieceZ();
			return pieceZ;
		}else if(numeroPiece == 4) { //piece S 
			Piece pieceT = new PieceT();
			return pieceT;
		}else if(numeroPiece == 5) { //piece T 
			Piece pieceI = new PieceI();
			return pieceI;
		}else if(numeroPiece == 6) { //piece Z 
			Piece pieceJ = new PieceJ();
			return pieceJ;
		}
		return null;
	}
	
	
	/**
	 * Methode permettant de rechercher une piece dans la grille de jeu 
	 * @param x position
	 * @param y position 
	 * @return true si une pièce a été trouvé, false sinon
	 */
	 public boolean searchCurrentPiece(int x,int y)
	    {
		 	if( (x < this.hauteur && x >= 0) && (y < this.largeur && y >= 0)) {
		 		for(Piece p : this.listePieces){
	            	for(int i = 0;i<p.getHeight();i++) {
	    				for(int j = 0;j<p.getWidth();j++) {
	    						int xi = p.getPositionPiece()[0] + i;
	    						int yi = p.getPositionPiece()[1] + j;
	    						//verification 
	    						if( (x == xi && y == yi) && (p.getGrille()[i][j] == true ) ) {
	    							this.currentPiece = p;
	    							return true;
	    						}
	    				}
	    			}
	             }
	            this.currentPiece = null;
	            return false;
		 	}
		 	return false;
	    }
	 
	 
	 
	 /**
	  * Methode permettant dappliquer les differents mouvements possible
	  * @param piece
	  * @param str chaine de caractere
	  */
	 public void playOfState( Piece piece, String str) {
			Piece pieceClone = this.clonePiece(piece); 
			
			if(str == "rot_droite") {
				pieceClone.makeRotationToRight();
				
				// verification colision
				Piece p = piece;
				this.listePieces.remove(piece);
				
				//On verifie si on peut placer une piece
				if(estPlacable(pieceClone)) {
					p.makeRotationToRight();
					this.listePieces.add(p);
					System.out.println("Rotation appliquee à droite");
					
				}else {
					this.listePieces.add(p);
					System.out.println("Rotation impossible !!!!");
				}
			}
			
			else if(str == "rot_gauche") {
				pieceClone.makeRotationToLeft();
				
				// verification colision
				Piece p = piece;
				this.listePieces.remove(piece);
				
				//On verifie si on peut placer une piece
				if(estPlacable(pieceClone)) {
					p.makeRotationToRight();
					this.listePieces.add(p);
					System.out.println("Rotation appliquée à droite");
					
				}else {
					this.listePieces.add(p);
					System.out.println("Rotation impossible !!!!");
				}
			}
			else if(str == "droite"){
				pieceClone.makeTranslation(Direction.Droite);
				
				// verification colision
				Piece p = piece;
				this.listePieces.remove(piece);
				
				//On verifie si on peut placer une piece
				if(estPlacable(pieceClone)) {
					p.makeTranslation(Direction.Droite);
					this.listePieces.add(p);
					System.out.println("Deplacement appliquée à droite!!!!");
					
				}else {
					this.listePieces.add(p);
					System.out.println("Deplacement impossible !!!!");
				}
				
			}else if(str =="gauche") {
				pieceClone.makeTranslation(Direction.Gauche);
				
				// verification de la colision
				Piece p = piece;
				this.listePieces.remove(piece);
				if(estPlacable(pieceClone)) {
					p.makeTranslation(Direction.Gauche);
					this.listePieces.add(p);
					System.out.println("Deplacement appliquée à gauche!!!!");
				}else {
					this.listePieces.add(p);
				}
			}
			
			else if(str =="bas") {
				pieceClone.makeTranslation(Direction.Bas);
				
				// verification de la colision
				Piece p = piece;
				this.listePieces.remove(piece);
				if(estPlacable(pieceClone)) {
					p.makeTranslation(Direction.Bas);
					this.listePieces.add(p);
					System.out.println("Deplacement appliquée en bas!!!!");
				}else {
					this.listePieces.add(p);
				}
			}
			
			else if(str =="haut") {
				pieceClone.makeTranslation(Direction.Haut);
				
				// verification de la colision
				Piece p = piece;
				this.listePieces.remove(piece);
				if(estPlacable(pieceClone)) {
					p.makeTranslation(Direction.Haut);
					this.listePieces.add(p);
					System.out.println("Deplacement appliquée en droite!!!!");
				}else {
					this.listePieces.add(p);
				}
			}
		}
	 
	 
	 
	 /**
	  * Methode permettant de cloner une piece afin de tester les mouvements sur le clone
	  * @param piece
	  * @return une pièce clonée
	  */
	 public Piece clonePiece( Piece piece) {
			
			Piece clonepiece = new PieceO(); 
			
			for(int i=0; i<clonepiece.getHeight(); i++) {
				for(int j=0; j<clonepiece.getWidth(); j++) {
					clonepiece.getGrille()[i][j] =  piece.getGrille()[i][j]; 
				}
			}
			
			clonepiece.setPiecePosition(piece.getPositionPiece()[0], piece.getPositionPiece()[1]);
			return clonepiece; 
		}
	 


	 /**
	  * Methode permettant de calculer le score de la partie 
	  * @return le score obtenu
	  */
	 public float score() {
			
			float Xmin =Float.NEGATIVE_INFINITY;
			float Ymin = Float.NEGATIVE_INFINITY; 
			float Xmax = Float.POSITIVE_INFINITY; 
			float Ymax = Float.POSITIVE_INFINITY; 
			float X, Y, s, S; 
			
			// Parcours de notre conteneur de Piece 
			for (Piece piece : getListePieces()) {
				if (Xmin<piece.getPositionPiece()[0] ) {
					Xmin = piece.getPositionPiece()[1]; 
				}
				if (Ymin<piece.getPositionPiece()[1] ) {
					Ymin = piece.getPositionPiece()[1]; 
				}
				if (Xmax>piece.getPositionPiece()[0]+piece.getHeight()) {
					Xmax = piece.getPositionPiece()[0]+piece.getHeight(); 
				}
				if (Ymax>piece.getPositionPiece()[0]+piece.getHeight()) {
					Ymax = piece.getPositionPiece()[1]+piece.getHeight(); 
				}
			}
			X = Xmax - Xmin; 
			Y=  Ymax - Ymin; 
			s = X*Y; 
			S = (float) (this.hauteur * this.hauteur); 
			float score ; 
					score = 1 - s/S;
					System.out.println("lE Score est = " + score);
					return score; 
		}
	 


	 /**
	  * Sauvegarde la partie en cours dans un fichier
	  * @throws FileNotFoundException
	  * @throws IOException
	  */
	public void saveConfigGame() throws FileNotFoundException, IOException {
		if(this.listePieces.size() > 0) {
			try(FileOutputStream fos = new FileOutputStream(new File(BACKUP_FILE_NAME)); 
					ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(this.listePieces);
				
				System.out.println("La Partie est enregistree avec succes");
			}
		}
	}
	
	
	
	
	/**
	 * Restaure une sauvegarde du jeu existante
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void restoreConfigGame() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Piece> oldListePieces = new ArrayList<Piece>();
		
		try(FileInputStream fis = new FileInputStream(new File(BACKUP_FILE_NAME)); 
				ObjectInputStream ois = new ObjectInputStream(fis)){
			oldListePieces = (ArrayList<Piece>) ois.readObject();
		}
		
		if(oldListePieces.size() > 0) {
			this.listePieces = new ArrayList<Piece>();
			this.listePieces.addAll(oldListePieces);
			this.placerPiecesDansGrille();
		}
	}
	
	
	
	/**
	 * Vide le fichier de sauvegarde lorsqu'on initialise la partie
	 * @throws FileNotFoundException
	 */
	public void resetBackup() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File(BACKUP_FILE_NAME));
		writer.print("");
		writer.close();
	}
	
	/**
	 * Enregistre le score maximal dans un fichier.
	 * Si le score en cours est superieur au score enregistr� on �crase le score sauvegard�.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void setMaxScore() throws FileNotFoundException, IOException {
		float maxScore = 0f;
		try {
			maxScore = getMaxScore();
		}catch (Exception e) {
			maxScore = 0f;
		}
		
		if(this.score() > maxScore) { 
			try(FileOutputStream fos = new FileOutputStream(new File(MAX_SCORE_FILE_NAME));
					DataOutputStream dos = new DataOutputStream(fos)) {
				dos.writeFloat(this.score());
			}
		}
	}
	
	/**
	 * Retourne le score maximal de l'application
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public float getMaxScore() throws FileNotFoundException, IOException {
		float maxScore = 0f;
		try(FileInputStream fis = new FileInputStream(new File(MAX_SCORE_FILE_NAME));
				DataInputStream dis = new DataInputStream(fis)) {
			maxScore = dis.readFloat();
		}
		return maxScore;
	}
	
	/**
	 * Reinitialise la partie en vidant les pieces de la grille.
	 */
	public void resetGame() {
		this.listePieces = new ArrayList<Piece>();
		for(boolean[] grille: grillePrincipale) {
			Arrays.fill(grille, false);
		}
	}
	
	/***********************************************
	 * Les Setters et Getters 
	 ***********************************************/
	
	public boolean[][] getGrillePrincipale() {
		return grillePrincipale;
	}
	 public void update() {
		 this.placerPiecesDansGrille();
		 this.notifyObservers();
	 }


	public Piece getCurrentPiece() {
		return currentPiece;
	}


	public int getHauteur() {
		return hauteur;
	}


	public int getLargeur() {
		return largeur;
	}


	public ArrayList<Piece> getListePieces() {
		return listePieces;
	}
	
	public int getScore() {
		return score;
	}
}
