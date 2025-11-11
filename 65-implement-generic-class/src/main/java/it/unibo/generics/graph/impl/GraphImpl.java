package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Map<N, Set<N>> edges;

    public GraphImpl(Map<N, Set<N>> edge) {
        this.edges = new HashMap<>();
    }

    /**
     * Adds a node: nothing happens if node is null or already there.
     *
     * @param node
     *            the node to add
     */
    @Override
    public void addNode(N node) {
        if(!(node.equals(null))) {
            edges.put(node, new HashSet<>());
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
            edges.get(source).add(target);
        }
    }

    /**
     * @return all the nodes
     */
    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
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
        return new HashSet<>(edges.get(node));
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
        return list;
    }
}