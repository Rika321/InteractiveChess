package model;
import java.awt.Color;
import java.util.Vector;

public class Move {


    /**
     * default constructor for Move
     */
    public Move() {
    }

    /**
     * determine whether it's valid to move myPiece to destination position
     * with the option of consideration of check.
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @param conCheck
     * @return
     */
    public boolean isValidMove(Piece myPiece, ChessBoard myChessBoard, int destX, int destY, boolean conCheck) {
        if (!isInBound(destX, destY)) {
            return false;
        }
        if (conCheck && willbeChecked(myPiece, myChessBoard, destX, destY)){
            return false;
        }
        return myPiece.isValidMove(myPiece, myChessBoard,destX,destY,conCheck);
    }


    /**
     * find specific type of model with side color
     * @param type
     * @param sideColor
     * @param myChessBoard
     * @return
     *
     *
     *
     */
    public Vector<Piece> findPiece(char type, Color sideColor, ChessBoard myChessBoard){
        Vector<Piece> pieces = new Vector<Piece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (myChessBoard.isPiece(i, j) && myChessBoard.getPiece(i, j).pieceColor == sideColor
                        && myChessBoard.getPiece(i, j).type == type) {
                    pieces.add(myChessBoard.getPiece(i, j));
                }
            }
        }
        return pieces;
    }


    /**
     * check whether myColor side is being Checked
     * @param myChessBoard
     * @param myColor
     * @return
     */
    public boolean isBeingChecked(ChessBoard myChessBoard, Color myColor) {
        Color enemyColor = (myColor == Color.BLACK) ? Color.WHITE : Color.BLACK;
        Vector<Integer> allEnemyMoves = new Vector<Integer>();
        Vector<Piece> allEnemyPieces = myChessBoard.getAllPieces(enemyColor);
        Vector<Piece> kingVect = findPiece('K',myColor,myChessBoard);
        if (kingVect.isEmpty()) {
            return false;
        }
        for (int k = 0; k < allEnemyPieces.size(); k++) {
            Vector<Integer> currEnemyMoves = availableMoves(allEnemyPieces.get(k), myChessBoard,  false);
            for (int j = 0; j < currEnemyMoves.size(); j++) {
                allEnemyMoves.add(currEnemyMoves.get(j));
            }
        }
        if (allEnemyMoves.contains(kingVect.get(0).yPos * 8 + kingVect.get(0).xPos)) {
            return true;
        }
        return false;
    }

    /**
     * check whether myColor side has no model and places to move
     * @param myChessBoard
     * @param myColor
     * @return
     */
    private boolean noWhereMove(ChessBoard myChessBoard, Color myColor){
        Vector<Integer> allMyMoves = new Vector<Integer>();
        Vector<Piece>   allMyPieces = myChessBoard.getAllPieces(myColor);
        for (int k = 0; k < allMyPieces.size(); k++) {
            Vector<Integer> currMyMoves = availableMoves(allMyPieces.get(k), myChessBoard, true);
            for (int j = 0; j < currMyMoves.size(); j++) {
                allMyMoves.add(currMyMoves.get(j));
            }
        }
        if (allMyMoves.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * check whether current controller is a stalemate
     * @param myChessBoard
     * @param myColor
     * @return
     */
    public boolean isBeingStalemate(ChessBoard myChessBoard, Color myColor) {
        return noWhereMove(myChessBoard, myColor) && (!isBeingChecked(myChessBoard, myColor));
    }

    /**
     * check whether myColor side is lost
     * @param myChessBoard
     * @param myColor
     * @return
     */
    public boolean isCheckmate(ChessBoard myChessBoard, Color myColor) {
        //boolean isCheckmate = isBeingChecked(myChessBoard);
        return noWhereMove(myChessBoard, myColor) && (isBeingChecked(myChessBoard, myColor));

    }


    /**
     * check whether next move will cause check condition
     * @param myPiece
     * @param myChessBoard
     * @param destX
     * @param destY
     * @return
     */
    public boolean willbeChecked(Piece myPiece, ChessBoard myChessBoard, int destX, int destY) {
        int origX = myPiece.xPos;
        int origY = myPiece.yPos;
        ChessBoard testChessBoard = new ChessBoard(myChessBoard);
        testChessBoard.moveChessPiece(origX, origY, destX, destY);
        return isBeingChecked(testChessBoard, myPiece.pieceColor);
    }


    /**
     * get all available moves from myPiece
     * @param myPiece
     * @param myChessBoard
     * @param conCheck
     * @return
     */
    public Vector<Integer> availableMoves(Piece myPiece, ChessBoard myChessBoard, boolean conCheck){
        Vector<Integer> result = new Vector<Integer>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(myPiece, myChessBoard, i, j, conCheck)) {
                    result.add(i + 8*j);
                }
            }
        }
        return result;
    }


    /**
     * check whether the destination position is inside the chessboard
     * @param destX
     * @param destY
     * @return
     */
    public boolean isInBound(int destX, int destY){
        if (0 <=  destX && destX <= 7 &&
                0 <= destY && destY <= 7) {
            return true;
        }
        else {
            return false;
        }
    }



}
