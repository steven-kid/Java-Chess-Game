### Chess Game🚩(Two mode)

#### Feature
- AI(based on pytorch) Player1 vs PC🤖
- one on one(Player1 vs Player2)

#### Java Classes
- Start Frame
  - (choose the mode)
- Chess Board(gui using swing framework)
  - set background image
  - set chessPiece 
- Play Game
  - check end game🔚 return winner 🏅
  - add event listener on buttons
  - communicate with ai(if on ai mode)
  - timer for play time🕔
- Ai Frame
  - ui frame for AI mode 
- Normal Frame
  - ui frame for normal mode 

#### Images
- chessboard.png(background)
- chessWhite.png 
- chessBlack.png

#### Sounds 🎵
- win.mp3
- bgm.mp3

#### Difficulties
How to load a background Icon 
   - solution -> just don't load it (Using background Color to initialize the chess board)
How to execute python in java
   - 
How to bind Action listener on a list of items
- using getSource() and convert it into JButtons, create a hashmap to get the index base on buttons

#### States statement
- state[i] = -1 not visited yet
- state[i] = 0 visited by player 0(Black chess)
- state[i] = 1 visited by player 1(White chess) 
 
#### Bug
A bad implement of paint function
A bad for loop which cause the chessboard response very slow
``` java
public void paint(){
    for (int i = 0; i < 16*16; i ++) {
        ImageIcon icon = new ImageIcon();
        if(state[i] == -1){
            continue;
        }else if (state[i] == 0){
             icon = new ImageIcon("static/black.png");
        }else if (state[i] == 1){
             icon = new ImageIcon("static/white.png");
        }
        Image temp1 = icon.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
        icon = new ImageIcon(temp1);
        buttons[i].setIcon(icon);
    }
    this.repaint();
}
```

Go logic
- check if the states did appear before ban it
- on update 
  - check side block color
  - if same(they are connected block qi1 + qi2 - 2 => both & connect to the same union)
  - else (their qi minus 1 => both)

Reference
A marking method using alpha-beta pruning
https://blog.csdn.net/u011587401/article/details/50877828

update: 11/12
make chessboard large
(considering adding a go game)
