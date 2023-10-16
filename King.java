package chess;
import java.util.ArrayList;

class King extends ReturnPiece 
{
	private boolean castlingDone = false; //castling CAN BE DONE ONCE
	boolean hasMoved = false;
	boolean white;
	

	/*
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
	public void setCastlingDone()
	{
		this.castlingDone = true;
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
		//else if(this.kingCastling(currentBoard, move)) //if castling is valid
		//{
		//	return true;
		//}
		else 
		{
            return false;
        }
	}

	/*boolean kingCastling(ArrayList<ReturnPiece> currentBoard, String move) //can the king castle
	{
		if(this.isCastlingDone()){
			return false; //cannot castle since already has castled
		}
		if(this.hasMoved()){
			return false; //cannot castle since already has moved itself (will check for Rook later)
		}

        char startFile = move.substring(0, 1).toLowerCase().charAt(0);
        int startRank = Integer.parseInt(move.substring(1, 2));
        char endFile = move.substring(3, 1).toLowerCase().charAt(0);
        int endRank = Integer.parseInt(move.substring(4, 5));

        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        if (fileDiff == 2 && rankDiff == 0) //move is valid
		{

            if (startFile == 'e' && startRank == 1 && endRank == 1 && this.white) {

                for (ReturnPiece piece : currentBoard) {
                    if (piece.toString().equals("h1 R") && piece instanceof Rook) {

                        piece.setPiecePosition("h1 f1");
                        break;
                    }
                }


                this.setPiecePosition("e1 g1");


                this.setCastlingDone(true);

                return true;
            } else if (startFile == 'e' && startRank == 1 && endRank == 1 && !this.white) {

                if (this.castlingDone) {

                    return false;
                }


                for (ReturnPiece piece : currentBoard) {
                    if (piece.toString().equals("h8 r") && piece instanceof Rook) {

                        piece.setPiecePosition("h8 f8");
                        break;
                    }
                }


                this.setPiecePosition("e8 g8");


                this.setCastlingDone(true);

                return true;
            }
        }
        return false;
    }*/





	
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


			//CHECKING IF VULNERABLE TO PAWN------------------------------------------------------------------------------------------
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
			//CHECKING IF VULNERABLE TO KNIGHT------------------------------------------------------------------------------------------
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
			//CHECKING IF VULNERABLE TO DIAGONALS (BISHOP AND QUEEN)------------------------------------------------------------------------------------------
			
		 	for (ReturnPiece piece : currentBoard) 
			{
				String piecePosition = piece.toString().substring(0, 2);
		
				//distance from king to piece
				int fileDifference = Math.abs(kingFile - piecePosition.charAt(0));
				int rankDifference = Math.abs(kingRank - Integer.parseInt(piecePosition.substring(1)));

				int FileOffset, RankOffset; //x = Column, y = row;

					if(kingRank < Integer.parseInt(piecePosition.substring(1))){ 
						RankOffset = 1;
					}
					else
					{
						RankOffset = -1;
					}
					if(kingFile < piecePosition.charAt(0)){
						FileOffset = 1;
					}
					else{
						FileOffset = -1;
					}


				if (fileDifference == rankDifference) //only considers diagonal pieces
				{

					bishopCheck:
					if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BB : ReturnPiece.PieceType.WB)) //successfully finds opposite bishop in diagonal
					{

						int x = (int)kingFile + FileOffset;

						
						for(int y = kingRank + RankOffset; y != Integer.parseInt(piecePosition.substring(1,2)); y += RankOffset)
						{
							for (ReturnPiece rp: currentBoard)
							{
								String checkPos = ""+(char)x+y+"";
								if(rp.toString().substring(0,2).equals(checkPos))
								{
									System.out.println("Something in the way of bishop diagonal");
									break bishopCheck; 
								}
							}
						x += FileOffset;
						}

						//only comes here if there is nothing in the way
						
						return true;

					}

					queenCheck:
					if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BQ : ReturnPiece.PieceType.WQ))  //successfully finds queen in diagonal
					{
						int x = (int)kingFile + FileOffset;

						for(int y = kingRank + RankOffset; y != Integer.parseInt(piecePosition.substring(1,2)); y += RankOffset)
						{
							for (ReturnPiece rp: currentBoard)
							{
								String checkPos = ""+(char)x+y+"";
								if(rp.toString().substring(0,2).equals(checkPos))
								{
									System.out.println("Something in the way of queen diagonal");
									break queenCheck; 
								}
							}
						x += FileOffset;
						}

						//only comes here if there is nothing in the way
						
						return true;
					}
					/*
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 */			

				} 
			}//end diagonal check	
			//something was in the way of the diagonal or there is no danger from diagonals.
			

			//CHECKING IF VULNERABLE TO horizontals and verticals (ROOK AND QUEEN)------------------------------------------------------------------------------------------
			for (ReturnPiece piece : currentBoard) 
			{
				String piecePosition = piece.toString().substring(0, 2);
		
				//distance from king to piece
				int fileDifference = Math.abs(kingFile - piecePosition.charAt(0));
				int rankDifference = Math.abs(kingRank - Integer.parseInt(piecePosition.substring(1,2)));

				int FileOffset = 0; 
				int RankOffset = 0; //x = Column, y = row;

					if(kingRank < Integer.parseInt(piecePosition.substring(1)))
					{ 
						RankOffset = 1;
					}
					if(kingRank > Integer.parseInt(piecePosition.substring(1)))
					{
						RankOffset = -1;
					}
					if(kingFile < piecePosition.charAt(0))
					{
						FileOffset = 1;
					}
					if(kingFile > piecePosition.charAt(0)){
						FileOffset = -1;
					}



					if (fileDifference == 0) //only considers vertical
					{
						rookCheck:
						if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BR : ReturnPiece.PieceType.WR)) //successfully finds rook in vertical
						{

							for (int rank = kingRank + RankOffset; rank != Integer.parseInt(piecePosition.substring(1,2)); rank += RankOffset) 
								{
									for (ReturnPiece rp: currentBoard)
									{
										String checkPos = ""+(char)kingFile+""+rank+"";
										if(rp.toString().substring(0,2).equals(checkPos))
										{
											System.out.println("Something in the way of rook vertical");
											break rookCheck; 
										}
									}

								}
							//only comes here if there is nothing in the way
							return true;

						}

						queenCheck:
						if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BQ : ReturnPiece.PieceType.WQ))  //successfully finds queen in vertical
						{
							for (int rank = kingRank + RankOffset; rank != Integer.parseInt(piecePosition.substring(1)); rank += RankOffset) 
								{
									for (ReturnPiece rp: currentBoard)
									{
										String checkPos = ""+(char)kingFile+""+rank+"";
										if(rp.toString().substring(0,2).equals(checkPos))
										{
											System.out.println("Something in the way of queen vertical");
											break queenCheck; 
										}
									}

								}
							//only comes here if there is nothing in the way
							return true;
						}
					}//end of vertical check
					if (rankDifference == 0) //only considers horizontal
					{
						rookCheck:
						if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BR : ReturnPiece.PieceType.WR)) //successfully finds rook in horizontal
						{
							for (char file = (char)(kingFile + FileOffset); file != piecePosition.charAt(0); file += FileOffset) 
							{
								for (ReturnPiece rp: currentBoard)
									{
										String checkPos = ""+(char)file+""+kingRank+"";
										if(rp.toString().substring(0,2).equals(checkPos))
										{
											System.out.println("Something in the way of rook horizontal");
											break rookCheck; 
										}
									}
							}
							//only comes here if there is nothing in the way
							return true;
						}

						queenCheck:
						if (piece.pieceType == (this.white ? ReturnPiece.PieceType.BQ : ReturnPiece.PieceType.WQ))  //successfully finds queen in honrizontal
						{
							for (char file = (char)(kingFile + FileOffset); file != piecePosition.charAt(0); file += FileOffset) 
							{
								for (ReturnPiece rp: currentBoard)
									{
										String checkPos = ""+(char)file+""+kingRank+"";
										if(rp.toString().substring(0,2).equals(checkPos))
										{
											System.out.println("Something in the way of queen horizontal");
											break queenCheck; 
										}
									}
							}
							//only comes here if there is nothing in the way
							return true;	
						}						
					}//end of horizontal check

					/*
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 */			

				
			} //end horizontal and vertical check
			//something was in the way of the horizontal/vertical OR there is no danger from horizontal/vertical.

			//CHECKING IF VULNERABLE TO KING
			//(WILL NEVER HIT NORMALLY BUT WILL BE NECESSARY FOR ONCHECK(BOARD MOVE) To prevent king facing king
			for (ReturnPiece piece : currentBoard) 
			{
				String piecePosition = piece.toString().substring(0, 2);
		
				//distance from king to piece
				int fileDifference = Math.abs(kingFile - piecePosition.charAt(0));
				int rankDifference = Math.abs(kingRank - Integer.parseInt(piecePosition.substring(1,2)));
	
				if (fileDifference <= 1 && rankDifference <= 1 && ( (this.white && piece.pieceType == ReturnPiece.PieceType.BK) || (!this.white && piece.pieceType == ReturnPiece.PieceType.WK) ) )//only considers direct vicinity kings
				{ //all within 1 space
					return true;
				} 
			}

		return false; 
	}


	//FUTURE MOVE CHECKER
	public boolean onCheck(ArrayList<ReturnPiece> currentBoard, String move) //returns true if in check after the move is done
	{ 
	/* 	//this comes after move validation
		Bishop currentBishop = null;
		Pawn currentPawn = null;
		King currentKing = null;
		Queen currentQueen = null;
		Knight currentKnight = null;
		Rook currentRook = null; 

		for (ReturnPiece rp: play.piecesOnBoard){

			//Bishop Check-------------
			if(move.substring(0, 2).equals(rp.toString().substring(0,2)) && rp instanceof Bishop) //find the piece and attach it to current piece
			{
					currentBishop = (Bishop)rp;	
			}
			//Pawn check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Pawn) //find the piece and attach it to current piece
			{
					currentPawn = (Pawn)rp;	
			}
			//King check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof King) //find the piece and attach it to current piece
			{
					currentKing = (King)rp;
				
			}
			//Queen check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Queen) //find the piece and attach it to current piece
			{
					currentQueen = (Queen)rp;	
			}
			//Knight check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Knight) //find the piece and attach it to current piece
			{
				currentKnight = (Knight)rp;
			}
			//Rook check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Rook) //find the piece and attach it to current piece
			{	
				currentRook = (Rook)rp;
			}
				
		} //end iterating through pieces for Piece attachment
		if(currentBishop == null && currentPawn == null && currentKing == null && currentQueen == null && currentKnight == null && currentRook == null)
		{
			play.message = ReturnPlay.Message.ILLEGAL_MOVE;
			System.out.println("Error, onCheck future did not bind a piece.");
			return play;
		}
		


			*/

		return false; 
	}
	public boolean onCheckMate(ArrayList<ReturnPiece> currentBoard) //should be called after any piece moves
	{

		//TODO:finish onCheckMate
		return false;
	}
	

}