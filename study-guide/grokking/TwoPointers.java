import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
            if (Math.pow(arr[p1], 2) > Math.pow(arr[p2], 2)) { // searching for biggest square at beginning and end of
                                                               // array, since we are working with negative numbers
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
        // here we are trying to find unique triplets that equal 0
        // can approach using X + Y + Z = 0 which can then be approached
        // as X + Y = -Z
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(arr); // unfortunately, need to sort the array
        for (int curIndex = 0; curIndex < arr.length - 2; curIndex++) { // curIndex acts as pointer for Z
            if (curIndex > 0 && arr[curIndex] == arr[curIndex + 1]) { // we want to avoid triplets for the same values
                continue;
            }
            int p1 = curIndex + 1; // p1 acts as pointer for X
            int p2 = arr.length - 1; // p2 acts as pointer for Y
            int curTarget = -arr[curIndex]; // curTarget acts as our -Z value
            while (p1 < p2) { // while X does not equal Y
                if (arr[p1] + arr[p2] == curTarget) { // X + Y = -Z, add to triplets
                    List<Integer> trip = Arrays.asList(arr[p1], arr[p2], arr[curIndex]);
                    triplets.add(trip);
                    p1++; // increment X
                    p2--; // decrement Y
                    while (p1 < p2 && arr[p1] == arr[p1 - 1]) {
                        p1++; // here we need to skip same element to avoid dupes
                    }
                    while (p1 < p2 && arr[p2] == arr[p2 + 1]) {
                        p2--; // here we need to skip same element to avoid dupes
                    }
                } else if (arr[p1] + arr[p2] < curTarget) // if X + Y < -Z, increment X
                    p1++;
                else // if X + Y > -Z, decrement Y
                    p2--;
            }
        }
        return triplets;
    }

    public static int searchTriplets(int[] arr, int target) {
        int count = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int p1 = i + 1;
            int p2 = arr.length - 1;
            while (p1 < p2) {
                if (arr[p1] + arr[p2] < target - arr[i]) {
                    count += p2 - p1;
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return count;
    }

    public static int searchTripletForNearest(int[] arr, int targetSum) {
        // Time: O(n^2), Space: O(n)
        // here we can approach similarly to the previous problem, except we are now
        // searching for the the closest possible sum to the target among triplets
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE; // need to keep track of the current minimum difference
        for (int curIndex = 0; curIndex < arr.length - 2; curIndex++) {
            int p1 = curIndex + 1; // p1 is our X pointer
            int p2 = arr.length - 1; // p2 is our Y pointer
            int curVal = arr[curIndex]; // curVal acts as our Z value
            while (p1 < p2) {
                int curMin = targetSum - curVal - arr[p1] - arr[p2]; // the currentMin difference from target sum is
                                                                     // (target - (X + Y + Z))
                if (curMin == 0) // the minimum difference is 0, we have found the closest minimum difference
                    return targetSum;
                if (Math.abs(curMin) < Math.abs(minDiff) // if the abs(currentMin) is smaller than the abs(savedMin) OR
                                                         // if
                                                         // the abs(currentMin) is the same as the abs(savedMin) AND the
                                                         // currentMin is a greater value than the savedMin
                        || Math.abs(curMin) == Math.abs(minDiff) && curMin > minDiff) {
                    minDiff = curMin; // then change the savedMin to the currentMin
                }
                if (curMin > 0)
                    p1++;
                else
                    p2--;
            }
        }
        return targetSum - minDiff;
    }

    public static int searchTripletWithSmallerSum(int[] arr, int target) {
        // Time: O(n^2), Space: O(n)
        // again, similar approach to previous problems, but needing to find triplet
        // that will be smaller than the target sum, can approach using
        // X + Y < target - Z
        int count = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int p1 = i + 1; // X
            int p2 = arr.length - 1; // Y
            while (p1 < p2) {
                if (arr[p1] + arr[p2] < target - arr[i]) { // X + Y < target - Z
                    count += p2 - p1; // if true, all values between index p2 and p1 will satify this condition
                                      // because the array is sorted
                    p1++; // increment X
                } else {
                    p2--; // we need a pair with a smaller sum
                }
            }
        }
        return count;
    }

    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        // Time: O(n^3), Space: O(n^3)
        // in the worst case, we will have n(n+1)/2 choices of indices
        // this problem uses a combination of sliding window and two pointers
        List<List<Integer>> subarrays = new ArrayList<>();
        double product = 1;
        int p1 = 0;
        for (int i = 0; i < arr.length; i++) {
            product *= arr[i]; // we increment our product by the next value in the array
            while (product >= target && p1 < arr.length) { // if the product is larger than our target value and p1 is
                                                           // smaller than the array length, then we can divide our
                                                           // product by the left pointer
                product /= arr[p1++];
            }
            List<Integer> temp = new LinkedList<>(); // create a temporary list to iterate through to capture subarrays
            for (int cur = i; cur >= p1; cur--) { // i and cur act as our p2 or left pointer, so as long as p2 >= p1
                temp.add(0, arr[cur]); // we want to add the left most val to our linked list
                subarrays.add(new ArrayList<>(temp)); // to then add to our subarrays list; as the for-loop decrements,
                                                      // more values are added to the temp list and further added to the
                                                      // subarrays list
            }
        }
        return subarrays;
    }

    public static void main(String[] args) {
        System.out.println(searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
    }
}