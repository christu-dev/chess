package chess;
import java.util.ArrayList;

class Pawn extends ReturnPiece {
	boolean firstMoved; //checking if it has moved first already (for en passant)
    boolean white;

	Pawn(boolean white)
	{
		if(white == true){
        this.pieceType = ReturnPiece.PieceType.WP;
        this.firstMoved = false;
        this.white = true;
        }
		
		else
        {
        this.pieceType = ReturnPiece.PieceType.BP;
		this.firstMoved = false;
        this.white = false;
        }
		
	}
	boolean hasFirstMoved(){ //for en passant
		return firstMoved;
	}
		
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));

		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));


        int rankDiff = endRank - startRank;
        int fileDiff = (int)startFile - (int)endFile;

		if (startFile == endFile) 
        { //if it stays in the same file (letter)

            if (this.pieceType == ReturnPiece.PieceType.WP) {
                if (rankDiff == 1) {
                    
					//checking if any pieces are in the way
                    for (ReturnPiece rp : currentBoard) {
                        String checkPos = "" + startFile + (startRank + 1); //temporary space checker
                        if (rp.toString().substring(0, 2).equals(checkPos)) {
                            //System.out.println("There is a piece in the way (Normal Move White Pawn)");
                            return false;
                        }
                    }
                    this.firstMoved = false;
                    return true; //no piece in the way, validate pawn movement
                } 

				else if (rankDiff == 2 && startRank == 2) { //checks for first move (if someone wants to double move pawn)
                  

					//checking if any pieces are in the way
                    for (ReturnPiece rp : currentBoard) {
                        String checkPos1 = "" + startFile + (startRank + 1); 
                        String checkPos2 = "" + startFile + (startRank + 2);
                        if (rp.toString().substring(0, 2).equals(checkPos1) || rp.toString().substring(0, 2).equals(checkPos2)) {
                          //  System.out.println("There is a piece in the way (Double Move White Pawn)");
                            return false;
                        }
                    }
					this.firstMoved = true;
                    return true;
                }
            }
          
            else if (this.pieceType == ReturnPiece.PieceType.BP) {
                if (rankDiff == -1) 
				{

                   //checking for pieces in the wave
                    for (ReturnPiece rp : currentBoard) {
                        String checkPos = "" + startFile + (startRank - 1);
                        if (rp.toString().substring(0, 2).equals(checkPos)) {
                         //   System.out.println("There is a piece in the way (Normal Move Black Pawn)");
                            return false;
                        }
                    }
                    this.firstMoved = false;
                    return true;
                } else if (rankDiff == -2 && startRank == 7) {
                    
                    for (ReturnPiece rp : currentBoard) {
                        String checkPos1 = "" + startFile + (startRank - 1);
                        String checkPos2 = "" + startFile + (startRank - 2);
                        if (rp.toString().substring(0, 2).equals(checkPos1) || rp.toString().substring(0, 2).equals(checkPos2)) {
                           // System.out.println("There is a piece in the way (Double Move Black Pawn)");
                            return false;
                        }
                    }
					this.firstMoved = true;
                    return true;
                }
            }
        }
        else if(Capture.canPawnCapture(currentBoard, move, this.white)){
            
                 return true;       
        }
        System.out.println("Regular move validate failed. canPawnCapture failed.");
        return false; //all other moves are wrong
    }
}


		