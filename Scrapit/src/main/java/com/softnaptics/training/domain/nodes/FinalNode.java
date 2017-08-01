package com.softnaptics.training.domain.nodes;

import com.softnaptics.training.domain.mapper.ObjectMapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FinalNode extends Node {
    private final ObjectMapper mapper;

    public FinalNode(String nextNodeHrefCssQuery, ObjectMapper mapper) {
        super(nextNodeHrefCssQuery);
        this.mapper = mapper;
    }

    @Override
    public String getUrlForNextNode(Elements source) {
        return null;
    }

    @Override
    public String getUrlForNextNode(Element source) {
        return null;
    }

    @Override
    public void displayData(Elements elements) {
        if (mapper == null) {
            throw new IllegalStateException("You should declare a mapper on final node.\nnextNodeUrlCssQuery="
                    + getNextNodeUrlCssQuery());
        }
        mapper.map(elements);
    }

    @Override
    public boolean isFinalNode() {
        return true;
    }

    @Override
    public Object clone() {
        return new FinalNode(getNextNodeUrlCssQuery(), mapper);
    }

    @Override
    public void beforeSeekingNextNodes(String url) {
        // Nothing to do
    }
}
