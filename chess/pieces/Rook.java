package chess.pieces;

import java.util.ArrayList;

import boardGame.Board;
import boardGame.Position;
import chess.ChessException;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	private ArrayList<Position> straightMotion(boolean isItInTheColumn, int increment, Position currentPosition) throws ChessException {
		ArrayList<Position> positionsBoo = new ArrayList<>();
		if (Math.abs(increment) > 1) {
			throw new ChessException("A torre se movimenta de um em um apenas");
		}
		if (isItInTheColumn) {
			currentPosition.setValues(position.getRow(), position.getColumn() + increment);
		} else {
			currentPosition.setValues(position.getRow() + increment, position.getColumn());
		}
			while (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
				Position truePositon = new Position(currentPosition.getRow(), currentPosition.getColumn());
				positionsBoo.add(truePositon);
				if (isItInTheColumn) {
					currentPosition.setColumn(currentPosition.getColumn() + increment);
				} else {
					currentPosition.setRow(currentPosition.getRow()  + increment);
				}
			}
			if (canCaptureOpponentPiece(currentPosition)) {
				Position truePositon = new Position(currentPosition.getRow(), currentPosition.getColumn());
				positionsBoo.add(truePositon);
			}
			return positionsBoo;
	}

	@Override
	public boolean[][] possibleMoves() throws ChessException {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		ArrayList<Position> allPossibleMoves = new ArrayList<>();		
		//above
		ArrayList<Position> abovePositions =  straightMotion(false,-1,p);
		allPossibleMoves.addAll(abovePositions);
		//left
		ArrayList<Position> leftPositions = straightMotion(true,-1,p);
		allPossibleMoves.addAll(leftPositions);
		//right
		ArrayList<Position> rightPositions = straightMotion(true,1,p);
		allPossibleMoves.addAll(rightPositions);
		//below
		ArrayList<Position> belowPositions = straightMotion(false,1,p);
		allPossibleMoves.addAll(belowPositions);
	
		for (Position positionTrue : allPossibleMoves) {
			mat[positionTrue.getRow()][positionTrue.getColumn()] = true;
		}
		return mat;
	}
}