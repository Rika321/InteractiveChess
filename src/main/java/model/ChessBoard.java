package model;
import java.awt.Color;
import java.util.Vector;

public class ChessBoard {
    private Piece[][] chessBoard;

    /**
     * default constructor fro ChessBoard
     */
    public ChessBoard(boolean isCustom){
        this.chessBoard = new Piece [8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.chessBoard[i][j] = addInitialChessPiece(i, j, isCustom);
            }
        }
    }

    /**
     * copy constructor for ChessBoard
     * @param otherBoard
     */
    public ChessBoard(ChessBoard otherBoard){
        this.chessBoard = new Piece [8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(otherBoard.isPiece(i, j)) {
                    this.chessBoard[i][j] = copyPiece(otherBoard.getPiece(i, j));
                } else {
                    this.chessBoard[i][j] = null;
                }
            }
        }
    }

    /**
     * determine whether otherBoard object is "approximately" equal to current ChessBoard
     * @param otherBoard
     * @return
     */
    public boolean isEqual(ChessBoard otherBoard){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(otherBoard.isPiece(i, j) ^ isPiece(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * make a deep copy of otherPiece
     * @param otherPiece
     * @return
     */
    public Piece copyPiece(Piece otherPiece){

        Piece result;
        if (otherPiece == null){
            System.out.println("copy model failed");
            return null;
        }
        switch (otherPiece.type) {
            case 'P':  result = new Pawn(otherPiece);
                break;
            case 'R':  result = new Rook(otherPiece);
                break;
            case 'N':  result = new Knight(otherPiece);
                break;
            case 'B':  result = new Bishop(otherPiece);
                break;
            case 'Q':  result = new Queen(otherPiece);
                break;
            case 'K':  result = new King(otherPiece);
                break;
            case 'C':  result = new Cannon(otherPiece);
                break;
            case 'E':  result = new Elephant(otherPiece);
                break;
            default: result = null;
                break;
        }
        return result;


    }


    /**
     * determine whether there exits a model on chessBoard with xPos and yPos
     * @param xPos
     * @param yPos
     * @return
     */
    public boolean isPiece(int xPos, int yPos) {
        if (chessBoard[xPos][yPos] == null) {
            return false;
        }
        return true;
    }

    /**
     * determine whether the current chessBoard is empty
     */
    public void emptyBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.chessBoard[i][j] = null;
            }
        }
    }

    /**
     * get the model with xPos and yPos from the current ChessBoard
     * @param xPos
     * @param yPos
     * @return
     */
    public Piece getPiece(int xPos, int yPos) {
        return chessBoard[xPos][yPos];
    }

    /**
     * add the model with xPos and yPos to the current chessBoard
     * @param xPos
     * @param yPos
     * @param addPiece
     */
    public void addPiece(int xPos, int yPos, Piece addPiece){
        chessBoard[xPos][yPos] = copyPiece(addPiece);
        chessBoard[xPos][yPos].xPos = xPos;
        chessBoard[xPos][yPos].yPos = yPos;
    }


    /**
     * remove the model with xPos and yPos to the current chessBoard
     * @param xPos
     * @param yPos
     * @return
     */
    public Piece removePiece(int xPos, int yPos) {
        if (isPiece(xPos,yPos)) {
            Piece result = getPiece(xPos, yPos);
            chessBoard[xPos][yPos] = null;
            return result;
        }
        else {
            //System.out.println("Remove Error! current Box is empty");
            return null;
        }
    }

    /**
     * move the model from original position to destination position
     * @param origX
     * @param origY
     * @param destX
     * @param destY
     */
    public void moveChessPiece(int origX, int origY, int destX, int destY) {
        Piece currChessPiece =  removePiece(origX, origY);
        chessBoard[destX][destY] = currChessPiece;
        currChessPiece.xPos = destX;
        currChessPiece.yPos = destY;
    }


    /**
     * get all pieces from one side
     * @param sideColor
     * @return
     */
    public Vector<Piece> getAllPieces(Color sideColor) {
        Vector<Piece> result = new Vector<Piece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isPiece(i, j) && getPiece(i, j).pieceColor == sideColor) {
                    result.add(getPiece(i, j));
                }
            }
        }
        return result;
    }


    /**
     * add the model to the chessBoard with their initial position
     * @param xPos
     * @param yPos
     * @return
     */
    private Piece addInitialChessPiece(int xPos, int yPos, boolean isCustom) {

        //Piece(Player currPlayer, Color pieceColor, int xPos, int yPos, boolean captured, char type)
        if (yPos == 2 && xPos == 0 && isCustom) {
            return new Cannon(Color.BLACK, xPos, yPos, true, 'C');
        }
        else if (yPos == 5 && xPos == 0 && isCustom) {
            return new Cannon(Color.WHITE, xPos, yPos, true, 'C');
        }
        else if (yPos == 2 && xPos == 7 && isCustom) {
            return new Elephant(Color.BLACK, xPos, yPos, true, 'E');
        }
        else if (yPos == 5 && xPos == 7 && isCustom) {
            return new Elephant(Color.WHITE, xPos, yPos, true, 'E');
        }
        else if (yPos == 0 && (xPos == 0 || xPos == 7))  {
            return new Rook(Color.BLACK, xPos, yPos,true, 'R');
        }
        else if (yPos == 0 && (xPos == 1 || xPos == 6)) {
            return new Knight(Color.BLACK, xPos, yPos, true, 'N');
        }
        else if (yPos == 0 && (xPos == 2 || xPos == 5)) {
            return new Bishop(Color.BLACK, xPos, yPos, true, 'B');
        }
        else if (yPos == 0 && xPos == 3) {
            return new Queen(Color.BLACK, xPos, yPos, true, 'Q');
        }
        else if (yPos == 0 && xPos == 4) {
            return new King(Color.BLACK, xPos, yPos, true, 'K');
        }
        else if (yPos == 1) {
            return new Pawn(Color.BLACK, xPos, yPos, true, 'P');
        }
//------------------------------------------------------------------------black
        else if (yPos == 7 && (xPos == 0 || xPos == 7)) {
            return new Rook(Color.WHITE, xPos, yPos, true, 'R');
        }
        else if (yPos == 7 && (xPos == 1 || xPos == 6)) {
            return new Knight(Color.WHITE, xPos, yPos, true, 'N');
        }
        else if (yPos == 7 && (xPos == 2 || xPos == 5)) {
            return new Bishop(Color.WHITE, xPos, yPos, true, 'B');
        }
        else if (yPos == 7 && xPos == 3) {
            return new Queen(Color.WHITE, xPos, yPos, true, 'Q');
        }
        else if (yPos == 7 && xPos == 4) {
            return new King(Color.WHITE, xPos, yPos, true, 'K');
        }
        else if (yPos == 6) {
            return new Pawn(Color.WHITE, xPos, yPos, true, 'P');
        }
//---------------------------------
        else {
            return null;
        }
    }
}
