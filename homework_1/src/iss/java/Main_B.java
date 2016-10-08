package iss.java;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import iss.java.list.*;

/**
 * Created by wenke on 2016/9/16.
 */

class list_operation2{
	public static Lock lock = new ReentrantLock();
	private MyList list=null;
	//ReadWriteLock my_lock= new ReentrantReadWriteLock(); 
	public void insert(MyList list)
	{
		this.list=list;
		lock.lock();
		try {
			for(Node n = this.list.getHead().getNext();n!=this.list.getTail();n=n.getNext())
			{
				//System.out.println(Thread.currentThread().getName()+"准备读入数据！");
				System.out.println(Thread.currentThread().getName()+"读出数据："+n.getData());
				if( n.getData()==1){
					this.list.insert(n, 1000);
					System.out.println(Thread.currentThread().getName()+"读取到Data是1的节点，插入数据："+n.getNext().getData());
				}
				
			}
       } finally {
           lock.unlock();
       }
	}

	public void delete(MyList list)
	{
		this.list=list;
		lock.lock();
		try{
			for(Node n = this.list.getHead().getNext();n!=this.list.getTail();n=n.getNext())
			{
				System.out.println(Thread.currentThread().getName()+"读出数据："+n.getData());
				if( n.getData()==3){
					System.out.printf(Thread.currentThread().getName()+"读取到Data是%d的节点，删除Data为%d节点.\n",n.getData(),n.getData());
					this.list.remove(n);
				}
			}
		}finally{
			lock.unlock();
		}
		
	}
}
//thread 1, add 20 for each data.
class thread_list_insert extends Thread{
	private MyList list = null;
	private list_operation2 list_operation=new list_operation2();
	public thread_list_insert(MyList list){
		this.list=list;
	}
	public void run(){
		list_operation.insert(list);
	}
}
//thread 2, decrease 10 for each data. result should be :  10,11,12,13,14,15
class thread_list_delete extends Thread{
	private MyList list=null;
	private list_operation2 list_operation=new list_operation2();
	public thread_list_delete(MyList list){
		
		this.list=list;
	}
	public void run(){
		list_operation.delete(list);

	}
}



public class Main_B {
    
	public static void main(String[] args) throws InterruptedException {
        // TODO: Implement a multithreaded test case against requirement A.
    	MyList list1= new MyList();
    	list1.insert(list1.getHead(), 5);
    	list1.insert(list1.getHead(), 4);
    	list1.insert(list1.getHead(), 3);
    	list1.insert(list1.getHead(), 2);
    	list1.insert(list1.getHead(), 1);
    	list1.insert(list1.getHead(), 0);
    	
    	list_operation2 ml= new list_operation2();
    	thread_list_delete t0= new thread_list_delete(list1);
    	thread_list_insert t1=new thread_list_insert(list1);
    	list1.getHead().setStatus((t0.isAlive() && t1.isAlive()) );
    	t0.start();
    	t1.start();
    	Thread.sleep(2000);
    	System.out.println("处理后链表size为："+list1.getSize()+"原始size为6，插入删除各1次");
    	System.out.println("处理后正遍历结果为");
    	for (Node n=list1.getHead().getNext();n!=list1.getTail();n=n.getNext())
    	{
    		System.out.println(n.getData());
    	}
    	System.out.println("处理后反遍历结果为");
    	for (Node n=list1.getTail().getPrev();n!=list1.getHead();n=n.getPrev())
    	{
    		System.out.println(n.getData());
    	}
    	
    	
    	
    }
	
	
}
