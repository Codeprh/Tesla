# DemoAll项目日常算法和小栗子代码

1. **[主要是用于刷剑指offer的题目](https://github.com/Codeprh/DemoAll/tree/master/src/swordFingerOffer)**

2. [**主要用于实现SOA的负载均衡算法**](https://codeprh.github.io/2018/11/07/%E5%A4%A7%E5%9E%8B%E5%88%86%E5%B8%83%E5%BC%8F%E7%BD%91%E7%AB%99%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E8%B7%B5/)

   - [**详细参考本人博客**](https://codeprh.github.io/2018/11/07/%E5%A4%A7%E5%9E%8B%E5%88%86%E5%B8%83%E5%BC%8F%E7%BD%91%E7%AB%99%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E8%B7%B5/)
   - **轮训法**
   - **随机法**
   - **源地址哈希法**
   - **加权轮训法**
   - **加权随机法**
   - **最小链接法**

3. [**实现线程同步的方法**](https://github.com/Codeprh/DemoAll/blob/master/md/2018-12-18-%E7%BA%BF%E7%A8%8B%E5%90%8C%E6%AD%A5.md)

   - **同步方式一：使用synchronized同步方法**
   - **同步方式二：使用synchronized同步代码块**
   - **同步方式三：使用volatile关键字**
   - **同步方式四：使用ReentrantLock重入锁关键字**
   - **同步方式五：使用ThreadLocal局部变量实现线程同步**
   - **同步方式六：使用阻塞队列实现线程同步**
   - **同步方式七：使用原子变量实现线程同步**

4. 实现泛型List,使用forEeach()

   ```java
   /**
    * 描述:
    * 自己创建的链表
    *
    * @author codingprh
    * @create 2019-01-12 7:52 AM
    */
   public class MyLinkedList<T> implements Iterable<T> {
   
       private MyNode<T> head;
       private MyNode<T> tail;
   
       public MyNode getHead() {
           return head;
       }
   
       public MyNode getTail() {
           return tail;
       }
   
       public MyLinkedList() {
       }
   
   
       public static <T> MyLinkedList<T> newEmptyList() {
           return new MyLinkedList<T>();
       }
   
       public void addNode(T val) {
           MyNode<T> node = new MyNode<>(val);
           //初始化数据
           if (tail == null) {
               head = node;
           } else {
               tail.setNext(node);
           }
           tail = node;
       }
   
   
       private class MyListIterator implements Iterator<T> {
           private MyNode<T> currentNode;
   
           @Override
           public boolean hasNext() {
               return currentNode != null;
           }
   
           @Override
           public T next() {
               if (currentNode == null) {
                   throw new NoSuchElementException();
               }
               T val = currentNode.getValue();
               currentNode = currentNode.getNext();
               return val;
           }
   
           public MyListIterator(MyNode<T> currentNode) {
               this.currentNode = currentNode;
           }
       }
   
       @Override
       public Iterator<T> iterator() {
           return new MyListIterator(head);
       }
   
   
   }
   ```

5. 编写两个线程，一个线程打印1~25，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z，要求使用线程间的通信。

   ```java
       /**
        * 实现思路：
        * 1、通过getDigital(max)取出最大的number数值
        * 2、通过 getAlphabet(maxAlphabet)取出需要打印的字母，使用char A=(char)65;
        * 3、private int lock = 2;作为信号量进行线程之间的通信
        * 4、实现单例模式的线程锁：enum实现和双重锁实现
        *
        * @param digitalList
        * @param alphabetList
        */
   public void go(List<Integer> digitalList, List<String> alphabetList) {
           //SingletonThreadPoolEnum executorServicePool = SingletonThreadPoolEnum.INSTANCE;
           DoubleCheckedSingthonThreadPool executorServicePool = DoubleCheckedSingthonThreadPool.getInstallce();
           executorServicePool.run(() -> {
               try {
                   synchronized (this) {
                       for (int i = 0; i < digitalList.size(); i = i + 2) {
                           if (Objects.equals(this.lock, 1)) {
                               this.wait();
                           }
                           printList(digitalList.subList(i, i + 2));
                           this.lock = 1;
                           this.notify();
                       }
   
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
   
           });
   
           executorServicePool.run(() -> {
               try {
                   synchronized (this) {
                       for (int i = 0; i < alphabetList.size(); i++) {
                           if (Objects.equals(this.lock, 2)) {
                               this.wait();
                           }
                           printList(alphabetList.subList(i, i + 1));
                           this.lock = 2;
                           this.notify();
                       }
   
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
   
           });
       }
   
   ```

6. 