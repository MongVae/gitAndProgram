import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @Author: mengwei
 * @Date: 2021-1-12 14:44
 */
public class twoNumbers {
        public static int[] twoSum(int[] nums, int target) {
            Map<Integer,Integer> numMap = new HashMap<>();
            for (int i = 0;i<nums.length;i++){
                if (numMap.containsKey(target-nums[i])){
                    return new int[]{numMap.get(target-nums[i]),i};
                }else{
                    numMap.put(nums[i],i);
                }
            }
            return new int[0];
    }
}
