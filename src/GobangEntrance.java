import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GobangEntrance extends JFrame {
    JButton AIMode = new JButton("AI \uD83E\uDD16");
    JButton HumanMode = new JButton("HUMAN \uD83E\uDD3A");

    static JPanel mainPanel = new JPanel();

    Font font = new Font(null, Font.BOLD, 20);
    Font fontBold = new Font(null, Font.BOLD, 30);

    Border border = new LineBorder(new Color(198, 121, 21), 3);

    Border borderThick = new LineBorder(new Color(198, 121, 21), 6);

    ActionListener human = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GobangNormalGame();
        }
    };

    ActionListener ai = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GobangAiGame();
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

    public GobangEntrance(){
        AIMode.setFont(font);
        HumanMode.setFont(font);
        AIMode.addMouseListener(hover);
        HumanMode.addMouseListener(hover);
        AIMode.setPreferredSize(new Dimension(200,100));
        HumanMode.setPreferredSize(new Dimension(200, 100));

        AIMode.setBorder(border);
        AIMode.setBackground(new Color(245, 202, 105));
        AIMode.setForeground(new Color(107, 61, 0));
        AIMode.addActionListener(ai);
        HumanMode.setBorder(border);
        HumanMode.setBackground(new Color(245, 202, 105));
        HumanMode.setForeground(new Color(107, 61, 0));
        HumanMode.addActionListener(human);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(AIMode, BorderLayout.WEST);
        mainPanel.add(HumanMode, BorderLayout.EAST);
        this.setTitle("Choose game mode \uD83E\uDD77");
        this.setContentPane(mainPanel);
        this.setSize(413, 300);
        this.setVisible(true);
    }
}
