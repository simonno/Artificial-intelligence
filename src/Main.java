import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\input files\\input.txt";
        String algorithm;
        int gridSize;
        ArrayList<ArrayList<State<Cell, Integer>>> grid;
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

                    switch (line.charAt(j)) {
                        case 'D':
                            row.add(new State<>(Cell.D, Cell.D.getCost()));
                            break;
                        case 'G':
                            row.add(new State<>(Cell.HILL, Cell.HILL.getCost()));
                            break;
                        case 'H':
                            row.add(new State<>(Cell.HILL, Cell.HILL.getCost()));
                            break;
                        case 'R':
                            row.add(new State<>(Cell.ROAD, Cell.ROAD.getCost()));
                            break;
                        case 'S':
                            row.add(new State<>(Cell.START, Cell.START.getCost()));
                            start_i = i;
                            start_j = j;
                            break;
                        case 'W':
                            row.add(new State<>(Cell.WATER, Cell.WATER.getCost()));
                            break;
                        default:
                            row.add(new State<>(Cell.ROAD, Cell.ROAD.getCost()));
                            break;
                    }
                }
                grid.add(row);
            }
            Searchable board = new Grid(grid, start_i, start_j);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

