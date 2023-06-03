package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

  public Pawn(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString(){
    return "P";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    int positiveOrNegativeMultiplier = (getColor() == Color.WHITE) ? -1 : 1;
    Position nextOnePosition = new Position(position.getRow() +(1*positiveOrNegativeMultiplier), position.getColumn());
    Position nextTwoPosition = new Position(position.getRow() +(2*positiveOrNegativeMultiplier), position.getColumn());
    Position firstDiagonal = new Position(position.getRow() +(1*positiveOrNegativeMultiplier), position.getColumn() -1);
    Position lastdiagonal = new Position(position.getRow() +(1*positiveOrNegativeMultiplier), position.getColumn() +1);
    if (emptyPlace(nextOnePosition)) {
      mat[nextOnePosition.getRow()][nextOnePosition.getColumn()] = true;
    }
    if (emptyPlace(nextOnePosition) && emptyPlace(nextTwoPosition) && getMoveCount() == 0) {
      mat[nextTwoPosition.getRow()][nextTwoPosition.getColumn()] = true;
    }
    if (canCaptureOpponentPiece(firstDiagonal)) {
      mat[firstDiagonal.getRow()][firstDiagonal.getColumn()] = true;
    }
    if (canCaptureOpponentPiece(lastdiagonal)) {
      mat[lastdiagonal.getRow()][lastdiagonal.getColumn()] = true;
    }
    return mat;
  }
  
}
