package controller;

import model.ChessBoard;
import model.Move;
import model.Piece;
import model.Player;
import utility.PrintFormat;
import utility.Status;
import view.BoardView;
import view.PieceView;
import view.StatusView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GameControl implements MouseListener {
    public JFrame figure;
    public JMenuBar menuBar;
    public Status result;
    public PieceView seleBtn;
    public Frame     currFrame;

    public StatusView currStatusView;
    public BoardView currBoardView;
    public ChessBoard initBoard;
    public boolean specialMode;


    public PrintFormat PF = new PrintFormat();

    public Vector<Frame> frameList;
    public           int currListIndex;

//    public String play1Name;
//    public String play2Name;
    public Player player1;
    public Player player2;


    public GameControl(String play1Name, String play2Name, boolean visible){
        JFrame figure = new JFrame("Chess");
        this.figure = figure;
        this.player1  = new Player(0, play1Name, Color.WHITE);
        this.player2  = new Player(0, play2Name, Color.BLACK);


        this.initBoard     = new ChessBoard(true);
        this.currFrame     = new Frame(this.initBoard , Color.WHITE);
        this.currBoardView = new BoardView(480,480,  this.initBoard );
        this.currBoardView.addMouseControl(this);

        this.menuBar = createMenuBar();
        this.currStatusView = new StatusView(player1,player2);



        figure.setLayout(new BorderLayout());
        figure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        figure.setSize(1200,700);
        figure.add(this.currBoardView,  BorderLayout.CENTER);
        figure.add(currStatusView , BorderLayout.LINE_END);
        figure.setJMenuBar(menuBar);
        figure.setVisible(visible);


        frameList = new Vector<Frame>();
        frameList.add(currFrame);
        currListIndex = 0;
        seleBtn = null;
        specialMode = false;
    }


    public JMenuBar createMenuBar() {
        JMenuBar myMenu =  new JMenuBar();

        JMenu truceMenu  = createTruceMenu();
        JMenu editeMenu  = createEditMenu();
        JMenu aboutMenu  = createAboutMenu(); //new JMenu("About");

        myMenu.add(truceMenu);
        myMenu.add(editeMenu);
        myMenu.add(aboutMenu);
        return myMenu;
    }

    public JMenu createAboutMenu(){
        JMenu aboutMenu  = new JMenu("About");
        JMenuItem aboutGame = new JMenuItem("about the game");
        aboutMenu.add(aboutGame);
        return aboutMenu;
    }


    public JMenu createTruceMenu(){
        JMenu truceMenu  = new JMenu("Truce");
        JMenuItem stdGame = new JMenuItem("new standard game");
        JMenuItem speGame = new JMenuItem("new special game");

        stdGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truceHelp(false);
            }
        });

        speGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truceHelp(true);
            }
        });

        truceMenu.add(stdGame);
        truceMenu.add(speGame);
        return truceMenu;
    }

    public JMenu createEditMenu(){
        JMenu editMenu  = new JMenu("Edit");

        JMenuItem forfeit  = new JMenuItem("forfeit");
        JMenuItem undo     = new JMenuItem("undo");
        JMenuItem redo     = new JMenuItem("redo");
        JMenuItem free     = new JMenuItem("free");

        forfeit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forfeitHelp();
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoHelper();
            }
        });

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redoHelper();
            }
        });

        free.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currStatusView.updateStatus(Status.FREE);
                specialMode = true;
            }
        });


        editMenu.add(forfeit);
        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(free);
        //JMenuItem undo    = new JMenuItem("Undo");
        return editMenu;
    }



    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        JButton currBtn = (JButton) e.getSource();
        if(specialMode){
            specialClickPiece(currBtn);
            currStatusView.updateStatus(Status.DEFAULT);
        } else{
            clickPiece(currBtn);
        }
    }


    /**
     * add some reaction for pieces on chessboard
     * @param currBtn
     */
    public void specialClickPiece(JButton currBtn){
        if(seleBtn == null && currFrame.selectOrig((PieceView)currBtn) == Status.FAIL){
            return;
        }
        else if(seleBtn == null && currFrame.selectOrig((PieceView)currBtn) == Status.SUCCESS){
            //System.out.println("selected:"+currFrame.currBoard.getPiece(currFrame.origX,currFrame.origY).getFullName());
            seleBtn = (PieceView)currBtn;
            return;
        }
        else if(seleBtn != null && currFrame.selectDest((PieceView)currBtn) == Status.FAIL){
            return;
        }
        else if(seleBtn != null && currFrame.selectDest((PieceView)currBtn) == Status.SUCCESS){
            nextStepHelper();
            checkEnding();
            specialMode = false;
            return;
        }
        else {return;}
    }



    /**
     * add some reaction for pieces on chessboard
     * @param currBtn
     */
    public void clickPiece(JButton currBtn){
        if(seleBtn == null && currFrame.selectOrig((PieceView)currBtn) == Status.FAIL){
            currStatusView.updateStatus(Status.DEFAULT);
            return;
        }
        else if(seleBtn == null && currFrame.selectOrig((PieceView)currBtn) == Status.SUCCESS){
            currStatusView.updateStatus(Status.DEFAULT);
            System.out.println("selected:"+currFrame.currBoard.getPiece(currFrame.origX,currFrame.origY).getFullName());
            selectButHelp((PieceView)currBtn);
            return;
        }
        else if(seleBtn != null && currFrame.selectDest((PieceView)currBtn) == Status.FAIL){
            currStatusView.updateStatus(Status.INVALID_DESTINATION);
            unSelectButHelp();
            return;
        }
        else if(seleBtn != null && currFrame.selectDest((PieceView)currBtn) == Status.SUCCESS){
            unSelectButHelp();
            this.result = currFrame.analyzeMove(currFrame.currBoard, currFrame.currColor);
            if (this.result !=  Status.VALID_MOVE){
                currStatusView.updateStatus(result);
                System.out.println(result);
                return;
            } else {
                currStatusView.updateStatus(result);
                nextStepHelper();
                checkEnding();
                return;
            }
        }
        else {return;}
    }


    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    private void updateBoardView(ChessBoard updateBoard){
        currBoardView.boardRedraw( updateBoard);
        currBoardView.addMouseControl(this);
        //figure.setContentPane(currBoardView);
        figure.setVisible(true);
    }

    /**
     * add some reaction for pieces on chessboard
     * @param currBtn
     */
    public Status selectButHelp(PieceView currBtn){

        currStatusView.updateIcon(currFrame.currBoard.getPiece(currFrame.origX,currFrame.origY).getFullName());
        seleBtn = currBtn;
        currBoardView.highLightBut(currFrame.origX, currFrame.origY, Color.BLUE);
        highLightAvaiPosi();
        return Status.SUCCESS;
    }

    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status unSelectButHelp(){
        currStatusView.updateIcon("empty");
        currBoardView.unhighLightBut(currFrame.origX, currFrame.origY);
        unhighLightAvaiPosi();
        seleBtn = null;
        return Status.SUCCESS;
    }

    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status truceHelp(boolean isCustom){
        unSelectButHelp();
        ChessBoard newBoard = new ChessBoard(isCustom);
        updateBoardView(newBoard);
        currFrame     = new Frame(newBoard, Color.WHITE);
        currStatusView.updateColor(Color.WHITE);
        frameList     = new Vector<Frame>();
        frameList.add(currFrame);
        currListIndex = 0;
        switchSideHelper(null);
        return Status.SUCCESS;
    }


    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status undoHelper(){
        unSelectButHelp();
        if((currListIndex-1)< 0){
            JOptionPane.showMessageDialog(figure,"Invalid Undo!");
            return Status.FAIL;
        } else {
            currListIndex -= 1;
            currFrame = frameList.get(currListIndex);
            currStatusView.updateColor(currFrame.currColor);
            updateBoardView(currFrame.currBoard);
            seleBtn = null;
            return Status.SUCCESS;
        }
    }

    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status redoHelper(){
        unSelectButHelp();
        int listSize = frameList.size();
        if((currListIndex+1) > (listSize-1)){
            JOptionPane.showMessageDialog(figure,"Invalid Redo!");
            return Status.FAIL;
        } else {
            currListIndex += 1;
            currFrame = frameList.get(currListIndex);
            currStatusView.updateColor(currFrame.currColor);
            updateBoardView(currFrame.currBoard);
            seleBtn = null;
            return Status.SUCCESS;
        }
    }

    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status forfeitHelp(){
        unSelectButHelp();
        switchSideHelper(currFrame.currColor);
        ChessBoard newBoard = new ChessBoard(false);
        updateBoardView(newBoard);
        currFrame     = new Frame(newBoard, Color.WHITE);
        currStatusView.updateColor(Color.WHITE);
        frameList     = new Vector<Frame>();
        frameList.add(currFrame);
        currListIndex = 0;
        return Status.SUCCESS;
    }


    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status nextStepHelper(){
        Frame newFrame = currFrame.nextFrame();
        updateBoardView(newFrame.currBoard);
        seleBtn = null;
        this.currFrame = newFrame;
        int listSize = frameList.size();
        if(currListIndex == (listSize-1)){ //normal case, append directly
            this.frameList.add(this.currFrame);
            currListIndex += 1;
        } else {                          //edge    case, reconstruct linked list
            Vector<Frame> newFrameList = new Vector<Frame>();
            for (int i = 0; i <= currListIndex; i++){
                newFrameList.add(frameList.get(i));
            }
            newFrameList.add(this.currFrame);
            frameList = newFrameList;
            currListIndex += 1;
        }
        currStatusView.updateColor(currFrame.currColor);
        return Status.SUCCESS;
    }

    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public Status switchSideHelper(Color lostSide){
        if (lostSide == null){
            player1.color =  player1.color == Color.WHITE?Color.BLACK:Color.WHITE;
            player2.color =  player1.color == Color.WHITE?Color.BLACK:Color.WHITE;
            currStatusView.updateColors(player1, player2);
        } else {
            if (lostSide == player1.color){
                player1.score += 0;
                player2.score += 1;
            } else {
                player1.score += 1;
                player2.score += 0;
            }
            player1.color =  player1.color == Color.WHITE?Color.BLACK:Color.WHITE;
            player2.color =  player1.color == Color.WHITE?Color.BLACK:Color.WHITE;
            currStatusView.updateScore(player1, player2);
            currStatusView.updateColors(player1, player2);
        }
        return  Status.SUCCESS;
    }


    /**
     * add some reaction for pieces on chessboard
     * @param
     */
    public void checkEnding(){
        Status result = currFrame.checkEnding(currFrame.currBoard, currFrame.currColor);
        Move   move = new Move();

        if (result == Status.DRAW){
            currStatusView.updateStatus(result);
            JOptionPane.showMessageDialog(figure,"Draw!");
            truceHelp(false);
            switchSideHelper(null);
        }
        else if (result == Status.LOST){
            System.out.print("LOST");
            currStatusView.updateStatus(result);
            JOptionPane.showMessageDialog(figure,"Lost!");
            truceHelp(false);
            switchSideHelper(currFrame.currColor);
        }
        else if (result == Status.BEING_CHECK){
            currStatusView.updateStatus(result);
            Vector<Piece> kings = move.findPiece('K', currFrame.currColor, currFrame.currBoard);
            if (kings.size() > 0){
                currBoardView.highLightBut(kings.get(0).xPos, kings.get(0).yPos, Color.RED);
            }
        }
        else{
            currStatusView.updateStatus(result);
            return;
        }
    }



    /**
     * high light current available position on the chess board
     */
    public void highLightAvaiPosi(){
        Vector<Integer> availableMoves = currFrame.availableMoves();
        for (int iPosition: availableMoves){
            int yPos = (int)iPosition / 8;
            int xPos = (int)iPosition % 8;
            currBoardView.highLightBut(xPos, yPos, Color.GREEN);
        }
    }

    /**
     * unhigh light current available postion on the chess board
     */
    public void unhighLightAvaiPosi(){
        Vector<Integer> availableMoves = currFrame.availableMoves();
        for (int iPosition: availableMoves){
            int yPos = (int)iPosition/8;
            int xPos = (int)iPosition%8;
            currBoardView.unhighLightBut(xPos, yPos);
        }
    }



    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e){ }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}
