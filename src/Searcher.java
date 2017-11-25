
public abstract class Searcher {

    /**
     * The evaluated nodes were evaluated by the algorithm.
     */
    private int evaluatedNodes;

    public Searcher() {
        evaluatedNodes = 0;
    }



    /**
     * Gets how many nodes were evaluated by the algorithm.
     *
     * @return integer number of the number of nodes evaluated.
     */
    public int getNumberOfNodesEvaluated() {
        return evaluatedNodes;
    }
}