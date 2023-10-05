package chess;
import java.util.ArrayList;

class Knight extends ReturnPiece {
	Knight(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WN;
		else
		this.pieceType = ReturnPiece.PieceType.BN;
	}
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		return true;
	}

}
