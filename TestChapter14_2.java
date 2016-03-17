import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程 二
 *
 */
public class TestChapter14_2 implements Chapter
{
	@Override
	public void test() 
	{
/*		//测试线程局部变量
		testThreadLocal();*/
		
/*		//测试 trylock和超时
		testTryLockandTimeout();*/
		
		
/*		//测试读写锁
		testReentrantReadWriteLock();*/
		
/*		//测试阻塞队列
		testBlokingQueue();*/
		
		//测试TransferQueue接口
		testTransferQueue();
		
	}

	private void testTransferQueue() 
	{
		/*
		 * 生成者消费者模型！
		 * LinkedTransferQueue实现了这个接口
		 */
		
		  TransferQueue<String> queue = new LinkedTransferQueue<String>();  
	        Thread producer = new Thread(new Producer(queue));  
	        producer.setDaemon(true); //设置为守护进程使得线程执行结束后程序自动结束运行  
	        producer.start();  
	        for (int i = 0; i < 10; i++) {  
	            Thread consumer = new Thread(new Consumer(queue));  
	            consumer.setDaemon(true);  
	            consumer.start();  
	            try {  
	                // 消费者进程休眠一秒钟，以便以便生产者获得CPU，从而生产产品  
	                Thread.sleep(5000);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }
		
	}

	//测试阻塞队列
	private void testBlokingQueue() 
	{
		/*
		 * BlokingQueue是个接口
		 * BlokingQueue的作用主要不是作为容器，而是作为线程同步的工具  常用于生产者消费者
		 * concurrent包提供了阻塞队列的实现：
		 * 		Linked、Array、Priority
		 * 阻塞方法：put take 记住这两个即可     方法比较多 用的时候看文档
		 * 
		 * put和take会抛出重中断异常 貌似corejava和疯狂java都没有说
		 * 
		 * 可以选择设置公平参数
		 * 
		 */
		BlockingQueue<Integer> b1 = new ArrayBlockingQueue<>(2);  //一定要指明容量啊
		try {
			b1.put(1);
			b1.put(2);
			//b1.put(3);
			b1.take();
			System.out.println(b1);
			
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		
		//LinkedBlockingQueue是双端队列！
		BlockingQueue<String> b2 = new LinkedBlockingQueue<>(2);  //一定要指明最大容量啊
		
		
	}

	//测试读写锁
	private void testReentrantReadWriteLock() 
	{
		/*
		 * 重要！
		 * 如果多个线程读取，而一个线程写入的话，用读写锁是非常方便的！
		 */
		
		ReadWriteLock rwl = new ReadWriteLock();
		
		//启动一个写线程
		new Thread(new WriteThread(rwl)).start();
		
		
		//启动3个读线程  这里保证了所有读线程读取的数值都是一样的
		for(int i = 0 ; i < 3; i++)
		{
			new Thread(new ReadThread(rwl)).start();
		}
		

		
	}

	//测试 trylock和超时
	private void testTryLockandTimeout() 
	{
		/*
		 * try lock返回true or false
		 * 
		 * tryLock试图获得锁，成功返回true,否则立即返回false.不会阻塞
		 * 
		 * 可以加超时参数mylock.tryLock(100, TimeUnit.MILESECONDS)
		 * 
		 * 带有超时的trylock可以中断的，会抛出InterruptedException异常	 这是一个非常有用的特性，因为运行程序打破死锁
		 */
		
		
	}

	//测试线程局部变量
	private void testThreadLocal() 
	{
		/*
		 * ThreadLocal，很多地方叫做线程本地变量，也有些地方叫做线程本地存储，其实意思差不多。
		 * 可能很多朋友都知道ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
		 * 
		 * 如连接数据库的例子 多个线程间如果共享connection的话，就需要同步connection，但是同步后会大大降低效率所以，要用线程局部变量
		 * 
		 * get\set\remove\initialValue   initialValue用来重写
		 * 如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
		 * 
		 * 最常见的ThreadLocal使用场景为 用来解决 数据库连接、Session管理等。
		 * 
		 * 同步机制是为了共享资源、线程通信，ThreadLocal是为了隔离资源的共享！
		 */
		
		
		String dateStamp = MyThreadLocal.dateFormat.get().format(new Date());
		System.out.println(dateStamp);
		
		int random = ThreadLocalRandom.current().nextInt(20);
		System.out.println(random);
	}
}


//测试线程局部变量
class MyThreadLocal
{
	
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
    
    //有了set就不需要initalValue
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    
    public long getLong() {
        return longLocal.get();
    }
     
    public String getString() {
        return stringLocal.get();
    }
    
	
	public static final ThreadLocal<SimpleDateFormat> dateFormat =
			new ThreadLocal<SimpleDateFormat> ()
			{
		@Override
		protected SimpleDateFormat initialValue()
		{
			return new SimpleDateFormat("yyyy-MM-dd");
		}
		};
}


//用于测试TryLock  
class BankForTryLock
{
	private Lock lockBank = new ReentrantLock();
	
	public BankForTryLock()
	{
	}
	
	
	//transfer同时只能一个线程进入
		public void transfer() throws InterruptedException //await抛出
		{
			//tryLock试图获得锁，成功返回true,否则立即返回false.不会阻塞
			if(lockBank.tryLock()) //if(lockBank.tryLock(100, TimeUnit.MILLISECONDS))
				//也可以调用lockBank.lockInterruptibly(); 是一个超时设为无限的trylock方法
			{
				try
				{
					
				}
				finally
				{
					//切记放在finally中
					lockBank.unlock();
				}
			}
			
		}
}

//测试读写锁
class ReadThread implements Runnable
{
	private ReadWriteLock rl;
	public ReadThread(ReadWriteLock readWriteLock)
	{
		this.rl = readWriteLock;
	}

	
	@Override
	public void run() 
	{
		while(true)
		{
			rl.getNum();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
//测试读写锁
class WriteThread implements Runnable
{
	private ReadWriteLock rl;
	public WriteThread(ReadWriteLock readWriteLock)
	{
		this.rl = readWriteLock;
	}

	
	@Override
	public void run() 
	{
		while(true)
		{
			rl.add();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
//测试读写锁
class ReadWriteLock
{
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//抽取读锁
	private Lock readlock = rwl.readLock();
	
	//抽取写锁
	private Lock writelock = rwl.writeLock();
	
	private int num = 0;
	
	public void add()
	{
		writelock.lock();
		// System.out.println(Thread.currentThread().getName() + " be ready to write  data!");
		 num += 3;
		 System.out.println(Thread.currentThread().getName() + "have write data :" + num); 
		writelock.unlock();
	}
	
	public void  getNum()
	{
		//如果不加锁，你会发现各个读线程读取的数据是不同的
		readlock.lock();
		System.out.println(Thread.currentThread().getName() + "have read data :" + num); 
		readlock.unlock();
	}

}

//生产者
class Producer implements Runnable {  
    private final TransferQueue<String> queue;  
  
    public Producer(TransferQueue<String> queue) {  
        this.queue = queue;  
    }  
  
    private String produce() {  
        return " your lucky number " + (new Random().nextInt(100));  
    }  
  
    @Override  
    public void run() {  
        try {  
            while (true) {  
                if (queue.hasWaitingConsumer()) {  
                    queue.transfer(produce());  
                }  
                TimeUnit.SECONDS.sleep(1);//生产者睡眠一秒钟,这样可以看出程序的执行过程  
            }  
        } catch (InterruptedException e) {  
        }  
    }  
}  
	
//消费者
class Consumer implements Runnable {  
    private final TransferQueue<String> queue;  
  
    public Consumer(TransferQueue<String> queue) {  
        this.queue = queue;  
    }  
  
    @Override  
    public void run() {  
        try {  
            System.out.println(" Consumer " + Thread.currentThread().getName()  
                    + queue.take());  
        } catch (InterruptedException e) {  
        }  
    }  
}  