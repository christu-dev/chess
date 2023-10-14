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
		
		
		//TODO: finish OnCheck

		return false;
	}
	public boolean onCheck(ArrayList<ReturnPiece> currentBoard, String move) //returns true if in check after the move is done
	{ 
	
		//Simulates a move?
		//TODO: finish OnCheck

		return false;
	}
	public boolean onCheckMate(ArrayList<ReturnPiece> currentBoard) //should be called after any piece moves
	{

		//TODO:finish onCheckMate
		return false;
	}

	//TODO: move checker and castle checker and check checker 
	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		return true;
	}

}