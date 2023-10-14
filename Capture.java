package chess;
import java.util.ArrayList;

public class Capture {
    public static boolean canCapture(ArrayList<ReturnPiece> currentBoard, String move, boolean white){

        char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

        if(white == true){ //piece is white
            for (ReturnPiece piece : currentBoard) {
                if (piece.toString().substring(0,2).equals(""+endFile+endRank) && piece.toString().substring(3,4).equals("W")) 
                {
                    System.out.println("white piece trying to take white piece");
                    return false; //it's in the target space and is same color (white), can't capture
                }
            }
        }
        else if(white == false) //piece is black
        {
            for (ReturnPiece piece : currentBoard) {
                if (piece.toString().substring(0,2).equals(""+endFile+endRank) && piece.toString().substring(3,4).equals("B")) 
                {
                    System.out.println("black piece trying to take black piece");
                    return false; //it's in the target space and is the same color (black), can't capture
                }
            }
        }

        //------->passes if they differ in color (or empty) - okay


        //check if the piece they are attacking is a king
        //not sure if this ever hits, but just to be sure
        for (ReturnPiece piece : currentBoard){ 
            if (piece.toString().substring(0,2).equals(""+endFile+endRank) && (piece.pieceType == ReturnPiece.PieceType.WK || piece.pieceType == ReturnPiece.PieceType.BK)) 
            {
                return false;
            }
        }

        //there is a piece to be captured.
        for (ReturnPiece piece : currentBoard){ 
            if (piece.toString().substring(0,2).equals(""+endFile+endRank)) 
            {
                System.out.println("canCapture: there is a valid piece to capture, return true.");
                return true;
            }
        }

        System.out.println("canCapture: There is no piece to capture but returns true as in it can end up in that space.");
        return true; //there is no piece to capture (empty space)

    } //canCapture

    public static boolean canPawnCapture(ArrayList<ReturnPiece> currentBoard, String move, boolean white)
    {
        char startFile = move.substring(0,1).toLowerCase().charAt(0);
		int startRank = Integer.parseInt(move.substring(1,2));
		char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

        int rankDiff = endRank - startRank;
        int fileDiff = (int)startFile - (int)endFile;

        if(Math.abs(fileDiff) == 1 && Math.abs(rankDiff) == 1)
        { //if pawn is moving diagonally

                if(white == true){ //piece is white
                    for (ReturnPiece piece : currentBoard) {
                        if (piece.toString().substring(0,2).equals(""+endFile+endRank) && piece.toString().substring(3,4).equals("W")) 
                        {
                            System.out.println("white pawn trying to take white piece");
                            return false; //it's in the target space and is same color (white), can't capture
                        }
                    }
                }
                else if(white == false) //piece is black
                {
                    for (ReturnPiece piece : currentBoard) {
                        if (piece.toString().substring(0,2).equals(""+endFile+endRank) && piece.toString().substring(3,4).equals("B")) 
                        {
                            System.out.println("black pawn trying to take black piece");
                            return false; //it's in the target space and is the same color (black), can't capture
                        }
                    }
                }


                //------->passes if they differ in color (or empty) - okay

                //check if the piece they are attacking is a king
                //not sure if this ever hits, but just to be sure
                for (ReturnPiece piece : currentBoard){ 
                    if (piece.toString().substring(0,2).equals(""+endFile+endRank) && (piece.pieceType == ReturnPiece.PieceType.WK || piece.pieceType == ReturnPiece.PieceType.BK)) 
                    {
                        return false;
                    }
                }

                //there is a piece for the pawn to be captured
                for (ReturnPiece piece : currentBoard){ 
                    if (piece.toString().substring(0,2).equals(""+endFile+endRank)) 
                    {
                        return true; //pawn can capture piece
                    }
                }

                //TODO en passant

                Pawn currPawn = null;
                for (ReturnPiece piece : currentBoard){
                    if(white){
                        if (piece.toString().equals(""+endFile+(endRank-1)+":BP")) //one under white pawn is black pawn
                        {
                            currPawn = (Pawn)piece;
                            if(currPawn.firstMoved == true) //pawn just made initial two square advance
                            {
                                System.out.println("canPawnCapture: En passant, white pawn takes black");
                                return true;
                            }
                        
                        }
                    }
                    else if(!white){
                        if (piece.toString().equals(""+endFile+(endRank+1)+":WP"))  //one above black pawn is white pawn
                        {
                            currPawn = (Pawn)piece;
                            if(currPawn.firstMoved == true) //pawn just made initial two square advance
                            {
                                System.out.println("canPawnCapture: En passant, black pawn takes white");
                                return true;
                            }
                        }
                    }                   
                }
        } //if diagonal

        return false; //EVERY OTHER CASE PAWN CANNOT CAPTURE.
    
    } //canPawnCapture

    public static ArrayList<ReturnPiece> takePiece(ArrayList<ReturnPiece> currentBoard, String move) //removes captured piece
    {
        ArrayList<ReturnPiece> tempBoard = currentBoard;

        char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

        ReturnPiece removedPiece = null;
        for (ReturnPiece piece : tempBoard){
            if (piece.toString().substring(0,2).equals(""+endFile+endRank))
            {
                removedPiece = piece; //only removes if there is a piece there
            }
        }
        if(removedPiece != null)
        {
            tempBoard.remove(removedPiece);
            System.out.println("removed "+removedPiece);
        }
        

        return tempBoard;
    }
    public static ArrayList<ReturnPiece> pawntakePiece(ArrayList<ReturnPiece> currentBoard, String move, boolean white) //removes captured piece
    {
        ArrayList<ReturnPiece> tempBoard = currentBoard;

        char endFile = move.substring(3,4).toLowerCase().charAt(0);
		int endRank = Integer.parseInt(move.substring(4,5));

        ReturnPiece removedPiece = null;
        for (ReturnPiece piece : tempBoard){
            if (piece.toString().substring(0,2).equals(""+endFile+endRank))
            {
                removedPiece = piece; //only removes if there is a piece there
            }
            if (piece.toString().substring(0,2).equals(""+endFile+endRank))
            {
                removedPiece = piece; //only removes if there is a piece there
            }
            if(white){
                if (piece.toString().equals(""+endFile+(endRank-1)+":BP"))
                {
                    removedPiece = piece;
                }
            }
            else if(!white){
                if (piece.toString().equals(""+endFile+(endRank+1)+":WP"))  
                {
                    removedPiece = piece; 
                }
            } 
        }
        if(removedPiece != null)
        {
            tempBoard.remove(removedPiece);
            System.out.println("removed "+removedPiece);
        }
        return tempBoard;
    }

} //class body
