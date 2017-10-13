package controller;
import java.awt.Color;
import junit.framework.TestCase;
import model.ChessBoard;

public class ChessBoardTest extends TestCase {
    ChessBoard myChessBoard = new ChessBoard(false);
    public void testCopyPiece() throws Exception {
        assertEquals(myChessBoard.getPiece(0,0).xPos,
                myChessBoard.copyPiece(myChessBoard.getPiece(0,0)).xPos);
        assertEquals(myChessBoard.getPiece(0,0).yPos,
                myChessBoard.copyPiece(myChessBoard.getPiece(0,0)).yPos);

    }

    public void testIsPiece() throws Exception {
        assertEquals(myChessBoard.isPiece(0,0), true);
    }

    public void testGetPiece() throws Exception {
        assertEquals(myChessBoard.getPiece(0,0).xPos,
                myChessBoard.copyPiece(myChessBoard.getPiece(0,0)).xPos);
    }

    public void testRemovePiece() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(myChessBoard);
        assertEquals(testChessBoard.removePiece(0,2), null);
        assertEquals(testChessBoard.removePiece(6,0).xPos,
                myChessBoard.getPiece(6,0).xPos);

    }

    public void testMoveChessPiece() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(myChessBoard);
        testChessBoard.moveChessPiece(7, 0, 7, 2);
        assertEquals( testChessBoard.getPiece(7,2).type,
                myChessBoard.getPiece(7,0).type);

    }

    public void testGetAllPieces() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(false);
        assertEquals(testChessBoard.getAllPieces(Color.BLACK).size(),
                testChessBoard.getAllPieces(Color.WHITE).size());

    }

}