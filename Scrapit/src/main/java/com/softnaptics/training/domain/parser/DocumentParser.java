package com.softnaptics.training.domain.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DocumentParser {
   private static DocumentParser instance;

   private DocumentParser() {}

   public static DocumentParser getInstance() {
      if (instance == null) {
         instance = new DocumentParser();
      }
      return instance;
   }

   public Elements getElements(String url, String cssQuery) {
      try {
         return Jsoup.connect(url).get().select(cssQuery);
      } catch (IOException e) {
         throw new RuntimeException("Error when trying to retrieve elements", e);
      }
   }

   public String getAttribute(Elements elements, String attributeKey) {
      return elements.attr(attributeKey);
   }

   public String getAttribute(Element element, String attributeKey) {
      return element.attr(attributeKey);
   }
}
