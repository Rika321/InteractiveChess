package controller;

import junit.framework.TestCase;
import model.ChessBoard;
import view.BoardView;

import java.awt.*;

public class FrameTest extends TestCase {

    GameControl game = new GameControl("aa", "oo", false);
    Frame test1 = new Frame(new ChessBoard(false), Color.WHITE);

    public void testCheckEnding() throws Exception {
    }

    public void testAnalyzeMove() throws Exception {
    }

    public void testSelectOrig() throws Exception {
    }

    public void testSelectDest() throws Exception {
    }

    public void testAvailableMoves() throws Exception {

    }

    public void testNextFrame() throws Exception {

        BoardView currBoardView = new BoardView(480,480,  new ChessBoard(false) );
        test1.origBut = currBoardView.getButton(4,6);
        test1.destBut = currBoardView.getButton(4,4);
        test1.nextFrame();
    }

}