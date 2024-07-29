package com.zaremba.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static ArrayList<Node> graph;
    public static void main(String[] args) throws FileNotFoundException {
        setupTree();
        BFS();
    }

    /**
     * Performs Breadth-First Search on the graph starting from the node with key 1.
     * Prints each node as it is visited.
     */
    private static void BFS() {
        if (graph == null || graph.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        //Find the start node
        Node startNode = null;
        for (Node node : graph) {
            if (node.getKey() == 1) {
                startNode = node;
                break;
            }
        }

        //If start node is not found...
        if (startNode == null) {
            System.out.println("Start node not found.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        startNode.setVisited(true);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.println(currentNode);
            for (int neighborKey : currentNode.getNodes()) {    //Iterate over each neighbor of the current node
                Node neighbor = currentNode.getNodeByKey(graph, neighborKey);    // Find the neighbor node by key
                if (neighbor != null && !neighbor.isVisited()) {
                    queue.add(neighbor);
                    neighbor.setVisited(true);
                }
            }
        }
    }
    private static void setupTree() throws FileNotFoundException {
        graph = new ArrayList<>();
        Scanner scan = new Scanner(new File("tree.txt"));
        while(scan.hasNext()){
            String line = scan.nextLine();
            parseLine(line);
        }
    }

    private static void parseLine(String line) {
        String[] keys = line.split(" ");
        int key = Integer.parseInt(keys[0]);
        ArrayList<Integer> points = new ArrayList<>();
        for(int i = 1; i < keys.length;i++){
            points.add(Integer.parseInt(keys[i]));
        }
        graph.add(new Node(key, points));
    }
}