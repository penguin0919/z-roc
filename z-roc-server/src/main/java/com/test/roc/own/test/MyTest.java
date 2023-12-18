package com.test.roc.own.test;
import org.junit.Test;
/**
 * 测试
 *
 * @author z-Roc
 * @date 2023-12-18 15:28
 **/
public class MyTest {

    /**
     * 打印圣诞树
     * */
    @Test
    public void christmasTree() {
        int height = 10;
        int space = height - 1;
        int stars = 1;

        for (int i = 0; i < height; i++) {
            // 打印空格
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            // 打印星号
            for (int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            // 每行加两个星号
            stars = stars + 2;
            // 换行
            System.out.println();
            // 减少空格数
            space = space - 1;
        }
        // 打印树干
        for (int i = 0; i<height - 1; i++){
            System.out.print(" ");
        }
        System.out.print("|");
    }
}
