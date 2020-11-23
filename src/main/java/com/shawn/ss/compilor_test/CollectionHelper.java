package com.shawn.ss.compilor_test;

//import com.shawn.ss.lib.tools.structure.FIFOList;

import java.util.*;
//import com.app.china.framework.api._base.Stringifiable;
//import com.google.gson.Gson;

public abstract class CollectionHelper<K, T> {

    public static final int LINKED_LIST_TYPE = 5, ARRAY_LIST_TYPE = 4, HASH_SET_TYPE = 2, TREE_SET_TYPE = 3, HASH_MAP_TYPE = 0, TREE_MAP_TYPE = 1;

    public static interface Traveler<K, V> {
        public boolean travel(K k, V v, int times);
    }

    private transient final int type;

    public static <E> List newEmptyList() {
        return Collections.emptyList();
    }

    public static <E> List<E> newList() {
        return new ArrayList<E>();
    }

    public static <E> List<E> newList(int size) {
        return new ArrayList<E>(size);
    }

    public static <E> List<E> newList(E... elements) {
        int capacity = (elements.length * 110) / 100 + 20;
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> List<E> newLinkedList() {
        return new LinkedList<>();
    }

//    public static <E> List<E> newFixedList(int size) {
//        return new FIFOList<>(size);
//    }

//    public static <E> List<E> newFixedList(E... element) {
//        FIFOList<E> ret = new FIFOList<>(element.length);
//        for (E el : element) {
//            ret.add(el);
//        }
//        return ret;
//    }

    public static <K, V> Map newEmptyMap() {
        return Collections.emptyMap();
    }

    public static <K, V> Map<K, V> newMap() {
        return new LinkedHashMap<K, V>();
    }

    public static <K, V> Map<K, V> newMap(Map<K, V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> Map<K, V> newMap(int n) {
        return new LinkedHashMap<K, V>(n);
    }

    //
    public static <K, V> Map<K, V> newSortedMap() {
        return new TreeMap<K, V>();
    }

    //
    public static <K> Set<K> newSet() {
        return new HashSet<K>();
    }

    public static <K> Set<K> newSet(Collection<K> values) {
        return new HashSet<K>(values);
    }


    public static <E> Set<E> newSet(E... elements) {
        int capacity = elements.length * 4 / 3 + 1;
        HashSet<E> set = new HashSet<E>(capacity);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> Set<E> newSet(int size) {
//        int capacity = elements.length * 4 / 3 + 1;
        HashSet<E> set = new HashSet<E>(size);
//        Collections.addAll(set, elements);
        return set;
    }

    public static <E> Set<E> newEmptySet() {
        return Collections.emptySet();
    }

    public static <E> SortedSet<E> newSortedSet() {
        return new TreeSet<E>();
    }

    public static <E> SortedSet<E> newSortedSet(E... elements) {
        SortedSet<E> set = new TreeSet<E>();
        Collections.addAll(set, elements);
        return set;
    }

    public static boolean isCollectionEmpty(Collection e){
        return e==null || e.size()==0;
    }

    public static boolean isCollectionEmpty(Map e){
        return e==null || e.size()==0;
    }


    public static int putInMapByKeys(String[] keys, Map src, Map dist) {
        int ret = 0;
        for (String s : keys) {
            if (src.containsKey(s)) {
                dist.put(s, src.get(s));
                ++ret;
            }
        }
        return ret;
    }

    public static <T> Set<T> arrayToSet(T[] fs) {
        Set<T> ret = CollectionHelper.newSet(fs.length);
        Collections.addAll(ret, fs);
        return ret;
    }

    public static Map<String, Object> arrayToMap(Object... params) {
        Map<String, Object> ret = newMap();
        if (params.length % 2 == 0) {
            for (int i = 0, n = params.length; i < n; i += 2) {
                if (params[i] instanceof String) {
                    ret.put(params[i].toString(), params[i + 1]);
                }
            }
        } else {
            throw new IllegalArgumentException(
                    "parameters number should be even");
        }
        return ret;
    }

    public static Map<String, Object> arrayToMap(String[] keys, String[] values) {
        Map<String, Object> ret = newMap();
        int n = (keys.length == values.length ? keys.length : 0);
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                ret.put(keys[i], values[i]);
            }
        } else {
            throw new IllegalArgumentException(
                    "keys length don't match values length");
        }
        return ret;
    }

    public static <K, V> void travelMap(Map<K, V> map, Traveler<K, V> t) {
        Set<Map.Entry<K, V>> entries = map.entrySet();
        int n = 0;
        for (Map.Entry<K, V> entry : entries) {
            boolean travel = t.travel(entry.getKey(), entry.getValue(), n);
            if (!travel) {
                break;
            }
            ++n;
        }
    }

    protected CollectionHelper(int type) {
        this.type = type;
    }

    public static boolean getBoolean(Map src, Object k) {
        Object get = src.get(k);
        if (get == null) {
            return false;
        } else {
            return Boolean.parseBoolean(get.toString());
        }
    }

//    public static long getLong(Map src, Object k) {
//        Object get = src.get(k);
//        return StringHelper.getLong(get);
//    }
//
//    public static double getDouble(Map src, Object k) {
//        Object get = src.get(k);
//        return StringHelper.getDouble(get);
//    }
//
//    public static int getInt(Map src, Object k) {
//        Object get = src.get(k);
//        return StringHelper.getInt(get);
//    }
//
//    public static String getString(Map src, Object k) {
//        Object get = src.get(k);
//        return StringHelper.getString(get);
//    }
//
//    public static Date getDate(Map src, Object k) {
//        Object get = src.get(k);
//        return StringHelper.getDate(get);
//    }


    public static <T> void fillListWithMapValue(List<T> target, Map<?, T> source) {
        for (Map.Entry<?, T> entry : source.entrySet()) {
            target.add(entry.getValue());
        }
    }

    public static <T> List<T> getListFilledWithElement(T el, int size) {
        List<T> ret = newList(size);
//        Collections.fill(ret,el);
        for (int i = 0; i < size; ++i) {
            ret.add(el);
        }
        return ret;
    }

    public static <T, E> Map<T, E> revertMap(Map<E, T> src) {
        if (src != null) {
            Map<T, E> ret = newMap();
            Set<Map.Entry<E, T>> entries = src.entrySet();
            for (Map.Entry<E, T> entry : entries) {
                ret.put(entry.getValue(), entry.getKey());
            }
            return ret;
        }
        return null;
    }

    public static <K, T> MapBuilder<K, T> mapBuilder() {
        return CollectionHelper.mapBuilder(false);
    }

    public static <T> SetBuilder<T> setBuilder() {
        return CollectionHelper.setBuilder(false);
    }

    public static <T> ListBuilder<T> listBuilder() {
        return CollectionHelper.listBuilder(false);
    }

    public static <K, T> MapBuilder<K, T> mapBuilder(boolean sorted) {
        return new MapBuilder<K, T>(sorted ? TREE_MAP_TYPE : HASH_MAP_TYPE);
    }

    public static <T> SetBuilder<T> setBuilder(boolean sorted) {
        return new SetBuilder<T>(sorted ? TREE_SET_TYPE : HASH_SET_TYPE);
    }

    public static <T> ListBuilder<T> listBuilder(boolean linked) {
        return new ListBuilder<T>(linked ? LINKED_LIST_TYPE : ARRAY_LIST_TYPE);
    }

    public static final class MapBuilder<K, T> extends CollectionHelper<K, T> {

        private final Map<K, T> map;

        private MapBuilder(int type) {
            super(type);
            if (type == HASH_MAP_TYPE)
                map = CollectionHelper.newMap();
            else
                map = CollectionHelper.newSortedMap();
        }

        public Map<K, T> getMap() {
            return map;
        }

        public <KK extends K, TT extends T> MapBuilder<K, T> put(KK key, TT value) {
            map.put(key, value);
            return this;
        }

        public MapBuilder<K, T> putAll(Map<? extends K, ? extends T> nMap) {
            map.putAll(nMap);
            return this;
        }

    }

    public static final class ListBuilder<T> extends CollectionHelper<T, T> {

        private final List<T> list;

        private ListBuilder(int type) {
            super(type);
            if (type == ARRAY_LIST_TYPE)
                list = CollectionHelper.newList();
            else
                list = CollectionHelper.newLinkedList();
        }

        public <TT extends T> ListBuilder<T> add(TT l) {
            list.add(l);
            return this;
        }

        public <TT extends T> ListBuilder<T> add(TT... args) {
            for (T arg : args) {
                list.add(arg);
            }
            return this;
        }

        public <TT extends T> ListBuilder<T> addAll(Collection<TT> args) {
            for (T arg : args) {
                list.add(arg);
            }
            return this;
        }

        public List<T> getList() {
            return list;
        }

    }

    public static final class SetBuilder<T> extends CollectionHelper<T, T> {

        private final Set<T> set;

        private SetBuilder(int type) {
            super(type);
            if (HASH_SET_TYPE == type)
                set = CollectionHelper.newSet();
            else
                set = CollectionHelper.newSortedSet();
        }

        public <TT extends T> SetBuilder<T> add(TT l) {
            this.set.add(l);
            return this;
        }

        public <TT extends T> SetBuilder<T> add(TT... args) {
            for (T arg : args) {
                set.add(arg);
            }
            return this;
        }

        public <TT extends T> SetBuilder<T> addAll(Collection<TT> args) {
            for (T arg : args) {
                set.add(arg);
            }
            return this;
        }

        public Set<T> getSet() {
            return set;
        }

    }

}
