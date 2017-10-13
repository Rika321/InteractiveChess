package model;
import java.awt.Color;

public class King extends Piece{

    /**
     * constructor of King, inherited from abstract Piece constructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public King(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }

    /**
     * copy constructor for King
     * @param otherPiece
     */
    public King(Piece otherPiece) {
        super(otherPiece);
    }

    /**
     * determine whether the current move is valid by myPiece King
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
    @Override
    public boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck) {
        int origX = myPiece.xPos;
        int origY = myPiece.yPos;
        int xDiff = Math.abs(origX - destX);
        int yDiff = Math.abs(origY - destY);
        if ((xDiff == 0 && yDiff == 1) || (xDiff == 1 && yDiff == 0) || (xDiff == 1 && yDiff == 1)) {
            return isBoxPossible(myPiece, myChessBoard, destX, destY, false);
        }
        return false;
    }

    /**
     * @return name of the piece
     * eg: black_bishop
     */
    @Override
    public String getFullName() {
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"king";
    }
}
