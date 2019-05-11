public class Test3 {
    public static void main(String[] args) {
        long sum = 0;
        int i;
        for (i = 2; i < 2000000; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    /**
     * 判断一个数字是否是质数
     * @param num 数字
     * @return 判断结果，如果num是质数，返回true
     */
    public static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}