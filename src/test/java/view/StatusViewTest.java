package view;

import junit.framework.TestCase;
import model.Player;
import utility.Status;

import javax.swing.*;
import java.awt.*;

public class StatusViewTest extends TestCase {
    public JPanel scoreSection;
    public JPanel statusSection;
    JLabel score1Label;
    JLabel score2Label;
    JPanel color1Panel;
    JPanel color2Panel;
    JPanel colorPanel;
    JButton piecePanel;
    JPanel statusPanel;


     Player player1  = new Player(0, "rika", Color.WHITE);
     Player player2  = new Player(0, "rena", Color.BLACK);
    StatusView sv = new StatusView(player1, player2);

    public void testCreateStatusSection() throws Exception {
        scoreSection = sv.createScoreSection();
    }

    public void testCreateScoreSection() throws Exception {
        statusSection = sv.createStatusSection();

    }

    public void testUpdateColors() throws Exception {
        sv.updateColors(player1, player2);
    }

    public void testUpdateColor() throws Exception {
        sv.updateColor(Color.WHITE);
    }

    public void testUpdateIcon() throws Exception {
        sv.updateIcon(null);
        sv.updateIcon("empty");
    }

    public void testUpdateScore() throws Exception {
        sv.updateScore(player1, player2);
    }

    public void testUpdateStatus() throws Exception {
        sv.updateStatus(Status.SUCCESS);
        sv.updateStatus(Status.BEING_CHECK);
        sv.updateStatus(Status.DRAW);
        sv.updateStatus(Status.LOST);
    }

}