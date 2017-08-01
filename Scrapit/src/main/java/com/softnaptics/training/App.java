package com.softnaptics.training;

import com.softnaptics.training.domain.ScrappingManager;
import com.softnaptics.training.domain.mapper.ObjectMapper;
import com.softnaptics.training.domain.nodes.FinalNode;
import com.softnaptics.training.domain.nodes.HrefNode;
import com.softnaptics.training.domain.nodes.Node;
import com.softnaptics.training.domain.nodes.processors.ListNextNodeCollector;
import com.softnaptics.training.domain.nodes.processors.NextNodeCollector;
import com.softnaptics.training.domain.nodes.processors.PaginationNextNodeCollector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        connectViaNewManager();
    }

    private static void connectViaNewManager() {
        long start = System.currentTimeMillis();
        ScrappingManager manager = new ScrappingManager();

        Node mainPageNode = new HrefNode(
                "body > section > div > div.Block-ModuleFaceb-Contenu-TopMusi > div > div.sous-rubrique > div > ul > li:nth-child(2) > a");
        Node paginationNode = new HrefNode("#idContenu > div.Lapagination > a.SuivantActif");
        Node categoryNode = new HrefNode("#zik_and_description > div.description_zik > p.titre_zik > a");
        Node finalNode = new FinalNode("#idContenu", new ObjectMapper() {
            public void map(Elements elements) {
                Element content = elements.first();
                final String PATH_ARTIST = ".nom_artisteDownload";
                final String PATH_TITLE = ".titre_zikDownload";
                final String PATH_DURATION = ".time_zikDownload";
                final String PATH_SIZE = ".taille_zikDownload";
                final String DOWNLOAD_LINK = ".IconeDownload a";

                StringBuilder builder = new StringBuilder();
                System.err.println("----------------------------------");
                builder.append("Artist = ").append(content.select(PATH_ARTIST).first().text()).append("\n").append("Title = ")
                        .append(content.select(PATH_TITLE).first().text()).append("\n").append("Duration = ")
                        .append(content.select(PATH_DURATION).first().text()).append("\n").append("Size = ")
                        .append(content.select(PATH_SIZE).first().text()).append("\n").append("Download link = ")
                        .append(content.select(DOWNLOAD_LINK).attr("href")).append("\n");

                System.out.println(builder.toString());
            }
        });

        NextNodeCollector categoryNodeCollector = new ListNextNodeCollector(5);
        categoryNode.attachCollector(categoryNodeCollector);
        NextNodeCollector paginationNodeCollector = new PaginationNextNodeCollector(1);
        paginationNode.attachCollector(paginationNodeCollector);
        NextNodeCollector mainNodeCollector = new ListNextNodeCollector();
        mainPageNode.attachCollector(mainNodeCollector);

        manager.addNode(mainPageNode);
        manager.addNode(paginationNode);
        manager.addNode(categoryNode);
        manager.addNode(finalNode);

        manager.processNew();
        System.err.println("Time execution = " + (System.currentTimeMillis() - start) + "ms");
    }
}
