package com.softnaptics.training.domain;


import com.softnaptics.training.domain.nodes.Node;
import com.softnaptics.training.domain.parser.DocumentParser;

import java.util.ArrayList;
import java.util.List;

public class ScrappingManager {
    private final List<Node> nodes;

    private Node firstNode;

    private Node lastNode;

    private final DocumentParser documentParser = DocumentParser.getInstance();

    private final Api api = Api.getInstance();

    public ScrappingManager() {
        nodes = new ArrayList<Node>();
    }

    public void addNode(Node node) {
        if (firstNode == null) {
            firstNode = node;
        }
        if (lastNode != null) {
            lastNode.setNextNode(node);
        }
        lastNode = node;
        nodes.add(node);
    }

    public void process() {
        String pathUrlForNextNode = "";
        for (Node node : nodes) {
            if (node.isFinalNode()) {
                node.displayData(documentParser.getElements(api.getFullUrl(pathUrlForNextNode), node.getNextNodeUrlCssQuery()));
                break;
            }
            pathUrlForNextNode = node.getUrlForNextNode(documentParser.getElements(api.getFullUrl(pathUrlForNextNode),
                    node.getNextNodeUrlCssQuery()));
        }
    }

    public void processNew() {
        if (firstNode == null) {
            throw new IllegalStateException("You should provide at least one node to process");
        }
        loop(firstNode, "");
    }

    private void loop(Node node, String pathUrlForNextNode) {
        String fullUrl = api.getFullUrl(pathUrlForNextNode);
        if (node.isFinalNode()) {
            node.displayData(documentParser.getElements(fullUrl, node.getNextNodeUrlCssQuery()));
        } else {
            node.beforeSeekingNextNodes(fullUrl);
            while (node.hasNextNodeElement()) {
                process(node);
            }
        }
    }

    private void process(Node node) {
        if (node.getNextNode() == null) {
            throw new IllegalArgumentException("Next node should be null only for final node.\nnextNodeUrlCssQuery="
                    + node.getNextNodeUrlCssQuery());
        }
        loop((Node) node.getNextNode().clone(), node.getNextNodePathUrl());
    }
}