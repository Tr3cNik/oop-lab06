package it.unibo.generics.graph.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Set<N> nodes;
    private Map<N, Set<N>> edges;
    private Set<N> reachedNodes;

    public GraphImpl(Set<N> node, Map<N, Set<N>> edge) {
        this.nodes = new TreeSet<>();
        this.edges = new TreeMap<>();
        this.reachedNodes = new TreeSet<>();
    }

    /**
     * Adds a node: nothing happens if node is null or already there.
     *
     * @param node
     *            the node to add
     */
    @Override
    public void addNode(N node) {
        if(!(nodes.contains(node) && node.equals(null))) {
            nodes.add(node);
        }
    }

    /**
     * Adds an edge: nothing happens if source or target are null.
     *
     * @param source
     *            starting node
     * @param target
     *            ending node
     */
    @Override
    public void addEdge(N source, N target) {
        if(!(source.equals(null) && target.equals(null))) {
            reachedNodes.add(target);
            edges.putIfAbsent(source, reachedNodes);
        }
    }

    /**
     * @return all the nodes
     */
    @Override
    public Set<N> nodeSet() {
        return this.nodes;
    }

    /**
     * Returns all the nodes directly targeted from a node.
     *
     * @param node
     *            the node
     * @return all the nodes directly targeted from the passed node
     */
    @Override
    public Set<N> linkedNodes(N node) {
        Set<N> linkedNds = new TreeSet<>();
        for(N n: nodes) {
            if(edges.get(node).contains(n)) {
                linkedNds.add(n);
            }
        }
        return linkedNds;
    }

    /**
     * Gets one sequence of nodes connecting source to target.
     *
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return a sequence of nodes connecting sources and target
     */
    @Override
    public List<N> getPath(N source, N target) {
        List<N> list = new LinkedList<>();
        for(N n: nodes) {
            if(edges.containsValue(source) && edges.containsValue(target)) {
                list.add(n);
            }
        }
        return list;
    }
}