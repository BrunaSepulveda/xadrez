package boardGame;

public abstract class Piece {
  protected Position position;
  private Board board;

  public Piece(Board board) {
    this.board = board;
    position = null;
  }

  protected Board getBoard() {
    return board;
  }

  public abstract boolean[][] possibleMoves();

  public boolean possibleMove(Position position){
    //hook methods usa implementação construida em uma subclasse para esta
    return possibleMoves()[position.getRow()][position.getColumn()];
  }

  public boolean isThereAnyPossibleMove(){
    boolean[][] mat = possibleMoves();
    for (boolean[] boolList : mat) {
      for (boolean bool : boolList) {
        if (bool) {
          return true;
        }
      }
    }
    return false;
  }
}
