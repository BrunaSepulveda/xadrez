import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;

public class Program {
  public static void main(String[] args){
    ChessMatch chessMatch = new ChessMatch();
    UI.printBoad(chessMatch.getChessPieces());
  }
}