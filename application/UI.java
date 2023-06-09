import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
		String currentPlayerPt = ((chessMatch.getCurrentPlayer()== Color.WHITE)? "branca" : "preta");
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turno: "+ chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {
			System.out.println("Aguardando jogada das peças de cor " + currentPlayerPt);
			if (chessMatch.getCheck()) {
				System.out.println("Peças de cor " + currentPlayerPt +" estão em CHEQUE!");
			}
		} else {
			System.out.println("Chequemate!");
			System.out.println("AS peças de cor " + currentPlayerPt + " venceram!");
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

  public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
    if (background) {
      System.out.print(ANSI_CYAN_BACKGROUND);
    }
    if (piece == null) {
            System.out.print("-"+ ANSI_RESET);
        }
      else {
				if (background) {
					if (piece.getColor() == Color.WHITE) {
						System.out.print(ANSI_WHITE + piece + ANSI_RESET);
					}
					else {
						System.out.print(ANSI_BLACK + piece + ANSI_RESET);
					}
				} else {
					if (piece.getColor() == Color.WHITE) {
						System.out.print( ANSI_BLACK + ANSI_WHITE_BACKGROUND + piece + ANSI_RESET);
					}
					else {
						System.out.print(ANSI_WHITE + ANSI_BLACK_BACKGROUND + piece + ANSI_RESET);
					}
				}
      }
      System.out.print(" ");
	}

	private static void printCapturedPieces(List<ChessPiece> captured){
		List<ChessPiece> whiteChessPieces = captured.stream().filter(piece -> piece.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> blackChessPieces = captured.stream().filter(piece -> piece.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Peças capturadas: ");
		System.out.println("Brancas: ");
		System.out.println(ANSI_BLACK + ANSI_WHITE_BACKGROUND + Arrays.toString(whiteChessPieces.toArray()) + ANSI_RESET);
		System.out.println("Pretas: ");
		System.out.println(ANSI_WHITE + ANSI_BLACK_BACKGROUND + Arrays.toString(blackChessPieces.toArray()) + ANSI_RESET);
	}
}