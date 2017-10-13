package view;

import model.ChessBoard;
import model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class BoardView extends JPanel {

    public JPanel BoardSpace;

    public JPanel    [][] grid;
    public PieceView [][] buttons;
    public Vector<JButton> controlButs;


    /**
     * default constructor for BoardView
     * @param width
     * @param height
     * @param myChessBoard
     */
    public BoardView(int width, int height, ChessBoard myChessBoard){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        this.setSize(width,height);
        this.setLayout(new GridLayout(1, 1));


        addBoard(myChessBoard);
        //addElement();
    }

    /**
     * helper function to add BoardSpace
     * @param currBoard
     */
    public void addBoard(ChessBoard currBoard) {
        BoardSpace = new JPanel();
        BoardSpace.setLayout(new GridLayout(9, 9));

        grid    = new JPanel[9][9];
        buttons = new PieceView[8][8];
        Vector<Piece> pieces = new Vector<Piece>();
        pieces.addAll(currBoard.getAllPieces(Color.BLACK));
        pieces.addAll(currBoard.getAllPieces(Color.WHITE));

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid[i][j] = createBox(i, j);
                if (i != 8 && j != 0){
                    buttons[i][j-1] = new PieceView(j-1, i);
                    grid[i][j].add(buttons[i][j-1]);
                }
                BoardSpace.add(grid[i][j]);
            }
        }
        for (Piece iPiece: pieces){
            buttons[iPiece.yPos][iPiece.xPos].addPieceIcon(iPiece);
        }
        this.add(BoardSpace);
    }

    /**
     * helper function to return desired button
     * @param i
     * @param j
     * @return
     */
    public PieceView getButton(int xPos, int yPos){
        if (xPos >= 8 || xPos < 0 || yPos >= 8 || yPos <=0){
            return null;
        }
        return buttons[yPos][xPos];
    }

    /**
     * helper function to generate click button
     * @param i
     * @param j
     * @return
     */
    public  JPanel createBox(int i, int j){
        JPanel currPanel =  new JPanel();
        //currPanel.setSize(60,60);
        currPanel.setLayout(new GridLayout(1, 1));
        currPanel.setBackground(Color.cyan);
        Font myFont = new Font("Serif", Font.BOLD, 20);
        if (j == 0 && i == 8){
            return currPanel;
        }
        else if( j == 0){
            JLabel label = new JLabel(Integer.toString((int)(8-i)));
            label.setFont(myFont);
            label.setHorizontalAlignment(SwingConstants.CENTER); // set the horizontal alignement on the x axis !
            label.setVerticalAlignment(SwingConstants.CENTER);
            currPanel.add(label, BorderLayout.CENTER);
            return currPanel;
        }
        else if(i == 8){
            JLabel label = new JLabel(Character.toString( (char)('A'+(j-1))));
            label.setFont(myFont);
            label.setHorizontalAlignment(SwingConstants.CENTER); // set the horizontal alignement on the x axis !
            label.setVerticalAlignment(SwingConstants.CENTER);
            currPanel.add(label, BorderLayout.CENTER);
            return currPanel;
        } else {
            return currPanel;
        }

    }

    public void boardRedraw(ChessBoard newBoard){
        this.remove(BoardSpace);
        BoardSpace = null;
        addBoard(newBoard);
    }



    /**
     * to add mouse control unit to the board
     * @param listener
     */
    public void addMouseControl( MouseListener listener) {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                buttons[i][j].addMouseListener(listener);
            }
        }
//        for (JButton currBut: controlButs){
//            currBut.addMouseListener(listener);
//        }
    }

    /**
     * high light current button with yPos and xPos
     * @param xPos
     * @param yPos
     */
    public void highLightBut(int xPos, int yPos, Color color){
        buttons[yPos][xPos].highLightCurr(color);
    }

    /**
     * unhigh light current button with yPos and xPos
     * @param xPos
     * @param yPos
     */
    public void unhighLightBut(int xPos, int yPos){
        buttons[yPos][xPos].unhighLightCurr();
    }


    /**
     * generate click buttons on the boardView
     */

}
