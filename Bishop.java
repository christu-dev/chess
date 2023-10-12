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


		char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));

		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

		/* works: currentBoard param is passing from piecesOnBoard of ReturnPlay play
		System.out.println(startFile);
		System.out.println(startRank);
		System.out.println(endFile);
		System.out.println(endRank);*/

		if(startFile == endFile || startRank == endRank){
			//System.out.println("Bishop Did not move Diagonally");
			return false;
		}
	 	if(Math.abs(endRank - startRank) != Math.abs(startFile - endFile))
		{
			//System.out.println("Bishop Did not move diagonally respective to starting position");
			return false;
		}


		//checking for which direction you are going
		int FileOffset, RankOffset; //x = Column, y = row;

		if(startRank < endRank){ 
			RankOffset = 1;
		}
		else{
			RankOffset = -1;
		}

		if(startFile < endFile){
			FileOffset = 1;
		}
		else{
			FileOffset = -1;
		}

		//checking for any pieces in the way
		int x = (int)startFile + FileOffset;
		for(int y = startRank + RankOffset; y != endRank; y += RankOffset){
			for (ReturnPiece rp: currentBoard)
			{
				String checkPos = ""+(char)x+y+"";
				if(rp.toString().substring(0,2).equals(checkPos)){
					//System.out.println("PIECE IN WAY");
					return false;
				}
			}
			x += FileOffset;
		}
		//no issues
		return true;

		
	}

}