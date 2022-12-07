import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class normalGame {
    private static int currentPlayer = 0;

    private static boolean end = false;

    private static chessBoard chess = new chessBoard("Normal Game mode\uD83E\uDD3A");
    private MouseListener hover = new MouseListener() {
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

    public static ActionListener click = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton) e.getSource();
            // get the index
            int tempIndex = chess.btnToInt.get(temp);
            if (chess.state[tempIndex] == -1 && (!end)) {
                chess.state[tempIndex] = currentPlayer;
                ImageIcon icon = new ImageIcon();
                if (chess.state[tempIndex] == 0) {
                    icon = new ImageIcon("static/black.png");
                } else if (chess.state[tempIndex] == 1) {
                    icon = new ImageIcon("static/white.png");
                }
                Image temp1 = icon.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
                icon = new ImageIcon(temp1);
                chess.buttons[tempIndex].setIcon(icon);
                chess.repaint();
                if (checkEndGame.checkState(chess)) {
                    JOptionPane.showMessageDialog(null, "Player" + (currentPlayer + 1) + " win the game");
                    chess.Jtitle.setText("Player" + (currentPlayer + 1) + " win the game");
                    end = true;
                }
                currentPlayer ^= 1;
                if(currentPlayer == 0)
                    chess.Jtitle.setText("Player 1's turn\uD83D\uDC15");
                else
                    chess.Jtitle.setText("Player 2's turn\uD83D\uDC14");
            }
        }
    };

    public normalGame(){
        chess.Jtitle.setText("Player 1's turn\uD83D\uDC15");
        for (int i = 0; i < 64; i ++) {
            chess.buttons[i].addActionListener(click);
            chess.buttons[i].addMouseListener(hover);
        }
    }
}

