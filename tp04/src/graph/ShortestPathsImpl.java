package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathsImpl implements ShortestPaths {

    private Map<Vertex, Vertex> predecessors;

    public ShortestPathsImpl() {
        this.predecessors = new HashMap<Vertex, Vertex>();
    }
    
    @Override
    public List<Vertex> getShortestPath(Vertex endVertex) {
    	List<Vertex> path = new ArrayList<>();
    	Vertex current = endVertex;
    	while (current != null) {
    		path.add(current);
    	    current = predecessors.get(current);
    	}
    	Collections.reverse(path);
    	return path;
    }

    @Override
    public void setPrevious(Vertex son, Vertex father) {
    	predecessors.put(son, father);
    }

    @Override
    public Vertex getPrevious(Vertex father) {
        return predecessors.get(father);
    }
}
