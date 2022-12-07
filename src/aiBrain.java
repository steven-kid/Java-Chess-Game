public class aiBrain {

    public static int CHESSBOARD_SIZE = 8;

    public static int[][] score = new int[8][8];

    public static int searchLocation(int[] state){
        int[][] chessboard = new int[8][8];
        for (int i = 0; i < 8; i ++)
            System.arraycopy(state, i * 8, chessboard[i], 0, 8);
        //每次都初始化下score评分数组
        for(int i = 0; i  < CHESSBOARD_SIZE; i++){
            for(int j = 0; j < CHESSBOARD_SIZE; j++){
                score[i][j] = 0;
            }
        }

        //每次机器找寻落子位置，评分都重新算一遍（虽然算了很多多余的，因为上次落子时候算的大多都没变）
        //先定义一些变量
        int humanChessmanNum = 0;//五元组中的黑棋数量
        int machineChessmanNum = 0;//五元组中的白棋数量
        int tupleScoreTmp = 0;//五元组得分临时变量

        int goalX = -1;//目标位置x坐标
        int goalY = -1;//目标位置y坐标
        int maxScore = -1;//最大分数

        //1.扫描横向的8个行
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[i][k] == 0) machineChessmanNum++;
                    else if(chessboard[i][k] == 1)humanChessmanNum++;
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for(k = j; k < j + 5; k++){
                    score[i][k] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //2.扫描纵向8行
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[k][i] == 0) machineChessmanNum++;
                    else if(chessboard[k][i] == 1)humanChessmanNum++;
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for(k = j; k < j + 5; k++){
                    score[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //3.扫描右上角到左下角上侧部分
        for(int i = 7; i >= 4; i--){
            for(int k = i, j = 0; j < 8 && k >= 0; j++, k--){
                int m = k;
                int n = j;
                while(m > k - 5 && k - 5 >= -1){
                    if(chessboard[m][n] == 0) machineChessmanNum++;
                    else if(chessboard[m][n] == 1)humanChessmanNum++;

                    m--;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k-5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m > k - 5 ; m--, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //4.扫描右上角到左下角下侧部分
        for(int i = 1; i < 8; i++){
            for(int k = i, j = 7; j >= 0 && k < 8; j--, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 8){
                    if(chessboard[n][m] == 0) machineChessmanNum++;
                    else if(chessboard[n][m] == 1)humanChessmanNum++;
                    m++;
                    n--;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k+5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n--){
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //5.扫描左上角到右下角上侧部分
        for(int i = 0; i < 4; i++){
            for(int k = i, j = 0; j < 8 && k < 8; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 8){
                    if(chessboard[m][n] == 0) machineChessmanNum++;
                    else if(chessboard[m][n] == 1)humanChessmanNum++;

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //6.扫描左上角到右下角下侧部分
        for(int i = 1; i < 4; i++){
            for(int k = i, j = 0; j < 8 && k < 8; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= 8){
                    if(chessboard[n][m] == 0) machineChessmanNum++;
                    else if(chessboard[n][m] == 1)humanChessmanNum++;

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[n][m] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //从空位置中找到得分最大的位置
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] == -1 && score[i][j] > maxScore){
                    goalX = i;
                    goalY = j;
                    maxScore = score[i][j];
                }
            }
        }
        if(goalX != -1 && goalY != -1){
            return goalX * 8 + goalY;
        }
        //没找到坐标说明平局了，笔者不处理平局
        return -1;
    }

    //各种五元组情况评分表
    public static int tupleScore(int humanChessmanNum, int machineChessmanNum){
        //1.既有人类落子，又有机器落子，判分为0
        if(humanChessmanNum > 0 && machineChessmanNum > 0){
            return 0;
        }
        //2.全部为空，没有落子，判分为7
        if(humanChessmanNum == 0 && machineChessmanNum == 0){
            return 7;
        }
        //3.机器落1子，判分为35
        if(machineChessmanNum == 1){
            return 35;
        }
        //4.机器落2子，判分为800
        if(machineChessmanNum == 2){
            return 800;
        }
        //5.机器落3子，判分为8000
        if(machineChessmanNum == 3){
            return 8000;
        }
        //6.机器落4子，判分为800000
        if(machineChessmanNum == 4){
            return 800000;
        }
        //7.人类落1子，判分为8
        if(humanChessmanNum == 1){
            return 8;
        }
        //8.人类落2子，判分为400
        if(humanChessmanNum == 2){
            return 400;
        }
        //9.人类落3子，判分为1800
        if(humanChessmanNum == 3){
            return 1800;
        }
        //10.人类落4子，判分为100000
        if(humanChessmanNum == 4){
            return 100000;
        }
        return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
    }
}
