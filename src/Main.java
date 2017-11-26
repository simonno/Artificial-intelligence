import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "input.txt";
        String algorithm;
        int gridSize;
        ArrayList<ArrayList<Cell>> grid;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            algorithm = br.readLine();
            gridSize = Integer.getInteger(br.readLine());
            grid = new ArrayList<ArrayList<Cell>>(gridSize);
            String line;
            for (int i = 0; i < gridSize; i++) {
                line = br.readLine();
                ArrayList<Cell> row = new ArrayList<Cell>(gridSize);
                for (int j = 0; j < gridSize; j++) {

                    switch (line.charAt(j)) {
                        case 'D':
                            row.set(j, Cell.D);
                            break;
                        case 'G':
                            row.set(j, Cell.GOAL);
                            break;
                        case 'H':
                            row.set(j, Cell.HILL);
                            break;
                        case 'R':
                            row.set(j, Cell.ROAD);
                            break;
                        case 'S':
                            row.set(j, Cell.START);
                            break;
                        case 'W':
                            row.set(j, Cell.WATER);
                            break;
                        default:
                            row.set(j, Cell.ROAD);
                            break;
                    }
                }
                grid.set(i, row);
            }
            Searchable board = new Grid(grid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

