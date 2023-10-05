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

		/* works: currentBoard param is passing from piecesOnBoard of ReturnPlay play
		System.out.println(startFile);
		System.out.println(startRank);
		System.out.println(endFile);
		System.out.println(endRank);*/

		if(startFile.equals(endFile) || startRank == endRank){
			System.out.println("Did not move Diagonally");
			return false;
		}



		return true;

		
	}

}