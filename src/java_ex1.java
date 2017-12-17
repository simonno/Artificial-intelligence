//C:\Users\simon\IdeaProjects\artificial intelligence\ex1\input files\
import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * The type Search algorithms.
 */
public class java_ex1 {

    private static int gridSize;
    private static String fileName;
    private static String algorithm;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        fileName = "input.txt";
        algorithm = "";
        gridSize = 0;
        ArrayList<ArrayList<GridCell>> grid = parseFile();
        Searchable<GridCell> board = new Grid(grid, gridSize, gridSize, 0, 0,
                gridSize - 1, gridSize - 1);
        Searcher<GridCell> searcher;
        if (algorithm.matches("IDS")) {
            searcher = new IDS<GridCell>();
        } else {
            searcher = new Astar<GridCell>();
        }

        writeToOutputFile(parseSolution(searcher.search(board)));
    }

    /**
     * Write the solution to the file
     *
     * @param solution is the solution of the search.
     */
    private static void writeToOutputFile(String solution) {
        try {
            File statText = new File("output.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(solution);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file output.txt");
        }
    }

    /**
     * Parse the input file.
     *
     * @return 2 dimension array of the grid cells.
     */
    private static ArrayList<ArrayList<GridCell>> parseFile() {
        ArrayList<ArrayList<GridCell>> grid = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            algorithm = br.readLine();
            gridSize = Integer.parseInt(br.readLine());
            grid = new ArrayList<ArrayList<GridCell>>(gridSize);
            String line;
            for (int i = 0; i < gridSize; i++) {
                line = br.readLine();
                ArrayList<GridCell> row = new ArrayList<GridCell>(gridSize);
                for (int j = 0; j < gridSize; j++) {
                    GridCell c = new GridCell(i, j);
                    switch (line.charAt(j)) {
                        case 'D':
                            c.setType(GridCell.Type.D);
                            break;
                        case 'G':
                            c.setType(GridCell.Type.GOAL);
                            break;
                        case 'H':
                            c.setType(GridCell.Type.HILL);
                            break;
                        case 'R':
                            c.setType(GridCell.Type.ROAD);
                            break;
                        case 'S':
                            c.setType(GridCell.Type.START);
                            break;
                        case 'W':
                            c.setType(GridCell.Type.WATER);
                            break;
                        default:
                            c.setType(GridCell.Type.ROAD);
                            break;
                    }
                    row.add(c);
                }
                grid.add(row);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
        return grid;
    }

    /**
     * Parse the solution.
     *
     * @param goal is the goal cell.
     * @return the solution of the search.
     */
    private static String parseSolution(State<GridCell> goal) {
        if (goal == null) {
            return "no path";
        }
        State<GridCell> temp;
        StringBuffer solution = new StringBuffer();
        int count = 0;
        while ((temp = goal.getCameFrom()) != null) {
            count += temp.getElement().getCost();
            StringBuffer direction = new StringBuffer();
            int rowDiff = goal.getElement().getRow() - temp.getElement().getRow();
            int columnDiff = temp.getElement().getColumn() - goal.getElement().getColumn();

            direction.append("-");
            switch (columnDiff) {
                case -1: // right
                    direction.append('R');
                    break;
                case 0: // same column
                    break;
                case 1: // Left
                    direction.append('L');
                    break;
            }

            switch (rowDiff) {
                case -1: // up
                    direction.append('U');
                    break;
                case 0: // same row
                    break;
                case 1: // down
                    direction.append('D');
                    break;
            }
            direction.append(solution);
            solution = direction;
            goal = temp;
        }
        solution.replace(0, 1, "");
        solution.append(" ").append(String.valueOf(count));
        return solution.toString();
    }

}

