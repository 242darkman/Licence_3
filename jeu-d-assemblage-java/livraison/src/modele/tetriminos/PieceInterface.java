package modele.tetriminos;

import java.awt.Color;

import modele.game.Position;




/**
 * Définition des méthodes implémentables pour les pièces
 *
 */
public interface PieceInterface {

	public int getWidth();
    
    public int getHeight();
    
   /** public boolean getElement(int x, int y); **/
    
    public boolean getElement(Position position);
    
    public Position getCenterPosition();
    
    public void setWidth(int width);
    
    public void setHeight(int height);
        
    public void applyRotation(Direction dir);
    
    public void applyTranslation(Direction direction);
}
