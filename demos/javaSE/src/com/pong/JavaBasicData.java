package com.pong;

/**
 * @author hy
 * @version 1.0
 * @since 1.8
 */
public class JavaBasicData {
    public static void main(String[] args) {
        /**
         * 进制
         * 可以通过不同的前缀表示不同的进制
         */
        int i = 10;
        int j = 010; //八进制
        int k = 0x10; //十六进制
        System.out.println(i + "--" + j + "--" + k);

        /**
         * 浮点数
         * 存在精度的丢失问题
         * 最好完全避免使用浮点数进行比较
         * 一般使用 BigDecimal 类
         */
        float f = 0.1f;
        double d = 1 / 10;
        System.out.println(f);
        System.out.println(d);
        System.out.println(f == d);

        float f1 = 123456789f;
        float f2 = f1 + 1;
        System.out.println(f1 == f2);

        /**
         * 将字符串转换为 int
         * 字符本质还是数字
         * 编码格式是 Unicode，可以转化任何语言，占两个字节
         */

        char c1 = 'a';
        char c2 = '中';
        char c3 = '\u0061';

        System.out.println(c1 + "---" + (int) c1);
        System.out.println(c2 + "---" + (int) c2);
        System.out.println(c3);

    }
}
