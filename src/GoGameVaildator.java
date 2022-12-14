import java.util.HashMap;
import java.util.HashSet;

public class GoGameVaildator {
    HashSet<int[]> set;
    public boolean checkValid(int[] state, int place, int player){
        int[] newState = state.clone();
        newState[place] = player;
        return !set.contains(newState);
    }
    public void update(int [] state){
        set.add(state);
    }
    public GoGameVaildator(){
        set = new HashSet<int[]>();
    }
}
