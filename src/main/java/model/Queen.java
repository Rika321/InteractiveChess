package model;
import java.awt.Color;

public class Queen extends Piece{

    /**
     * constructor of Queen, inherited from abstract Piece constructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public Queen(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }

    /**
     * copy constructor for Queen
     * @param otherPiece
     */
    public Queen(Piece otherPiece) {
        super(otherPiece);
    }


    /**
     * determine whether the current move is valid by myPiece Queen
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
    @Override
    public boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck) {
        if (isDiagnalPossible(myPiece, myChessBoard, destX, destY, false)
                || isLinearPossible(myPiece, myChessBoard, destX, destY, false)) {
            return true;
        }
        return false;
    }

    /**
     * @return name of the piece
     * eg: black_bishop
     */
    @Override
    public String getFullName() {
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"queen";
    }
}
