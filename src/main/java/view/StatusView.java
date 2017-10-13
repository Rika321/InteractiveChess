package view;

import model.Player;
import utility.Status;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StatusView extends JPanel {
    public JPanel scoreSection;
    public JPanel statusSection;
    public Player player1;
    public Player player2;

    JLabel score1Label;
    JLabel score2Label;
    JPanel color1Panel;
    JPanel color2Panel;
    JPanel colorPanel;
    JButton piecePanel;
    JPanel statusPanel;


    public StatusView(Player player1, Player player2){
        this.player1  = player1;
        this.player2  = player2;

        this.setLayout(new GridLayout(3,1,0, 50));
        this.setSize(550,480);
        scoreSection = createScoreSection();
        statusSection = createStatusSection();
        this.add(scoreSection, 0);
        this.add(statusSection, 1);


        this.setBackground(Color.cyan);
        //this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setVisible(true);
    }

    public JPanel createStatusSection() {
        JPanel myStatusSection = new JPanel();
        Border thickBorder = new LineBorder(Color.BLUE, 3);
        myStatusSection.setBorder(thickBorder);

        myStatusSection.setLayout(new GridLayout(3,3, 20, 10));
        myStatusSection.add(new JLabel(" Piece   "));
        myStatusSection.add(new JLabel(""));
        piecePanel = new JButton();
        piecePanel.setBackground(Color.WHITE);
        updateIcon("empty");
        myStatusSection.add(piecePanel);

        myStatusSection.add(new JLabel(" Color    "));
        myStatusSection.add(new JLabel(""));
        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);
        myStatusSection.add(colorPanel);


        myStatusSection.add(new JLabel(" Status   "));
        myStatusSection.add(new JLabel(""));
        statusPanel = new JPanel();
        statusPanel.setBackground(Color.WHITE);
        myStatusSection.add(statusPanel);
        return myStatusSection;
    }

    public JPanel createScoreSection() {
        JPanel myScoreSection = new JPanel();
        Border thickBorder = new LineBorder(Color.BLUE, 3);
        myScoreSection.setBorder(thickBorder);


        myScoreSection.setLayout(new GridLayout(3,3, 20, 10));
        myScoreSection.add(new JLabel(" Player"));
        myScoreSection.add(new JLabel("Score"));
        myScoreSection.add(new JLabel("Color"));

        myScoreSection.add(new JLabel(player1.name));
        score1Label = new JLabel(Integer.toString(player1.score));
        myScoreSection.add(score1Label);
        color1Panel = new JPanel();
        color1Panel.setBackground(Color.WHITE);
        myScoreSection.add(color1Panel);


        myScoreSection.add(new JLabel(player2.name));
        score2Label = new JLabel(Integer.toString(player2.score));
        myScoreSection.add(score2Label);
        color2Panel = new JPanel();
        color2Panel.setBackground(Color.BLACK);
        myScoreSection.add(color2Panel);

        return myScoreSection;
    }

//    JLabel score1Label;
//    JLabel score2Label;
//    JPanel color1Panel;
//    JPanel color2Panel;
//    JPanel colorPanel;
//    JPanel piecePanel;
//    JPanel statusPanel;

    public void updateColors(Player player1 , Player player2){
        color1Panel.setBackground(player1.color);
        color2Panel.setBackground(player2.color);
    }

    public void updateColor(Color color){
        colorPanel.setBackground(color);
    }

    public void updateIcon(String pieceName){
        if(pieceName == null){
            return;
        }
        BufferedImage buttonIcon = null;
        try{
            buttonIcon = ImageIO.read(new File("C:\\Users\\rika\\Desktop\\CS242\\Assignment1.1\\src\\data\\" + pieceName + ".png"));
        } catch (IOException e) {
        }
        piecePanel.setIcon(new ImageIcon(buttonIcon));
    }

    public void updateScore(Player player1, Player player2){
        score1Label.setText(Integer.toString(player1.score));
        score2Label.setText(Integer.toString(player2.score));
    }

    public void updateStatus(Status status){
        statusPanel.removeAll();
        if (status == Status.VALID_MOVE){
            JLabel curr = new JLabel("Valid!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.GREEN);
        }
        else if (status == Status.BEING_CHECK){
            JLabel curr = new JLabel("Danger!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.RED);
        }
        else if(status == Status.INVALID_DESTINATION){
            JLabel curr = new JLabel("Invalid!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.magenta);
        }
        else if(status == Status.LOST){
            JLabel curr = new JLabel("Lost!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.YELLOW);
        }
        else if(status == Status.DRAW){
            JLabel curr = new JLabel("Draw!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.PINK);
        }
        else if(status == Status.FREE){
            JLabel curr = new JLabel("FREE!");
            statusPanel.add(curr);
            statusPanel.setBackground(Color.PINK);
        }
        else{
            statusPanel.setBackground(Color.WHITE);
        }
        statusPanel.revalidate();
        statusPanel.repaint();

    }







}
