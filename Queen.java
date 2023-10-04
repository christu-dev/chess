package chess;

class Queen extends ReturnPiece {

	Queen(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WQ;
		else
		this.pieceType = ReturnPiece.PieceType.BQ;
	}

}
