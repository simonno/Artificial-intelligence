import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * The type Search algorithms.
 */
public class SearchAlgorithms {

    private static int gridSize;
    private static String fileName;
    private static String algorithm;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        fileName = "C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\input files\\input4.txt";
        algorithm = "";
        gridSize = 0;
        ArrayList<ArrayList<Cell>> grid = parseFile();
        Searchable<Cell> board = new Grid(grid, gridSize, gridSize, 0, 0,
                gridSize - 1, gridSize - 1);
        Searcher<Cell> searcher;
        if (algorithm.matches("IDS")) {
            searcher = new IDS<Cell>();
        } else {
            searcher = new Astar<Cell>();
        }

        writeToOutputFile(parseSolution(searcher.search(board)));
    }

    private static void writeToOutputFile(String solution) {
        try {
            File statText = new File("C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\output.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(solution);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file output.txt");
        }
    }

    private static ArrayList<ArrayList<Cell>> parseFile() {
        ArrayList<ArrayList<Cell>> grid = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            algorithm = br.readLine();
            gridSize = Integer.parseInt(br.readLine());
            grid = new ArrayList<ArrayList<Cell>>(gridSize);
            String line;
            for (int i = 0; i < gridSize; i++) {
                line = br.readLine();
                ArrayList<Cell> row = new ArrayList<Cell>(gridSize);
                for (int j = 0; j < gridSize; j++) {
                    Cell c = new Cell(i, j);
                    switch (line.charAt(j)) {
                        case 'D':
                            c.setType(Cell.Type.D);
                            break;
                        case 'G':
                            c.setType(Cell.Type.GOAL);
                            break;
                        case 'H':
                            c.setType(Cell.Type.HILL);
                            break;
                        case 'R':
                            c.setType(Cell.Type.ROAD);
                            break;
                        case 'S':
                            c.setType(Cell.Type.START);
                            break;
                        case 'W':
                            c.setType(Cell.Type.WATER);
                            break;
                        default:
                            c.setType(Cell.Type.ROAD);
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

    private static String parseSolution(State<Cell> goal) {
        if (goal == null) {
            return "no path";
        }
        State<Cell> temp;
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

