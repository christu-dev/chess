package chess;
import java.util.ArrayList;

class Bishop extends ReturnPiece {

	Bishop(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WB;
		else
		this.pieceType = ReturnPiece.PieceType.BB;
	}
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{
		//move is of form RankFile RankFile


		String startFile = move.substring(0,1);
		int startRank = Integer.parseInt(move.substring(1,2));

		String endFile = move.substring(3,4);
		int endRank = Integer.parseInt(move.substring(4,5));


		return false;

		
	}

}