package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

  public Knight(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString(){
    return "N";
  }

  private boolean canMove(Position position){
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p == null || p.getColor() != getColor();
  }

  private ArrayList<Position> possitionsAroundKnight(){
    return new ArrayList<>(
      List.of(
        new Position(position.getRow() -1, position.getColumn() -2),
        new Position(position.getRow() -2, position.getColumn() -1),
        new Position(position.getRow() -2, position.getColumn() +1),
        new Position(position.getRow() -1, position.getColumn() +2),
        new Position(position.getRow() +1, position.getColumn() +2),
        new Position(position.getRow() +2, position.getColumn() +1),
        new Position(position.getRow() +2, position.getColumn() -1),
        new Position(position.getRow() +1, position.getColumn() -2)
      )
    );
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		ArrayList<Position> possitionsAround = possitionsAroundKnight();
    for (Position possitionAround : possitionsAround) {
      if (getBoard().positionExists(possitionAround) && canMove(possitionAround)) {
        mat[possitionAround.getRow()][possitionAround.getColumn()] = true;
      }
    }
    return mat;
  }
}
