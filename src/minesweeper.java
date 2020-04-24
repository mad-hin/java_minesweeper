import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Game extends JFrame implements ActionListener, MouseListener {
    private int width = 480, height = 480, row = 9, col = 9;
    private int mines = 10;
    private boolean[][] isTurned;
    private boolean gameOver;
    private boolean gameRunning;
    private int[][] aroundBombNum;
    private boolean[][] map;
    private boolean[][] isPressed;
    private JButton[][] buttons;
    private JPanel minePanel;
    private JLabel gameMessage;
    private int[] first_click_location = new int[2];

    Game() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);

        //menu bar
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

        //Beginner level (9x9)
        MenuItem beginner = new MenuItem("Beginner");
        beginner.setActionCommand("beg");
        beginner.addActionListener(this);
        level.add(beginner);

        //Intermediate level (16x16)
        MenuItem intermediate = new MenuItem("Intermediate");
        intermediate.setActionCommand("mid");
        intermediate.addActionListener(this);
        level.add(intermediate);

        //Expert level (16x30)
        MenuItem expert = new MenuItem("Expert");
        expert.setActionCommand("exp");
        expert.addActionListener(this);
        level.add(expert);

        //MenuItem custom = new MenuItem("Custom");
        //level.add(custom);
        menu.add(level);

        //interface
        minePanel = new JPanel();
        view(col, row, mines);
        add(minePanel, BorderLayout.CENTER);

        gameRunning = false;
        gameOver = false;

        //make it visible
        setVisible(true);
    }

    public void view(int block_width, int block_height, int bomballnum) {
        minePanel.removeAll();
        int mineCount = 0, location = 0;
        buttons = new JButton[block_width][block_height];
        aroundBombNum = new int[block_width][block_height];
        map = new boolean[block_width][block_height];
        first_click_location = new int[2];
        minePanel.setLayout(new GridLayout(block_width, block_height));
        gameOver = false;

        //add buttons
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = false;
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setActionCommand(i + " " + j);
                minePanel.add(buttons[i][j]);
                buttons[i][j].addMouseListener(this);
                buttons[i][j].addActionListener(this);
            }
        }

        //make it visible
        minePanel.revalidate();
        minePanel.repaint();
        System.out.println(block_width + " " + block_height + " ");
    }

    public void clear(int w, int h) {
        gameOver = false;
        width = w;
        height = h;
        setSize(w, h);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        minePanel.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "ot":
                System.exit(0);
                break;
            case "beg":
                clear(480, 480);
                col = 9;
                row = 9;
                view(9, 9, 10);
                break;
            case "mid":
                clear(480, 480);
                col = 16;
                row = 16;
                view(16, 16, 30);
                break;
            case "exp":
                clear(900, 480);
                col = 16;
                row = 30;
                view(col, row, 30);
                break;
            case "re":
                clear(width, height);
                break;
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
        Game g = new Game();
    }
}
