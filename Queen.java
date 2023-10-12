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

	//checks if piece is in the way
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

	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));
		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));



		int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        int fileOffset = (startFile < endFile) ? 1 : -1; //deciding in positive or negative direction
        int rankOffset = (startRank < endRank) ? 1 : -1;

		if (startFile == endFile || startRank == endRank) { //if stayed in same file or rank (moved vertically or horizontally)

            

            if (fileDiff == 0) //stayed in same file, moved vertically
			{
          
                for (int rank = startRank + rankOffset; rank != endRank; rank += rankOffset) 
				{
                    if (isOccupied(currentBoard, startFile, rank)) {
						//System.out.println("something is blocking the queen");
                        return false; //piece is in way while moving vertically
                    }
                }
		
            } 
			else if (rankDiff == 0) //stayed in same rank, moved horizontally
			{
       
                for (char file = (char)(startFile + fileOffset); file != endFile; file += fileOffset) 
				{
                    if (isOccupied(currentBoard, file, startRank)) 
					{
						//System.out.println("something is blocking the queen");
                        return false; //piece is in way while moving horizontally
                    }
                }

            } 
        }
		else if (fileDiff == rankDiff) //moved diagonally (neither vert nor horizontal)
		{
            char file = (char)(startFile + fileOffset); //file starts incremented/decremented once
            for (int rank = startRank + rankOffset; rank != endRank; rank += rankOffset) //rank starts incremented/decremented once
			{
                if (isOccupied(currentBoard, file, rank)) {
					//System.out.println("something is blocking the queen");
                    return false;
                }
                file = (char)(file + fileOffset); 
				//increment file
				//(next loop): increment rank
            }
        }
		else //wrong move 
		{
        	return false;
        }

        return true; //move is valid
    }

    
}




	