package model;
import java.awt.Color;

import junit.framework.TestCase;

public class MoveTest extends TestCase {
    public void testIsValidMove() throws Exception {
        Move test = new Move();
        ChessBoard copyChessBoard = new ChessBoard(false);
        ChessBoard testChessBoard = new ChessBoard(false);
        testChessBoard.emptyBoard();
       //rook
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(0,0));
        assertEquals(true,testChessBoard.isPiece(4,4));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 4, 0, true));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 4, true));

        //knight
        testChessBoard.emptyBoard();
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(1,0));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 3, 2, true));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 4, true));

        //bishop
        testChessBoard.emptyBoard();
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(2,0));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 0, true));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 4, true));

        //queen
        testChessBoard.emptyBoard();
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(3,0));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 2, 1, true));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 0, true));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 4, true));

        //king
        testChessBoard.emptyBoard();
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(4,0));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 5, 4, true));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 0, true));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 0, 4, true));

        //pawn
        testChessBoard = new ChessBoard(false);
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(0,1));
        Piece testPawn = testChessBoard.getPiece(4,4);

        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 4, 6, true));
        assertEquals(true, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 4, 5, true));
        assertEquals(false, test.isValidMove(testChessBoard.getPiece(4,4), testChessBoard, 3, 5, true));

    }

    public void testIsBeingChecked() throws Exception {
        Move test = new Move();
        ChessBoard copyChessBoard = new ChessBoard(false);
        ChessBoard testChessBoard = new ChessBoard(false);
        testChessBoard.emptyBoard();
        //rook
        testChessBoard.addPiece(4,4, copyChessBoard.getPiece(0,0));
        testChessBoard.addPiece(4,7, copyChessBoard.getPiece(4,7));
        assertEquals(true,test.isBeingChecked(testChessBoard, Color.WHITE));
    }

    public void testIsBeingStalemate() throws Exception {
        Move test = new Move();
        ChessBoard copyChessBoard = new ChessBoard(false);
        ChessBoard testChessBoard = new ChessBoard(false);
        testChessBoard.emptyBoard();
        //rook
        testChessBoard.addPiece(6,5, copyChessBoard.getPiece(3,0));
        testChessBoard.addPiece(7,7, copyChessBoard.getPiece(4,7));
        assertEquals(true,test.isBeingStalemate(testChessBoard, Color.WHITE));
    }

    public void testIsCheckmate() throws Exception {
        Move test = new Move();
        ChessBoard copyChessBoard = new ChessBoard(false);
        ChessBoard testChessBoard = new ChessBoard(false);
        testChessBoard.emptyBoard();
        //rook
        testChessBoard.addPiece(6,6, copyChessBoard.getPiece(3,0));
        testChessBoard.addPiece(5,5, copyChessBoard.getPiece(3,0));
        testChessBoard.addPiece(7,7, copyChessBoard.getPiece(4,7));
        testChessBoard.addPiece(0,5, copyChessBoard.getPiece(0,7));
        assertEquals(true,test.isCheckmate(testChessBoard, Color.WHITE));

    }

}