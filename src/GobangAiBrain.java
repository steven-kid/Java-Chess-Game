public class GobangAiBrain {

    public static int CHESSBOARD_SIZE = 16;
    // score table
    public static int[][] score = new int[16][16];

    public static int searchLocation(int[] state){
        int humanChessmanNum = 0;//五元组中的黑棋数量
        int machineChessmanNum = 0;// human's' chess number
        int tupleScoreTmp = 0;//temporary score

        int goalX = -1;//goal position x
        int goalY = -1;//goal position y
        int maxScore = -1;//maxScore
        int[][] chessboard = new int[16][16];
        for (int i = 0; i < 16; i ++)
            System.arraycopy(state, i * 16, chessboard[i], 0, 16);
        //initialize the score table
        for(int i = 0; i  < CHESSBOARD_SIZE; i++){
            for(int j = 0; j < CHESSBOARD_SIZE; j++){
                score[i][j] = 0;
            }
        }


        //horizontal
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 12; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[i][k] == 0) machineChessmanNum++;
                    else if(chessboard[i][k] == 1)humanChessmanNum++;
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //add score
                for(k = j; k < j + 5; k++){
                    score[i][k] += tupleScoreTmp;
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;
            }
        }
        // vertical
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 12; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[k][i] == 0) machineChessmanNum++;
                    else if(chessboard[k][i] == 1)humanChessmanNum++;
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //add score
                for(k = j; k < j + 5; k++){
                    score[k][i] += tupleScoreTmp;
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;
            }
        }
        // upperright to downleft
        for(int i = 15; i >= 4; i--){
            for(int k = i, j = 0; j < 16 && k >= 0; j++, k--){
                int m = k;
                int n = j;
                while(m > k - 5 && k - 5 >= -1){
                    if(chessboard[m][n] == 0) machineChessmanNum++;
                    else if(chessboard[m][n] == 1)humanChessmanNum++;
                    m--;
                    n++;
                }
                if(m == k-5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    for(m = k, n = j; m > k - 5 ; m--, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;

            }
        }
        // upperright to downleft
        for(int i = 1; i < 16; i++){
            for(int k = i, j = 15; j >= 0 && k < 16; j--, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 15){
                    if(chessboard[n][m] == 0) machineChessmanNum++;
                    else if(chessboard[n][m] == 1)humanChessmanNum++;
                    m++;
                    n--;
                }
                // side pruning
                if(m == k+5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    for(m = k, n = j; m < k + 5; m++, n--){
                        score[n][m] += tupleScoreTmp;
                    }
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;
            }
        }

        //upperleft to upperright
        for(int i = 0; i < 12; i++){
            for(int k = i, j = 0; j < 16 && k < 16; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 16){
                    if(chessboard[m][n] == 0) machineChessmanNum++;
                    else if(chessboard[m][n] == 1)humanChessmanNum++;

                    m++;
                    n++;
                }
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;

            }
        }

        // upperleft to downright
        for(int i = 0; i < 12; i++){
            for(int k = i, j = 0; j < 16 && k < 16; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 16){
                    if(chessboard[n][m] == 0) machineChessmanNum++;
                    else if(chessboard[n][m] == 1)humanChessmanNum++;
                    m++;
                    n++;
                }
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[n][m] += tupleScoreTmp;
                    }
                }
                humanChessmanNum = 0;
                machineChessmanNum = 0;
                tupleScoreTmp = 0;
            }
        }

        //get max points
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                if(chessboard[i][j] == -1 && score[i][j] > maxScore){
                    goalX = i;
                    goalY = j;
                    maxScore = score[i][j];
                }
            }
        }
        // return ai's choice
        if(goalX != -1 && goalY != -1){
            return goalX * 16 + goalY;
        }
        // error return
        return -1;
    }

    //score table
    public static int tupleScore(int humanChessmanNum, int machineChessmanNum){
        //has chess
        if(humanChessmanNum > 0 && machineChessmanNum > 0){
            return 0;
        }
        //normal
        if(humanChessmanNum == 0 && machineChessmanNum == 0){
            return 7;
        }
        //1 white
        if(machineChessmanNum == 1){
            return 35;
        }
        //2 white
        if(machineChessmanNum == 2){
            return 800;
        }
        //3 white(connect to four)
        if(machineChessmanNum == 3){
            return 8000;
        }
        //4 machine(win)
        if(machineChessmanNum == 4){
            return 800000;
        }
        //1 human
        if(humanChessmanNum == 1){
            return 8;
        }
        //2 human
        if(humanChessmanNum == 2){
            return 400;
        }
        //3 human
        if(humanChessmanNum == 3){
            return 1800;
        }
        //4 human
        if(humanChessmanNum == 4){
            return 100000;
        }
        // wrong case
        return -1;
    }
}