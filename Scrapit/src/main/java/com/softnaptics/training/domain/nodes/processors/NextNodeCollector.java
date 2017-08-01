package com.softnaptics.training.domain.nodes.processors;

import com.softnaptics.training.domain.nodes.Node;
import org.jsoup.nodes.Element;

/**
 * Never exist without a node.
 * It helps to collect the different elements that will be containing the nextNodeElement.<br/>
 * It send the result to the node, which in turn will retrieve the url conveniently
 * @author a02510
 *
 */
public abstract class NextNodeCollector implements Cloneable {
    private static final int COUNT_NO_LIMIT = Integer.MAX_VALUE;

    Node node;

    int limitOfElements;

    NextNodeCollector() {
        limitOfElements = COUNT_NO_LIMIT;
    }

    NextNodeCollector(int limit) {
        limitOfElements = limit;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public abstract boolean hasNextNodeElement();

    public abstract Element getNextNodeElement();

    public abstract void init(String url);

    /**
     * Should use a builder for the next improvement
     */
    @Override
    public abstract Object clone();

    @Override
    public String toString() {
        return "NextNodeCollector [node=" + node + "]";
    }
}
