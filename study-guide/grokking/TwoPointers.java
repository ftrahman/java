public class TwoPointers {
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
}