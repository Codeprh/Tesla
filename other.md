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