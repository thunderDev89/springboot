package com.softnaptics.training.domain.nodes;

import com.softnaptics.training.domain.nodes.processors.NextNodeCollector;
import com.softnaptics.training.domain.parser.DocumentParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HrefNode extends Node {

    public HrefNode(String nextNodeHrefCssQuery) {
        super(nextNodeHrefCssQuery);
    }

    @Override
    public void attachCollector(NextNodeCollector nextNodeCollector) {
        super.attachCollector(nextNodeCollector);
    }

    @Override
    public void beforeSeekingNextNodes(String url) {
        getNextNodeCollector().init(url);
    }

    @Override
    public String getUrlForNextNode(Elements source) {
        return DocumentParser.getInstance().getAttribute(source, "href");
    }

    @Override
    public String getUrlForNextNode(Element source) {
        return DocumentParser.getInstance().getAttribute(source, "href");
    }

    @Override
    public Object clone() {
        HrefNode clone = new HrefNode(getNextNodeUrlCssQuery());
        clone.attachCollector((NextNodeCollector) nextNodeCollector.clone());
        clone.setNextNode((Node) nextNode.clone());
        return clone;
    }
}
