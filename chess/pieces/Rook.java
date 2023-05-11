package chess.pieces;

import java.util.ArrayList;

import boardGame.Board;
import boardGame.Position;
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

	private ArrayList<int[]> straightMotion(boolean isItInTheColumn, boolean isItSum, Position currentPosition){
		ArrayList<int[]> positionsBoo = new ArrayList<int[]>();
		if (isItInTheColumn) {
			currentPosition.setValues(position.getRow(), position.getColumn() + (isItSum ? 1 : -1));
		} else {
			currentPosition.setValues(position.getRow() + (isItSum ? 1 : -1), position.getColumn());
		}
			while (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
				int [] truePositon = {currentPosition.getRow(), currentPosition.getColumn()};
				positionsBoo.add(truePositon);
				if (isItInTheColumn) {
					currentPosition.setColumn(currentPosition.getColumn() + (isItSum ? 1 : -1));
				} else {
					currentPosition.setRow(currentPosition.getRow()  + (isItSum ? 1 : -1));
				}
			}
			if (getBoard().positionExists(currentPosition) && isThereOpponentPiece(currentPosition)) {
				int [] truePositon = {currentPosition.getRow(), currentPosition.getColumn()};
				positionsBoo.add(truePositon);
			}
			return positionsBoo;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		ArrayList<int[]> allPossibleMoves = new ArrayList<>();
		//above
		ArrayList<int[]> abovePositions =  straightMotion(false,false,p);
		allPossibleMoves.addAll(abovePositions);
		//left
		ArrayList<int[]> leftPositions = straightMotion(true,false,p);
		allPossibleMoves.addAll(leftPositions);
		//right
		ArrayList<int[]> rightPositions = straightMotion(true,true,p);
		allPossibleMoves.addAll(rightPositions);
		//below
		ArrayList<int[]> belowPositions = straightMotion(false,true,p);
		allPossibleMoves.addAll(belowPositions);

		for (int[] positionTrue : allPossibleMoves) {
			mat[positionTrue[0]][positionTrue[1]] = true;
		}
		return mat;
	}
}