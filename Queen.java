package chess;
import java.util.ArrayList;

class Queen extends ReturnPiece {

	Queen(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WQ;
		else
		this.pieceType = ReturnPiece.PieceType.BQ;
	}
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		return true;
	}

}
