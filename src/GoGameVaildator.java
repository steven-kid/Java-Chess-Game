import java.util.HashMap;
import java.util.HashSet;

public class GoGameVaildator {
    HashSet<Integer> set;

    public static int mod = (int)1e9 + 7;

    int getHashValue(int[] arr){
        int hash = 31, ret = 0;
        for (int i = 0;i < 16 * 16; i ++){
            ret = (ret + arr[i] * hash % mod) % mod;
            hash = hash * 31 % mod;
        }
        return ret;
    }

    public boolean checkValid(int[] state, int place, int player){
        int[] newState = state.clone();
        newState[place] = player;
        System.out.println("set size = " + set.size());
        System.out.println("hash value = " + getHashValue(newState));
        for (int x: set){
            System.out.println(x);
        }
        return !set.contains(getHashValue(newState));
    }

    public boolean checkSuicide(int[] state, int place, int player){
        return false;
    }
    public void update(int [] state){
        set.add(getHashValue(state));
    }
    public GoGameVaildator(){
        set = new HashSet<Integer>();
    }
}
