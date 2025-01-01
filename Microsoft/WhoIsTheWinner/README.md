# Find the Winner of the Circular Game

## Problem Overview

You are given an integer `n` representing the number of people standing in a circle, numbered from 1 to `n` (1-indexed). Initially, the game starts with player number 1, and we count from this player `k` positions. The `k`-th player from the current player loses and exits the game. After a player is removed, the counting continues from the next player. You need to determine the index of the player who will win the game.

## Problem Input

- Integer `n` - the number of friends in the circle.
- Integer `k` - how many you count from the current player to eliminate the next player.

## Problem Output

- The 1-indexed index of the player who will win the game.

## Solution Approaches

### 1. **Simulation Approach**

**Intuition:**
We can simulate the circular game with an array. When we reach the last element in the array, we go back to the first element in the array. We use 1-indexed values to represent the original circle. To find the next player to be removed, we calculate their index using the formula:  
`(cur_ind + k - 1) % n`

**Algorithm:**
1. Initialize a list `circle` with integers from 1 to `n` representing the initial players.
2. Set the initial index `cur_ind` to 0.
3. While the length of the `circle` is greater than 1:
   - Calculate the index of the next player to be removed.
   - Remove the player at that index.
   - Update the `cur_ind` to the index of the removed player.
4. The last remaining player in the circle is the winner.

**Time Complexity:**  
O(n²), since you remove `n - 1` players using the `pop` operation (which is O(n)).

**Space Complexity:**  
O(n), due to the extra space required for the `circle` array.

**Code:**
```java
class Solution {
    public int findTheWinner(int n, int k) {
        ArrayList<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            circle.add(i);
        }
        int cur_ind = 0;

        while (circle.size() > 1) {
            int next_to_remove = (cur_ind + k - 1) % circle.size();
            circle.remove(next_to_remove);
            cur_ind = next_to_remove;
        }

        return circle.get(0);
    }
}

# Josephus Problem Solution Approaches

## 2. Recursion Approach with Subproblems

### Intuition:
If we observe the pattern, we can break the problem into subproblems. The winner of the game with `n` players is closely related to the winner of the game with `n-1` players. We can use recursion to solve this problem. Given a circle of size `n`, the position of the winner is given by the formula:


### Algorithm:
1. Define a recursive function to calculate the winner for a smaller problem.
2. **Base Case**: If `n == 1`, return `0` (since the only player remaining is the winner).
3. **Recursive Case**: Calculate `(recursion(n - 1, k) + k) % n` for the subproblem.
4. Return the result after adjusting it to 1-indexed format.

### Time Complexity:
- **O(n)**, due to the `n` recursive calls.

### Space Complexity:
- **O(n)**, due to the recursion call stack.

### Code:

```java
class Solution {
    public int findTheWinner(int n, int k) {
        return recursion(n, k) + 1;
    }

    private int recursion(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (recursion(n - 1, k) + k) % n;
    }
}



### 3. Bottom-up Dynamic Programming Approach with Constant Space

#### Intuition:
In this approach, we use an iterative method instead of recursion to solve the problem from bottom-up. We calculate the winner for each subproblem iteratively, starting from smaller sizes (2) up to n. This approach only requires constant space since we do not need to store intermediate results for the subproblems.

#### Algorithm:
1. Initialize a variable `res` to 0. This will store the winner's index for each subproblem.
2. Iterate through each player number from 2 to n and update `res` using the formula `(res + k) % player_num`.
3. Return the result after adjusting it to a 1-indexed format.

#### Time Complexity:
- **O(n)**: We iterate from 2 to n, which takes linear time.

#### Space Complexity:
- **O(1)**: We use a constant amount of space, making the solution efficient in terms of space.

#### Code:

```java
class Solution {
    public int findTheWinner(int n, int k) {
        int res = 0;
        for (int player_num = 2; player_num <= n; ++player_num) {
            res = (res + k) % player_num;
        }
        return res + 1;
    }
}
