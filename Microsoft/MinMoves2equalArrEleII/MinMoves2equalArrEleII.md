# Minimum Moves to Equal Array Elements II

## Problem Statement

Given an integer array `nums` of size `n`, return the minimum number of moves required to make all array elements equal. In one move, you can increment or decrement an element of the array by 1. Test cases are designed such that the answer will fit in a 32-bit integer.

### Examples

#### Example 1:

**Input:** `nums = [1,2,3]`\
**Output:** `2`\
**Explanation:**

- Initial array: [1, 2, 3]
- Step 1: [1, 2, 3] → [2, 2, 3]
- Step 2: [2, 2, 3] → [2, 2, 2]

#### Example 2:

**Input:** `nums = [1,10,2,9]`\
**Output:** `16`\
**Explanation:**

- Changes required to move all elements to a common value. The minimum moves are 16.

### Constraints

- `n == nums.length`
- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach

To minimize the number of moves required to make all elements equal, observe that the optimal value to converge all elements is the **median** of the array. The median minimizes the sum of absolute differences, which is essential for this problem.

### Steps to Solve:

1. **Sort the Array:**

   - Sorting the array ensures that elements are in increasing order. This helps in efficiently determining the median.
   - Time Complexity: `O(N log N)`.

2. **Calculate the Median:**

   - For an odd-length array, the median is the middle element.
   - For an even-length array, the median can be any value between the two middle elements as the absolute difference cost remains the same.

3. **Calculate Total Steps:**

   - Iterate through the array and calculate the sum of absolute differences between each element and the median.
   - This represents the total number of moves required to make all elements equal.

### Alternate Two-Pointer Optimization

Instead of explicitly calculating the median:

- Use two pointers (`i` starting from the beginning, `j` from the end).
- Accumulate the difference between corresponding pairs as they "move" towards each other.
- This avoids the explicit use of the median and achieves the same result.

Time Complexity: `O(N log N)`\
Space Complexity: `O(1)`

### Approach Using Quickselect (Median Finding)

Quickselect is an efficient algorithm to find the k-th smallest element in an unordered list without fully sorting the array. To solve this problem:

1. **Find the Median Using Quickselect:**
   - Partition the array and recursively locate the median.
   - This approach works in `O(N)` average time complexity.

2. **Calculate Total Steps:**
   - Iterate through the array and compute the sum of absolute differences between each element and the median.

Time Complexity: `O(N)` (average)\
Space Complexity: `O(1)`

### Approach Using Mean (Suboptimal)

Though the median minimizes the sum of absolute differences, the mean minimizes the sum of squared differences. This approach can be used as a quick approximation but may not yield the correct minimum steps for this problem.

1. **Calculate the Mean:**
   - Compute the arithmetic mean of the array.
   - Round the mean to the nearest integer.

2. **Calculate Total Steps:**
   - Compute the sum of absolute differences between each element and the rounded mean.

Time Complexity: `O(N)`\
Space Complexity: `O(1)`

Note: This approach is not recommended for exact results as it does not guarantee the minimum moves.

---

## Dry Run

### Input:

`nums = [1, 10, 2, 9]`

### Step-by-Step Execution:

1. **Sort the Array:**

   - Original: [1, 10, 2, 9]
   - Sorted: [1, 2, 9, 10]

2. **Two-Pointer Calculation:**

   - Initialize `steps = 0`, `i = 0`, `j = 3`.
   - **Iteration 1:**
     - Difference: `nums[j] - nums[i] = 10 - 1 = 9`
     - `steps = 9`, `i = 1`, `j = 2`
   - **Iteration 2:**
     - Difference: `nums[j] - nums[i] = 9 - 2 = 7`
     - `steps = 9 + 7 = 16`, `i = 2`, `j = 1`

3. **Final Result:**

   - Total `steps = 16`.

### Output:

`16`

---

## Code

### Two-Pointer Approach

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums); // O(N log N)
        int i = 0, j = nums.length - 1;
        int steps = 0;
        while (i < j) {
            steps += (nums[j] - nums[i]);
            i++;
            j--;
        }
        return steps;
    }
}
```

### Quickselect Approach

```java
class Solution {
    public int minMoves2(int[] nums) {
        int median = quickSelect(nums, nums.length / 2);
        int steps = 0;
        for (int num : nums) {
            steps += Math.abs(num - median);
        }
        return steps;
    }

    private int quickSelect(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == k) return nums[pivot];
            else if (pivot < k) left = pivot + 1;
            else right = pivot - 1;
        }
        return nums[left];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

