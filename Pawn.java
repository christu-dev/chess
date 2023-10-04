package chess;

class Pawn extends ReturnPiece {
	Pawn(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WP;
		else
		this.pieceType = ReturnPiece.PieceType.BP;
	}

}