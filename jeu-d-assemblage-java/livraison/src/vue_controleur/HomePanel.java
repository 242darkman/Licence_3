package vue_controleur;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8042154872159192005L;
	private final JButton bJouerInterface;
	private final JButton bJouerConsole;
	private final JButton bRegles;
	private final JButton bQuitter;
	private final JButton bAbout;
	private String strJouerInterface   = "JOUER SUR L'INTERFACE GRAPHIQUE";
	private String strJouerConsole   = "AFFICHER LA CONSOLE AVEC LES PIECES ";
	private String strRegles  = "REGLES DU JEU";
	private String strQuitter = "QUITTER";
	private String strAbout   = "À PROPOS";
	
	
	
	/**
	 * HomePanel constructor
	 */
	public HomePanel() {
			// Création des boutons du menu
		this.bJouerInterface   = new JButton(strJouerInterface);
		this.bJouerConsole   = new JButton(strJouerConsole);
		this.bRegles  = new JButton(strRegles);
		this.bAbout   = new JButton(strAbout);
		this.bQuitter = new JButton(strQuitter);
		
			// définition des couleurs pour chaque boutons
		this.bJouerInterface.setBackground(Color.CYAN);
		this.bJouerConsole.setBackground(Color.CYAN);
		this.bRegles.setBackground(Color.CYAN);
		this.bAbout.setBackground(Color.CYAN);
		this.bQuitter.setBackground(Color.RED);
		
			// Affecte au conteneur un gestionnaire de placement
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.LIGHT_GRAY);
		
			// définition de la position et de la taille des éléments
		GridBagConstraints bagConstraints = new GridBagConstraints();
		
			// le parametre fill sert à définir comment le composant sera rempli GridBagConstraints.BOTH permet d'occuper tout l'espace disponible
		bagConstraints.fill = GridBagConstraints.BOTH;
		
			// définition de la marge entre les composants
		bagConstraints.insets = new Insets(35, 50, 35, 50);
		
			// emplacement du composant au cas où il n'occupe pas la totalité de l'espace disponible sur l'écran
		bagConstraints.ipady = bagConstraints.anchor = GridBagConstraints.CENTER;
		
			// nombre de case en abcisse
		bagConstraints.weightx = 5;
		
			// nombre de case en ordonnée
		bagConstraints.weighty = 1;
		
			// ajout d'un composant en position(i,j)
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 0;
		
			// ajout du composant au panel
		this.add(this.bJouerInterface, bagConstraints);
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 1;
		
		this.add(this.bJouerConsole, bagConstraints);
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 2;
		
		this.add(this.bRegles, bagConstraints);
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 3;
		
		this.add(bAbout, bagConstraints);
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 4;
		
		this.add(this.bQuitter, bagConstraints);
	}



	public JButton getbJouerInterface() {
		return bJouerInterface;
	}



	public JButton getbJouerConsole() {
		return bJouerConsole;
	}



	public JButton getbJouer() {
		return bJouerInterface;
	}



	public JButton getbRegles() {
		return bRegles;
	}



	public JButton getbQuitter() {
		return bQuitter;
	}



	public JButton getbAbout() {
		return bAbout;
	}
	
	
	
}
