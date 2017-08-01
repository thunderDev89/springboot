package com.softnaptics.training.domain.nodes.processors;

import com.softnaptics.training.domain.parser.DocumentParser;
import org.jsoup.nodes.Element;

import java.util.Iterator;

public class ListNextNodeCollector extends NextNodeCollector {
    private Iterator<Element> nextNodesIterator;

    private int count = 1;

    public ListNextNodeCollector() {
        super();
    }

    public ListNextNodeCollector(int limit) {
        super(limit);
    }

    @Override
    public boolean hasNextNodeElement() {
        return count <= limitOfElements && nextNodesIterator.hasNext();
    }

    @Override
    public Element getNextNodeElement() {
        count++;
        return nextNodesIterator.next();
    }

    @Override
    public void init(String url) {
        nextNodesIterator = DocumentParser.getInstance().getElements(url, node.getNextNodeUrlCssQuery()).iterator();
    }

    @Override
    public Object clone() {
        return new ListNextNodeCollector(limitOfElements);
    }
}
