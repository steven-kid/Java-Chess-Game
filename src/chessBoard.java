import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;

public class chessBoard extends JFrame {
    public int size = 16;
    JButton[] buttons = new JButton[size * size];
    JPanel infoDock = new JPanel();

    JLabel Jtitle =  new JLabel("test");
    JPanel chessPanel = new JPanel();
    JPanel mainPanel = new JPanel();

    int [] state = new int[size*size];

    public HashMap<JButton, Integer> btnToInt = new HashMap<JButton, Integer>();

    public chessBoard(String title){
        super(title);
        chessPanel.setLayout(new GridLayout(16, 16));
        for (int i = 0; i < size*size; i ++)
        {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(50,50));
            //set border
            Border border = new LineBorder(new Color(198, 121, 21), 3);
            buttons[i].setBorder(border);
            // set background of every key
            buttons[i].setBackground(new Color(245, 202, 105));
            chessPanel.add(buttons[i]);
        }

        // set Font
        Jtitle.setFont(new Font("Courier", Font.BOLD, 18));
        Jtitle.setForeground(new Color(107, 61, 0));
        // init chess board state
        // state[i] = -1 not visited yet
        // state[i] = 0 visited by player 0
        // state[i] = 1 visited by player 1
        for (int i = 0; i < size*size; i ++) {
            this.state[i] = -1;
            btnToInt.put(buttons[i], i);
        }

        infoDock.add(Jtitle);
        mainPanel.setLayout(new BorderLayout());
        infoDock.setBackground(new Color(245, 202, 105));
        mainPanel.add(infoDock, BorderLayout.NORTH);
        mainPanel.add(chessPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    }
}