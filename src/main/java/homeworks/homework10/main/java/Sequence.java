import java.util.ArrayList;
import java.util.List;

public class Sequence {

    public static int[] filter(int[] array, ByCondition condition) {
        List<Integer> result = new ArrayList<>();
        for (int num : array) {
            if (condition.isOk(num)) {
                result.add(num);
            }
        }

        int[] filtered = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            filtered[i] = result.get(i);
        }
        return filtered;
    }
}
