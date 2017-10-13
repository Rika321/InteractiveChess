package model;

import java.awt.*;
import java.awt.Color;

public class Player {
    public int score;
    public String name;
    public Color color;

    public Player(int score, String name, Color color) {
        this.score = score;
        this.name = name;
        this.color = color;
    }
}
