package com.softnaptics.training.controller;

import com.softnaptics.training.domain.Music;
import com.softnaptics.training.domain.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/musics")
public class MusicController {

    private MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @RequestMapping(path = "/camer", method = RequestMethod.GET)
    public String cameroonMusics(Model model) {
        model.addAttribute("title", "Camer Songs");
        List<Music> musics = musicService.getLatestCameroonMusics();
        model.addAttribute("musics", musics);
        return "views/music/list";
    }

    @RequestMapping(path = "/camers", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Music> cameroonMusicsJSON(Model model) {
        model.addAttribute("title", "Camer Songs");
        List<Music> musics = musicService.getLatestCameroonMusics();
        model.addAttribute("musics", musics);
        return musics;
    }
}
