package org.example.practice.service;

import org.example.practice.entity.Music;

import java.util.List;

public interface MusicService {
    void add(Music music);
    void remove(Music music);
    void loadImageInfo();
    List<Music> findByKeyword(String keyword);
    List<Music> findAll();
}
