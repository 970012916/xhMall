package com.xhMall.test;

import java.util.Arrays;

/**
 * Created by sheting on Administrator
 * DateTime  2019/2/11,10:32
 */

    public class QuickTest {


        public static void sort(int[] a){
            if (a.length>0) {
                sort(a,0,a.length-1);
            }

        }
        public static void sort(int[] a,int low,int height){
            int i=low;
            int j=height;
            if (i>j) {
                return;
            }
            int k=a[i];

            while (i<j) {
                while (i<j&&a[j]>k) {
                    j--;
                }
                while (i<j&&a[i]<=k) {//找出大的数
                    i++;
                }
                if(i<j) {//交换
                    int swap = a[i];
                    a[i] = a[j];
                    a[j] = swap;
                }
            }
            //交换K
            k=a[i];
            a[i]=a[low];
            a[low]=k;

            //对左边进行排序,递归算法
            sort(a, 0, i-1);
            //对右边进行排序
            sort(a,i+1,height-1);
        }
        public static void main(String[] args) {
            int[] arr={5,9,7,4,5,7,6,1,9,9,7,4};
            System.out.println(Arrays.toString(arr));
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }
}
