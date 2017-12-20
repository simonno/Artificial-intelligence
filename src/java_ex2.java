//C:\Users\simon\IdeaProjects\artificial intelligence\ex1\input files\

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * The type Search algorithms.
 */
public class java_ex2 {

    private static int boardSize;
    private static String fileName;
    private static String algorithm;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        fileName = "//C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\input files ex2\\input.txt";
        boardSize = 5;
        ArrayList<ArrayList<BoardCell>> board = parseFile();
        //BoardCell.Type type = BoardCell.Type.BLACK;
        Reversi searchable = new Reversi(new Board(board, boardSize, BoardCell.Type.BLACK));
        MinMax<Board> searcher = new MinMax<Board>(true);
        State<Board> s = searcher.search(searchable);

        while (!searchable.isGoal(s)) {
            Board b = s.getElement();
            searchable.setBoard(b);
            if (b.getTypeTurn() == BoardCell.Type.BLACK) {
                searcher.setMaximizing(true);
            } else {
                searcher.setMaximizing(false);
            }
            s = searcher.search(searchable);
        }

        if (s.getCost() > 0) {
            writeToOutputFile("B");
        } else if (s.getCost() < 0) {
            writeToOutputFile("W");
        } else { // draw
            writeToOutputFile("D");
        }
    }

    /**
     * Write the output to the file
     *
     * @param output is the output of the search.
     */
    private static void writeToOutputFile(String output) {
        try {
            File statText = new File("//C:\\Users\\simon\\IdeaProjects\\artificial intelligence\\ex1\\output files ex2\\output.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(output);
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
    private static ArrayList<ArrayList<BoardCell>> parseFile() {
        ArrayList<ArrayList<BoardCell>> board = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            board = new ArrayList<ArrayList<BoardCell>>(boardSize);
            String line;
            for (int i = 0; i < boardSize; i++) {
                line = br.readLine();
                ArrayList<BoardCell> row = new ArrayList<BoardCell>(boardSize);
                for (int j = 0; j < boardSize; j++) {
                    switch (line.charAt(j)) {
                        case 'B':
                            row.add(new BoardCell(i, j, BoardCell.Type.BLACK));
                            break;
                        case 'W':
                            row.add(new BoardCell(i, j, BoardCell.Type.WHITE));
                            break;
                        case 'E':
                            row.add(new BoardCell(i, j, BoardCell.Type.EMPTY));
                            break;
                        default:
                            row.add(new BoardCell(i, j, BoardCell.Type.EMPTY));
                            break;
                    }
                }
                board.add(row);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }
        return board;
    }
}

