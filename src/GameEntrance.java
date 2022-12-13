import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameEntrance extends JFrame{
    JButton Go = new JButton("Go♟");
    JButton Gobang = new JButton("Gobang ♜");
    static JPanel mainPanel = new JPanel();
    Font font = new Font(null, Font.BOLD, 20);
    Font fontBold = new Font(null, Font.BOLD, 30);
    Border border = new LineBorder(new Color(198, 121, 21), 3);

    ActionListener go = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //new goEntrance();
        }
    };

    ActionListener gobang = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GobangEntrance();
        }
    };

    public MouseAdapter hover = new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent e){
            JButton temp = (JButton) e.getSource();
            temp.setFont(fontBold);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JButton temp = (JButton) e.getSource();
            temp.setFont(font);
        }
    };

    public GameEntrance(){
        Go.setFont(font);
        Gobang.setFont(font);
        Go.addMouseListener(hover);
        Gobang.addMouseListener(hover);
        Go.setPreferredSize(new Dimension(200,100));
        Gobang.setPreferredSize(new Dimension(200, 100));

        Go.setBorder(border);
        Go.setBackground(new Color(245, 202, 105));
        Go.setForeground(new Color(107, 61, 0));
        Go.addActionListener(go);
        Gobang.setBorder(border);
        Gobang.setBackground(new Color(245, 202, 105));
        Gobang.setForeground(new Color(107, 61, 0));
        Gobang.addActionListener(gobang);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(Go, BorderLayout.WEST);
        mainPanel.add(Gobang, BorderLayout.EAST);
        this.setTitle("Chosse your Game! \uD83E\uDD77");
        this.setContentPane(mainPanel);
        this.setSize(413, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameEntrance();
    }
}
