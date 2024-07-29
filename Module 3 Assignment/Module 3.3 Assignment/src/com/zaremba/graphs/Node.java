package com.zaremba.graphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int key;
    private List<Integer> nodes;
    private boolean visited;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Searches the ArrayList parameter for a node which corresponds to the key parameter.
     * @param graph an ArrayList containing nodes.
     * @param key an identifier for a node.
     * @return the node corresponding to the key or null if not found.
     */
    public Node getNodeByKey(ArrayList<Node> graph, int key) {
        for (Node node : graph) {
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    /**
     * Creates a new node with the specified key and list of neighbor keys.
     * @param key the unique identifier for the node.
     * @param nodes the list of neighbor keys.
     */
    public Node(int key, ArrayList<Integer> nodes){
        this.key = key;
        this.nodes = nodes;
    }

    @Override
    public int hashCode() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node){
            Node other = (Node)obj;
            if(this.key == other.key){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node: " + key;
    }
}
