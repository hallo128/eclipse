package MyStack;

import java.util.Iterator;

public class MyStackTest {

	public static void main(String[] args) {
		MyStack<String> stack = new MyStack<String>();

		for (int i = 0; i < 5; i++) {
			stack.push("入栈"+(i+1));
		}
//		System.out.println("出栈：");
//		for (int i = 0; i < 5; i++) {
//			System.out.println(stack.pop());
//		}
		
		//使用迭代器遍历栈
		Iterator<String> stackIt = stack.iterator();
		while(stackIt.hasNext()){
			System.out.println(stackIt.next());
		}
		
	}

}
