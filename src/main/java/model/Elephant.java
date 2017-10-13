package model;

import java.awt.*;
import java.awt.Color;

public class Elephant extends Piece{
    /**
     * constructor of Elephant, inherited from abstract Piece constructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public Elephant(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }

    /**
     * copy constructor for Elephant
     * @param otherPiece
     */
    public Elephant(Piece otherPiece) {
        super(otherPiece);
    }

    /**
     * determine whether the current move is valid by myPiece Elephant
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
    @Override
    public boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck) {

        boolean result = isDiagnalPossible(myPiece, myChessBoard, destX, destY, false) &&
                (Math.abs(myPiece.xPos - destX) <= 2);
        return result;
    }

    /**
     * @return name of the piece
     * eg: black_bishop
     */
    @Override
    public String getFullName() {
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"elephant";
    }
}
