package chess;
import java.util.ArrayList;

class Rook extends ReturnPiece {
	boolean white;
	boolean hasMoved = false;

	Rook(boolean white)
	{
		if(white == true)
		{
			this.pieceType = ReturnPiece.PieceType.WR;
			this.white = true;
		}
		else
		{
			this.pieceType = ReturnPiece.PieceType.BR;
			this.white = false;
		}
		
	}

	private boolean isOccupied(ArrayList<ReturnPiece> currentBoard, char file, int rank) 
	{ 
        for (ReturnPiece piece : currentBoard) {
            if (piece.toString().substring(0,2).equals(""+file+rank)) 
			{
                return true;
            }
        }
        return false;
    }
	void possibleMoves()
    {
        //ArrayList<String> possibleMoves = new ArrayList<>();
		//String temp;
        for(int i = 1; i < 8; i++)
		{
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" + (int)(Integer.parseInt(this.toString().substring(1,2)));
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + i) + "" + (int)(Integer.parseInt(this.toString().substring(1,2))));
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" + (int)(Integer.parseInt(this.toString().substring(1,2)));
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - i) + "" + (int)(Integer.parseInt(this.toString().substring(1,2))));
		}
		for(int i = 1; i < 8; i++)
		{
				

				//adding all in bounds vertical rook positions
				//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0)) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i);
				Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0)) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+i));

				if((int)((Integer.parseInt(this.toString().substring(1,2)))-i)<1)
				continue;
				
				//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0)) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i);
				Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0)) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-i));		
		}

        //return possibleMoves; //56 possible locations, gets weeded out by checkValidMove
    }


	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));
		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

		if(startFile < 'a' || startFile > 'h' || startRank < 1 || startRank > 8){
			//System.out.println("out of bounds");
			return false;
		}
		if(endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8){
			//System.out.println("out of bounds");
			return false;
		}



		int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        int fileOffset = (startFile < endFile) ? 1 : -1; //deciding in positive or negative direction
        int rankOffset = (startRank < endRank) ? 1 : -1;

		if (startFile == endFile || startRank == endRank) { //if stayed in same file or rank (moved vertically or horizontally)

            if (fileDiff == 0) //stayed in same file, moved vertically
			{
          
                for (int rank = startRank + rankOffset; rank != endRank; rank += rankOffset) 
				{
                    if (isOccupied(currentBoard, startFile, rank)) 
					{
						System.out.println("Something is in way of Rook while moving vertically");
                        return false; //piece is in way while moving vertically
                    }
                }
				return true;
            } 
			else if (rankDiff == 0) //stayed in same rank, moved horizontally
			{
       
                for (char file = (char)(startFile + fileOffset); file != endFile; file += fileOffset) 
				{
                    if (isOccupied(currentBoard, file, startRank)) 
					{
						System.out.println("Something is in way of Rook while moving horizontally");
                        return false; //piece is in way while moving horizontally
                    }
                }
				return true;
            } 
        }
		
		return false;

		
	}

}