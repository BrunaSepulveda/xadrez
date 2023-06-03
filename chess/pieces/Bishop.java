package chess.pieces;

import java.util.ArrayList;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

  private ArrayList<Position> positions(Integer r, Integer c){
    ArrayList<Position> pl= new ArrayList<>();
    Position p = new Position(0, 0);
    p.setValues(position.getRow() + r, position.getColumn() + c);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			pl.add(new Position(p.getRow(),p.getColumn()));
			p.setValues(p.getRow() + r, p.getColumn() + c);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			pl.add(new Position(p.getRow(),p.getColumn()));
		}
    return pl;
  }
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    ArrayList<Position> allPossibleMoves = new ArrayList<>();
    allPossibleMoves.addAll(positions(-1,-1));
    allPossibleMoves.addAll(positions(+1,+1));
    allPossibleMoves.addAll(positions(+1,-1));
    allPossibleMoves.addAll(positions(-1,+1));
    for (Position positionTrue : allPossibleMoves) {
			mat[positionTrue.getRow()][positionTrue.getColumn()] = true;
		}		
		return mat;
	}
}