import java.util.LinkedList;
import java.util.List;

public class IDS {
    public List<State> search(State initial) {
        List<State> solution = new LinkedList<>();
        int depth = 0;
        while (solution.isEmpty()) {
            List<State> temp = DLS(initial, depth);
            if (temp != null) {
                solution.addAll(temp);
                return solution;
            }
        }
        return null;
    }

    private List<State> DLS(State initial, int depth) {
        List<State> solution = new LinkedList<>();
        if (depth == 0 && initial.getType() == State.Type.GOAL) {
            solution.add(initial);
            return solution;
        }
        if (depth > 0) {
            for (State successor : initial.getSuccessors()) {
                List<State> temp = DLS(successor, depth - 1);
                if (temp != null) {
                    solution.addAll(temp);
                    solution.add(initial);
                    return solution;
                }
            }
        }
        return null
    ;
    }
}
