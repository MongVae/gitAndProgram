/**
 * @Author: mengwei
 * @Date: 2021-1-12 17:08
 */
public class twoArray {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count = nums1.length + nums2.length;
        int[] nums3 = new int[count / 2 + 1];
        int m = 0;
        int n = 0;
        for (int i = 0; i < count / 2 + 1; i++) {
            if (m == nums1.length) {
                nums3[i] = nums2[n];
                n++;
                continue;
            }
            if (n == nums2.length) {
                nums3[i] = nums1[m];
                m++;
                continue;
            }
            if (nums1[m] <= nums2[n]) {
                nums3[i] = nums1[m];
                m++;
            } else {
                nums3[i] = nums2[n];
                n++;
            }
        }
        if (count % 2 == 0) {
            return (double) (nums3[count / 2 - 1] + nums3[count / 2]) / 2;
        } else {
            return nums3[(count - 1) / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double a = findMedianSortedArrays(nums1, nums2);
        System.out.println(a);
    }
}
