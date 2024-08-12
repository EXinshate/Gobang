import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class test7 {
    public static void main(String[] args) {
        var arr1 = new int[]{3, -4, 25, 16, 30, 18};
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
        var arr2 = new int[] {3, -4, 25, 16, 30, 18};
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        System.out.println(Arrays.toString(arr2));
    }
}
