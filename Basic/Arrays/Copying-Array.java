
class Arrays {
    public static void main(String[] args) {
        //int[] nums = new int[10];
        //int nums = new int[10];
        int nums[] = {1, 3, 4, 5, 3};
        int nums1[] = java.util.Arrays.copyOfRange(nums, 0, nums.length);
        
        for(int num : nums) {
            System.out.println(num);
        }
    } 
}