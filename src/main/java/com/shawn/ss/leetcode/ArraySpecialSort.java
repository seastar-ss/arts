package com.shawn.ss.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/*
给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

 

示例：

输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]
 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/relative-sort-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArraySpecialSort {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) return arr1;
        if (arr2 == null || arr2.length == 0) return arr1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, n = arr2.length; i < n; ++i) {
            map.put(arr2[i], i);
        }
        return Arrays.stream(arr1).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer i1 = map.get(o1);
                Integer i2 = map.get(o2);
                if(i1==null && i2==null) return o1-o2;
                else if(i1==null && i2!=null) return 1;
                else if(i1!=null && i2==null) return -1;
                else return i1-i2;
            }
        }).mapToInt(i->i).toArray();
//        return arr1;
    }
}
