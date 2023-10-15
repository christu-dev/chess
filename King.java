package chess;
import java.util.ArrayList;

class King extends ReturnPiece 
{
	private boolean castlingDone = false; //castling CAN BE DONE ONCE
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