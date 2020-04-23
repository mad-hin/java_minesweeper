import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Game extends JFrame implements ActionListener , MouseListener {
    private int width = 800, height = 650, row, col;
    private int mines = 99;
    private boolean[][] isTurned;
    private boolean gameOver;
    private boolean gameRunning;
    private int[][] aroundBombNum;
    private boolean[][] map;
    private boolean[][] isPressed;
    private JButton[][] buttons;
    private JPanel minePanel;
    private JLabel gameMessage;

    Game(int bx, int by) {
        row = bx;
        col = by;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);

        MenuBar menu = new MenuBar();
        setMenuBar(menu);

        //first menu : "Game"
        Menu file = new Menu("Game");
        MenuItem restart = new MenuItem("New Game");
        restart.setActionCommand("re");
        restart.addActionListener(this);
        file.add(restart);
        MenuItem end = new MenuItem("End Game");
        end.setActionCommand("endGame");
        end.addActionListener(this);
        MenuItem exit = new MenuItem("Exit");
        exit.setActionCommand("ot");
        exit.addActionListener(this);
        file.add(end);
        file.add(exit);
        menu.add(file);

        //second menu : "Level"
        Menu level = new Menu("Level");
        MenuItem beginner = new MenuItem("Beginner");
        beginner.setActionCommand("beg");
        beginner.addActionListener(this);
        MenuItem intermediate = new MenuItem("Intermediate");
        intermediate.setActionCommand("mid");
        intermediate.addActionListener(this);
        MenuItem expert = new MenuItem("Expert");
        expert.setActionCommand("exp");
        expert.addActionListener(this);
        //MenuItem custom = new MenuItem("Custom");
        level.add(beginner);
        level.add(intermediate);
        level.add(expert);
        //level.add(custom);
        menu.add(level);

        //interface
        minePanel = new JPanel();
        minePanel.setLayout(new GridLayout(col, row));
        //minePanel.addMouseListener(this);

        //buttons (a.k.a mines)
        buttons = new JButton[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setActionCommand(i + " " + j);
                minePanel.add(buttons[i][j]);
                buttons[i][j].addMouseListener(this);
                buttons[i][j].addActionListener(this);
            }
        }
        add(minePanel, BorderLayout.CENTER);

        gameRunning = false;
        //make it visible
        setVisible(true);
    }

    public void boomview() {
        // TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("ot")){
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

public class minesweeper {
    public static void main(String[] args) {
        Game g = new Game(9, 9);
    }
}
