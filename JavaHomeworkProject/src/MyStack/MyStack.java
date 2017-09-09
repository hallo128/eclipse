package MyStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 堆栈类——先进后出
 * 入栈（放入元素）：在首元素位置进行插入
 * 出栈（取出元素）：取出并删除首元素
 * 涉及：泛型编程
 * @author hallo128
 * @date 2017年9月8日 下午3:54:48
 * @remark TODO
 *
 */
public class MyStack<T> {
	/**保存元素的对象数组*/
	protected Object[] elementData;
	/**元素个数*/
	protected int elementCount;
	/**容量的增加量——当存储元素的数组空间不够时，自动增加那么多空间*/
	protected int capacityIncrement;
	
	//构造函数(实例化时，必须具有：1-initialCapacity初始容量[以参数传入，不作为属性]、2-增加量)
	public MyStack(){
		this(10);
	}
	public MyStack(int initialCapacity){
		this(initialCapacity,10);
	}
	public MyStack(int initialCapacity, int capacityIncrement){
		//1-先判断初始化容量是否为0
		if(initialCapacity < 0){
			throw new IllegalArgumentException("初始容量不能小于0："+ initialCapacity);
		}
		elementData = new Object[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}
	
	//入栈：扩容、复制、增加
	
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
	 * 入栈：在栈的第一个元素位置，插入新元素t
	 * 步骤：扩容、复制、增加
	 * 
	 * 解析：System.arraycopy(src, srcPos, dest, destPos, length);
	 * src-要复制的源数组
	 * srcPos—要复制的源数组的起始下标
	 * dest-复制的目标数组
	 * destPos-复制到目标数组的destPos处
	 * length：复制的长度
	 * 
	 * @param T类型的一个元素
	 */
	public void push(T t){
		//1-扩容
		grow();
		//2-复制源数组
		System.arraycopy(elementData, 0, elementData, 1, elementCount);
		//3-添加到首元素
		elementData[0] = t;
		elementCount++;
	}
	
	/**
	 * 判断当前数组是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return elementCount == 0;
	}
	
	/**
	 * 查看栈顶元素，不删除
	 * 步骤：当前数组是否为空，返回首元素
	 * @return 返回出栈值
	 */
	public T peek(){
		//1-当前数组是否为空
		if(isEmpty()) return null;
		//2-不为空时，返回首元素
		return (T)elementData[0];
	}
	
	//出栈：isEmpty，根据0下标删除（默默改变数组），返回出栈值
	
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
	 * 出栈-在数组中删除首元素
	 * 步骤:isEmpty，返回出栈值,根据0下标删除（默默改变数组）
	 * @return 返回栈顶元素
	 */
	public T pop(){
		//1-isEmpty
		if(isEmpty()){
			System.out.println("数组为空，不能进行出栈操作！");
			return null;
		}
		//2-返回出栈值
		T t = peek();
		//3-remove
		remove(0);
		return t;
	}
	
	/**
	 * 返回栈对象的迭代器
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
