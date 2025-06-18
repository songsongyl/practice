package org.example.practice.service;

public interface ICacheService {
    void add(String key, String value);
    String get(String key);
}
