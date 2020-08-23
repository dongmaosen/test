package org.rookie.test;

import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 学习java的位操作
 */
public class BitTest {
    @Test
    public void binaryInJava() {
//        原码：10000000 00000000 00000000 00000100（转二进制，最高位为符号位）
//        反码：11111111 11111111 11111111 11111011（符号位不变，其余取反）
//        补码：11111111 11111111 11111111 11111100（反码+1）
        int a = 7;
        int b = -7;
        System.out.println(Integer.toBinaryString(a));
        //补码=反码+1
        System.out.println(Integer.toBinaryString(b));
    }

    /**
     * 移位过程详细描述
     */
    @Test
    public void shift() {
        //原码 10000000 00000000 00000000 00000111
        //反码 11111111 11111111 11111111 11111000
        //补码 11111111 11111111 11111111 11111001 移位操作对象是补码
        //左移一位 11111111 11111111 11111111 11110010 （这时候还是补码）
        //# 如果最高位符号位为0，就不需要继续操作了，因为正数的补码=原码，如果最高位是1，继续往下走
        //转成反码 11111111 11111111 11111111 11110001 （补码-1）
        //转成原码 10000000 00000000 00000000 00001110 （忽略符号位取反）
        //转十进制 -14
        int a = -7;
        System.out.println(Integer.toBinaryString(a << 1));
        System.out.println(Integer.toString(a << 1));
    }

    @Test
    public void options() {
        // << 整体左移，右边空位补零，左边丢弃
        // >> 整体右移，正数左边补0，负数左边补1，右边舍弃
        // >>> 整体右移，左边补0，只补0
        // 00000000 00000000 00000000 00000111
        int a = 7;
        // 11111111 11111111 11111111 11111001
        int b = -7;
//        System.out.println(Integer.toBinaryString(a >>> 1));
//        System.out.println(a >>> 1);
//        System.out.println(Integer.toBinaryString(b >>>30).length() + ":" + Integer.toBinaryString(b >>> 30));
//        System.out.println(b >>> 30);
        // &  每一位进行与操作
//        System.out.println(a & b);
        // | 按位异或
//        System.out.println(Integer.toBinaryString(a | b));
//        System.out.println(a | b);
        // ~ 按位取反，符号位也取反
        // 11111111 11111111 11111111 11111000 补码
        System.out.println(Integer.toBinaryString(~a));
        // 11111111 11111111 11111111 11110111 反码
        // 10000000 00000000 00000000 00001000 原码
        System.out.println(~a);
        // ^ 按位异或,包括符号位
        // 11111111 11111111 11111111 11111110
        System.out.println(Integer.toBinaryString(a ^ b));
        System.out.println(a ^ b);
    }

    /**
     * 小白鼠问题
     */
    @Test
    public void whiteMouse() {
        int bitSize = 3;
        int poisonousNumber = 2;
        int bottles = 8;
        findBottle(bitSize, poisonousNumber, bottles);
    }

    /**
     * 寻找有毒瓶子编号的过程，打印出哪些小白鼠会死亡，反推的过程
     * @param bitSize 比特位数
     * @param poisonousNumber 有毒瓶子的编号
     * @param bottles 瓶子数量
     */
    private void findBottle(int bitSize, int poisonousNumber, int bottles) {
        int[] bs = new int[bottles];
        String[] binaryString = new String[bottles];
        for(int i = 0; i < bottles; i++) {
            bs[i] = i;
            binaryString[i] = Integer.toBinaryString(i);
        }
        //分组，从第一位开始，各个位对应是1的为一组，获得的分组将给第i个小白鼠
        for (int i = 1; i <= bitSize; i++) {
            boolean die = false;
            System.out.print(i + " : ");
            for(int j = 0; j < bottles; j++) {
                if (binaryString[j].length() >= i){
                    char ch = binaryString[j].charAt(binaryString[j].length() - i);
                    if (ch == '1') {
                        System.out.print(bs[j] + " ");
                        if (poisonousNumber == i) {
                            die = true;
                        }
                    }
                }
            }
            System.out.println(" : die = " + die);
        }

    }
}
