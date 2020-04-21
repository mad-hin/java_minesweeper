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

class Game extends JFrame {
    private int width = 800, height = 650, row = 10, col = 10;
    private int mines = 99;
    Game() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);

        MenuBar menu = new MenuBar();
        setMenuBar(menu);

        //first menu : "Game"
        Menu file = new Menu("Game");
        MenuItem restart = new MenuItem ("New Game");
        file.add(restart);
        MenuItem end = new MenuItem("End Game");
        file.add(end);
        menu.add(file);

        //second menu : "Level"
        Menu level = new Menu("Level");
        MenuItem beginner = new MenuItem("Beginner");
        MenuItem intermediate = new MenuItem("Intermediate");
        MenuItem expert = new MenuItem("Expert");
        MenuItem custom = new MenuItem("Custom");
        level.add(beginner);
        level.add(intermediate);
        level.add(expert);
        level.add(custom);
        menu.add(level);
        setVisible(true);
    }
}

public class minesweeper {
    public static void main(String[] args) {
        Game g = new Game();
    }
}
