package chess;
import java.util.ArrayList;

class King extends ReturnPiece 
{
	private boolean castlingDone = false; //castling CAN BE DONE ONCE
	boolean white;
	

	/*
	 * 
	 * 
	 *  TODO: WRITE A METHOD CALLED King.onCheck
	 * 
	 * 	TODO: Later: Write a method called King.onCheckmate;
	 * 
	 * 
	 * 	AFTER EVERY MOVE VALIDATION, CHECK IF CHECK OCCURS OR CHECK IS STOPPED using chess.Chess's static boolean check_initiated
	 * 	Both kings are checked.
	 * 
	 */
	King(boolean white) //constructor and differentiates color for piece
	{
		if(white == true)
		{
			this.pieceType = ReturnPiece.PieceType.WK;
			this.white = true;
		}
		else
		{
			this.pieceType = ReturnPiece.PieceType.BK;
			this.white = false;
		}
		
	}
	public boolean isCastlingDone()
	{
		return this.castlingDone;
	}
	//edit castling status 
	public void setCastlingDone(boolean castlingDone)
	{
		this.castlingDone = castlingDone;
	}

	
	public boolean onCheck(ArrayList<ReturnPiece> currentBoard) //returns true if passed board parameter is in check
	{ 
		/*System.out.println("On check was called on " + this.toString());	*/

		
			String kingPosition = this.toString().substring(0, 2);
	
			char kingFile = kingPosition.charAt(0);
			int kingRank = Integer.parseInt(kingPosition.substring(1));
	
			String upRight = "" + (char)(kingFile + 1) + (kingRank + 1);
			String upLeft = "" + (char)(kingFile - 1) + (kingRank + 1);
	
			String downRight = "" + (char)(kingFile + 1) + (kingRank - 1);
			String downLeft = "" + (char)(kingFile - 1) + (kingRank - 1);


			//CHECKING IF VULNERABLE TO PAWN
			for (ReturnPiece piece : currentBoard) {
				if ((this.white && (piece.toString().substring(3,4).equals("B"))) || (!this.white && (piece.toString().substring(3,4).equals("W"))) ) {
					String piecePosition = piece.toString().substring(0, 2);
	
					if (piecePosition.equals(upRight) || piecePosition.equals(upLeft) ||piecePosition.equals(downRight) || piecePosition.equals(downLeft)) {
					   if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BP : ReturnPiece.PieceType.WP)) {
							return true;
						}
					}
				}
			}
			//CHECKING IF VULNERABLE TO KNIGHT
			String[] knightPositions = {
				"" + (char)(kingFile + 1) + (kingRank + 2),
				"" + (char)(kingFile - 1) + (kingRank + 2),
				"" + (char)(kingFile + 2) + (kingRank + 1),
				"" + (char)(kingFile - 2) + (kingRank + 1),
				"" + (char)(kingFile + 1) + (kingRank - 2),
				"" + (char)(kingFile - 1) + (kingRank - 2),
				"" + (char)(kingFile + 2) + (kingRank - 1),
				"" + (char)(kingFile - 2) + (kingRank - 1)
			};
			for (ReturnPiece piece : currentBoard) 
			{
				if ((this.white && (piece.toString().substring(3,4).equals("B"))) || (!this.white && (piece.toString().substring(3,4).equals("W"))) ) {
					String piecePosition = piece.toString().substring(0, 2);
					for (String knightPos : knightPositions) {
						if (piecePosition.equals(knightPos)) {
							if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BN : ReturnPiece.PieceType.WN)) {
								return true;
							}
						}
					}
				}
			}



			return false;
	}
	
	

	//FUTURE MOVE CHECKER
	public boolean onCheck(ArrayList<ReturnPiece> currentBoard, String move) //returns true if in check after the move is done
	{ 
		return false;
	}
	public boolean onCheckMate(ArrayList<ReturnPiece> currentBoard) //should be called after any piece moves
	{

		//TODO:finish onCheckMate
		return false;
	}

	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		char startFile = move.substring(0, 1).toLowerCase().charAt(0);
        int startRank = Integer.parseInt(move.substring(1, 2));
        char endFile = move.substring(3, 4).toLowerCase().charAt(0);
        int endRank = Integer.parseInt(move.substring(4, 5));

        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        if (fileDiff <= 1 && rankDiff <= 1) {

            return true;
        } 
		else 
		{

            return false;
        }
	}

}