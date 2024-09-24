import java.util.ArrayList;

public class MazeSolver {
    public static boolean solveMaze(char[][] maze, int row, int col, ArrayList<String> path, MazeVisualizer visualizer) {
        // Base cases
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] == '#' || maze[row][col] == '+') {
            return false;
        }
        
        if (maze[row][col] == 'E') {
            return true;
        }
        
        // Mark the current cell as visited
        maze[row][col] = '+';
        
        // Try moving in all four directions
        // Move right
        if (solveMaze(maze, row, col + 1, path, visualizer)) {
            path.add("right");
            return true;
        }
        
        // Move down
        if (solveMaze(maze, row + 1, col, path, visualizer)) {
            path.add("down");
            return true;
        }
        
        // Move left
        if (solveMaze(maze, row, col - 1, path, visualizer)) {
            path.add("left");
            return true;
        }
        
        // Move up
        if (solveMaze(maze, row - 1, col, path, visualizer)) {
            path.add("up");
            return true;
        }
        
        // Unmark the current cell (backtrack)
        maze[row][col] = ' ';
        
        return false;
    }


    public static void main(String[] args) {
        Maze maze = new Maze("maze5.txt"); // to change the maze you're solving, change this filename (maze1.txt, maze2.txt, maze3.txt, maze4.txt, or maze5.txt)
        maze.printMaze();

        ArrayList<String> path = new ArrayList<>();

        // create a frame to display the maze
        MazeVisualizer visualizer = new MazeVisualizer(maze.getMaze(), path);
        visualizer.display();

        if (solveMaze(maze.getMaze(), maze.getStartRow(), maze.getStartCol(), path, visualizer)) {
            System.out.println("Maze solved:");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i));
            }
        } else {
            System.out.println("No solution found for the maze.");
        }
    }
}
