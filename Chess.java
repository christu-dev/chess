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
	

	static boolean white_currently_checked;
	static boolean black_currently_checked;
	//check status




	enum Player { white, black }

	private static chess.Chess.Player currentPlayer = chess.Chess.Player.white;  //enum Player variable currentPlayer tracks player color
	
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


		//resign over everything
		if(move.equals("resign")){
			if(currentPlayer == chess.Chess.Player.white){
				play.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
				return play;
			}
			else if(currentPlayer == chess.Chess.Player.black){
				play.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				return play;
			}
		}


		String startFile = move.substring(0,1);
		int startRank = Integer.parseInt(move.substring(1,2));

		String endFile = move.substring(3,4);
		int endRank = Integer.parseInt(move.substring(4,5));



		//YOU CANNOT SKIP OR PASS A MOVE. MUST MOVE IF YOU CAN
		//If Player did not move
		if (startFile.equals(endFile) && startRank == endRank) {
            play.message = ReturnPlay.Message.ILLEGAL_MOVE;
			//System.out.println("did not move");
			return play;
        }



		//Illegal State: Out of board Bounds
		if(startFile.charAt(0) < 'a' || startFile.charAt(0) > 'h' || startRank < 1 || startRank > 8){
			play.message = ReturnPlay.Message.ILLEGAL_MOVE;
			//System.out.println("out of bounds");
			return play;
		}
		if(endFile.charAt(0) < 'a' || endFile.charAt(0) > 'h' || endRank < 1 || endRank > 8){
			play.message = ReturnPlay.Message.ILLEGAL_MOVE;
			//System.out.println("out of bounds");
			return play;
		}

		//have a suite of current ReturnPiece objects, then player validation/move validation/check validation is split based on
		Bishop currentBishop = null;
		Pawn currentPawn = null;
		King currentKing = null;
		Queen currentQueen = null;
		Knight currentKnight = null;
		Rook currentRook = null; 
		//set all possible current pieces to null, then if statements check for if(currentPiece != null)
		
	


		//COLOR VALIDATION BASED ON PIECE----------------------------------------------------------------------------------------
		for (ReturnPiece rp: play.piecesOnBoard){
	
			//testing: this WORKS AND arraylist is populated: 
			//System.out.println(rp.toString());
			//System.out.println(rp.toString().substring(0,2));
			//FIND TYPE OF PIECE AT ORIGIN

			//Bishop Check-------------
			if(move.substring(0, 2).equals(rp.toString().substring(0,2)) && rp instanceof Bishop) //find the piece and attach it to current piece
			{
				//check if the piece belongs to player
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WB)
				{
					play.message = null; //no play message state - game is ongoing
					currentBishop = (Bishop)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BB)
				{
					play.message = null;
					currentBishop = (Bishop)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("bishop picked wrong move ill move");
					return play;
				}
				
			}
			//Pawn check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Pawn) //find the piece and attach it to current piece
			{
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WP)
				{
					play.message = null; //no play message state - game is ongoing
					currentPawn = (Pawn)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BP)
				{
					play.message = null;
					currentPawn = (Pawn)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("pawn picked wrong move ill move");
					return play;
				}
			}
			//King check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof King) //find the piece and attach it to current piece
			{
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WK)
				{
					play.message = null; //no play message state - game is ongoing
					currentKing = (King)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BK)
				{
					play.message = null;
					currentKing = (King)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("king picked wrong move ill move");
					return play;
				}
			}
			//Queen check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Queen) //find the piece and attach it to current piece
			{
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WQ)
				{
					play.message = null; //no play message state - game is ongoing
					currentQueen = (Queen)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BQ)
				{
					play.message = null;
					currentQueen = (Queen)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("queen picked wrong move ill move");
					return play;
				}
			}
			//Knight check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Knight) //find the piece and attach it to current piece
			{
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WN)
				{
					play.message = null; //no play message state - game is ongoing
					currentKnight = (Knight)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BN)
				{
					play.message = null;
					currentKnight = (Knight)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("knight picked wrong move ill move");
					return play;
				} 
			}
			//Rook check------------
			if(rp.toString().substring(0,2).equals(move.substring(0, 2)) && rp instanceof Rook) //find the piece and attach it to current piece
			{
				if(currentPlayer == chess.Chess.Player.white && rp.pieceType == ReturnPiece.PieceType.WR)
				{
					play.message = null; //no play message state - game is ongoing
					currentRook = (Rook)rp;
					break; //leave loop
				}
				else if(currentPlayer == chess.Chess.Player.black && rp.pieceType == ReturnPiece.PieceType.BR)
				{
					play.message = null;
					currentRook = (Rook)rp;
					break; 
				}
				else
				{	//picked wrong color
					play.message = ReturnPlay.Message.ILLEGAL_MOVE;
					//System.out.println("Rook picked wrong move ill move");
					return play;
				}
			}
				
		} //end iterating through pieces for COLOR validation


		if(currentBishop == null && currentPawn == null && currentKing == null && currentQueen == null && currentKnight == null && currentRook == null){
			play.message = ReturnPlay.Message.ILLEGAL_MOVE;
			//System.out.println("PIECE DOESNT EXIST");
			return play;
		}

		/*		
		 *
		 *      Q. If the user is asks for a draw but the move they entered is illegal, is it a draw or an illegal move?
		 * 		A. Since the move happens before the draw, the move is executed first. So it is an illegal move, that is what
		 *		would be reported, not draw. Which means the player will get another chance to make a move.
		 * 
		 * 
		 * 		"YOU CAN ONLY DRAW AFTER A LEGAL MOVE"
		 * 
		 * 
		 * 		!!! For now, draw will take place even after checkmate/check and will overlap both.
		 * 
		 * 
		 * 		
		 * 		
		 * 	
		 * 		RIght after move VALIDATION is CHECK VALIDATION: run if both Kings are in Check using King.onCheck(currentBoard)
		 * 		Many check -> uncheck, uncheck -> check game states
		 * 		all covered by 10/12/2023 commits
		 * 
		 */

		//TODO if piece found was a Bishop:----------------------------------------------------------------------------------------------------------------
		if(currentBishop != null)
		{

			boolean canMove = currentBishop.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("bishop invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							//Capture Check
						
							if(Capture.canCapture(play.piecesOnBoard, move, currentBishop.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}
							//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentBishop))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

							//Capture Check
							
							if(Capture.canCapture(play.piecesOnBoard, move, currentBishop.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

							//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentBishop))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
	
					//move the piece
					//Capture Check
						
						if(Capture.canCapture(play.piecesOnBoard, move, currentBishop.white)){
							ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
							play.piecesOnBoard = tempboard; //call this right before any move is made
						}
						else
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							System.out.println("Cannot move there.");
							return play; //collision: cannot move to target piece. (maybe same color of moving piece)
						}

					//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}
							
					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentBishop))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}
							
			} //encapsulates move is valid

		}//finish piece
		//TODO if piece found was a Pawn:----------------------------------------------------------------------------------------------------------------
		else if(currentPawn != null)
		{
			boolean canMove = currentPawn.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("pawn invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							    ArrayList<ReturnPiece> tempboard = Capture.pawntakePiece(play.piecesOnBoard, move, currentPawn.white);
								play.piecesOnBoard = tempboard; //call this right before any move is made.

							//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
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
							
							ReturnPiece PromotionPiece = null;
							for (ReturnPiece rp: play.piecesOnBoard){
								if(rp.equals(currentPawn))
								{
									//update the respectiev piece in play.piecesOnBoard
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
							}
							//promotion if eligible
							if(PromotionPiece != null){
								play.piecesOnBoard.remove(currentPawn);
								play.piecesOnBoard.add(PromotionPiece);
							}

							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

								ArrayList<ReturnPiece> tempboard = Capture.pawntakePiece(play.piecesOnBoard, move, currentPawn.white);
								play.piecesOnBoard = tempboard; //call this right before any move is made.


								//en passant limiter
								for (ReturnPiece rp : play.piecesOnBoard){
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
							
							ReturnPiece PromotionPiece = null;
							for (ReturnPiece rp: play.piecesOnBoard){
								if(rp.equals(currentPawn))
								{
									//update the respectiev piece in play.piecesOnBoard
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
							}
							//promotion if eligible
							if(PromotionPiece != null){
								play.piecesOnBoard.remove(currentPawn);
								play.piecesOnBoard.add(PromotionPiece);
							}

							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								System.out.println("black moves and leaves check");
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
	
					//move the piece
					ArrayList<ReturnPiece> tempboard = Capture.pawntakePiece(play.piecesOnBoard, move, currentPawn.white);
					play.piecesOnBoard = tempboard; //call this right before any move is made.

                    //en passant limiter
								for (ReturnPiece rp : play.piecesOnBoard){
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

					
					System.out.println("normal move - pawn");

					ReturnPiece PromotionPiece = null;
					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentPawn))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					}
					//promotion if eligible
					if(PromotionPiece != null){
						play.piecesOnBoard.remove(currentPawn);
						play.piecesOnBoard.add(PromotionPiece);
					}

					
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}		
			}//encapsulates move is valid
		}
		//TODO if piece found was a King:----------------------------------------------------------------------------------------------------------------
		else if(currentKing != null)
		{
			boolean canMove = currentKing.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("king invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKing.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentKing))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKing.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentKing))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
	
					//move the piece

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKing.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentKing))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}	
			} //encapsulates move is valid
		}
		//TODO if piece found was a Queen:----------------------------------------------------------------------------------------------------------------
		else if(currentQueen != null)
		{
			boolean canMove = currentQueen.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("queen invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentQueen.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentQueen))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentQueen.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}
												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}
							
								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentQueen))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
					//move the piece

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentQueen.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}
	
					
					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentQueen))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}	
			} //encapsulates move is valid
		}
		//TODO if piece found was a Knight----------------------------------------------------------------------------------
		else if(currentKnight != null)
		{
			boolean canMove = currentKnight.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("knight invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKnight.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentKnight))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKnight.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentKnight))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
	
					//move the piece

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentKnight.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

					
					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentKnight))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}	
			} //encapsulates move is valid
		}
		//TODO if piece found was a Rook:----------------------------------------------------------------------------------------------------------------
		else if(currentRook != null)
		{
			boolean canMove = currentRook.checkValidMove(play.piecesOnBoard,move);
			if(canMove == false)
			{
				play.message = ReturnPlay.Message.ILLEGAL_MOVE;
				//System.out.println("rook invalid movement ill move");
				return play;
			}
			else //move is valid
			{
				play.message = null;
				King currWK = null;
				King currBK = null;
				for(ReturnPiece rp: play.piecesOnBoard)
				{
					if(rp.pieceType == ReturnPiece.PieceType.WK){ //attach white king to current White King
						currWK = (King)rp;
					}
					if(rp.pieceType == ReturnPiece.PieceType.BK){ //attach black king to current Black King
						currBK = (King)rp;
					}

				}

				white_currently_checked = currWK.onCheck(play.piecesOnBoard); //returns true if white is checked before move
				black_currently_checked = currBK.onCheck(play.piecesOnBoard); //returns true if black is checked before move

				if(white_currently_checked || black_currently_checked) //currently there exists a check before move
				{
					if(currentPlayer == chess.Chess.Player.white && white_currently_checked) //if currently check and player is white
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently white check - white moves and fails to leave check
						} 
						else //white moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							white_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentRook.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}
							
								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentRook))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currBK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
								return play; //white leaves check and simultaneously checkmates black
							}
							else if(currBK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								black_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.black; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.black; //player switches
								play.message = null;
								return play; //white moves and leaves check
							}
						}
					}
					else if(currentPlayer == chess.Chess.Player.black && black_currently_checked) //if currently check and player is black
					{
						if(currBK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //currently black check - black moves and fails to leave check
						} 
						else //black moves and successfully leaves check (future move doesn't result in check)
						{
							//move the piece
							black_currently_checked = false; //leave check

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentRook.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

								for (ReturnPiece rp: play.piecesOnBoard){
									if(rp.equals(currentRook))
									{
										//update the respectiev piece in play.piecesOnBoard
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
							if(currWK.onCheckMate(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								return play; //black leaves check and simultaneously checkmates white
							}
							else if(currWK.onCheck(play.piecesOnBoard)){
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								white_currently_checked = true;
								play.message = ReturnPlay.Message.CHECK;
								currentPlayer = chess.Chess.Player.white; //player switches
								return play;
							}
							else //not check/checkmate after move
							{
								if(move.contains("draw?")) //draw overlaps everything
								{
									play.message = ReturnPlay.Message.DRAW;
									return play;
								}
								currentPlayer = chess.Chess.Player.white; //player switches
								play.message = null;
								return play; //black moves and leaves check
							}
						}
					}	

				}
				else  //not currently checked before the player moves move
				{
					//checks if moving puts own King in danger
					if(currentPlayer == chess.Chess.Player.white)
					{
						if(currWK.onCheck(play.piecesOnBoard, move))//simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)
						} 
					}
					else if(currentPlayer == chess.Chess.Player.black)
					{
						if(currBK.onCheck(play.piecesOnBoard, move)) //simulates the move in future
						{
							play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
							return play; //Not currently check - white moves and puts own king in danger (illegal play)

						} 
					}
	
					//move the piece

							//Capture Check

							if(Capture.canCapture(play.piecesOnBoard, move, currentRook.white)){
								ArrayList<ReturnPiece> tempboard = Capture.takePiece(play.piecesOnBoard, move);
								play.piecesOnBoard = tempboard; //call this right before any move is made
							}
							else
							{
								play.message = ReturnPlay.Message.ILLEGAL_MOVE; 
								System.out.println("Cannot move there.");
								return play; //collision: cannot move to target piece. (maybe same color of moving piece)
							}

												//en passant limiter
							for (ReturnPiece rp : play.piecesOnBoard){
								if(rp.toString().contains("P")){
									Pawn tempPawn = (Pawn)rp;
									tempPawn.notFirstMoved();
									rp = tempPawn;
								}
							}

					for (ReturnPiece rp: play.piecesOnBoard){
						if(rp.equals(currentRook))
						{
							//update the respectiev piece in play.piecesOnBoard
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
					//checks checkmate after piece is moved
					if(currBK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
						return play;
					}
					if(currWK.onCheckMate(play.piecesOnBoard))
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
						return play;
					}
					//checks check after piece is moved
					if(currWK.onCheck(play.piecesOnBoard) || currBK.onCheck(play.piecesOnBoard)) 
					{
						play.message = ReturnPlay.Message.CHECK;
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						//Not currently check -> white or black moves and puts white or black in check
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							return play;
						} 
					}					
					else //not in check after move
					{
						if(move.contains("draw?")) //draw overlaps everything
						{
							play.message = ReturnPlay.Message.DRAW;
							return play;
						}
						//SWITCH COLORS and RETURN PLAY
						if(currentPlayer == chess.Chess.Player.white)
						{
							currentPlayer = chess.Chess.Player.black;
							play.message = null;
							return play;
						}
						if(currentPlayer == chess.Chess.Player.black)
						{
							currentPlayer = chess.Chess.Player.white;
							play.message = null;
							return play;
						}
					}

				}	
			}//encapsulates move is valid
		}
				

		System.out.println("Nothing changed, some if statement booted out the game state to the end.");
		return play;
	}
	//end of chess.Chess.play

	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		//make an array list to hold all the new pieces
		//somehow set player to white to start
		currentPlayer = chess.Chess.Player.white; 

		white_currently_checked = false;
		black_currently_checked = false;

		play = new ReturnPlay();
		play.piecesOnBoard = new ArrayList<ReturnPiece>();
		play.piecesOnBoard.clear();
		play.message = null;

	
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

