package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

  public King(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString(){
    return "K";
  }

  private boolean canMove(Position position){
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p == null || p.getColor() != getColor();
  }

  private ArrayList<Position> possitionsAroundKing(){
    return new ArrayList<>(
      List.of(
        new Position(position.getRow() -1, position.getColumn()),
        new Position(position.getRow() +1, position.getColumn()),
        new Position(position.getRow(), position.getColumn() -1),
        new Position(position.getRow(), position.getColumn() +1),
        new Position(position.getRow() -1, position.getColumn() -1),
        new Position(position.getRow() -1, position.getColumn() +1),
        new Position(position.getRow() +1, position.getColumn() -1),
        new Position(position.getRow() +1, position.getColumn() +1)
      )
    );
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		ArrayList<Position> possitionsAround = possitionsAroundKing();
    for (Position possitionAround : possitionsAround) {
      if (getBoard().positionExists(possitionAround) && canMove(possitionAround)) {
        mat[possitionAround.getRow()][possitionAround.getColumn()] = true;
      }
    }
    return mat;
  }
}
