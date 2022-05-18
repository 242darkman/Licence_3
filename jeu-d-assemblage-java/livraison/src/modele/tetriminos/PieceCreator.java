package modele.tetriminos;



public class PieceCreator extends AbstractPieceCreator {

	
	/**
	 * PieceCreator constructor
	 */
	public PieceCreator() {
	}
	
	/**
	 * Méthode donnant une pièce correspondant à la forme passée en @param
	 * @param forme : pièce que l'on veut créer
	 * @return : piece du type de la forme passée
	 */
	
	@Override
	public Piece PieceBuilder(TypeDeTetriminos forme) {
		Piece p = null;
		
		switch (forme) {
		case PieceL:
			p = new PieceL();
			break;
		case PieceZ:
			p = new PieceZ();
			break;
		case PieceT:
			p = new PieceT();
			break;
		case PieceI:
			p = new PieceI();
			break;
		case PieceO:
			p = new PieceO();
			break;
		case PieceS:
			p = new PieceS();
			break;
		case PieceJ:
			p = new PieceJ();
			break;

		}
		
		return p;
	}
	
	
	
	

}
