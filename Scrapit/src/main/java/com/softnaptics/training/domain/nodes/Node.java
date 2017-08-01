package com.softnaptics.training.domain.nodes;

import com.softnaptics.training.domain.nodes.processors.NextNodeCollector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Helps to keep and retrieve informations about next node by its method {@link #getUrlForNextNode(Elements)}
 * @author a02510
 *
 */
public abstract class Node implements Cloneable {
    private final String nextNodeUrlCssQuery;

    Node nextNode;

    NextNodeCollector nextNodeCollector;

    Node(String nextNodeHrefCssQuery) {
        this.nextNodeUrlCssQuery = nextNodeHrefCssQuery;
    }

    public String getNextNodeUrlCssQuery() {
        return nextNodeUrlCssQuery;
    }

    public abstract String getUrlForNextNode(Elements source);

    public abstract String getUrlForNextNode(Element source);

    public abstract void beforeSeekingNextNodes(String url);

    /**
     * Indicates if a node is the last in the tree of node
     */
    public boolean isFinalNode() {
        return false;
    }

    public void displayData(Elements elements) {
        throw new RuntimeException("displayData() should not be called here !");
    }

    NextNodeCollector getNextNodeCollector() {
        return nextNodeCollector;
    }

    public void attachCollector(NextNodeCollector nextNodeCollector) {
        this.nextNodeCollector = nextNodeCollector;
        nextNodeCollector.setNode(this);
    }

    public boolean hasNextNodeElement() {
        if (nextNodeCollector == null) {
            throw new IllegalStateException("No collector specified for this node.\nnextNodeHrefCssQuery=" + nextNodeUrlCssQuery);
        }
        return nextNodeCollector.hasNextNodeElement();
    }

    public String getNextNodePathUrl() {
        return getUrlForNextNode(nextNodeCollector.getNextNodeElement());
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Should use builder for next improvement
     */
    @Override
    public abstract Object clone();

    @Override
    public String toString() {
        return "Node [nextNodeUrlCssQuery=" + nextNodeUrlCssQuery + ", nextNode=" + nextNode + ", nextNodeCollector="
                + nextNodeCollector + "]";
    }

}