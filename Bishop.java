package chess;
import java.util.ArrayList;

class Bishop extends ReturnPiece {
	boolean white;

	Bishop(boolean white)
	{
		if(white == true){
			this.pieceType = ReturnPiece.PieceType.WB;
			this.white = true;
		}
		else{
			this.pieceType = ReturnPiece.PieceType.BB;
			this.white = false;
		}
		
	}
	void possibleMoves()
    {
        //ArrayList<String> possibleMoves = new ArrayList<>();
		//String temp;
        for(int i = 1; i < 8; i++)
		{
			
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i));
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i));

			if(((int)(Integer.parseInt(this.toString().substring(1,2)))-i)<1)
				continue;

			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i));
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i));
		}

        //return possibleMoves;
    }

	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{
		//move is of form RankFile RankFile


		char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));

		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

		if(startFile < 'a' || startFile > 'h' || startRank < 1 || startRank > 8){
			
			return false;
		}
		if(endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8){
			
			return false;
		}

		

		if(startFile == endFile || startRank == endRank){
			
			return false;
		}
	 	if(Math.abs(endRank - startRank) != Math.abs(startFile - endFile))
		{
			
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
				
					return false;
				}
			}
			x += FileOffset;
		}
		//no issues
		return true;

		
	}

}