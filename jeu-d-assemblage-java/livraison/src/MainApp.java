import modele.game.Plateau;
import vue_controleur.Interface3D;

public class MainApp {

	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		
		
		// Lancement de l'interface
		new Interface3D(plateau);
		
	}

}
