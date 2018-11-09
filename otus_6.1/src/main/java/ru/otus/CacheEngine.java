package ru.otus;

import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CacheEngine<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;

    private final Map<K, SoftReference<MapElement<K, V>>> elements = new Map<K, SoftReference<MapElement<K, V>>>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public SoftReference<MapElement<K, V>> get(Object key) {
            return null;
        }

        @Override
        public SoftReference<MapElement<K, V>> put(K key, SoftReference<MapElement<K, V>> value) {
            return null;
        }

        @Override
        public SoftReference<MapElement<K, V>> remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends K, ? extends SoftReference<MapElement<K, V>>> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<K> keySet() {
            return null;
        }

        @Override
        public Collection<SoftReference<MapElement<K, V>>> values() {
            return null;
        }

        @Override
        public Set<Entry<K, SoftReference<MapElement<K, V>>>> entrySet() {
            return null;
        }
    };

    CacheEngine(int m, long l) {
        this.maxElements = m;
        this.lifeTimeMs = l > 0 ? l : 0;
    }

    public void add(MapElement<K,V> element){
        if (elements.size() == maxElements){
            K firstKey = elements.keySet().iterator().next();
            System.out.println(firstKey);
            System.out.println("->> Deleting from cache element " + elements.get(firstKey) + " due to size exceeding");
            elements.remove(firstKey);
        }
        elements.put(element.getKey(), new SoftReference<>(element));
        System.out.println("Added to cache " + element.getKey() + " " + element.getValue());

    }

    public V get(K key){
        SoftReference<MapElement<K, V>> element = elements.get(key);
        if (element == null) {
            return null;
        } else {
            return element.get().getValue();
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
