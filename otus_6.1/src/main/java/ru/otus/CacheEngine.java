package ru.otus;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheEngine<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;

    private final Map<K, MapElement<K,V>> elements = new LinkedHashMap<>();

    CacheEngine(int m, long l) {
        this.maxElements = m;
        this.lifeTimeMs = l > 0 ? l : 0;
    }

    public void add(MapElement<K,V> element){
        if (elements.size() == maxElements){
            K firstKey = elements.keySet().iterator().next();
            System.out.println(firstKey);
            System.out.println("->> Deleting from cache element " + elements.get(firstKey).getValue() + " due to size exceeding");
            elements.remove(firstKey);
        }
        elements.put(element.getKey(), element);
        System.out.println("Added to cache " + element.getKey() + " " + element.getValue());

    }

    public V get(K key){
        MapElement<K,V> element = elements.get(key);
        if (element == null) {
            return null;
        } else {
            return element.getValue();
        }
    }

    public int size(){
        return elements.size();
    }
}


class MapElement<K,V>{
    private final K key;
    private final V value;

    MapElement(K key, V value){
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


class BigObject {
    final byte[] array = new byte[1024 * 1024];

    public byte[] getArray() {
        return array;
    }
}
