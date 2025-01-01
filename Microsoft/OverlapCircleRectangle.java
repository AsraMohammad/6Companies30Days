class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Step 1: Find the nearest X coordinate on the rectangle to the circle's center
        int nearestX = Math.max(x1, Math.min(x2, xCenter));

        // Step 2: Find the nearest Y coordinate on the rectangle to the circle's center
        int nearestY = Math.max(y1, Math.min(y2, yCenter));

        // Step 3: Calculate the distance between the circle's center and the nearest point on the rectangle
        int distX = xCenter - nearestX;
        int distY = yCenter - nearestY;

        // Step 4: Check if the distance is less than or equal to the circle's radius
        return distX * distX + distY * distY <= radius * radius;
    }
}
