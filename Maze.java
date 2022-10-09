import java.util.*;
import java.io.*;

public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter the file name or absolute path of the Maze: ");
        Scanner console = new Scanner(System.in);
        String file = console.next();
        char[][] myMaze = readMaze(file);
        printMaze(myMaze);
    }

    public static char[][] readMaze(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        int countCol = input.nextInt();
        int countRow = input.nextInt();

        int startRow = input.nextInt();
        int startCol = input.nextInt();
        int endRow = input.nextInt();
        int endCol = input.nextInt();
        input.nextLine();

        char[][] myMaze = new char[countRow][countCol];
        int row = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            for (int col = 0; col < line.length(); col++)
                myMaze[row][col] = line.charAt(col);
            row++;
        }

        if (checkMaze(myMaze, endRow, endCol) == 1)
            myMaze[endRow][endCol] = 'E';
        solveMaze(myMaze, startRow, startCol);
        myMaze[startRow][startCol] = 'S';
        return myMaze;
    }

    public static int checkMaze(char[][] myMaze, int row, int col) {
        if (row >= 0 && row < myMaze.length && col >= 0 && col < myMaze[0].length && myMaze[row][col] == ' ')
            return 1;
        return 0;
    }

    public static int freeMaze(char[][] myMaze) {
        if (myMaze.length > 0 && myMaze[0].length > 0) {
            char[][] maze = new char[0][0];
            myMaze = maze;
            return 1;
        }
        return 0;
    }

    public static int printMaze(char[][] myMaze) {
        if (myMaze.length > 0 && myMaze[0].length > 0) {
            for (int row = 0; row < myMaze.length; row++) {
                for (int col = 0; col < myMaze[row].length; col++) {
                    System.out.print(myMaze[row][col]);
                }
                System.out.println();
            }
            return 1;
        }
        return 0;
    }

    public static int solveMaze(char[][] myMaze, int x, int y) {
        if (myMaze[x][y] == 'E')
            return 1;
        else {
            if (checkMaze(myMaze, x, y) == 1) {
                myMaze[x][y] = '@';
                if (solveMaze(myMaze, x, y - 1) == 1)
                    return 1;
                if (solveMaze(myMaze, x + 1, y) == 1)
                    return 1;
                if (solveMaze(myMaze, x, y + 1) == 1)
                    return 1;
                if (solveMaze(myMaze, x - 1, y) == 1)
                    return 1;
                myMaze[x][y] = '?';
            }
            return 0;
        }
    }
}
