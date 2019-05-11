public class Test2 {
    public static void main(String[] args) {
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        // 准备1~100按顺序排列的数字序列
        for (int i = 1; i <= 100; i++) {
            doubleLinkList.addLast(i);
        }
        doubleLinkList.displayAllElements();
        doubleLinkList.convert();
        doubleLinkList.displayAllElements();
    }
}

/**
 * 双向链表类
 */
class DoubleLinkList {
    // 始终指向链表中第一个节点
    private Link first;
    // 始终指向链表中最后一个节点
    private Link last;

    /**
     * 在链表的末尾添加元素
     *
     * @param value 元素的值
     */
    public void addLast(int value) {
        Link newLink = new Link(value);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            newLink.pre = last;
            last.next = newLink;
            last = newLink;
        }
    }

    /**
     * 转换函数，将顺序数列转换成题目中要求的数列
     */
    public void convert() {
        Link p = first;
        while (p != null) {
            if (p == first) {
                p.pre = last;
                removeLast();
                p.pre.next = p;
                p.pre.pre = null;
                first = p.pre;
            } else {
                p.pre.next = last;
                Link oldPre = p.pre;
                p.pre = last;
                removeLast();
                p.pre.next = p;
                p.pre.pre = oldPre;
            }
            p = p.next;
        }
    }

    /**
     * 按照链表中元素的顺序显示所有元素的值
     */
    public void displayAllElements() {
        for (Link p = first; p != null; p = p.next) {
            System.out.print(p.value + " ");
        }
        System.out.println();
    }

    /**
     * 删除链表中的最后一个元素
     */
    private void removeLast() {
        last.pre.next = null;
        last = last.pre;
    }
}

/**
 * 双向链表中的元素类
 */
class Link {
    // 元素值
    int value;
    // 向前指针
    Link next;
    // 向后指针
    Link pre;

    public Link(int value) {
        this.value = value;
    }
}