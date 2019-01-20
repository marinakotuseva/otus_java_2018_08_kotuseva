package ru.otus.DBService.cache;

public class MapElement<K,V>{
    private final K key;
    private final V value;

    public MapElement(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
}
