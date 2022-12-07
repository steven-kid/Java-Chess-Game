import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class aiGame {
    private static int currentPlayer = 0;

    public static MouseListener hover = new MouseListener() {
        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e){
            JButton temp = (JButton) e.getSource();
            // get the index
            int tempIndex = chess.btnToInt.get(temp);
            if (chess.state[tempIndex] == -1 && (!end)) {
                ImageIcon icon = new ImageIcon();
                if (currentPlayer == 0) {
                    icon = new ImageIcon("static/black.png");
                } else if (currentPlayer == 1) {
                    icon = new ImageIcon("static/white.png");
                }
                Image temp1 = icon.getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT);
                icon = new ImageIcon(temp1);
                chess.buttons[tempIndex].setIcon(icon);
                chess.repaint();
            }
        }
        public void mouseExited(MouseEvent e) {
            JButton temp = (JButton) e.getSource();
            // get the index
            int tempIndex = chess.btnToInt.get(temp);
            if(chess.state[tempIndex] == -1 && (!end)){
                chess.buttons[tempIndex].setIcon(null);
            }
        }
    };

    private static boolean end = false;

    private static chessBoard chess = new chessBoard("AI Game mode\uD83E\uDD16");

    public static ActionListener click = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton) e.getSource();
            // get the index
            int tempIndex = chess.btnToInt.get(temp);
            if (chess.state[tempIndex] == -1 && (!end)) {
                chess.Jtitle.setText("Your turn!");
                chess.state[tempIndex] = currentPlayer;
                ImageIcon iconMan = new ImageIcon("static/black.png");
                ImageIcon iconAI = new ImageIcon("static/white.png");
                Image temp1 = iconMan.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
                Image temp2 = iconAI.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
                iconMan = new ImageIcon(temp1);
                iconAI = new ImageIcon(temp2);
                if (checkEndGame.checkState(chess)) {
                    chess.Jtitle.setText("Human win the this time!");
                    JOptionPane.showMessageDialog(null, "Human win the game!\uD83E\uDD3A");
                    end = true;
                }
                chess.buttons[tempIndex].setIcon(iconMan);
                chess.Jtitle.setText("Let AI think think!\uD83E\uDD14");
                int AIPosition = aiBrain.searchLocation(chess.state);
                chess.state[AIPosition] = 1;
                chess.buttons[AIPosition].setIcon(iconAI);
                if (checkEndGame.checkState(chess) && (!end)) {
                    chess.Jtitle.setText("AI win the this time!");
                    JOptionPane.showMessageDialog(null, "AI win this time!\uD83E\uDD16");
                    end = true;
                }
                chess.Jtitle.setText("Your turn!\uD83D\uDE80");
                chess.repaint();
            }
        }
    };


    public aiGame(){
        chess.Jtitle.setText("Your turn!\uD83D\uDE80");
        for (int i = 0; i < 64; i ++) {
            chess.buttons[i].addActionListener(click);
            chess.buttons[i].addMouseListener(hover);
        }
    }
}
