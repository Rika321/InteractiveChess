package controller;

import model.ChessBoard;
import view.BoardView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainControl {


    public static void main(String[] args) throws IOException {
        JFrame figure = new JFrame("Chess");
        JOptionPane.showMessageDialog(figure,"Welcome to Chess");
        String play1Name = JOptionPane.showInputDialog(
                figure,
                "Enter White player's nickname to continue",
                "Chess Game Setup",
                JOptionPane.WARNING_MESSAGE
        );

        String play2Name = JOptionPane.showInputDialog(
                figure,
                "Enter Black player's nickname to continue",
                "Chess Game Setup",
                JOptionPane.WARNING_MESSAGE
        );
        GameControl game = new GameControl(play1Name,play2Name, true);
    }

}
