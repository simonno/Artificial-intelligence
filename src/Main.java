import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\input files\\input2.txt";
        String algorithm;
        int gridSize = 0;
        ArrayList<ArrayList<State<Cell, Integer>>> grid = null;
        int start_i = 0;
        int start_j = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            algorithm = br.readLine();
            gridSize = Integer.parseInt(br.readLine());
            grid = new ArrayList<ArrayList<State<Cell, Integer>>>(gridSize);
            String line;
            for (int i = 0; i < gridSize; i++) {
                line = br.readLine();
                ArrayList<State<Cell, Integer>> row = new ArrayList<State<Cell, Integer>>(gridSize);
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
                            start_i = i;
                            start_j = j;
                            break;
                        case 'W':
                            c.setType(Cell.Type.WATER);
                            break;
                        default:
                            c.setType(Cell.Type.ROAD);
                            break;
                    }
                    row.add(new State<Cell, Integer>(c, c.getCost()));
                }
                grid.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Searchable<Cell, Integer> board = new Grid(grid, gridSize, gridSize, start_i, start_j);
        Searcher<Cell, Integer> searcher = new IDS<Cell, Integer>();
        State<Cell, Integer> goal = searcher.search(board);

        State<Cell, Integer> s = goal;
        State<Cell, Integer> temp;
        StringBuffer solution = new StringBuffer();
        int count = 1;
        while ((temp = s.getCameFrom()) != null) {
            StringBuffer direction = new StringBuffer();
            int rowDiff = s.getElement().getRow() - temp.getElement().getRow();
            int columnDiff = temp.getElement().getColumn() - s.getElement().getColumn();

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
            s=temp;
            count++;
        }
        solution.replace(0,1,"");
        solution.append(" ").append(String.valueOf(count));
        System.out.println(solution);
        return;

    }

}

