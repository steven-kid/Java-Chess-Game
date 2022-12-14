import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GoNormalGame {
    private static int currentPlayer = 0;
    private static boolean end = false;

    private static GoGameVaildator validator = null;

    private static int[] setFather = new int[16 * 16];

    private static final int[] dx = {1, 0, -1, 0};

    private static final int[] dy = {0, 1, 0, -1};

    private static int[] setQiSize = new int[16*16];

    private static boolean[] eaten = new boolean[16 * 16];

    private static chessBoard chess = null;

    // find set boss
    private static int findSet(int x){
        // he not is the boss start recursion
        if(setFather[x] != x) {
            setFather[x] = findSet(setFather[x]);
            return setFather[x];
        }
        else
            return x;
    }

    private static void printSetFatherState(){
        System.out.println("SetFatherState");
        for (int i = 0; i < 16; i ++ ){
            for (int j = 0 ;j < 16; j ++){
                System.out.printf("%3d ", setFather[i * 16 + j]);
            }
            System.out.println("");
        }
    }
    private static void printSetQiState(){
        System.out.println("SetQiState");
        for (int i = 0; i < 16; i ++ ){
            for (int j = 0 ;j < 16; j ++){
                System.out.printf("%3d ",setQiSize[i * 16 + j]);
            }
            System.out.println("");
        }
    }
    // merge two set
    private static void mergeSameColor(int a, int b){
        int fa = findSet(a);
        int fb = findSet(b);
        // fine set Qi
        if(fa != fb){
            setQiSize[fb] = setQiSize[fa] + setQiSize[fb] - 2;
            setFather[fa] = fb;
        }
    }

    // been eaten => switch back to none
    public static void renderEat(int x){
        // render the chessboard if some chessman is eaten
        // replace buttons whose setFather is x with empty bg
        for (int i = 0; i < 16*16; i++) {
            if(findSet(i) == x){
                // set empty background
                chess.buttons[i].setIcon(null);
            }
        }
    }
    private MouseListener hover = new MouseListener() {
        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e){
            JButton temp = (JButton) e.getSource();
            // get the index
            int tempIndex = chess.btnToInt.get(temp);
            // able to play here
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
                if(!validator.checkValid(chess.state, tempIndex, currentPlayer)){
                    JOptionPane.showMessageDialog(null,"Same situation occur before", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // set player
                chess.state[tempIndex] = currentPlayer;
                ImageIcon icon = new ImageIcon();
                if (chess.state[tempIndex] == 0) {
                    icon = new ImageIcon("static/black.png");
                } else if (chess.state[tempIndex] == 1) {
                    icon = new ImageIcon("static/white.png");
                }
                // check 4 side if any of their qi goes down to 0;
                int tempx = tempIndex / 16, tempy = tempIndex % 16;
                for (int i = 0; i < 4; i ++)
                {
                    int x = tempx + dx[i], y = tempy  + dy[i];
                    if(x >= 0 && x < 16 && y >= 0 && y < 16 && chess.state[x * 16 + y] != -1){
                        int newIndex = x * 16 + y;
                        if(chess.state[tempIndex] == chess.state[newIndex]){
                            // same color
                            mergeSameColor(tempIndex, newIndex);
                        }
                        else{
                            // not the same color
                            setQiSize[findSet(tempIndex)] -- ;
                            setQiSize[findSet(newIndex)] -- ;
                            if(setQiSize[findSet(newIndex)] == 0){
                                renderEat(findSet(newIndex));
                            }
                        }
                    }
                }
                printSetFatherState();
                printSetQiState();
                Image temp1 = icon.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
                icon = new ImageIcon(temp1);
                chess.buttons[tempIndex].setIcon(icon);
                chess.repaint();
                currentPlayer ^= 1;
                if(currentPlayer == 0)
                    chess.Jtitle.setText("Player 1's turn\uD83D\uDC15");
                else
                    chess.Jtitle.setText("Player 2's turn\uD83D\uDC14");
            }
        }
    };

    public GoNormalGame(){
        end = false;
        currentPlayer = 0;
        chess = new chessBoard("Normal Game mode\uD83E\uDD3A");
        chess.Jtitle.setText("Player 1's turn\uD83D\uDC15");
        validator = new GoGameVaildator();
        for (int i = 0; i < 16; i ++ ){
            for (int j = 0; j < 16; j ++){
                int index = i * 16 + j;
                if(i == 0 || i == 15 || j == 0 || j == 15)
                    setQiSize[index] = 3;
                else
                    setQiSize[index] = 4;
            }
        }
        setQiSize[0] = setQiSize[15] = setQiSize[15 * 16] = setQiSize[15 * 16 + 15] = 2;
        for (int i = 0; i < 16*16; i ++) {
            // initialize union
            setFather[i] = i;
            chess.buttons[i].addActionListener(click);
            chess.buttons[i].addMouseListener(hover);
        }
    }
}