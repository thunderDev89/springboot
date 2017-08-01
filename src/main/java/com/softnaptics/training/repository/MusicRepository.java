package com.softnaptics.training.repository;

import com.softnaptics.training.domain.Api;
import com.softnaptics.training.domain.Music;
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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Audrik !
 */
@Repository
public class MusicRepository {
    private static final String PATH_ARTIST = ".nom_artisteDownload";
    private static final String PATH_TITLE = ".titre_zikDownload";
    private static final String PATH_DURATION = ".time_zikDownload";
    private static final String PATH_SIZE = ".taille_zikDownload";
    private static final String DOWNLOAD_LINK = ".IconeDownload a";

    public List<Music> getLatestCamerSongs() {
        List<Music> camerSongs = new ArrayList<>();

            long start = System.currentTimeMillis();
            ScrappingManager manager = new ScrappingManager();

            Node mainPageNode = new HrefNode(
                    "body > section > div > div.Block-ModuleFaceb-Contenu-TopMusi > div > div.sous-rubrique > div > ul > li:nth-child(2) > a");
            Node paginationNode = new HrefNode("#idContenu > div.Lapagination > a.SuivantActif");
            Node categoryNode = new HrefNode("#zik_and_description > div.description_zik > p.titre_zik > a");
            Node finalNode = new FinalNode("#idContenu", new ObjectMapper() {
                public void map(Elements elements) {
                    Music music = mapMusic(elements.first());
                    camerSongs.add(music);
                    System.out.println(music);
                }
            });

            NextNodeCollector categoryNodeCollector = new ListNextNodeCollector(45);
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

        return camerSongs;
    }

    private Music mapMusic(Element content) {
        Music music = new Music();
        music.setTitle(content.select(PATH_TITLE).first().text());
        music.setArtist(content.select(PATH_ARTIST).first().text());
        music.setDuration(content.select(PATH_DURATION).first().text());
        music.setSize(content.select(PATH_SIZE).first().text());
        music.setLink(Api.getInstance().getFullUrl(content.select(DOWNLOAD_LINK).attr("href")));

        return music;
    }
}
