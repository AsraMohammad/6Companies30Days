 
### Java Code: Longest Increasing Subsequence (LIS)
```java
import java.util.Arrays;

public class LIS {

    // Method to find the length of the longest increasing subsequence
    static int lis(int[] arr, int n) {
        int[] lis = new int[n]; // Array to store LIS values for each index
        Arrays.fill(lis, 1); // Initialize all values in lis[] to 1

        // Build LIS values for each index in a bottom-up manner
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1; // Update LIS value
                }
            }
        }

        // Find the maximum value in the LIS array
        int maxLIS = 0;
        for (int i = 0; i < n; i++) {
            maxLIS = Math.max(maxLIS, lis[i]);
        }

        return maxLIS; // Return the length of the LIS
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
        int n = arr.length;
        System.out.println("Length of LIS is " + lis(arr, n));
    }
}
```

---

### Explanation (Step-by-Step)

1. **What is the problem?**
   - We are finding the **longest increasing subsequence (LIS)** in an array.
   - A subsequence is a sequence derived by removing elements without reordering the remaining ones.
   - Example: For array `[10, 22, 9, 33, 21, 50]`, the LIS is `[10, 22, 33, 50]` with length 4.

2. **How does the algorithm work?**
   - Create an array `lis[]` where `lis[i]` stores the length of the LIS ending at index `i`.
   - Start with `lis[i] = 1` for all indices since every element itself can be a subsequence of length 1.
   - For each element, check all previous elements. If `arr[i] > arr[j]`, it means `arr[i]` can extend the subsequence ending at `arr[j]`. Update `lis[i] = lis[j] + 1` if it results in a longer LIS.

3. **Final step:**
   - Find the maximum value in the `lis[]` array. This value is the length of the longest increasing subsequence.

---

### Dry Run

Given array: `[10, 22, 9, 33, 21, 50, 41, 60]`

**Initialization:**
- `lis[] = [1, 1, 1, 1, 1, 1, 1, 1]` (all values are 1)

**Processing:**

1. **i = 1 (arr[1] = 22)**  
   Compare with `arr[0] = 10`:  
   `22 > 10`, so `lis[1] = lis[0] + 1 = 2`.  
   `lis[] = [1, 2, 1, 1, 1, 1, 1, 1]`.

2. **i = 2 (arr[2] = 9)**  
   Compare with `arr[0] = 10` and `arr[1] = 22`:  
   No updates since `9` is not greater than any of them.  
   `lis[] = [1, 2, 1, 1, 1, 1, 1, 1]`.

3. **i = 3 (arr[3] = 33)**  
   Compare with `arr[0] = 10`, `arr[1] = 22`, `arr[2] = 9`:  
   - `33 > 10`, so `lis[3] = lis[0] + 1 = 2`.  
   - `33 > 22`, so `lis[3] = lis[1] + 1 = 3`.  
   `lis[] = [1, 2, 1, 3, 1, 1, 1, 1]`.

4. **i = 4 (arr[4] = 21)**  
   Compare with `arr[0] = 10`, `arr[1] = 22`, `arr[2] = 9`, `arr[3] = 33`:  
   - `21 > 10`, so `lis[4] = lis[0] + 1 = 2`.  
   `lis[] = [1, 2, 1, 3, 2, 1, 1, 1]`.

5. **i = 5 (arr[5] = 50)**  
   Compare with all previous elements:  
   - `50 > 10`, `lis[5] = lis[0] + 1 = 2`.  
   - `50 > 22`, `lis[5] = lis[1] + 1 = 3`.  
   - `50 > 33`, `lis[5] = lis[3] + 1 = 4`.  
   `lis[] = [1, 2, 1, 3, 2, 4, 1, 1]`.

6. **i = 6 (arr[6] = 41)**  
   Compare with all previous elements:  
   - `41 > 10`, `lis[6] = lis[0] + 1 = 2`.  
   - `41 > 22`, `lis[6] = lis[1] + 1 = 3`.  
   - `41 > 33`, `lis[6] = lis[3] + 1 = 4`.  
   `lis[] = [1, 2, 1, 3, 2, 4, 4, 1]`.

7. **i = 7 (arr[7] = 60)**  
   Compare with all previous elements:  
   - `60 > 10`, `lis[7] = lis[0] + 1 = 2`.  
   - `60 > 22`, `lis[7] = lis[1] + 1 = 3`.  
   - `60 > 33`, `lis[7] = lis[3] + 1 = 4`.  
   - `60 > 50`, `lis[7] = lis[5] + 1 = 5`.  
   `lis[] = [1, 2, 1, 3, 2, 4, 4, 5]`.

**Result:**
- Maximum value in `lis[]` is `5`.  
- Longest Increasing Subsequence length = **5**.

---

### Output
```
Length of LIS is 5
```
