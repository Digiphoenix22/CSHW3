import java.util.Scanner;

public class CountingClouds {

    public static int countClouds(int[][][] grid) {
        int depth = grid.length;
        int rows = grid[0].length;
        int cols = grid[1].length;
        boolean[][][] visited = new boolean[depth][rows][cols];
        int cloudCount = 0;

        for (int d = 0; d < depth; d++) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[d][r][c] == 1 && !visited[d][r][c]) {
                        dfs(grid, visited, d, r, c);
                        cloudCount++;
                    }
                }
            }
        }

        return cloudCount;
    }

    private static void dfs(int[][][] grid, boolean[][][] visited, int depth, int row, int col) {
        if (depth < 0 || row < 0 || col < 0 || depth >= grid.length || row >= grid[0].length || col >= grid[0][0].length || grid[depth][row][col] == 0 || visited[depth][row][col]) {
            return;
        }

        visited[depth][row][col] = true;

        dfs(grid, visited, depth - 1, row, col);
        dfs(grid, visited, depth + 1, row, col);
        dfs(grid, visited, depth, row - 1, col);
        dfs(grid, visited, depth, row + 1, col);
        dfs(grid, visited, depth, row, col - 1);
        dfs(grid, visited, depth, row, col + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of your 3D matrix (rows cols depth):");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int depth = scanner.nextInt();

        int[][][] grid = new int[depth][rows][cols];

        for (int d = 0; d < depth; d++) {
            System.out.println("Enter the elements for depth " + (d + 1) + " (use space to separate numbers):");
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[d][r][c] = scanner.nextInt();
                }
            }
        }

        int cloudCount = countClouds(grid);
        System.out.println("The number of clouds is: " + cloudCount);
        scanner.close(); // Good practice to close the scanner when done
    }
}
