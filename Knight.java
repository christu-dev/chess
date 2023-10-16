package chess;
import java.util.ArrayList;

class Knight extends ReturnPiece {
    boolean white;
	Knight(boolean white)
	{
		if(white == true)
        {
            this.pieceType = ReturnPiece.PieceType.WN;
            this.white = true;
        }
		else
        {
            this.pieceType = ReturnPiece.PieceType.BN;
            this.white=false;
        }
		
	}
    void possibleMoves()
    {
        //ArrayList<String> possibleMoves = new ArrayList<String>();

		//String temp;
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+2);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+2));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+1);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+1));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-1);
        if(!(((Integer.parseInt(this.toString().substring(1,2)))-1)<1)){
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-1));
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-1));

        }
        
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-2);
        if(!(((Integer.parseInt(this.toString().substring(1,2)))-2)<1))
        {
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-2));
            //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-2);
            Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-2));
            //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))-1);
        }
        
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+1);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 2) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+1));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+2);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)((Integer.parseInt(this.toString().substring(1,2)))+2));


        //return possibleMoves();
    }
	
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move) 
	{
        // Extract the start and end positions from the move string
        char startFile = move.substring(0, 1).toLowerCase().charAt(0);
        int startRank = Integer.parseInt(move.substring(1, 2));
        char endFile = move.substring(3, 4).toLowerCase().charAt(0);
        int endRank = Integer.parseInt(move.substring(4, 5));
        if(startFile < 'a' || startFile > 'h' || startRank < 1 || startRank > 8){
			//System.out.println("out of bounds");
			return false;
		}
		if(endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8){
			//System.out.println("out of bounds");
			return false;
		}


        // Calculate the  difference in rank and file
        int rankOffset = Math.abs(endRank - startRank);
        int fileOffset = Math.abs((int)endFile - (int)startFile);

        // Check if the move is a valid knight's move (L-shaped)
        if ((rankOffset == 1 && fileOffset == 2) || (rankOffset == 2 && fileOffset == 1)) {

            return true;
        } else {
            // Knight move is not valid
            return false;
        }
    }
}

