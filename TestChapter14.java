import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���߳�
 *
 */
public class TestChapter14 implements Chapter
{
	@Override
	public void test() 
	{
/*		//����interruptted
		testInterrupted();*/
		
/*		//�����߳�״̬
		testThreadState();*/
		
/*		//�����߳����ȼ����ػ�����
		testThreadPriority();*/
		
/*		//����ReentrantLock ��������  Ҳ���������Ե���
		testReentrantLock();*/
		
/*		//������������(Ҳ����������)
		testCondition();*/
		
/*		//����Synchronized����
		testSynchronizedFunction();*/
		
/*		//����Synchronized��
		testSynchronizedBlock();*/
		
/*		//����volatile
		testVolatile();*/
		
		//����ԭ����
		testAtomic();
	}

	
	private void testAtomic() 
	{
		/*
		 * ԭ�ӻ�������������
		 * 		AtomicBoolean��ԭ�Ӹ��²������͡�AtomicInteger��ԭ�Ӹ������͡�AtomicLong��ԭ�Ӹ��³����͡�
		 * ԭ��������������
		 * 		AtomicIntegerArray��ԭ�Ӹ��������������Ԫ�ء�AtomicLongArray��ԭ�Ӹ��³������������Ԫ�ء�AtomicReferenceArray��ԭ�Ӹ������������������Ԫ�ء�
		 * ԭ�Ӹ����������������ࣺ
		 * 		AtomicReference��ԭ�Ӹ����������͡�
		 * ԭ�Ӹ����ֶ��������ࣺ
		 */
		AtomicInteger a = new AtomicInteger(4);
		int sum  = a.addAndGet(4);
		System.out.println(sum);
		int ret = a.getAndIncrement(); 
		System.out.println(ret + ":" + a); //AtomicInteger��д��toString
		ret = a.getAndSet(98);
		System.out.println(ret + ":" + a);
		
		ret = a.getAndDecrement();
		System.out.println(ret + ":" + a);
		
		int[] value = {1,23,4};
		AtomicIntegerArray array = new AtomicIntegerArray(value);
		
		ret = array.addAndGet(2, 8);
		System.out.println(ret);
		
		
		
		
	}

	volatile boolean isRunning; //volatileֻ�������γ�Ա�������мǣ�֮ǰ��֪��
	private void testVolatile() 
	{
		/*
		 * һ��Ҫע��volatile�ĸ��� �������̰߳�ȫ�� ������ԭ����
		 * volatileֻ����������ʹ�õ�ˢ��ֵ
		 * һ���̵߳�һ��������������һ���̲߳�ѯ�����ã����������Ϊvolatile����Ϊ����ⲿ�ı��˸ñ�����ԭ���иñ������̻߳����ϸ�֪��
		 * ����Ļ�Ҳ����Brian Goetz��ͬ�����ԣ�
		 * ���һ����һ������д��ֵ��������������������ܻᱻ��һ���̶߳�ȡ�����ߣ���һ��������ȡֵ���������������֮ǰ����һ���߳�д�룬��ʱ����ʹ��ͬ��
		 * 
		 */
		
		//volatile boolean isRunning;  //�����д�� Ϊʲô�� ��Ϊ�����߳�ֱ�ӵ��£���Ȼ�����Ǿֲ�������
	
		//��Ȼ��final��Ҳ���̰߳�ȫ��
		
		A14 a14 = new A14();
		new Thread(a14).start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		a14.isRun = false; //��������Ч
		
	}

	private void testSynchronizedFunction() 
	{
		/*
		 * �����������ò���lock��condition ��Synchronized���㹻��
		 * Synchronizedʹ�õ����ڲ��� 
		 * javaÿ��������һ�����ص��ڲ�������һ��������Synchronized���Σ�������Ϳ��Ա�����������
		 * ���⣬ÿ���ڲ�����ʵ��һ�����ص�������condition���Ӧ. wait������ʵ�͵ȼ���await  notify�ȼ���signal ������
		 * �����Ϊʲôʹ��wait��������Ҫ����Synchronized���η�����ԭ����Ҫ
		 * 
		 * ���⣬Ϊʲô��Ҫ�ֿ������֣���Ϊwait��notify/all��object��final������Condition��await sinall�Ͳ����ͻ
		 * 
		 */
		
		/*
		 * Synchronized���������ξ�̬�����������ͱ�ʾ������������Bank.class���������������������Ͳ��ܵ���ͬһ�����ࡱ������ͬ���ľ�̬������
		 */
		
		/*
		 * ������Ҫʹ��������ʹ��concurrent���ƣ������Ҫ��������Synchronized��
		 */	
		
		/*
		 * ����Synchronizedͬ������������Synchronizedͬ���� �������
		 */
	}
	
	
	private void testSynchronizedBlock()
	{
		/*
		 * ����ʲô��ͬ����������obj����ͬ�����������߳�ִ��ͬ�������֮ǰ�������ȡ���á�ͬ��������������
		 */
		
		Object obj = new Object();
		
		//�������һ��������  ������һ������������  �������˻�account
		synchronized(obj) //objͬ��������
		{
			
		}
		
	}
	
	

	private void testCondition() 
	{
		//ǰ�����Ѿ��������
		/*Ϊʲô��Ҫ��������
		��ʱ����ֻ��������Ժ���ʵȴûɶ�ô�����Ϊ����Ҫͨ����������������߳����У���ʱ����Ҫ����*/
		
		//��Ҫ��������ת�˵������У�����Ϊ��ͬ����������������Ϊ�˷�ֹû��Ǯ��û��Ǯ��Ͳ��ܴ���ȥ
		
	}

	//����ReentrantLock
	private void testReentrantLock() 
	{
		
		Bank1 b = new Bank1();
	}

	//�����߳����ȼ����ػ�����
	private void testThreadPriority() 
	{
		//��ʹ��
		
		/*static void yield() �������µ�ǰ�̴߳����ò�״̬*/
		Thread.yield();
		
		//�ػ��߳�
		//�ػ��̵߳�Ψһ���þ���Ϊ�����߳��ṩ�������ʱ�߳�
		//��ֻʣ���ػ��߳�ʱ����������˳���,��Ϊû��Ҫ�������г�����
		Thread t = new Thread(new MyThread());
		t.setDaemon(true); //�������߳�����ǰ����
		t.start();	
	}


	//�����߳�״̬
	private void testThreadState() 
	{
		/*
		 * 6��״̬ �´����������У���Ϊ���ȣ����Կ�������Ҳ����û�����С�������(��)�����ȴ���֪ͨ������ʱ�ȴ�������ֹ��
		 */
	}
	
	



	//����interrupt��isinterrupted
	private void testInterrupted() 
	{
		//ע��runnabel��������������Ϊthread��goal����ȥ
		Thread t = new Thread(new MyThread());
		t.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		t.interrupt();//������ֹ    ���ڷ��������߳����������ʹ�жϱ�־��Ϊtrue��
				      //���������Ľ��̣��������������߳��׳�InterruptedException�쳣������ʹ�жϱ�־��Ϊfalse��
		//interrupted�Ǹ���̬����
	}
}

//���ڲ���interrupt
class MyThread implements Runnable
{

	@Override
	public void run() 
	{
		//isInterrupted���������������ص�ǰ���߳��жϱ�־
		while(!Thread.currentThread().isInterrupted())
		{
			try 
			{ 
				/*��sleep���ж�ʱ���׳�InterruptedException�쳣������жϱ�ʾΪfalse,ԭ�����£�
				 *   ���ڴ���sleep��join�Ȳ������̣߳����������interrupt()�󣬻��׳�InterruptedException��Ȼ���̵߳��жϱ�־λ����true����Ϊfalse��
				 *   ��Ϊ�߳�Ϊ�˴����쳣�Ѿ����´��ھ���״̬�������������ܴ����쳣������
				 * 
				 * Ȼ��catch����Ȼ�����interrupt()��interrupt����������
				 * Ȼ���´�whileѭ���ͻ��⵽
				 * */
				 Thread.sleep(100000);   
			} 
			 catch (InterruptedException e) 
			 { 
				 System.out.println(Thread.currentThread().isInterrupted()?"true":"fasle"); //��interrupt������Ϊfalse
				 Thread.currentThread().interrupt();//�ٴ��ֶ������жϱ�ʾΪtrue   
		     }   
		}
		System.out.println(Thread.currentThread().isInterrupted()?"true":"fasle");
		System.out.println("whileѭ����⵽���жϱ�־Ϊtrue,����ѭ������");
	}
	
	
}

class MyThread2 implements Runnable
{

	@Override
	public void run() /*throws IOException //run���治����checked�쳣*/{
	}
}


//���ڲ���ReentrantLock��
class Bank1
{
	//ÿһ��Bank������һ��������ReentrantLock����
	private Lock lockBank = new ReentrantLock(); //��������
	
	//transferͬʱֻ��һ���߳̽���
	public void transfer()
	{
		try
		{
			//��Ҫ�� һ��һ���̷߳�ס��lockBank�����������̶߳��޷�ͨ��lock��䣬�ᱻ����
			lockBank.lock();
		}
		finally
		{
			//�мǷ���finally��
			lockBank.unlock();
		}
	}
}

//���ڲ�����������  
class Bank2
{
	private Condition cond;
	private Lock lockBank = new ReentrantLock();
	
	public Bank2()
	{
		//ͨ�������
		cond = lockBank.newCondition();
	}
	
	
	//transferͬʱֻ��һ���߳̽���
		public void transfer() throws InterruptedException //await�׳�
		{
			lockBank.lock();
			try
			{
				
				if(true/*xx����������*/) //xx�϶����ⲿ���Ƶ� ��������ֱ���Թ�If��cond.signalAll();
				{
					cond.await();//������**����������һ�£��ȴ�**������������sinalAll��������
				}
				
				//sinalAll������������ĳ���ȴ��̣߳��������������
				//signal�����ӵȴ��������ѡ��һ���߳̽�������������Ƽ�ʹ��signal��������������deadlock
				cond.signalAll(); //��������������ѡ�	
			}
			finally
			{
				//�мǷ���finally��
				lockBank.unlock();
			}
		}
}

//���ڲ���Synchronized
class Bank3
{
	synchronized void  test() throws InterruptedException
	{
		wait();//wait��ȻҪ�׳�InterruptedException�쳣	 
	}
	
}

//���ڲ���volatile
class A14 implements Runnable
{
	 public  volatile boolean isRun = true;
	

	@Override
	public void run() 
	{
		while(isRun)
		{
			System.out.println("running...");
		/*	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
}