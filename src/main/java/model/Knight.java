package model;
import java.awt.Color;
public class Knight extends Piece {

    /**
     * constructor of Knight, inherited from abstract Piece constructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public Knight(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }

    /**
     * copy constructor for Knight
     * @param otherPiece
     */
    public Knight(Piece otherPiece) {
        super(otherPiece);
    }

    /**
     * determine whether the current move is valid by myPiece Knight
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

        if ((xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2)) {
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
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"knight";
    }


}
