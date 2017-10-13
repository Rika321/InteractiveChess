package utility;
import java.awt.Color;
import model.ChessBoard;
import model.Move;
import model.Piece;

import java.util.Vector;

/**
 * print the current ChessBoard
 */
public class PrintFormat {
    public PrintFormat() {
    }
    public static void printBoard(ChessBoard myChessBoard) {
        int diff = 'a'- 'A';
        for (int i = -1; i < 8; i++) {
            for (int j = -1; j < 8; j++) {
                if (i == -1 && j == -1){
                    System.out.print(0);
                }
                else if (i == -1 || j == -1){
                    System.out.print((i==-1)?j:i);
                }
                else if (myChessBoard.isPiece(j, i)) {
                    Piece currPiece = myChessBoard.getPiece(j, i);
                    if (currPiece.pieceColor == Color.BLACK) { //black
                        System.out.print(currPiece.type);
                    }
                    else {
                        System.out.print((char)(currPiece.type+diff));
                    }
                }
                else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     * print the chessboard with all available positions of current model by star("*")
     * @param myPiece
     * @param currBoard
     */
    public void printAvalPosi(Piece myPiece, ChessBoard currBoard) {

        Move currMove = new Move();
        Vector<Integer> availableMoves = currMove.availableMoves(myPiece, currBoard, true);
        int diff = 'a'- 'A';
        for (int i = -1; i < 8; i++) {
            for (int j = -1; j < 8; j++) {
                if (i == -1 && j == -1){
                    System.out.print(0);
                }
                else if (i == -1 || j == -1){
                    System.out.print((i==-1)?j:i);
                }
                else if (availableMoves.contains(8*i +j)) {
                    System.out.print("*");
                }
                else if (currBoard.isPiece(j, i)) {
                    Piece nowPiece = currBoard.getPiece(j, i);
                    if (nowPiece.pieceColor == Color.WHITE) {
                        System.out.print((char)(nowPiece.type+diff));
                    }
                    else  { //black
                        System.out.print(nowPiece.type);
                    }
                }
                else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }


}
