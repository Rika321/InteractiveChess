package controller;

import junit.framework.TestCase;
import view.PieceView;

import javax.swing.*;
import java.awt.*;

public class GameControlTest extends TestCase {
    GameControl game = new GameControl("aa", "oo", false);
    public void testCreateMenuBar() throws Exception {
    }

    public void testCreateAboutMenu() throws Exception {
    }

    public void testCreateTruceMenu() throws Exception {
    }

    public void testCreateEditMenu() throws Exception {
    }

    public void testMouseClicked() throws Exception {
    }

    public void testClickPiece() throws Exception {
    }

    public void testSelectButHelp() throws Exception {
        PieceView test = game.currBoardView.getButton(4,6);
        game.selectButHelp(test);
    }

    public void testUnSelectButHelp() throws Exception {
        game.seleBtn = game.currBoardView.getButton(4,6);
        game.unSelectButHelp();
    }

    public void testTruceHelp() throws Exception {
        game.truceHelp(true);
    }

    public void testUndoHelper() throws Exception {
        game.currFrame.origBut = game.currBoardView.getButton(4,6);
        game.currFrame.destBut = game.currBoardView.getButton(4,4);
        game.nextStepHelper();
        game.undoHelper();
    }

    public void testRedoHelper() throws Exception {
        game.currFrame.origBut = game.currBoardView.getButton(4,6);
        game.currFrame.destBut = game.currBoardView.getButton(4,4);
        game.nextStepHelper();
        game.undoHelper();
        game.redoHelper();
    }

    public void testForfeitHelp() throws Exception {
        game.forfeitHelp();
    }

    public void testNextStepHelper() throws Exception {
        game.currFrame.origBut = game.currBoardView.getButton(4,6);
        game.currFrame.destBut = game.currBoardView.getButton(4,4);
        game.nextStepHelper();
    }

    public void testSwitchSideHelper() throws Exception {
        game.switchSideHelper(Color.WHITE);
    }

    public void testCheckEnding() throws Exception {
        game.checkEnding();
    }

    public void testHighLightAvaiPosi() throws Exception {
        game.highLightAvaiPosi();
    }

    public void testUnhighLightAvaiPosi() throws Exception {
        game.unhighLightAvaiPosi();
    }

}