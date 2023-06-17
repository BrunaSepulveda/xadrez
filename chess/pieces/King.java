package chess.pieces;


import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

  private boolean moveKing(Position position) {
    return getBoard().positionExists(position) && canMove(position);
  }  
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		mat[p.getRow()][p.getColumn()] = moveKing(p);

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		mat[p.getRow()][p.getColumn()] = moveKing(p);

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);
		

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);

		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);

		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);

		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);
		
		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		mat[p.getRow()][p.getColumn()] = moveKing(p);
		

		// #specialmove castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #specialmove castling kingside rook
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// #specialmove castling queenside rook
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		
		return mat;
	}
}