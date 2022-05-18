package vue_controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import modele.game.Plateau;
import modele.tetriminos.Direction;
import modele.tetriminos.Piece;

import modele.util.Observer;




public class Interface3D extends JFrame implements ActionListener, Observer{
	
	
	private static final long serialVersionUID = 2977437252828979652L;
	private Plateau modele;
	
	Grid3D grid3D;
	Dimension SIZEOFFCELL;
	HomePanel home;
	JPanel pan;
	Container container = this.getContentPane();

	/**
	 * creation des boutons qui seront prÃ©sents sur l'interface de jeu
	 */
	JButton terminer   = new JButton("Terminer");
	JButton save 	   = new JButton("Sauvegarde");
	JButton nouvel 	   = new JButton("Nouvelle Partie");
	JButton quitter    = new JButton("Quitter");
	JTextField text1 = new JTextField("", 25); 
	JTextField text2 = new JTextField("", 25); 
	
	
	
	
	
	
	
	
	
	Piece piece;  
	Plateau state = new Plateau();
	
	
	/**
	 * Constructor
	 * @param state
	 */
	public Interface3D(Plateau state) {
	
		this.SIZEOFFCELL    = new Dimension(250, 250);
		this.home   = new HomePanel();
		this.pan    = new JPanel();
		this.grid3D = new Grid3D(state);
		this.modele = state;
		this.modele.addObserver(this);
		
		
		//Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		Dimension d = new Dimension((int)Math.pow(state.getLargeur(), 2), (int) Math.pow(state.getHauteur(), 2));
		this.grid3D.setSize(d);
		
		
		
		//this.pan.setSize(this.dim);
		this.pack();
		this.setVisible(true);
		this.setSize(1200, 630);
		this.setTitle("JEU D'ASSEMBLAGE");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		
		// definition de la taille de notre page d'accueil et ecoute des actions liees aux boutons de l'accueil
		this.home.setSize(this.SIZEOFFCELL);
		this.home.getbJouerInterface().addActionListener(this);
		this.home.getbRegles().addActionListener(this);
		this.home.getbAbout().addActionListener(this);
		this.home.getbQuitter().addActionListener(this);
		this.home.getbJouerConsole().addActionListener(this);

		// ecoute des actions liees aux boutons de l'interface de jeu
		this.terminer.addActionListener(this);
		this.save.addActionListener(this);
		this.nouvel.addActionListener(this);
		this.quitter.addActionListener(this);
		
	

		

		// ajout des boutons sur le panel
		this.pan.setPreferredSize(this.SIZEOFFCELL);
		this.pan.add(terminer, BorderLayout.EAST);
		this.pan.add(save, BorderLayout.EAST); 
		this.pan.add(nouvel, BorderLayout.EAST); 
		this.pan.add(grid3D.haut); 
		this.pan.add(grid3D.bas); 
		this.pan.add(grid3D.droite);
		this.grid3D.add(grid3D.gauche);
		this.grid3D.add(grid3D.rot_droite);
		this.grid3D.add(grid3D.rot_gauche);
		
		
		
		this.pan.add(grid3D.haut); 
		this.pan.add(grid3D.bas); 
		this.pan.add(grid3D.droite); 
		this.pan.add(grid3D.gauche); 
		this.pan.add(grid3D.rot_droite); 
		this.pan.add(grid3D.rot_gauche); 
		this.pan.add(quitter); 
		this.pan.add(text1); 
		this.pan.add(text2);
		
		
		
		container.add(home);
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		/**
		 * Quand on doit jouer sur l'interface
		 */
		if (event.getSource() == this.home.getbJouerInterface()) {
			this.home.setVisible(false);
			this.pan.setVisible(true);
			this.grid3D.setVisible(true);
			
			container.add(this.grid3D);
			container.add(this.pan, BorderLayout.EAST);
			this.getContentPane().add(this.grid3D);

		}
		
		
		/**
		 * quand on doit jouer sur la console
		 */
		if (event.getSource() == this.home.getbJouerConsole()) {
			this.dispose();
			Plateau state = new Plateau();

			System.out.println("Grille  apres ajout de la  Piece \n" + state.toString()); // affichage de la grille aprÃ¨s
			
				
				for(int i=0; i<state.getListePieces().size(); i++) {
					System.out.println("La Piece Placée est  \n" + state.getListePieces().get(i));	
				}
			System.out.println("La Taille de la Liste des Pieces est : " +state.getListePieces().size());

		}
		
		
		
		
		/**
		 * quand l'Ã©vÃ¨nement captÃ© correspond au bouton RÃ¨gles de jeu
		 */
		if (event.getSource() == this.home.getbRegles()) {

			JOptionPane.showMessageDialog(null,"RÈGLES DU JEU : \n"
                    + "     ☛ Emboiter les pièces afin qu'elles occupent le moins d'espace possible sur la surface. \n\n"
                    + "Pour ce faire, vous devez bien evidemment déplacer les pièces. \n\n"
                    + "       ✔ Le déplacement d'une pièce vers la gauche ou vers la droite se fait avec les touches directionnelles"
                    + " ▶ ou ◀. \n"
                    + "       ✔ Le déplacement d'une pièce vers le haut ou vers le bas se fait avec les touches directionnelles"
                    + " ▲ ou ▼.\n\n"
                    + "\n\n Il est tout aussi possible de faire pivoter une pièce dans les deux sens : \n"
                    + "       ✔ Ⓓ pour effectuer une rotation dans le sens des aiguilles d'une montre \n"
                    + "       ✔ Ⓖ pour effectuer une rotation dans le sens inverse des aiguilles d'une montre \n\n"
                    + "     ☛ Une fois les déplacements et rotations effectués, il est possible de voir le taux d'occupation de la grille en cliquant sur 'Résultat'. \n\n"
                    + "                                 𝓑𝓞𝓝𝓝𝓔 𝓟𝓐𝓡𝓣𝓘𝓔                                    ");
		}
		
		
		/**
		 * quand l'Ã©vÃ¨nement captÃ© correspond au bouton a propos
		 */
		if (event.getSource() == this.home.getbAbout()) {
			
			 JOptionPane.showMessageDialog(null,"À PROPOS : \n"
	                    + "     ☛ Ce JEU D'ASSEMBLAGE a été conçu pour un projet de l'UE (INF5A1) Méthodes de conception par "
	                    + "4 étudiants de 3ème année en Licence informatique, à l'Université de Caen\n\n"
	                    + "Les concepteurs de ce projets sont : \n"
	                    + "       ✔ 21812350 VOUVOU Brandon \n"
	                    + "       ✔ 21911445 KEITA  Lansana \n"
	                    + "       ✔ 21910271 TOUBAKILA Naslie \n"
	                    + "       ✔ 21814023 OGOUWELE Derrick\n"
	                    + "Copyright © Novembre 2020");
		}
		
		/**
		 * quand l'Ã©vÃ¨nement captÃ© correspond au bouton quitter
		 */
		if (event.getSource() == this.home.getbQuitter()) {
			this.dispose();
		}
		
		/**
		 * Sauvegarder la partie
		 */
		if (event.getSource() == this.save) {
			
			JOptionPane.showMessageDialog(null,"LA PARTIE EST SAUVEGARDEE AVEC SUCCESS !!! \n"
                   );
			
			try {
				this.modele.saveConfigGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Click button Nouvelle partie
		 */
		if (event.getSource() == this.nouvel) {
			try {
				this.modele.resetBackup();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			this.modele.initGame();
			refreshPanel();
		}
		
		/**
		 * Terminer la partie et enregistrer le score maximal
		 */
		if (event.getSource() == this.terminer) {
			
			try {
				this.modele.setMaxScore();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"LA PARTIE EST FINIE !!! \n"
					+ "VERIFIER VOTRE SCORE "
	                   );
			
			float maxScore = 0f;
			try {
				maxScore = this.modele.getMaxScore();
			}catch (Exception e) {
				maxScore = 0f;
				// e.printStackTrace();
			}
			
			text1.setText("VOTRE SCORE EST : "  + this.modele.score()); 
			text2.setText("LE MEILLEUR SCORE EST : "  + maxScore);
			text1.setEditable(false);
			text2.setEditable(false);
			
			this.modele.resetGame();
			refreshPanel();
			
		}
		
		// Pour le Button quitter
		if (event.getSource() == this.quitter) {
			this.dispose();
		}
		
	
		
	}
	
	/**
	 * Rafrichir notre panel quand on lance une nouvelle partie
	 */
	private void refreshPanel() {
		this.grid3D = new Grid3D(this.modele);
		this.container.revalidate();
		this.container.repaint();
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
