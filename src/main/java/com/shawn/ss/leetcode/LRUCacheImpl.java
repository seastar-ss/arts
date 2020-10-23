package com.shawn.ss.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRUCacheImpl<K, V> {

    public static void main(String[] args) {
        LRUCacheImpl<Integer, Integer> cache = new LRUCacheImpl<>(15);
        cache.put(3, 4);
        cache.put(4, 5);
        for (int i = 0; i < 20; ++i) {
            cache.put(i, i + 3);
        }
        System.out.println(cache.get(2));
        System.out.println(cache.get(5));
        System.out.println(cache.get(6));
        cache.put(12, 2);
        System.out.println(cache.get(19));
    }

    private final int capicity;
    private final Map<K, Entry<K, V>> map;

    static class Entry<K, V> {
        V value;

        Entry<K, V> next;
        Entry<K, V> prev;
        K key;

        public Entry(K key, V value) {
            this.value = value;
            this.prev = null;
            this.next = null;
            this.key = key;
            //            this.node = node;
        }

        public Entry(K key, V value, Entry<K, V> next, Entry<K, V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
            this.key = key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next, prev);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) return false;
            Entry e = (Entry) obj;
            return this.next == e.next && this.prev == e.prev && Objects.equals(key, e.key) && Objects.equals(value, e.value);
        }
    }

    //    static class Node<K> {
    //
    //
    //        public Node(Node<K> next, Node<K> prev, K key) {
    //            this.next = next;
    //            this.prev = prev;
    //            this.key = key;
    //        }
    //    }

    /**
     * dummy node
     */
    Entry<K, V> headNode;
    Entry<K, V> lastNode;

    //2(2(2(1)+1)+1)+1
    //2^4-1

    public LRUCacheImpl(int capicity) {
        if (capicity < 1) throw new IllegalArgumentException("wrong capacity");
        this.capicity = capicity;
        this.map = new HashMap<>();
        headNode = new Entry<>(null, null);
    }

    public V get(K key) {
        Entry<K, V> kvEntry = map.get(key);
        if (kvEntry == null) return null;
        swapHeadNode(kvEntry);
        return kvEntry.value;
    }

    private void swapHeadNode(Entry<K, V> entry) {

        if (entry == lastNode && entry.prev != headNode) {
            lastNode = entry.prev;
            lastNode.next = null;
            Entry<K, V> hn = headNode.next;
            headNode.next = entry;
            entry.prev = headNode;
            hn.prev = entry;
            entry.next = hn;
        } else if (entry == lastNode && entry.prev == headNode) {
            //lastNode = null;
            //do nothing
        } else if (entry.prev == headNode) {
            //do nothing
        } else {
            Entry<K, V> next = entry.next;
            Entry<K, V> prev = entry.prev;
            prev.next = next;
            next.prev = prev;
            Entry<K, V> hn = headNode.next;
            headNode.next = entry;
            entry.prev = headNode;
            hn.prev = entry;
            entry.next = hn;
        }
    }

    public boolean put(K key, V v) {
        boolean add;
        if (!map.containsKey(key)) {
            Entry<K, V> node;
            Entry<K, V> next = headNode.next;
            node = new Entry<K, V>(key, v, next, headNode);
            map.put(key, node);
            if (next == null) {
                lastNode = node;
            } else {
                next.prev = node;
            }
            headNode.next = node;
            add = true;
        } else {
            Entry<K, V> kvEntry = map.get(key);
            swapHeadNode(kvEntry);
            add = false;
        }
        if (map.size() > capicity) {
            if (lastNode != null) {
                map.remove(lastNode.key);
                lastNode = lastNode.prev == headNode ? null : lastNode.prev;
            }
        }
        return add;
    }


}
