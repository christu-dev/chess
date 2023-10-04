package chess;
import java.util.ArrayList;

class Rook extends ReturnPiece {
	Rook(boolean white)
	{
		if(white == true)
		this.pieceType = ReturnPiece.PieceType.WR;
		else
		this.pieceType = ReturnPiece.PieceType.BR;
	}

}