package chess;
import java.util.ArrayList;

import javax.management.monitor.StringMonitorMBean;

/*
 * 
 * 
 * Overloaded method onCheck
 * 
 *  King.onCheck(ArrayList<ReturnPiece> currentBoard) 
 *  		- checks if the king is vulnerable given the current state of the board.
 *  King.onCheck(ArrayList<ReturnPiece> currentBoard, String move) 
 * 			- checks if the king is vulnerable if the move is performed on the board.
 *  		- It creates hypothetical future version of the board, calls King.onCheck(ArrayList<ReturnPiece> currentBoard)
 * 			- Used as implementation for checking for illegally endangering king, and successfully leaving a check.
 * 
 * 
 * The bounds checking in any given piece's possibleMove() method is to prevent Parsing a negative sign when passing char
 * onCheck has out of bounds checking for everything - it returns true if there are out of bounds moev - this works as expected anyways because it will print illegal move
 * this way, only valid moves are added in considered options for escape routes
 * 
 * 
 * onCheckmate is an algorithm that uses a static arraylist of Strings in Chess, adds and evaluates moves, then adds moves to a local method arraylist if it will get the King in question out of check
 * if the local arraylist (the escape options) is empty, that is checkmate.
 * 
 */

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
	void possibleMoves()
    {
        //ArrayList<String> possibleMoves = new ArrayList<>();
		//String temp;

        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 0);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 0));
        
        
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 0);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 0));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1);
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1));
        //temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 0) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1 );
        Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 0) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) + 1 ));


		if(!(((int)(Integer.parseInt(this.toString().substring(1,2)))- 1)<1))
		{
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1));
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 0) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) + 0) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1));
			//temp = this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1);
			Chess.possibleMoves.add(this.toString().substring(0,2) + " " + (char)(this.toString().charAt(0) - 1) + "" +(int)(Integer.parseInt(this.toString().substring(1,2)) - 1));
		}

        //return possibleMoves();
    }

	boolean checkValidMove(ArrayList<ReturnPiece> currentBoard, String move)
	{	
		
		char startFile = move.substring(0, 1).toLowerCase().charAt(0);
        int startRank = Integer.parseInt(move.substring(1, 2));
        char endFile = move.substring(3, 4).toLowerCase().charAt(0);
        int endRank = Integer.parseInt(move.substring(4, 5));

		if(startFile < 'a' || startFile > 'h' || startRank < 1 || startRank > 8){
			//System.out.println("out of bounds");
			return false;
		}
		if(endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8){
			//System.out.println("out of bounds");
			return false;
		}

        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        if (fileDiff <= 1 && rankDiff <= 1) {
            return true;
        }
		else if(this.kingCastling(currentBoard, move)) //if castling is valid
		{
			return true;
		}
		else 
		{
            return false;
        }
	}

	boolean kingCastling(ArrayList<ReturnPiece> currentBoard, String move) //can the king castle
	{
		if(this.isCastlingDone()){
			return false; //cannot castle since already has castled
		}
		if(this.hasMoved){
			return false; //cannot castle since already has moved itself (will check for Rook later)
		}

		ArrayList<ReturnPiece> tempBoard = currentBoard;
		ArrayList<ReturnPiece> tempBoard2 = currentBoard;
		ArrayList<ReturnPiece> tempBoard3 = currentBoard;
	

        char startFile = move.substring(0, 1).toLowerCase().charAt(0);
        int startRank = Integer.parseInt(move.substring(1, 2));
        char endFile = move.substring(3, 4).toLowerCase().charAt(0);
        int endRank = Integer.parseInt(move.substring(4,5));

        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);

        if (fileDiff == 2 && rankDiff == 0) //move is valid
		{

				if (startFile == 'e' && startRank == 1 && endRank == 1 && this.white && endFile == 'g') //White king side castle----------------------------------
				{

					//IN THE WAY CHECK
					for(ReturnPiece rp : currentBoard){
						if(rp.toString().substring(0,2).equals("f1")) //f1
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("g1")) //g1
						{
							return false;
						}
					}
					
					//now check for King passing check

					for(ReturnPiece rp: tempBoard){
						if(rp.pieceType == ReturnPiece.PieceType.WK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.f; //move the piece to fake position
							rp.pieceRank = 1;
							King tempKing = (King)rp;

							if(tempKing.onCheck(tempBoard))
							{
							return false;
							}
						}
						
					}
					for(ReturnPiece rp: tempBoard2){
						if(rp.pieceType == ReturnPiece.PieceType.WK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.g; //move the piece to fake position
							rp.pieceRank = 1;
							King tempKing = (King)rp;
							if(tempKing.onCheck(tempBoard2))
							{
							return false;
							}
						}
					}


					
					return true; //all conditions pass for white kingside castle


					

				}
				else if (startFile == 'e' && startRank == 1 && endRank == 1 && this.white && endFile == 'c') //White Queenside Castle----------------------------------
				{
					//IN THE WAY CHECK
					for(ReturnPiece rp : currentBoard){
						if(rp.toString().substring(0,2).equals("d1")) //d1
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("c1")) //c1
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("b1")) //b1
						{
							return false;
						}
					}

					for(ReturnPiece rp: tempBoard)
					{
						if(rp.pieceType == ReturnPiece.PieceType.WK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.d; //move the piece to fake position
							rp.pieceRank = 1;
							King tempKing = (King)rp;

							if(tempKing.onCheck(tempBoard))
							{
							return false;
							}
						}
						
					}
					for(ReturnPiece rp: tempBoard2)
					{
						if(rp.pieceType == ReturnPiece.PieceType.WK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.c; //move the piece to fake position
							rp.pieceRank = 1;
							King tempKing = (King)rp;
							if(tempKing.onCheck(tempBoard2))
							{
							return false;
							}
						}
					}
					for(ReturnPiece rp: tempBoard3)
					{
						if(rp.pieceType == ReturnPiece.PieceType.WK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.b; //move the piece to fake position
							rp.pieceRank = 1;
							King tempKing = (King)rp;
							if(tempKing.onCheck(tempBoard3))
							{
							return false;
							}
						}
					}

					
					return true; //all conditions pass for white queenside castle


				}
				else if (startFile == 'e' && startRank == 8 && endRank == 8 && !this.white && endFile == 'g') //Black Kingside Castle----------------------------------
				{
					//IN THE WAY CHECK
					for(ReturnPiece rp : currentBoard){
						if(rp.toString().substring(0,2).equals("f8")) //f8
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("g8")) //g8
						{
							return false;
						}
					}

					//now check for King passing check

					for(ReturnPiece rp: tempBoard){
						if(rp.pieceType == ReturnPiece.PieceType.BK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.f; //move the piece to fake position
							rp.pieceRank = 8;
							King tempKing = (King)rp;

							if(tempKing.onCheck(tempBoard))
							{
							return false;
							}
						}
						
					}

					for(ReturnPiece rp: tempBoard2){
						if(rp.pieceType == ReturnPiece.PieceType.BK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.g; //move the piece to fake position
							rp.pieceRank = 8;
							King tempKing = (King)rp;

							if(tempKing.onCheck(tempBoard2))
							{
							return false;
							}
						}
					}

					
					return true; //all conditions pass for black kingside castle

				}
				else if(startFile == 'e' && startRank == 8 && endRank == 8 && !this.white && endFile == 'c') //Black Queenside Castle----------------------------------
				{
					//IN THE WAY CHECK
					for(ReturnPiece rp : currentBoard){
						if(rp.toString().substring(0,2).equals("d8")) //d8
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("c8")) //c8
						{
							return false;
						}
						if(rp.toString().substring(0,2).equals("b8")) //b8
						{
							return false;
						}
					}

					for(ReturnPiece rp: tempBoard)
					{
						if(rp.pieceType == ReturnPiece.PieceType.BK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.d; //move the piece to fake position
							rp.pieceRank = 8;
							King tempKing = (King)rp;

							if(tempKing.onCheck(tempBoard))
							{
							return false;
							}
						}
						
					}
					for(ReturnPiece rp: tempBoard2)
					{
						if(rp.pieceType == ReturnPiece.PieceType.BK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.c; //move the piece to fake position
							rp.pieceRank = 8;
							King tempKing = (King)rp;
							if(tempKing.onCheck(tempBoard2))
							{
							return false;
							}
						}
					}
					for(ReturnPiece rp: tempBoard3)
					{
						if(rp.pieceType == ReturnPiece.PieceType.BK)
						{
							rp.pieceFile = ReturnPiece.PieceFile.b; //move the piece to fake position
							rp.pieceRank = 8;
							King tempKing = (King)rp;
							if(tempKing.onCheck(tempBoard3))
							{
							return false;
							}
						}
					}

					 //can't castle twice in a game
					return true; //all conditions pass for black queenside castle

				}
        }

        return false; //cannot castle
    }//end of catstling method





	
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
	public boolean onCheck(ArrayList<ReturnPiece> currentBoardMain, String move) //returns true if in check after the move is done
	{ 
		

		

		ArrayList<ReturnPiece> currentBoard = new ArrayList<>(); //deep copy

		for(ReturnPiece piece: currentBoardMain)//clone the board
		{
			ReturnPiece piece2 = null;
			if(piece instanceof Bishop){
				Bishop temp = (Bishop)piece;
				piece2 = new Bishop(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}
			else if(piece instanceof King){
				King temp = (King)piece;
				piece2 = new King(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}
			else if(piece instanceof Pawn){
				Pawn temp = (Pawn)piece;
				piece2 = new Pawn(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}
			else if(piece instanceof Knight){
				Knight temp = (Knight)piece;
				piece2 = new Knight(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}
			else if(piece instanceof Queen){
				Queen temp = (Queen)piece;
				piece2 = new Queen(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}
			else if(piece instanceof Rook){
				Rook temp = (Rook)piece;
				piece2 = new Rook(temp.white);
				piece2.pieceRank = piece.pieceRank;
				piece2.pieceFile = piece.pieceFile;
				piece2.pieceType = piece.pieceType;
				currentBoard.add(piece2);
			}	
		}
		/*for(ReturnPiece piece: currentBoard)//clone the board
		{
			System.out.println(piece);
		}
		//Deep copy is working
		*/

		String startFile = move.substring(0,1);
		int startRank = Integer.parseInt(move.substring(1,2));

		String endFile = move.substring(3,4);
		int endRank = Integer.parseInt(move.substring(4,5));

		if(startFile.charAt(0) < 'a' || startFile.charAt(0) > 'h' || startRank < 1 || startRank > 8){
			//System.out.println("out of bounds");
			return true; //for checkmate check purposes avoiding stack overflow
		}
		if(endFile.charAt(0) < 'a' || endFile.charAt(0) > 'h' || endRank < 1 || endRank > 8){
			//System.out.println("out of bounds");
			return true; //for checkmate check purposes avoiding stack overflow
		}

	 	//this comes after move validation
		Bishop currentBishop = null;
		Pawn currentPawn = null;
		King currentKing = null;
		Queen currentQueen = null;
		Knight currentKnight = null;
		Rook currentRook = null; 


		//ATTACH CURRENT PIECE TO CURRENT PIECE
		for (ReturnPiece rp: currentBoard){

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
			if(rp.equals(this)) //find the piece and attach it to current piece
			{
				currentKing = (King)rp; //king moves itself
				
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
				//System.out.println("knight attached"); works
			}
			//Rook check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Rook) //find the piece and attach it to current piece
			{	
				currentRook = (Rook)rp;
			}
				
		} //end iterating through pieces for Piece attachment
		if(currentBishop == null && currentPawn == null && currentKing == null && currentQueen == null && currentKnight == null && currentRook == null)
		{
			System.out.println("No legal move in onCheck future (this is not supposed to happen, it should've caught earlier)");
			return true; //this will result in illegal move
		}


		//System.out.println("we got here");
		//MOVING ALL THE HYPOTHETICAL PIECES
		//TODO if piece found was a Bishop:----------------------------------------------------------------------------------------------------------------
		if(currentBishop != null)
		{
					if(!(currentBishop.checkValidMove(currentBoard, move))) //if move is not valid
					{
						return true; //not a true check but returns illegal move as expected
					}
					//move the piece
					//Capture Check
					if(Capture.canCapture(currentBoard, move, currentBishop.white))
					{
						ArrayList<ReturnPiece> tempboard = Capture.takePiece(currentBoard, move);
						currentBoard = tempboard; //call this right before any move is made
					}
					else
					{
						System.out.println("oncheck future Bishop capture fail");
						return true; //not a check but this will return an illegal move regardless as in an early capture check
					}

					//en passant limiter
					for (ReturnPiece rp : currentBoard)
						{
						if(rp.toString().contains("P")){
								Pawn tempPawn = (Pawn)rp;
								tempPawn.notFirstMoved();
								rp = tempPawn;
							}
						}

					//piece is updated
					King currKing = null;		
					for (ReturnPiece rp: currentBoard){
						if(rp.equals(currentBishop))
						{
							
							if(endFile.equals("a"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.a;
							}
							if(endFile.equals("b"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.b;
							}
							if(endFile.equals("c"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.c;
							}
							if(endFile.equals("d"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.d;
							}
							if(endFile.equals("e"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.e;
							}
							if(endFile.equals("f"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.f;
							}
							if(endFile.equals("g"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.g;
							}
							if(endFile.equals("h"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.h;
							}

							rp.pieceRank = endRank;						
						}
						if(rp.equals(this)) //attaching self-reference king to current king in question
						{
							currKing = (King)rp;

						}
					}

					//CHECKING KING ON CHECK IN FUTURE
					if(currKing.onCheck(currentBoard))
					{
						System.out.println("bishop in oncheck future can't be moved: king is still in check or you're putting king in check this way.");
						return true;
					}
					//if the move doesn't return true; valid move for bishop
					return false;					
		}
		//TODO if piece found was a Pawn:----------------------------------------------------------------------------------------------------------------
		if(currentPawn != null)
		{
					//move the piece

					//to prevent pawn from taking own king - other pieces have capture check.
					if(!(currentPawn.checkValidMove(currentBoard, move))) //if pawn move is not valid
					{
						return true;
					}

					ArrayList<ReturnPiece> tempboard = Capture.pawntakePiece(currentBoard, move, currentPawn.white);
					currentBoard = tempboard; //call this right before any move is made.

                    //en passant limiter
					for (ReturnPiece rp : currentBoard)
						{
						if(rp == currentPawn)
						{
							continue;
						}
						if(rp.toString().contains("P")){
							Pawn tempPawn = (Pawn)rp;
							tempPawn.notFirstMoved();
							rp = tempPawn;
						}
					}

					King currKing = null;
					ReturnPiece PromotionPiece = null;
					for (ReturnPiece rp: currentBoard){
						if(rp.equals(currentPawn))
						{
							
							if(endFile.equals("a"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.a;
							}
							if(endFile.equals("b"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.b;
							}
							if(endFile.equals("c"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.c;
							}
							if(endFile.equals("d"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.d;
							}
							if(endFile.equals("e"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.e;
							}
							if(endFile.equals("f"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.f;
							}
							if(endFile.equals("g"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.g;
							}
							if(endFile.equals("h"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.h;
							}

							rp.pieceRank = endRank;	

							Pawn promoteChecker = (Pawn)rp; //make a pawn object set to rp after it has moved
							PromotionPiece = promoteChecker.pawnPromotion(move); //run pawnPromotion on it every time to see if promotion is valid						
						}
						if(rp.equals(this)) //attaching self-reference king to current king in question
						{
							currKing = (King)rp;

						}
					}

					//promotion if eligible
					if(PromotionPiece != null){
						currentBoard.remove(currentPawn);
						currentBoard.add(PromotionPiece);
					}

					//CHECKING KING ON CHECK IN FUTURE
					//System.out.println("made it here");
					if(currKing.onCheck(currentBoard))
					{
						System.out.println("pawn in oncheck future can't be moved: king is still in check or you're putting king into check this way.");
						return true;
					}
					//if the move doesn't return true; valid move for pawn
					return false;			
		}
		//TODO if piece found was a Queen:----------------------------------------------------------------------------------------------------------------
		if(currentQueen != null)
		{		
				if(!(currentQueen.checkValidMove(currentBoard, move))) //if move is not valid
					{
						return true; //not a true check but returns illegal move as expected
					}	
			
					//move the piece
					//Capture Check
					if(Capture.canCapture(currentBoard, move, currentQueen.white))
					{
						ArrayList<ReturnPiece> tempboard = Capture.takePiece(currentBoard, move);
						currentBoard = tempboard; //call this right before any move is made
					}
					else
					{
						System.out.println("oncheck future queen can capture failed");
						return true; //not a check but this will return an illegal move regardless as in an early capture check
					}

					//en passant limiter
					for (ReturnPiece rp : currentBoard)
						{
						if(rp.toString().contains("P")){
								Pawn tempPawn = (Pawn)rp;
								tempPawn.notFirstMoved();
								rp = tempPawn;
							}
						}

					//piece is updated
					King currKing = null;		
					for (ReturnPiece rp: currentBoard){
						if(rp.equals(currentQueen))
						{
							
							if(endFile.equals("a"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.a;
							}
							if(endFile.equals("b"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.b;
							}
							if(endFile.equals("c"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.c;
							}
							if(endFile.equals("d"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.d;
							}
							if(endFile.equals("e"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.e;
							}
							if(endFile.equals("f"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.f;
							}
							if(endFile.equals("g"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.g;
							}
							if(endFile.equals("h"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.h;
							}

							rp.pieceRank = endRank;						
						}
						if(rp.equals(this)) //attaching self-reference king to current king in question
						{
							currKing = (King)rp;

						}
					}

					

					//CHECKING KING ON CHECK IN FUTURE
					if(currKing.onCheck(currentBoard))
					{
						System.out.println("bishop in oncheck future can't be moved: king is still in check or you're putting king in check this way.");
						return true;
					}
					//if the move doesn't return true; valid move for bishop
					return false;
		}
		//TODO if piece found was a Knight----------------------------------------------------------------------------------
		if(currentKnight != null)
		{
			if(!(currentKnight.checkValidMove(currentBoard, move))) //if move is not valid
					{
						return true; //not a true check but returns illegal move as expected
					}
		//move the piece
		
				//Capture Check
				if(Capture.canCapture(currentBoard, move, currentKnight.white))
				{
					
					ArrayList<ReturnPiece> tempboard = Capture.takePiece(currentBoard, move);
					currentBoard = tempboard; //call this right before any move is made
				}
				else
				{
					System.out.println("oncheck future knight can capture fail");
					return true; //not a check but this will return an illegal move regardless as in an early capture check
				}

				
				//en passant limiter
				for (ReturnPiece rp : currentBoard)
					{
					if(rp.toString().contains("P")){
							Pawn tempPawn = (Pawn)rp;
							tempPawn.notFirstMoved();
							rp = tempPawn;
						}
					}

				//piece is updated
				King currKing = null;		
				for (ReturnPiece rp: currentBoard){
					if(rp.equals(currentKnight))
					{
						
						if(endFile.equals("a"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.a;
						}
						if(endFile.equals("b"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.b;
						}
						if(endFile.equals("c"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.c;
						}
						if(endFile.equals("d"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.d;
						}
						if(endFile.equals("e"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.e;
						}
						if(endFile.equals("f"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.f;
						}
						if(endFile.equals("g"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.g;
						}
						if(endFile.equals("h"))
						{
							rp.pieceFile = ReturnPiece.PieceFile.h;
						}

						rp.pieceRank = endRank;						
					}
					if(rp.equals(this)) //attaching self-reference king to current king in question
					{
						currKing = (King)rp;

					}
				}

				//CHECKING KING ON CHECK IN FUTURE
				if(currKing.onCheck(currentBoard))
				{
					System.out.println("knight in oncheck future can't be moved: king is still in check or you're putting king in check this way.");
					return true;
				}
				//if the move doesn't return true; valid move for bishop

			return false;
		}
		//TODO if piece found was a Rook:----------------------------------------------------------------------------------------------------------------
		if(currentRook != null)
		{
			if(!(currentRook.checkValidMove(currentBoard, move))) //if move is not valid
					{
						return true; //not a true check but returns illegal move as expected
					}
			//move the piece
					//Capture Check
					if(Capture.canCapture(currentBoard, move, currentRook.white))
					{
						ArrayList<ReturnPiece> tempboard = Capture.takePiece(currentBoard, move);
						currentBoard = tempboard; //call this right before any move is made
					}
					else
					{
						System.out.println("oncheck Rook oncheck cancapture fail");
						return true; //not a check but this will return an illegal move regardless as in an early capture check
					}

					//en passant limiter
					for (ReturnPiece rp : currentBoard)
						{
						if(rp.toString().contains("P")){
								Pawn tempPawn = (Pawn)rp;
								tempPawn.notFirstMoved();
								rp = tempPawn;
							}
						}

					//piece is updated
					King currKing = null;		
					for (ReturnPiece rp: currentBoard){
						if(rp.equals(currentRook))
						{
							if(endFile.equals("a"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.a;
							}
							if(endFile.equals("b"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.b;
							}
							if(endFile.equals("c"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.c;
							}
							if(endFile.equals("d"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.d;
							}
							if(endFile.equals("e"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.e;
							}
							if(endFile.equals("f"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.f;
							}
							if(endFile.equals("g"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.g;
							}
							if(endFile.equals("h"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.h;
							}

							rp.pieceRank = endRank;						
						}
						if(rp.equals(this)) //attaching self-reference king to current king in question
						{
							currKing = (King)rp;

						}
					}

					//CHECKING KING ON CHECK IN FUTURE
					if(currKing.onCheck(currentBoard))
					{
						System.out.println("bishop in oncheck future can't be moved: king is still in check or you're putting king in check this way.");
						return true;
					}
					//if the move doesn't return true; valid move for bishop
					return false;
		


		}
		//TODO if piece found was a King:----------------------------------------------------------------------------------------------------------------
		if(currentKing != null)
		{
			if(!(currentKing.checkValidMove(currentBoard, move))) //if move is not valid
				{
						return true; //not a true check but returns illegal move as expected
				}
				char startFile2 = move.substring(0, 1).toLowerCase().charAt(0);
        		int startRank2 = Integer.parseInt(move.substring(1, 2));
        		char endFile2 = move.substring(3, 4).toLowerCase().charAt(0);
        		int endRank2 = Integer.parseInt(move.substring(4, 5));

        		int fileDiff = Math.abs(endFile2 - startFile2);
        		int rankDiff = Math.abs(endRank2 - startRank2);

				if (this.onCheck(currentBoard) && fileDiff > 1 || rankDiff > 1){
					System.out.println("ooncheck future King rank diff failed");
					return true; //cannot castle to escape a check, returns false, which prints illegal move
				}

				//Capture Check
				if(Capture.canCapture(currentBoard, move, currentKing.white))
				{
					ArrayList<ReturnPiece> tempboard = Capture.takePiece(currentBoard, move);
					currentBoard = tempboard; //call this right before any move is made
				}
				else
				{
					System.out.println("oncheck King can capture failed");
					return true; //not a check but this will return an illegal move regardless as in an early capture check
				}

				for (ReturnPiece rp: currentBoard){
						if(rp.equals(currentKing))
						{
							
							if(endFile.equals("a"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.a;
							}
							if(endFile.equals("b"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.b;
							}
							if(endFile.equals("c"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.c;
							}
							if(endFile.equals("d"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.d;
							}
							if(endFile.equals("e"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.e;
							}
							if(endFile.equals("f"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.f;
							}
							if(endFile.equals("g"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.g;
							}
							if(endFile.equals("h"))
							{
								rp.pieceFile = ReturnPiece.PieceFile.h;
							}

							rp.pieceRank = endRank;
							
						}
					}
					
					if(currentKing.onCheck(currentBoard))
					{
						System.out.println("King in oncheck future can't be moved: king is still in check or you're putting king in check this way.");
						return true;
					}
					//if the move doesn't return true; valid move for king
					return false;
		} //ends all pieces moving.

		
			System.out.println("Somehow there was not a piece sent to onCheck future, pskipped simulated move, this shouldn't happen");
			return true; //will get illegal move
		
	}
	public boolean onCheckMate(ArrayList<ReturnPiece> currentBoard) //should be called after any piece moves
	{
		

		if (!onCheck(currentBoard)) { //if not in check, returns false, because it can't be in checkmate
			return false; 
		}
		ArrayList<String> escapeOptions = new ArrayList<String>();


		//checkmate logic
		//what we need to do: check every single possible move for every piece
		//for every piece, check valid move for every position
		//then check capture valid for every position
		//use the found valid move to form a move
		//use onCheck future on the board and the move
		//if true, then the move is added to arraylist String
		//if String isempty, return true. the king can't do anything to leave check.
		//therefore, checkmate

		if(this.white) //check all options for all white pieces
		{
			for(ReturnPiece rp : currentBoard)
			{
				if(rp.toString().substring(3,4).charAt(0) == 'W') //white king, white pieces
				{
					if(rp instanceof Rook)
					{
						Rook temp = (Rook)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}
					else if(rp instanceof Bishop)
					{

						Bishop temp = (Bishop)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();
					}
					else if(rp instanceof Knight)
					{
						Knight temp = (Knight)rp;
						//ArrayList<String> moves = 
						temp.possibleMoves();

						for(String move: Chess.possibleMoves)
						{
							//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
							//{
								
								if(!this.onCheck(currentBoard,move))
								{
									escapeOptions.add(move);
								}

								

							//}	
						}
						Chess.possibleMoves.clear();

					}
					else if(rp instanceof Queen){
						Queen temp = (Queen)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}
					else if(rp instanceof Pawn){
						Pawn temp = (Pawn)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();
					}
					else if(rp instanceof King){
						King temp = (King)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}

				}//if piece is "W" matches the white king in question
			}//piece in board iteration
		}
		else if(!this.white) //check all options for all black pieces
		{
			for(ReturnPiece rp : currentBoard)
			{
				if(rp.toString().substring(3,4).charAt(0) == 'B')
				{
					
					if(rp instanceof Rook)
					{
						Rook temp = (Rook)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}
					else if(rp instanceof Bishop)
					{

						Bishop temp = (Bishop)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();
					}
					else if(rp instanceof Knight)
					{
						Knight temp = (Knight)rp;
						//ArrayList<String> moves = 
						temp.possibleMoves();

						for(String move: Chess.possibleMoves)
						{
							//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
							//{
								
								if(!this.onCheck(currentBoard,move))
								{
									escapeOptions.add(move);
								}

								

							//}	
						}
						Chess.possibleMoves.clear();

					}
					else if(rp instanceof Queen){
						Queen temp = (Queen)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}
					else if(rp instanceof Pawn){
						Pawn temp = (Pawn)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();
					}
					else if(rp instanceof King){
						King temp = (King)rp;
						temp.possibleMoves();

							for(String move: Chess.possibleMoves)
							{
								//if(temp.checkValidMove(currentBoard, move)) //found a valid movement, valid capture, and this is no longer on check after
								//{		
									if(!this.onCheck(currentBoard,move))
									{
										escapeOptions.add(move);
									}	
								//}	
							}
							Chess.possibleMoves.clear();

					}
					
				}//if piece is "B" matches the black king in question
			}//piece in board iteration
		}
		//finish checking for escape options.

		//finish escape Option algorithm
		if(escapeOptions.isEmpty())
		{
			return true; //true checkmate, no escape options
		}
		else
		{
			for(String s : escapeOptions){
				System.out.println(s);
			}
			return false; //escape options don't exist
		}


	}
}

