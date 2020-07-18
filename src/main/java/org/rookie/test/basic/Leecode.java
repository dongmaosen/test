package org.rookie.test.basic;

public class Leecode {
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret ^= nums[i];
        }
        return ret;
    }

    /**
     *给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     */
    public int majorityElement(int[] nums) {
        int n = nums[0];
        int counter = 1;
        for (int i = 1; i < nums.length; i++) {
            if (n == nums[i]) {
                counter++;
            } else {
                counter--;
            }
            if (counter == 0 && i < nums.length - 1) {
                n = nums[i + 1];
            }
        }
        return n;
    }
    public static void main(String[] args) {
        Leecode lc = new Leecode();
//        int[] nums = {3,2,3};
//        System.out.println(lc.singleNumber(nums));
        int[] nums = {3,2,3,3,2,2,2};
        System.out.println(lc.majorityElement(nums));
    }
}
