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
    private int mineCount;
    private boolean[][] isTurned;
    private boolean gameOver;
    private boolean gameRunning;
    private int[][] aroundBombNum;
    private boolean[][] map;
    private boolean[][] isPressed;
    private boolean[][] isFlagged;
    private JButton[][] buttons;
    private JPanel minePanel;
    private JLabel gameMessage;

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
        gameMessage = new JLabel();
        view(col, row);
        add(gameMessage, BorderLayout.NORTH);
        add(minePanel, BorderLayout.CENTER);

        gameRunning = false;
        gameOver = false;

        //make it visible
        setVisible(true);
    }

    public void view(int block_width, int block_height) {
        minePanel.removeAll();
        int location = 0;
        buttons = new JButton[block_width][block_height];
        aroundBombNum = new int[block_width][block_height];
        map = new boolean[block_width][block_height];
        isPressed = new boolean[block_width][block_height];
        isFlagged = new boolean[block_width][block_height];
        minePanel.setLayout(new GridLayout(block_width, block_height));
        gameOver = false;

        if (block_height == 9 && block_width == 9) {
            gameMessage.setText("Level : Beginner");
        } else if (block_height == 16 && block_width == 16) {
            gameMessage.setText("Level : Intermediate");
        } else {
            gameMessage.setText("Level : Expert");
        }

        //add buttons
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = false;
                isPressed[i][j] = false;
                isFlagged[i][j] = false;
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
        gameRunning =false;
        width = w;
        height = h;
        setSize(w, h);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
                map[i][j] = false;
            }
        }
        minePanel.removeAll();
    }

    //initialize the mines
    public void startGame(int sx, int sy) {
        mineCount = 0;
        while (mineCount != mines) {
            int x = (int) (Math.random() * col);
            int y = (int) (Math.random() * row);
            if (x != sx && y != sy) {
                if (!map[x][y]) {
                    map[x][y] = true;
                    mineCount++;
                    for (int i = x - 1; i <= x + 1; i++) {
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (i >= 0 && i < col && j >= 0 && j < row) {
                                aroundBombNum[i][j]++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Mines generated");
        printMap();
    }

    //Print the whole map on console
    public void printMap() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (map[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print(aroundBombNum[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    //check if game over
    public void check(int x, int y) {
        if(map[x][y] && !isFlagged[x][y]){
            buttons[x][y].setBackground(Color.RED);
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    if (map[i][j]) {
                        buttons[i][j].setBackground(Color.RED);
                    }else if(!map[i][j] && isFlagged[i][j]){
                        buttons[i][j].setBackground(Color.yellow);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Game Over", "Game Over",JOptionPane.WARNING_MESSAGE);
        }
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
                mines = 10;
                view(9, 9);
                break;
            case "mid":
                clear(480, 480);
                col = 16;
                row = 16;
                mines = 30;
                view(16, 16);
                break;
            case "exp":
                clear(890, 480);
                col = 16;
                row = 30;
                mines = 99;
                view(col, row);
                break;
            case "re":
                clear(width, height);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String command = ((JButton) e.getSource()).getActionCommand();
        String[] press = command.split(" ");
        int x = Integer.parseInt(press[0]);
        int y = Integer.parseInt(press[1]);

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!gameRunning) {
                startGame(x, y);
                check(x,y);
                isPressed[x][y] = true;
                gameRunning = true;
            } else if (!gameOver && !isPressed[x][y]) {
                isPressed[x][y] = true;
                check(x,y);
            }
        }
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
