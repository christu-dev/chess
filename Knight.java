package chess;

class Knight extends ReturnPiece {
	Knight(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WN;
		else
		this.pieceType = ReturnPiece.PieceType.BN;
	}

}
