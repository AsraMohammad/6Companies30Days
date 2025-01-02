  

---

### **Java Code: Russian Doll Envelopes Problem**

```java
import java.util.Arrays;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) {
            return 0; // No envelopes, return 0
        }

        // Sort envelopes by width, and by height in case of ties
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])));

        // Create a dp array to store the maximum number of envelopes for each index
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each envelope can be the start of a sequence

        int max = 1; // To track the maximum sequence length

        // Loop through each envelope to build dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if envelope i can fit into envelope j
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]); // Update the maximum
        }

        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] envelopes = {
            {5, 4},
            {6, 4},
            {6, 7},
            {2, 3}
        };
        System.out.println("Maximum number of envelopes: " + sol.maxEnvelopes(envelopes));
    }
}
```

---

### **Explanation (Step-by-Step)**

#### 1. **What is the problem?**
The goal is to find the maximum number of envelopes you can "Russian doll" (nest inside each other).  
An envelope can fit inside another if both its width and height are smaller.

#### 2. **Steps to solve the problem:**

1. **Sort the envelopes**:
   - Sort envelopes by width. If two envelopes have the same width, sort them by height.
   - This makes sure the envelopes are considered in increasing width.

2. **Use Dynamic Programming**:
   - Create an array `dp[]` where `dp[i]` represents the maximum number of envelopes that can end at envelope `i`.
   - For every envelope `i`, check all previous envelopes (`j < i`). If envelope `j` can fit into envelope `i`, update `dp[i] = max(dp[i], dp[j] + 1)`.

3. **Track the maximum**:
   - After calculating `dp[i]` for all envelopes, find the maximum value in `dp[]`.

---

### **Dry Run**

#### Input:
```plaintext
envelopes = [
    {5, 4},
    {6, 4},
    {6, 7},
    {2, 3}
]
```

#### **Step 1: Sort envelopes**
Sorted order:  
```plaintext
{2, 3}, {5, 4}, {6, 4}, {6, 7}
```

#### **Step 2: Initialize DP**
`dp = [1, 1, 1, 1]` (each envelope can at least hold itself).

#### **Step 3: Iterate and Update DP**
- **i = 1** (`{5, 4}`):  
  Compare with **j = 0** (`{2, 3}`):  
  - `{5, 4}` can fit `{2, 3}` → `dp[1] = dp[0] + 1 = 2`.  
  `dp = [1, 2, 1, 1]`.

- **i = 2** (`{6, 4}`):  
  Compare with **j = 0** (`{2, 3}`):  
  - `{6, 4}` can fit `{2, 3}` → `dp[2] = dp[0] + 1 = 2`.  
  Compare with **j = 1** (`{5, 4}`):  
  - `{6, 4}` cannot fit `{5, 4}` (heights are equal).  
  `dp = [1, 2, 2, 1]`.

- **i = 3** (`{6, 7}`):  
  Compare with **j = 0** (`{2, 3}`):  
  - `{6, 7}` can fit `{2, 3}` → `dp[3] = dp[0] + 1 = 2`.  
  Compare with **j = 1** (`{5, 4}`):  
  - `{6, 7}` can fit `{5, 4}` → `dp[3] = dp[1] + 1 = 3`.  
  Compare with **j = 2** (`{6, 4}`):  
  - `{6, 7}` can fit `{6, 4}` → `dp[3] = dp[2] + 1 = 3` (no change).  
  `dp = [1, 2, 2, 3]`.

#### **Step 4: Find Max**
Maximum value in `dp[] = 3`.

---

### **Output**
```plaintext
Maximum number of envelopes: 3
```

---

### Key Takeaways
1. **Sorting helps structure the problem**: By sorting by width and height, we simplify comparisons.
2. **Dynamic programming captures relationships**: We build on the subsequences by checking valid nesting conditions.
3. **Efficiency**: The solution is \(O(n^2)\) due to nested loops. With advanced techniques (like binary search), this can be improved to \(O(n \log n)\).

 

---

### **1. Sorting the Envelopes**
```java
Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])));
```

#### **What does this line do?**
We are sorting the `envelopes` array using **custom sorting logic**. Here’s what happens:

1. **`a` and `b` are two envelopes being compared**:
   - `a[0]` and `b[0]` are the widths of the two envelopes.
   - `a[1]` and `b[1]` are the heights of the two envelopes.

2. **Sorting Rules**:
   - If the widths are the same (`a[0] == b[0]`), compare the heights (`Integer.compare(a[1], b[1])`).
   - Otherwise, compare the widths (`Integer.compare(a[0], b[0])`).

#### **Why do we need to sort?**
Sorting organizes the envelopes by increasing width. This is necessary because the problem requires checking increasing sequences of both width and height.

#### **Example**
```java
envelopes = {
    {5, 4},
    {6, 4},
    {6, 7},
    {2, 3}
};
```
After sorting:
```java
envelopes = {
    {2, 3},
    {5, 4},
    {6, 4},
    {6, 7}
};
```
Now the envelopes are in increasing order of width, and within the same width, increasing order of height.

---

### **2. Checking If One Envelope Can Fit into Another**
```java
if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
    dp[i] = Math.max(dp[i], dp[j] + 1);
}
```

#### **What does this line do?**

- `i` is the current envelope we are considering.
- `j` is a previous envelope that we are comparing with.

**Condition:**
- `envelopes[i][0] > envelopes[j][0]`: The width of envelope `i` is greater than the width of envelope `j`.
- `envelopes[i][1] > envelopes[j][1]`: The height of envelope `i` is greater than the height of envelope `j`.

If both conditions are true, it means **envelope `i` can fit into envelope `j`**.

**Action:**
- Update `dp[i]` to the maximum of its current value or `dp[j] + 1`. This means:
  - The longest sequence ending at envelope `i` can be extended by including envelope `j`.

---

### **Why do we use `Math.max`?**

We use `Math.max(dp[i], dp[j] + 1)` to ensure that:
- `dp[i]` always holds the **longest increasing sequence length** ending at index `i`.

---

### **Example**

Let’s use the sorted envelopes:  
`envelopes = {{2, 3}, {5, 4}, {6, 4}, {6, 7}}`

#### Step-by-Step
1. **Initialization**:  
   `dp = [1, 1, 1, 1]` (Each envelope can be a sequence of length 1 by itself).

2. **i = 1** (`{5, 4}`):
   - Compare with `j = 0` (`{2, 3}`):
     - `envelopes[1][0] > envelopes[0][0]` → `5 > 2` (True)
     - `envelopes[1][1] > envelopes[0][1]` → `4 > 3` (True)
     - Update: `dp[1] = Math.max(dp[1], dp[0] + 1) = 2`.  
   - `dp = [1, 2, 1, 1]`.

3. **i = 2** (`{6, 4}`):
   - Compare with `j = 0` (`{2, 3}`):
     - `envelopes[2][0] > envelopes[0][0]` → `6 > 2` (True)
     - `envelopes[2][1] > envelopes[0][1]` → `4 > 3` (True)
     - Update: `dp[2] = Math.max(dp[2], dp[0] + 1) = 2`.
   - Compare with `j = 1` (`{5, 4}`):
     - `envelopes[2][0] > envelopes[1][0]` → `6 > 5` (True)
     - `envelopes[2][1] > envelopes[1][1]` → `4 > 4` (False).  
     - No update.  
   - `dp = [1, 2, 2, 1]`.

4. **i = 3** (`{6, 7}`):
   - Compare with `j = 0` (`{2, 3}`):
     - `envelopes[3][0] > envelopes[0][0]` → `6 > 2` (True)
     - `envelopes[3][1] > envelopes[0][1]` → `7 > 3` (True)
     - Update: `dp[3] = Math.max(dp[3], dp[0] + 1) = 2`.
   - Compare with `j = 1` (`{5, 4}`):
     - `envelopes[3][0] > envelopes[1][0]` → `6 > 5` (True)
     - `envelopes[3][1] > envelopes[1][1]` → `7 > 4` (True)
     - Update: `dp[3] = Math.max(dp[3], dp[1] + 1) = 3`.
   - Compare with `j = 2` (`{6, 4}`):
     - `envelopes[3][0] > envelopes[2][0]` → `6 > 6` (False).  
     - No update.  
   - `dp = [1, 2, 2, 3]`.

---

### **Final DP Array**
`dp = [1, 2, 2, 3]`

The maximum value in `dp[]` is `3`. This means the maximum number of envelopes you can nest is **3**.

---

 
