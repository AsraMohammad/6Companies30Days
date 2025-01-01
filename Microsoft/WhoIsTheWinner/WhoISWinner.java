class Solution {
    public int findTheWinner(int n, int k) {
        // Initialize the result variable, res, to 0 (position of the winner for 1 person)
        int res = 0;
        
        // Iterate over the number of players from 2 to n
        for (int player_num = 2; player_num <= n; ++player_num) {
            // Update the winner's position using the Josephus formula
            // (res + k) % player_num ensures that the position wraps around within the circle
            res = (res + k) % player_num;
        }
        
        // Return the final winner's position, incremented by 1 to convert from 0-indexed to 1-indexed
        return res + 1;
    }
}

