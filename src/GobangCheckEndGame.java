public class GobangCheckEndGame {
    public static int [] dx = {1, 1, 0, -1};
    public static int [] dy = {0, 1, 1, 1};
    public static boolean checkState(chessBoard chess){
        for (int i = 0; i < 16*16; i++) {
            int tempState = chess.state[i];
            // no chessman yet
            if(tempState == -1)
                continue;
            int x = i / 16;
            int y = i % 16;
            // four in a row
            for (int j = 0; j < 4; j ++)
            {
                boolean win = true;
                for(int len = 1; len <= 4; len++)
                {
                    int tempx = x + dx[j] * len, tempy = y + dy[j] * len;
                    int index = tempx * 16 + tempy;
                    if(index < 0 || index >= 16 * 16 || chess.state[index] != tempState)
                    {
                        win = false;
                        break;
                    }
                }
                if(win)
                    return true;
            }
        }
        // no valid start chess
        return false;
    }
}
