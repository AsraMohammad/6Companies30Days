# Circle and Rectangle Overlapping

## Problem Overview
The goal is to determine if a circle overlaps with a rectangle. The circle is defined by:
- **Radius:** `radius`
- **Center:** `(xCenter, yCenter)`

The rectangle is defined by:
- **Bottom-left corner:** `(x1, y1)`
- **Top-right corner:** `(x2, y2)`

## Approach and Logic

### 1. Find the Nearest Point on the Rectangle to the Circle's Center
The nearest X-coordinate is calculated as:
```
nearestX = max(x1, min(x2, xCenter))
```
This ensures that the X-coordinate lies within the rectangle's horizontal bounds.

Similarly, the nearest Y-coordinate is:
```
nearestY = max(y1, min(y2, yCenter))
```
This ensures that the Y-coordinate lies within the rectangle's vertical bounds.

### 2. Calculate the Distance Between the Circle's Center and the Nearest Point
Compute the horizontal and vertical differences:
```
distX = xCenter - nearestX
distY = yCenter - nearestY
```
The squared Euclidean distance is then:
```
distance^2 = distX^2 + distY^2
```

### 3. Compare the Distance to the Circle's Radius
To determine overlap, check if the squared distance is less than or equal to the squared radius:
```
distance^2 <= radius^2
```
If true, the circle overlaps with the rectangle.

## Code Walkthrough
The code implements the above logic in a concise manner:
- It uses `Math.max` and `Math.min` to constrain the circle's center to the nearest point on the rectangle.
- It calculates the squared distance between the circle's center and the nearest point.
- Finally, it checks whether the distance is within the circle's radius.

## Space and Time Complexity
### Time Complexity
The solution runs in **O(1)** because all operations (finding nearest coordinates and calculating distances) are constant time operations.

### Space Complexity
The solution uses **O(1)** additional space as it only requires a few variables to store intermediate calculations.

## Example Usage
```java
class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int nearestX = Math.max(x1, Math.min(x2, xCenter));
        int nearestY = Math.max(y1, Math.min(y2, yCenter));

        int distX = xCenter - nearestX;
        int distY = yCenter - nearestY;
        return distX * distX + distY * distY <= radius * radius;
    }
}
```
