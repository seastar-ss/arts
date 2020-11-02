package com.shawn.ss.leetcode;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * <p>
 * 注意: 允许出现重复元素。
 * <p>
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * <p>
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * <p>
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * <p>
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * <p>
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RandomizedCollectionV2 {

    public static void main(String[] args) {
        RandomizedCollectionV2 v2 = new RandomizedCollectionV2();
        v2.insert(0);
        v2.insert(1);
        v2.remove(0);
        v2.insert(2);
        v2.remove(1);
        System.out.println(v2.elements);
    }

    HashMap<Integer, Set<Integer>> indexes;
    List<Integer> elements;
    Random rnd;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollectionV2() {
        //        random = null;
        //        preRandom = null;
        rnd = new Random();
        indexes = new HashMap<>();
        elements = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> set = indexes.computeIfAbsent(val, e -> new HashSet<>());
        set.add(elements.size());
        elements.add(val);
        return set.size() == 1;
    }


    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> set = indexes.get(val);
        if (set == null || set.size() == 0) {
            if (set != null) indexes.remove(val);
            return false;
        }
        Iterator<Integer> iterator = set.iterator();
        Integer idx = iterator.next();
        iterator.remove();
        if (idx != elements.size() - 1) {
            Integer lastVal = elements.get(elements.size() - 1);
            elements.set(idx, lastVal);
            Set<Integer> set1 = indexes.get(lastVal);
            set1.remove(elements.size() - 1);
            set1.add(idx);
        }
        elements.remove(elements.size() - 1);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int i = rnd.nextInt(elements.size());
        int ret = elements.get(i);
        return ret;
    }
}