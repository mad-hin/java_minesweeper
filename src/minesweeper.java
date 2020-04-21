import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.NumberFormat;

class Game extends JFrame {
    private int width = 800, height = 650, row = 10, col = 50;

    Game() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(this);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Height");
        model.addColumn("Width");
        model.addColumn("Mines");
        NumberFormatter formater = new NumberFormatter();
        formater.setAllowsInvalid(false);
        JFormattedTextField textHeight = new JFormattedTextField(formater);
        textHeight.setColumns(5);
        JTextField test = new JTextField(10);
        add(new JScrollPane(table));
        setVisible(true);
    }
}

public class minesweeper {
    public static void main(String[] args) {
        Game g = new Game();
    }
}
