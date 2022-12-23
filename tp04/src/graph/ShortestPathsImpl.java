package graph;

import java.util.HashMap;
import java.util.Map;

public class ShortestPathsImpl implements ShortestPaths {

    private Map<Vertex, Vertex> previousMap;

    public ShortestPathsImpl() {
        this.previousMap = new HashMap<Vertex, Vertex>();
    }

    @Override
    public void setPrevious(Vertex son, Vertex father) {
        previousMap.put(son, father);
    }

    @Override
    public Vertex getPrevious(Vertex father) {
        return previousMap.get(father);
    }
}
