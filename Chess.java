//Christopher Tu 
//netID: ct657
//Ala Ghaith 
//netID: aag254

package chess;

import java.util.ArrayList;

//TODO: Make all piece classes and inherit ReturnPiece

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	static ReturnPlay play; //ReturnPlay object holds ArrayList<ReturnPiece> piecesOnBoard

	enum Player { white, black }
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) { //move example is a1 a2
 
		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */




		
		return play;
	}

	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		//make an array list to hold all the new pieces
		play = new ReturnPlay();
		play.piecesOnBoard = new ArrayList<ReturnPiece>();
		play.piecesOnBoard.clear();

		
		play.piecesOnBoard.clear();
		//make a ReturnPiece object for every piece, and define its enums and fields

		//Pieces which have duplicates: 1 is LHS, 2 is RHS

		//White-----------------------------------------------------------------------------------------------
		//A1
		ReturnPiece WR1 = new Rook(true); //boolean in constructor already defines piece enum
		WR1.pieceFile = ReturnPiece.PieceFile.a; WR1.pieceRank = 1;
		play.piecesOnBoard.add(WR1); 
		//B1
		ReturnPiece WN1 = new Knight(true);
		WN1.pieceFile = ReturnPiece.PieceFile.b; WN1.pieceRank = 1;
		play.piecesOnBoard.add(WN1); 
		//C1
		ReturnPiece WB1 = new Bishop(true);
		WB1.pieceFile = ReturnPiece.PieceFile.c; WB1.pieceRank = 1;
		play.piecesOnBoard.add(WB1); 
		//D1
		ReturnPiece WQ = new Queen(true);
		WQ.pieceFile = ReturnPiece.PieceFile.d; WQ.pieceRank = 1;
		play.piecesOnBoard.add(WQ);
		//E1
		ReturnPiece WK = new King(true);
		WK.pieceFile = ReturnPiece.PieceFile.e; WK.pieceRank = 1;
		play.piecesOnBoard.add(WK);
		//F1
		ReturnPiece WB2 = new Bishop(true);
		WB2.pieceFile = ReturnPiece.PieceFile.f; WB2.pieceRank = 1;
		play.piecesOnBoard.add(WB2);
		//G1
		ReturnPiece WN2 = new Knight(true);
		WN2.pieceFile = ReturnPiece.PieceFile.g; WN2.pieceRank = 1;
		play.piecesOnBoard.add(WN2);
		//H1
		ReturnPiece WR2 = new Rook(true);
		WR2.pieceFile = ReturnPiece.PieceFile.h; WR2.pieceRank = 1;
		play.piecesOnBoard.add(WR2);
		//white pawns----------------------------
		//A2
		ReturnPiece WP1 = new Pawn(true);
		WP1.pieceFile = ReturnPiece.PieceFile.a; WP1.pieceRank = 2;
		play.piecesOnBoard.add(WP1);
		//B2
		ReturnPiece WP2 = new Pawn(true);
		WP2.pieceFile = ReturnPiece.PieceFile.b; WP2.pieceRank = 2;
		play.piecesOnBoard.add(WP2);
		//C2
		ReturnPiece WP3 = new Pawn(true);
		WP3.pieceFile = ReturnPiece.PieceFile.c; WP3.pieceRank = 2;
		play.piecesOnBoard.add(WP3);
		//D2
		ReturnPiece WP4 = new Pawn(true);
		WP4.pieceFile = ReturnPiece.PieceFile.d; WP4.pieceRank = 2;
		play.piecesOnBoard.add(WP4);
		//E2
		ReturnPiece WP5 = new Pawn(true);
		WP5.pieceFile = ReturnPiece.PieceFile.e; WP5.pieceRank = 2;
		play.piecesOnBoard.add(WP5);
		//F2
		ReturnPiece WP6 = new Pawn(true);
		WP6.pieceFile = ReturnPiece.PieceFile.f; WP6.pieceRank = 2;
		play.piecesOnBoard.add(WP6);
		//G2
		ReturnPiece WP7 = new Pawn(true);
		WP7.pieceFile = ReturnPiece.PieceFile.g; WP7.pieceRank = 2;
		play.piecesOnBoard.add(WP7);
		//H2
		ReturnPiece WP8 = new Pawn(true);
		WP8.pieceFile = ReturnPiece.PieceFile.h; WP8.pieceRank = 2;
		play.piecesOnBoard.add(WP8);
		//Black-----------------------------------------------------------------------------------------------
		//A8
		ReturnPiece BR1 = new Rook(false);
		BR1.pieceFile = ReturnPiece.PieceFile.a; BR1.pieceRank = 8;
		play.piecesOnBoard.add(BR1);
		//B8
		ReturnPiece BN1 = new Knight(false);
		BN1.pieceFile = ReturnPiece.PieceFile.b; BN1.pieceRank = 8;
		play.piecesOnBoard.add(BN1);
		//C8
		ReturnPiece BB1 = new Bishop(false);
		BB1.pieceFile = ReturnPiece.PieceFile.c; BB1.pieceRank = 8;
		play.piecesOnBoard.add(BB1);
		//D8
		ReturnPiece BQ = new Queen(false);
		BQ.pieceFile = ReturnPiece.PieceFile.d; BQ.pieceRank = 8;
		play.piecesOnBoard.add(BQ);
		//E8
		ReturnPiece BK = new King(false);
		BK.pieceFile = ReturnPiece.PieceFile.e; BK.pieceRank = 8;
		play.piecesOnBoard.add(BK);
		//F8
		ReturnPiece BB2 = new Bishop(false);
		BB2.pieceFile = ReturnPiece.PieceFile.f; BB2.pieceRank = 8;
		play.piecesOnBoard.add(BB2);
		//G8
		ReturnPiece BN2 = new Knight(false);
		BN2.pieceFile = ReturnPiece.PieceFile.g; BN2.pieceRank = 8;
		play.piecesOnBoard.add(BN2);
		//H8
		ReturnPiece BR2 = new Rook(false);
		BR2.pieceFile = ReturnPiece.PieceFile.h; BR2.pieceRank = 8;
		play.piecesOnBoard.add(BR2);
		//black pawns----------------------------
		//A7
		ReturnPiece BP1 = new Pawn(false);
		BP1.pieceFile = ReturnPiece.PieceFile.a; BP1.pieceRank = 7;
		play.piecesOnBoard.add(BP1);
		//B7
		ReturnPiece BP2 = new Pawn(false);
		BP2.pieceFile = ReturnPiece.PieceFile.b; BP2.pieceRank = 7;
		play.piecesOnBoard.add(BP2);
		//C7
		ReturnPiece BP3 = new Pawn(false);
		BP3.pieceFile = ReturnPiece.PieceFile.c; BP3.pieceRank = 7;
		play.piecesOnBoard.add(BP3);
		//D7
		ReturnPiece BP4 = new Pawn(false);
		BP4.pieceFile = ReturnPiece.PieceFile.d; BP4.pieceRank = 7;
		play.piecesOnBoard.add(BP4);
		//E7
		ReturnPiece BP5 = new Pawn(false);
		BP5.pieceFile = ReturnPiece.PieceFile.e; BP5.pieceRank = 7;
		play.piecesOnBoard.add(BP5);
		//F7
		ReturnPiece BP6 = new Pawn(false);
		BP6.pieceFile = ReturnPiece.PieceFile.f; BP6.pieceRank = 7;
		play.piecesOnBoard.add(BP6);
		//G7
		ReturnPiece BP7 = new Pawn(false);
		BP7.pieceFile = ReturnPiece.PieceFile.g; BP7.pieceRank = 7;
		play.piecesOnBoard.add(BP7);
		//H7
		ReturnPiece BP8 = new Pawn(false);
		BP8.pieceFile = ReturnPiece.PieceFile.h; BP8.pieceRank = 7;
		play.piecesOnBoard.add(BP8);	
	}
}

