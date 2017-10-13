package model;
import java.awt.Color;
public class Pawn extends Piece{

    /**
     * constructor of Pawn, inherited from abstract Piece contructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public Pawn(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }
    /**
     * copy constructor for Pawn
     * @param otherPiece
     */
    public Pawn(Piece otherPiece) {
        super(otherPiece);
    }

    /**
     * determine whether the current move is valid by myPiece Pawn
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

        if(myPiece.pieceColor == Color.BLACK) {
            if(isLinearPossible(myPiece, myChessBoard, destX, destY, false)){
                return (!myChessBoard.isPiece(destX,destY)) && ((destY - origY == 2) && myPiece.isFirst) ||(!myChessBoard.isPiece(destX,destY) && (destY - origY == 1));
            }
            if(isDiagnalPossible(myPiece, myChessBoard, destX, destY, true)) {
                return (destY - origY == 1);
            }

        } else { //currPiece.pieceColor == Color.WHITE
            if(isLinearPossible(myPiece, myChessBoard, destX, destY, false)){
                return (!myChessBoard.isPiece(destX,destY)) && ((destY - origY == -2) && myPiece.isFirst) ||( !myChessBoard.isPiece(destX,destY) && (destY - origY == -1));
            }
            if(isDiagnalPossible(myPiece,myChessBoard, destX, destY, true)) {
                return (destY - origY == -1);
            }
        }
        return false;
    }

    /**
     * @return name of the piece
     * eg: black_bishop
     */
    @Override
    public String getFullName() {
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"pawn";
    }

}
