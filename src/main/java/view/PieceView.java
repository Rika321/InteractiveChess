package view;

import model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PieceView extends JButton{
    //public Piece currPiece;


    /**
     * default constructor for Piece
     * @param xPos
     * @param yPos
     */
    PieceView(int xPos, int yPos){
        this.setSize(60,60);
        this.setBackground(((xPos+yPos)%2==1)? Color.lightGray:Color.WHITE);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.putClientProperty("xPos", (int)xPos);
        this.putClientProperty("yPos", (int)yPos);
        this.putClientProperty("position", yPos*8+xPos);
        this.putClientProperty("piece", null);
    }

    /**
     * add Piece Image from fileSystem
     * @param piece
     */
    public void addPieceIcon(Piece piece){
        this.putClientProperty("piece", piece);
        BufferedImage buttonIcon = null;
        try{
            buttonIcon = ImageIO.read(new File("C:\\Users\\rika\\Desktop\\CS242\\Assignment1.1\\src\\data\\" + piece.getFullName() + ".png"));
        } catch (IOException e) {
        }
        this.setIcon(new ImageIcon(buttonIcon));
    }


    /**
     * high light current button
     */
    public void highLightCurr(Color color){
        int xPos = (Integer) (this.getClientProperty("xPos"));
        int yPos = (Integer) (this.getClientProperty("yPos"));


        Border thickBorder = new LineBorder(color, 2);
        this.setBorder(thickBorder);
        this.setBackground(color);
        this.setContentAreaFilled(true);
        this.setOpaque(true);
    }

    /**
     * unhigh light current button
     */
    public void unhighLightCurr(){
        int xPos = (Integer)(this.getClientProperty("xPos"));
        int yPos = (Integer)(this.getClientProperty("yPos"));


        Border thickBorder = new LineBorder(Color.WHITE, 1);
        this.setBorder(thickBorder);
        this.setBackground(((xPos+yPos)%2==1)? Color.lightGray:Color.WHITE);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
    }

}
