package chess;
import java.util.ArrayList;

class King extends ReturnPiece 
{
	private boolean castlingDone = false; //castling CAN BE DONE ONCE
	
	King(boolean white) //constructor and differentiates color for piece
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WK;
		else
		this.pieceType = ReturnPiece.PieceType.BK;
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

	//TODO: move checker and castle checker and check checker 

}