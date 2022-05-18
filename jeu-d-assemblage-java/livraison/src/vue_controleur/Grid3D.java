package vue_controleur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import modele.game.Plateau;
import modele.tetriminos.Direction;
import modele.tetriminos.Piece;
import modele.util.Observer;




@SuppressWarnings("unused")
public class Grid3D extends JPanel implements Observer, MouseListener, KeyListener {

	
	private static final long serialVersionUID = -6913777401726757378L;
	private static final int SIZEOFCELL =55;
	private Plateau modele;
	
	Interface3D interface3D; 
	
	public  int posX ;
	public  int posY;
	
	
	public int dim = 26;
	public int arrondi = 5;
	
	/**
	 * Les Differents Buttons pour le Deplacement et la Rotation
	 */
	

	JButton terminer   = new JButton("Terminer");
	JButton save 	   = new JButton("Sauvegarde");
	JButton nouvel 	   = new JButton("Nouvelle Partie");
	JButton quitter    = new JButton("Quitter");
	JButton haut     		= new JButton("Haut");
	JButton bas     		= new JButton("Bas");
	JButton droite      	= new JButton("Droite");
	JButton gauche      	= new JButton("Gauche");
	JButton rot_droite      = new JButton("Rotation_Droite");
	JButton rot_gauche      = new JButton("Rotation_Gauche");
	
	
	
	
	
	

	/**
	 * Grid3D constructor
	 * @param grid : plateau de jeu
	 */
	public Grid3D(Plateau model) {
		
		 
		this.modele = model;
		this.setSize((int) Math.pow((int)this.modele.getGrillePrincipale().length, 2), (int)Math.pow(this.modele.getGrillePrincipale().length, 2));
		this.modele.addObserver(this);
		this.addKeyListener(this);
		
		
		
		
		
		haut.setPreferredSize(new Dimension(150,25));
		bas.setPreferredSize(new Dimension(150,25));
		droite.setPreferredSize(new Dimension(150,25));
		gauche.setPreferredSize(new Dimension(150,25));
		rot_droite.setPreferredSize(new Dimension(150,25));
		rot_gauche.setPreferredSize(new Dimension(150,25));
		
			this.addMouseListener(new MouseAdapter() { 
				  public void mouseClicked(MouseEvent event) { 
				
						  posY = event.getY()/dim;
						  posY -= 1; 
						  posX = event.getX()/dim;
						  posX -=1;
						  modele.searchCurrentPiece(posY, posX);
					
					  System.out.println(modele.getListePieces().size());
					  if(modele.getCurrentPiece() != null) {
						  System.out.println("Selection d'une pièce !!!");
					  }
				  }
			});	
			
			/**
			 * Le deplacement vers le bas
			 */
			haut.addMouseListener(new MouseAdapter() { 
					public void mouseClicked(MouseEvent event) {
						 if(modele.getCurrentPiece() != null) {
							 System.out.println("Application de l'action");
							modele.playOfState(modele.getCurrentPiece(), "haut");
							modele.update();
						}else {
							System.out.println("Aucune action");
								  }
								  
							  } 
						});
						
			/**
			 * Le deplacement vers le bas
			 */
			bas.addMouseListener(new MouseAdapter() { 
				  public void mouseClicked(MouseEvent event) {
					  if(modele.getCurrentPiece() != null) {
						 System.out.println("Application de l'action");
						 modele.playOfState(modele.getCurrentPiece(), "bas");
						 modele.update();
					  }else {
						  System.out.println("Aucune action");
					  }
					  
				  } 
			});
			
			
					/**
					 * Le deplacement vers la Droite
					 */
					droite.addMouseListener(new MouseAdapter() { 
						  public void mouseClicked(MouseEvent event) {
							  if(modele.getCurrentPiece() != null) {
								 System.out.println("Application de l'action");
								 modele.playOfState(modele.getCurrentPiece(), "droite");
								 modele.update();
							  }else {
								  System.out.println("Aucune action");
							  }
							  
						  } 
					});
			
					
					/**
					 * Le deplacement vers la gauche
					 */
					gauche.addMouseListener(new MouseAdapter() { 
						  public void mouseClicked(MouseEvent event) {
							  if(modele.getCurrentPiece() != null) {
								 System.out.println("Application de l'action");
								 modele.playOfState(modele.getCurrentPiece(), "gauche");
								 modele.update();
							  }else {
								  System.out.println("Aucune action");
							  }
						  } 
					});
					
					
					/**
					 * Rotation vers la droite
					 */
					rot_droite.addMouseListener(new MouseAdapter() { 
						  public void mouseClicked(MouseEvent event) { 
							 
							  if(modele.getCurrentPiece() != null) {
								 System.out.println("Application de l'action");
								 modele.playOfState(modele.getCurrentPiece(), "rot_droite");
								 modele.update();
							  }else {
								  System.out.println("Aucune action");
							  }
						  } 
					});
					
					
					/**
					 * Rotation vers la gauche
					 */
					rot_gauche.addMouseListener(new MouseAdapter() { 
						  public void mouseClicked(MouseEvent event) { 
							  if(modele.getCurrentPiece() != null) {
								 System.out.println("Application de l'action");
								 modele.playOfState(modele.getCurrentPiece(), "rot_gauche");
								 modele.update();
							  }else {
								  System.out.println("Aucune action");
							  }
						  }
							  
					});
						
	}

	

	public JButton getHaut() {
		return haut;
	}



	public void setHaut(JButton haut) {
		this.haut = haut;
	}



	public JButton getBas() {
		return bas;
	}



	public void setBas(JButton bas) {
		this.bas = bas;
	}


	/**
	 * Dessin de l'interface graphique 
	 */

	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2D= (Graphics2D) g;
			//Dessiner le cadre 
		//g2D.setPaint(Color.BLACK);
		g2D.setStroke(new BasicStroke(3));
		g2D.draw(new Rectangle2D.Double(dim,dim,dim*modele.getLargeur(),dim*modele.getHauteur()));
		
			//Dessiner le fond 
		for (int i = 0; i < modele.getLargeur(); i++) {
			for (int j = 0; j < modele.getHauteur(); j++) {
				if(this.modele.getGrillePrincipale()[i][j] == false) {
				g2D.fillRoundRect((j+1)*dim, (i+1)*dim, dim, dim, arrondi, arrondi);
				g2D.setColor(Color.GRAY);
					
				}
					g2D.fillRoundRect((j+1)*dim, (i+1)*dim, dim, dim, arrondi, arrondi);
					g2D.setColor(Color.RED);
			}
		}
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	/**************************************************
	 * Les Setters et Getters des JButtons
	 ****************************************************/
	
	
	
	
	@Override
	public void update() {
		this.repaint();
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_KP_LEFT) {
			System.out.println("Bonjour");
		} 
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
