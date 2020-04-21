import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

class Game extends JFrame {
    private int width = 800, height = 650, row = 10, col = 50;
    private int mines = 99;
    Game() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);

        JPanel topPenal = new JPanel();

        JLabel minesnum = new JLabel("Mines Left : " + mines);
        topPenal.add(minesnum);

        JButton restart = new JButton("Restart");
        restart.setActionCommand("r");

        topPenal.add(restart);

        add(topPenal);
        setVisible(true);
    }
}

public class minesweeper {
    public static void main(String[] args) {
        Game g = new Game();
    }
}
