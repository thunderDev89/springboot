package com.softnaptics.training.domain;

/**
 * @author Audrik !
 */
public class Music {
    private String title;
    private String artist;
    private String duration;
    private String size;
    private String link;

    public Music() {
    }

    public Music(String title, String artist, String duration, String size, String link) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
