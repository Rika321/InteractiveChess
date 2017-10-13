package view;

import controller.GameControl;
import junit.framework.TestCase;
import model.ChessBoard;

public class BoardViewTest extends TestCase {

    public BoardView bv1 = new  BoardView(450, 450, new ChessBoard(false));
    public BoardView bv2 = new  BoardView(450, 450, new ChessBoard(true));

    public void testAddBoard() throws Exception {
        //bv1.boardRedraw(new ChessBoard())

    }

    public void testCreateBox() throws Exception {
    }

    public void testBoardRedraw() throws Exception {
        bv1.boardRedraw(new ChessBoard(false));
    }

    public void testAddMouseControl() throws Exception {
        GameControl game = new GameControl("aa", "oo", false);
        bv1.addMouseControl(game);

    }

    public void testHighLightBut() throws Exception {
    }

    public void testUnhighLightBut() throws Exception {
    }

    public void testAddElement() throws Exception {
    }

}