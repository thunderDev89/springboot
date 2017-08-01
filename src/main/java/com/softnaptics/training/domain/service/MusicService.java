package com.softnaptics.training.domain.service;

import com.softnaptics.training.domain.Music;
import com.softnaptics.training.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    private MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getLatestCameroonMusics() {
        return musicRepository.getLatestCamerSongs();
    }
}
