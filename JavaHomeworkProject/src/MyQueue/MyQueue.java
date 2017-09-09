package MyQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列类——先进先出
 * 入队（放入元素）：在队列尾位置进行插入
 * 出队（取出元素）：取出并删除首元素
 * 涉及：泛型编程
 * @author hallo128
 * @date 2017年9月8日 下午9:25:41
 * @remark TODO
 *
 */
public class MyQueue<T> {
	/**保存元素的对象数组*/
	protected Object[] elementData;
	/**元素个数*/
	protected int elementCount;
	/**容量的增加量——当存储元素的数组空间不够时，自动增加那么多空间*/
	protected int capacityIncrement;
	
	//构造函数(实例化时，必须具有：1-initialCapacity初始容量[以参数传入，不作为属性]、2-增加量)
	public MyQueue(){
		this(10);
	}
	public MyQueue(int initialCapacity){
		this(initialCapacity,10);
	}
	public MyQueue(int initialCapacity, int capacityIncrement){
		//1-先判断初始化容量是否为0
		if(initialCapacity < 0){
			throw new IllegalArgumentException("初始容量不能小于0："+ initialCapacity);
		}
		elementData = new Object[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}
	
	//入队：扩容、追加
	
	/**
	 * 扩容：当元素总数与数组空间大小相同时，就增加容量
	 * 调用函数：Arrays.copyOf（其实是重新new了一个对象）
	 */
	public void grow(){
		if(elementCount == elementData.length){
			elementData = Arrays.copyOf(elementData, elementCount+capacityIncrement);
		}
	}
	
	/**
	 * 入队：在队尾，插入新元素t
	 * 步骤：扩容、追加
	 * @param T类型的一个元素
	 */
	public void offer(T t){
		//1-扩容
		grow();
		//2-添加到最后元素,并增加元素个数
		elementData[elementCount++] = t;
	}

	
	/**
	 * 判断当前数组是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return elementCount == 0;
	}
	
	/**
	 * 查看队首元素，不删除
	 * 步骤：当前数组是否为空，返回首元素
	 * @return 返回出栈值
	 */
	public T peek(){
		//1-当前数组是否为空
		if(isEmpty()) return null;
		//2-不为空时，返回首元素
		return (T)elementData[0];
	}
	
	//出队：isEmpty，根据0下标删除（默默改变数组），返回出队值
	
	/**
	 * 根据下标删除元素，改变数组
	 * 步骤：判断是否合法（isEmpty与index），改变数组
	 * @param index 下标
	 */
	public void remove(int index){
		//1-判断是否合法
		if(isEmpty()) return;
		//下标越界
		if(index < 0 || index >= elementCount){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		//2-进行删除
		System.arraycopy(elementData, index+1, elementData, index, elementCount-1-index);
		elementCount--;
		elementData[elementCount] = null;//必须将原来的最后一位重置为null
	}
	
	/**
	 * 出队-在数组中删除首元素
	 * 步骤:isEmpty，返回出栈值,根据0下标删除（默默改变数组）
	 * @return 返回队列的第一个元素
	 */
	public T poll(){
		//1-isEmpty
		if(isEmpty()){
			System.out.println("数组为空，不能进行出队操作！");
			return null;
		}
		//2-返回出队值
		T t = peek();
		//3-remove
		remove(0);
		return t;
	}
	
	/**
	 * 返回队列对象的迭代器
	 * @return
	 */
	public Iterator<T> iterator(){
		return new Iterator<T>() {//以匿名的方式实现Iterator接口
			int index = 0;
			@Override
			public boolean hasNext() {
				return index < elementCount;
			}
			@Override
			public T next() {
				if(hasNext()){
					return (T)elementData[index++];
				}
				throw new NoSuchElementException("没有接下来的元素了");
			}
		};
	}	
		

}
