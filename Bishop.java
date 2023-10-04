package chess;

class Bishop extends ReturnPiece {

	Bishop(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WB;
		else
		this.pieceType = ReturnPiece.PieceType.BB;
	}

}