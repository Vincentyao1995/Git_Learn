package iss.java;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import iss.java.list.*;


/**
 * Created by wenke on 2016/9/16.
 */
class list_operation{
	 public static Lock lock = new ReentrantLock();
	private MyList list=null;
	//ReadWriteLock my_lock= new ReentrantReadWriteLock(); 
	public void plus(MyList list)
	{
		this.list=list;
		lock.lock();
		try {
			for(Node n = this.list.getHead().getNext();n!=this.list.getTail();n=n.getNext())
			{
				//System.out.println(Thread.currentThread().getName()+"准备读入数据！");
				System.out.println(Thread.currentThread().getName()+"读出数据："+n.getData());
				n.setData(n.getData()+20);
				System.out.println(Thread.currentThread().getName()+"plus后数据："+n.getData());
			}
        } finally {
            lock.unlock();
        }
	}

	public void decrease(MyList list)
	{
		lock.lock();
		try{
			for(Node n = list.getHead().getNext();n!=list.getTail();n=n.getNext())
			{
				//System.out.println(Thread.currentThread().getName()+"准备读入数据！");
				System.out.println(Thread.currentThread().getName()+"读出数据："+n.getData());
				n.setData(n.getData()-10);
				System.out.println(Thread.currentThread().getName()+"decrease后数据："+n.getData());
			}
		}finally{
			lock.unlock();
		}
		
	}
}
//thread 1, add 20 for each data.
class thread_list_plus extends Thread{
	private MyList list = null;
	private list_operation list_operation=new list_operation();
	public thread_list_plus(MyList list){
		this.list=list;
	}
	public void run(){
		list_operation.plus(list);
	}
}
// thread 2, decrease 10 for each data. result should be :  10,11,12,13,14,15
class thread_list_decrease extends Thread{
	private MyList list=null;
	private list_operation list_operation=new list_operation();
	public thread_list_decrease(MyList list){
		
		this.list=list;
	}
	public void run(){
		list_operation.decrease(list);

	}
}


public class Main_A {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement a multithreaded test case against requirement A.
    	MyList list1= new MyList();
    	list1.insert(list1.getHead(), 5);
    	list1.insert(list1.getHead(), 4);
    	list1.insert(list1.getHead(), 3);
    	list1.insert(list1.getHead(), 2);
    	list1.insert(list1.getHead(), 1);
    	list1.insert(list1.getHead(), 0);
    	
    	list_operation ml= new list_operation();
    	thread_list_decrease t1= new thread_list_decrease(list1);
    	thread_list_plus t2=new thread_list_plus(list1);
    	t1.start();
    	t2.start();
    	Thread.sleep(2000);
    	System.out.println("处理后数据为：");
    	for (Node n=list1.getHead().getNext();n!=list1.getTail();n=n.getNext())
    	{
    		System.out.println(n.getData());
    	}
/*    	Thread t1_plus=new Thread(){
    		@Override
    		public void run()
    		{
    			for(Node i= list1.getHead().getNext();i!=list1.getTail();i=i.getNext())
    			{
    				System.out.println(Thread.currentThread().getName()+"准备读入数据！");
	    			System.out.println(Thread.currentThread().getName()+"读入数据:"+i.getData());
	    			i.getData()=i.getData()+1;
    			}
    		}*/
    	
    	
    	
    	
    }
}

