package chess;
import java.util.ArrayList;

class Pawn extends ReturnPiece {
	Pawn(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WP;
		else
		this.pieceType = ReturnPiece.PieceType.BP;
	}
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		return true;
	}

}