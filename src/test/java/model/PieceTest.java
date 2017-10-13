package model;
import java.awt.Color;
import junit.framework.TestCase;

public class PieceTest extends TestCase {
    public void testIsBoxPossible() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(false);
        Piece testPiece1 = testChessBoard.getPiece(3,1);
        assertEquals(false, testPiece1.isBoxPossible(testPiece1, testChessBoard, 3, 0, false));
        assertEquals(true, testPiece1.isBoxPossible(testPiece1, testChessBoard, 3, 2, false));
        assertEquals(false, testPiece1.isBoxPossible(testPiece1, testChessBoard, 3, 2, true));
        assertEquals(true, testPiece1.isBoxPossible(testPiece1, testChessBoard, 3, 6, true));
    }

    public void testIsLinearPossible() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(false);
        Piece testPiece1 = testChessBoard.getPiece(3,1);
        assertEquals(false, testPiece1.isLinearPossible(testPiece1, testChessBoard, 3, 0, false));
        assertEquals(true, testPiece1.isLinearPossible(testPiece1, testChessBoard, 3, 2, false));
        assertEquals(false, testPiece1.isLinearPossible(testPiece1, testChessBoard, 6, 1, false));
        assertEquals(false, testPiece1.isLinearPossible(testPiece1, testChessBoard, 0, 1, true));
    }

    public void testIsDiagnalPossible() throws Exception {
        ChessBoard testChessBoard = new ChessBoard(false);
        Piece testPiece1 = testChessBoard.getPiece(3,1);
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 3, 1, false));
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 4, 0, false));
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard,  2, 0, false));
        assertEquals(true, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 0, 4, false));
        assertEquals(true, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 7, 5, false));

        testChessBoard.moveChessPiece(5, 0, 5, 3);
        testChessBoard.moveChessPiece(1, 0, 1, 3);
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 4, 0, false));
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard,  2, 0, false));
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 0, 4, false));
        assertEquals(false, testPiece1.isDiagnalPossible(testPiece1, testChessBoard, 7, 4, false));
    }
}