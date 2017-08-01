package com.softnaptics.training.domain.nodes.processors;

import com.softnaptics.training.domain.Api;
import com.softnaptics.training.domain.parser.DocumentParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PaginationNextNodeCollector extends NextNodeCollector {
    private boolean isFirstPage;

    /** Represent the next node element. Not loaded when it's first page */
    private Element nextElementPage;

    private String currentPageUrl;

    private int count = 1;

    public PaginationNextNodeCollector() {
        super();
    }

    public PaginationNextNodeCollector(int limit) {
        super(limit);
    }

    @Override
    public boolean hasNextNodeElement() {
        if (count > limitOfElements) {
            return false;
        }
        boolean result = false;
        if (isFirstPage) {
            result = true;
        } else {
            generateUrlOfTheNextPage();
            //TODO to refactor! Only assign value in #getNextNodeElement() method
            Elements nextElementPageRaw = DocumentParser.getInstance().getElements(currentPageUrl, node.getNextNodeUrlCssQuery());
            if (!nextElementPageRaw.isEmpty()) {
                nextElementPage = nextElementPageRaw.first();
                result = true;
            }
        }
        return result;
    }

    /**
     * Using the previous currentPageUrl, generate the currentPageUrl of the next page
     */
    private void generateUrlOfTheNextPage() {
        if (nextElementPage != null) {
            currentPageUrl = Api.getInstance().getFullUrl(node.getUrlForNextNode(nextElementPage));
        }
    }

    @Override
    public Element getNextNodeElement() {
        count++;
        if (isFirstPage) {
            isFirstPage = false;
            return createNodeElementForFirstPage();
        }
        System.err.println("next element page = " + nextElementPage);
        return nextElementPage;
    }

    private Element createNodeElementForFirstPage() {
        //Too specific for a href tag. Should create the right tag by the Node tag attached
        Element firstPageElement = new Element("a");
        firstPageElement.attr("href", currentPageUrl.substring(Api.getInstance().getPrefixUrl().length()));
        return firstPageElement;
    }

    @Override
    public void init(String url) {
        isFirstPage = true;
        this.currentPageUrl = url;
    }

    @Override
    public Object clone() {
        return new PaginationNextNodeCollector(this.limitOfElements);
    }
}
