import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TwoPointers {
    public static int[] targetSum(int[] arr, int targetSum) {
        // Time: O(n), Space: O(1)
        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 < p2) {
            if (arr[p1] + arr[p2] == targetSum)
                break;
            else if (arr[p1] + arr[p2] > targetSum)
                p2--;
            else
                p1++;
        }
        return new int[] { p1, p2 };
    }

    public static int removeDuplicates(int[] arr) {
        // Time: O(n), Space: O(1)
        int count = 1;
        int p1 = 0;
        int p2 = 1;
        while (p2 < arr.length) {
            if (arr[p1] == arr[p2]) {
                p2++;
            } else {
                p1 = p2;
                p2++;
                count++;
            }
        }
        return count;
    }

    public static int[] makeSquares(int[] arr) {
        // Time: O(n), Space: O(n)
        // iterate array only one; take up space for new array
        int[] squares = new int[arr.length];
        int p1 = 0;
        int p2 = arr.length - 1;
        int curIndex = arr.length - 1;
        while (p1 <= p2) {
            if (Math.pow(arr[p1], 2) > Math.pow(arr[p2], 2)) {
                squares[curIndex--] = (int) Math.pow(arr[p1], 2); // need to cast because Math.pow will return a double
                p1++;
            } else {
                squares[curIndex--] = (int) Math.pow(arr[p2], 2);
                p2--;
            }
        }
        return squares;
    }

    public static List<List<Integer>> searchTriplets(int[] arr) {
        // Time: O(n^2), Space: O(n)
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(arr); // unfortunately, need to sort the array
        for (int curIndex = 0; curIndex < arr.length - 2; curIndex++) {
            if (curIndex > 0 && arr[curIndex] == arr[curIndex + 1]) {
                continue;
            }
            int p1 = curIndex + 1;
            int p2 = arr.length - 1;
            int curTarget = -arr[curIndex];
            while (p1 < p2) {
                if (arr[p1] + arr[p2] == curTarget) {
                    List<Integer> trip = Arrays.asList(arr[p1], arr[p2], arr[curIndex]);
                    triplets.add(trip);
                    p1++;
                    p2--;
                    while (p1 < p2 && arr[p1] == arr[p1 - 1]) {
                        p1++; // here we need to skip same element to avoid dupes
                    }
                    while (p1 < p2 && arr[p2] == arr[p2 + 1]) {
                        p2--; // here we need to skip same element to avoid dupes
                    }
                } else if (arr[p1] + arr[p2] < curTarget)
                    p1++;
                else
                    p2--;
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        System.out.println(searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
    }
}