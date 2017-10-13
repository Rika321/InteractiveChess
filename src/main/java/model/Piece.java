package model;
import java.awt.Color;

public abstract class Piece {

        public Color      pieceColor;
        public  int        xPos;
        public  int        yPos;
        public boolean    isFirst;
        public char       type;

    /**
     * constructor for Piece
     * @param pieceColor
     * @param xPos
     * @param yPos
     * @param isFirst
     * @param type
     */
        public Piece(Color pieceColor, int xPos, int yPos, Boolean isFirst, char type) {
            super();
            this.pieceColor = pieceColor;
            this.xPos = xPos;
            this.yPos = yPos;
            this.isFirst  = isFirst;
            this.type = type;
        }

    /**
     * copy constructor for Piece
     * @param otherPiece
     */
        public Piece(Piece otherPiece) {
            this.pieceColor = otherPiece.pieceColor;
            this.xPos       = otherPiece.xPos;
            this.yPos       = otherPiece.yPos;
            //this.captured   = otherPiece.captured;
            this.isFirst    = otherPiece.isFirst;
            this.type       = otherPiece.type;
        }


    /**
     * @return name of the piece
     * eg: black_bishop
     */
        public abstract String getFullName();

    /**
     * determine whether it's valid to move myPiece to destination position
     * with the option of consideration of being checked.
     * consider move rule of model only
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
        public abstract boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck);


    /**
     * check whether it's valid to set the current destination (only consider that box)
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param mustCapture
     * @return
     */
        public boolean isBoxPossible(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean mustCapture) {
            if (mustCapture){
                if (myChessBoard.isPiece(destX, destY) &&
                        myChessBoard.getPiece(destX,destY).pieceColor != myPiece.pieceColor) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (myChessBoard.isPiece(destX, destY) &&
                        myChessBoard.getPiece(destX,destY).pieceColor == myPiece.pieceColor) {
                    return false;
                }
                return true;
            }
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
        public boolean isLinearPossible(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean mustCapture){
            int origX = myPiece.xPos;
            int origY = myPiece.yPos;
            if (origX == destX) { // x axis equal
                if(origY== destY) { // same position, reject
                    return false;
                }
                else if(origY > destY) {
                    for (int j = origY - 1; j > destY; j --) {
                        if(myChessBoard.isPiece(origX, j)) {
                            return false;
                        }
                    }
                }
                else { //if (origY < destY) {
                    for (int j = origY + 1; j < destY; j ++) {
                        if(myChessBoard.isPiece(origX, j)) {
                            return false;
                        }
                    }
                }
                return isBoxPossible(myPiece, myChessBoard, destX, destY, mustCapture);
            }
            else if (origY == destY) { // y axis equal
                if(origX== destX) { // same position, reject
                    return false;
                }
                else if (origX < destX) {
                    for (int i = origX + 1; i < destX; i ++) {
                        if(myChessBoard.isPiece(i, origY)) {
                            return false;
                        }
                    }
                }
                else { // if (origX > destX)
                    for (int i = origX - 1; i > destX; i --) {
                        if(myChessBoard.isPiece(i, origY)) {
                            return false;
                        }
                    }
                }
                return isBoxPossible(myPiece, myChessBoard,destX, destY, mustCapture);
            }
            else {
                return false;
            }
        }


    /**
     * check whether it's valid to move in diagnal direction like a bishop
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param mustCapture
     * @return
     */
        public boolean isDiagnalPossible(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean mustCapture){
            int origX = myPiece.xPos;
            int origY = myPiece.yPos;
            if ( (destX - origX) == (destY - origY)) { // positive relationship
                if((destX - origX) == 0) { // same position, reject
                    return false;
                }
                else if (origX < destX) {
                    for (int k = 1; k < (destX - origX); k ++) {
                        if(myChessBoard.isPiece(origX + k, origY + k)) {
                            return false;
                        }
                    }
                }
                else { //if (currXPos > destXPos)
                    for (int k = -1; k > (destX - origX); k --) {
                        if(myChessBoard.isPiece(origX + k, origY + k)) {
                            return false;
                        }
                    }
                }
                return isBoxPossible(myPiece, myChessBoard, destX, destY, mustCapture);
            }
            else if ( (destX - origX) == (origY - destY)) { // negative relationship
                if((destX - origX) == 0) { // same position, reject
                    return false;
                }
                else if (origX < destX) {
                    for (int k = 1; k < (destX - origX); k ++) {
                        if(myChessBoard.isPiece(origX + k, origY -k)) {
                            return false;
                        }
                    }
                }
                else { //if (oirgX > destX)
                    for (int k = -1; k > (destX - origX); k --) {
                        if(myChessBoard.isPiece(origX + k, origY -k)) {
                            return false;
                        }
                    }
                }
                return isBoxPossible(myPiece, myChessBoard, destX, destY, mustCapture);
            }
            else {
                return false;
            }
        }

}
