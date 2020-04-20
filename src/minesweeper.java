import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Game extends JFrame {
    private int width = 800, height = 650, row = 10, col = 50;

    Game() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);
    }
}

public class minesweeper {
    public static void main(String[] args) {
        Game g = new Game();
    }
}
