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
		return this.firstMoved;
	}
    void setFirstMoved(){
        this.firstMoved = true;
    }
    void notFirstMoved(){
        this.firstMoved = false;
    }
    void possibleMoves()
    {
        //ArrayList<String> possibleMoves = new ArrayList<>();

       /*  //forward move
        String move1 = this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank + 1);
        String move2 = this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank + 2);
        String move3 = this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank - 1);
        String move4 = this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank - 2);

        //captures
        String move5 = this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)+1)+""+(this.pieceRank +1);
        String move6 = this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)-1)+""+(this.pieceRank +1);
        String move7 = this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)+1)+""+(this.pieceRank -1);
        String move8 = this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)-1)+""+(this.pieceRank -1);*/

        if(this.white)
        {
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank + 1));
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank + 2));
        }

        if(!this.white){
            if(!(this.pieceRank - 1 < 1))
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank - 1));

            if(!(this.pieceRank - 2 < 1))
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " +this.toString().charAt(0)+""+(this.pieceRank - 2));
        }

        if(this.white)
        {
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)+1)+""+(this.pieceRank +1));
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)-1)+""+(this.pieceRank +1));
        }
        

        if(!this.white)
        {
            if(!(this.pieceRank - 1 < 1))
        {
           Chess.possibleMoves.add(this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)+1)+""+(this.pieceRank -1));
           Chess.possibleMoves.add(this.toString().substring(0,2) + " " +(char)(this.toString().charAt(0)-1)+""+(this.pieceRank -1));
        }

        }
        
        //return possibleMoves;
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
                    //this.firstMoved = false;
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
					//this.firstMoved = true;
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
                    //this.firstMoved = false;
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
					//this.firstMoved = true;
                    return true;
                }
            }
        }
        else if(Capture.canPawnCapture(currentBoard, move, this.white)){
            
                System.out.println("got here");
                 return true;       
        }
        System.out.println("Regular move validate failed. canPawnCapture failed.");
        return false; //all other moves are wrong
    }// end of checkValidMove


    ReturnPiece pawnPromotion(String move) 
    {
        char startFile = move.charAt(0);
        int startRank = Integer.parseInt(String.valueOf(move.charAt(1)));
        char endFile = move.charAt(3);
        int endRank = Integer.parseInt(String.valueOf(move.charAt(4)));
    
    
        char promotionPiece;
        if ((this.white && endRank == 8) || (!this.white && endRank == 1)) 
        {
            if(move.length() >5)
            {
                promotionPiece = move.charAt(6);
                // If a promotion piece is specified, extract it from the move
            }
            else
            {
                promotionPiece = 'Q'; // Default to promoting to a queen if not specified
            }
            

            if (promotionPiece == 'Q' || promotionPiece == 'N' || promotionPiece == 'R' ||  promotionPiece == 'B') //queen, knight, rook, bishop
            {
                ReturnPiece newPiece = null;

                if (promotionPiece == 'Q') 
                {

                    newPiece = new Queen(this.white); //sets the piecetype
                    newPiece.pieceFile = this.pieceFile;
                    newPiece.pieceRank = this.pieceRank;

                } 
                else if (promotionPiece == 'R') 
                {

                    newPiece = new Rook(this.white); //sets the piecetype
                    newPiece.pieceFile = this.pieceFile;
                    newPiece.pieceRank = this.pieceRank;

                } 
                else if (promotionPiece == 'N') 
                {

                    newPiece = new Knight(this.white); //sets the piecetype;
                    newPiece.pieceFile = this.pieceFile;
                    newPiece.pieceRank = this.pieceRank;

                }
                else if(promotionPiece == 'B')
                {
                    newPiece = new Bishop(this.white); //sets the piecetype;
                    newPiece.pieceFile = this.pieceFile;
                    newPiece.pieceRank = this.pieceRank;
                }
    
                return newPiece;
            }
        }
        //if promotion is illegal, then just return null
        return null; //in chess.java have a statement checking if PawnPromotion is null.
    }
}


		