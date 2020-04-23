import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Game extends JFrame implements ActionListener, MouseListener {
    private int width = 720, height = 720, row = 16, col = 16;
    private int mines = 10;
    private boolean[][] isTurned;
    private boolean gameOver;
    private boolean gameRunning;
    private int[][] aroundBombNum;
    private boolean[][] map;
    private boolean[][] isPressed;
    private JButton[][] buttons;
    private JPanel minePanel, bombPanel;
    private JLabel gameMessage;
    private int[] first_click_location = new int[2];

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
        boomview(col, row, mines);
        add(minePanel, BorderLayout.CENTER);

        gameRunning = false;
        //make it visible
        setVisible(true);
    }

    public void boomview(int block_width, int block_height, int bomballnum) {
        int mineCount = 0, location = 0;
        buttons = new JButton[block_width][block_height];
        aroundBombNum = new int[block_width][block_height];
        map = new boolean[block_width][block_height];
        first_click_location = new int[2];
        minePanel.setLayout(new GridLayout(block_width, block_height));
        gameOver = false;
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
        minePanel.revalidate();
        minePanel.repaint();
        System.out.println(block_width+" "+ block_height+" ");
    }

    public void clear (){
        gameOver = false;
        setSize(width, height);
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        minePanel.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("ot")) {
            System.exit(0);
        } else if (command.equals("beg")) {
            clear();
            col = 9;
            row = 9;
            boomview(9, 9, 10);
        } else if (command.equals("mid")) {
            col = 16;
            row = 16;
            boomview(16, 16, 30);
            System.out.println("mid pressed");
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
