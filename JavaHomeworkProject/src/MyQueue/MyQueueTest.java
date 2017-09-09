package MyQueue;

import java.util.Iterator;

public class MyQueueTest {

	public static void main(String[] args) {
		MyQueue<String> queue = new MyQueue<String>();

		for (int i = 0; i < 5; i++) {
			queue.offer("入队"+(i+1));
		}
//		System.out.println("出队：");
//		for (int i = 0; i < 5; i++) {
//			System.out.println(queue.poll());
//		}
		
		//使用迭代器遍历栈
		Iterator<String> queueIt = queue.iterator();
		while(queueIt.hasNext()){
			System.out.println(queueIt.next());
		}
		
	}

}
