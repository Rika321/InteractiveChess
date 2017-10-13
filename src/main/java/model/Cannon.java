package model;

import java.awt.*;
import java.awt.Color;

public class Cannon extends Piece{
    /**
     * constructor of Cannon, inherited from abstract Piece constructor
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
    public Cannon(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
        super(pieceColor, xPos, yPos, isFirst, type);
    }

    /**
     * copy constructor for Knight
     * @param otherPiece
     */
    public Cannon(Piece otherPiece) {
        super(otherPiece);
    }

    /**
     * determine whether the current move is valid by myPiece Cannon
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
    @Override
    public boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck) {

        boolean result = isJumpPossible(myPiece, myChessBoard, destX, destY, true) ||
                (!myChessBoard.isPiece(destX,destY) && isLinearPossible(myPiece, myChessBoard, destX, destY, false));
        return result;
    }

    /**
     * @return name of the piece
     * eg: black_bishop
     */
    @Override
    public String getFullName() {
        return (pieceColor == Color.BLACK ? "black_" : "white_")+"cannon";
    }

    /**
     * check whether it's valid to move in linear directions like a rook
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param mustCapture
     * @return
     */
    public boolean isJumpPossible(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean mustCapture){
        int origX = myPiece.xPos;
        int origY = myPiece.yPos;
        int pieceCount = 0;
        if (origX == destX) { // x axis equal
            if(origY== destY) { // same position, reject
                return false;
            }
            else if(origY > destY) {
                for (int j = origY - 1; j > destY; j --) {
                    if(myChessBoard.isPiece(origX, j)) {
                        pieceCount ++;
                    }
                }
            }
            else { //if (origY < destY) {
                for (int j = origY + 1; j < destY; j ++) {
                    if(myChessBoard.isPiece(origX, j)) {
                        pieceCount ++;
                    }
                }
            }
            return isBoxPossible(myPiece, myChessBoard, destX, destY, mustCapture) && (pieceCount ==1);
        }
        else if (origY == destY) { // y axis equal
            if(origX== destX) { // same position, reject
                return false;
            }
            else if (origX < destX) {
                for (int i = origX + 1; i < destX; i ++) {
                    if(myChessBoard.isPiece(i, origY)) {
                        pieceCount ++;
                    }
                }
            }
            else { // if (origX > destX)
                for (int i = origX - 1; i > destX; i --) {
                    if(myChessBoard.isPiece(i, origY)) {
                        pieceCount ++;
                    }
                }
            }
            return isBoxPossible(myPiece, myChessBoard, destX, destY, mustCapture) && (pieceCount ==1);
        }
        else {
            return false;
        }
    }
}
