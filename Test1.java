public class Test1 {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        BigInteger result = fib.calculateFibNLength(1000);
        // 打印结果
        System.out.println(result);
    }
}

/**
 * 菲波那切数列类
 */
class Fibonacci {

    /**
     * 计算第一个长度为N的斐波那契数
     * @param n 斐波那契数的长度
     * @return 斐波那契数列中第一个长度是N的数字
     */
    public BigInteger calculateFibNLength(int n) {
        BigInteger aNum = new BigInteger(1, n);
        BigInteger bNum = new BigInteger(1, n);
        BigInteger temp;
        while(true) {
            aNum.add(bNum);
            if (aNum.checkNumLength()) {
                return aNum;
            }
            temp = aNum;
            aNum = bNum;
            bNum = temp;
        }
    }
}

/**
 * 大数类
 */
class BigInteger {
    /** 用于存储大数的数组 */
    private int[] numberArray;
    /** 记录数字的真正长度 */
    private int numberLength;

    /**
     * 构造函数
     * @param number 初始化数字
     * @param arrayLength 存储大数数组的长度
     */
    public BigInteger(int number, int arrayLength) {
        numberArray = new int[arrayLength];
        while(number != 0) {
            if (arrayLength == 0) {
                throw new IllegalArgumentException("数组长度分配太小，无法承载数字");
            }
            numberArray[--arrayLength] = number % 10;
            number /= 10;
            this.numberLength++;
        }
    }

    /**
     * 实现大数加法，相加结果存储到本对象的numberArray中，并更新数字长度numberLength
     * @param addend 被加数
     */
    public void add(BigInteger addend) {
        int carry = 0, temp;
        int i = this.numberArray.length - 1;
        int j = addend.numberArray.length - 1;
        int iEnd = this.numberArray.length - this.numberLength;
        int jEnd = addend.numberArray.length - addend.numberLength;
        while(i >= iEnd && j >= jEnd) {
            temp = this.numberArray[i] + addend.numberArray[j] + carry;
            this.numberArray[i] = temp % 10;
            carry = temp / 10;
            i--;
            j--;
        }
        while(i >= iEnd) {
            temp = this.numberArray[i] + carry;
            this.numberArray[i] = temp % 10;
            carry = temp / 10;
            i--;
        }
        while(j >= jEnd) {
            temp = addend.numberArray[j] + carry;
            this.numberArray[j] = temp % 10;
            carry = temp / 10;
            j--;
            // 始终用i变量记录第一个数的位置
            i = j;
        }
        if (carry > 0) {
            this.numberArray[i] = carry;
        } else {
            i++;
        }
        this.numberLength = this.numberArray.length - i;
    }

    /**
     * 通过检查数组第0个元素是否是0，判断数字是否到达指定长度
     * @return 判断结果，如果已经到达指定长度，返回true
     */
    public boolean checkNumLength() {
        return this.numberArray[0] > 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        boolean flag = false;
        for (int num : this.numberArray) {
            // 略过前面的0
            if (num > 0 && !flag) {
                // 代表数字前面的0已经全部略过，后面都是真正的数字
                flag = true;
            }
            if (flag) {
                sb.append(num);
            }
        }
        return sb.toString();
    }
}