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
 * ���߳� ��
 *
 */
public class TestChapter14_2 implements Chapter
{
	@Override
	public void test() 
	{
/*		//�����ֲ߳̾�����
		testThreadLocal();*/
		
/*		//���� trylock�ͳ�ʱ
		testTryLockandTimeout();*/
		
		
/*		//���Զ�д��
		testReentrantReadWriteLock();*/
		
/*		//������������
		testBlokingQueue();*/
		
		//����TransferQueue�ӿ�
		testTransferQueue();
		
	}

	private void testTransferQueue() 
	{
		/*
		 * ������������ģ�ͣ�
		 * LinkedTransferQueueʵ��������ӿ�
		 */
		
		  TransferQueue<String> queue = new LinkedTransferQueue<String>();  
	        Thread producer = new Thread(new Producer(queue));  
	        producer.setDaemon(true); //����Ϊ�ػ�����ʹ���߳�ִ�н���������Զ���������  
	        producer.start();  
	        for (int i = 0; i < 10; i++) {  
	            Thread consumer = new Thread(new Consumer(queue));  
	            consumer.setDaemon(true);  
	            consumer.start();  
	            try {  
	                // �����߽�������һ���ӣ��Ա��Ա������߻��CPU���Ӷ�������Ʒ  
	                Thread.sleep(5000);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }
		
	}

	//������������
	private void testBlokingQueue() 
	{
		/*
		 * BlokingQueue�Ǹ��ӿ�
		 * BlokingQueue��������Ҫ������Ϊ������������Ϊ�߳�ͬ���Ĺ���  ������������������
		 * concurrent���ṩ���������е�ʵ�֣�
		 * 		Linked��Array��Priority
		 * ����������put take ��ס����������     �����Ƚ϶� �õ�ʱ���ĵ�
		 * 
		 * put��take���׳����ж��쳣 ò��corejava�ͷ��java��û��˵
		 * 
		 * ����ѡ�����ù�ƽ����
		 * 
		 */
		BlockingQueue<Integer> b1 = new ArrayBlockingQueue<>(2);  //һ��Ҫָ��������
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
		
		
		//LinkedBlockingQueue��˫�˶��У�
		BlockingQueue<String> b2 = new LinkedBlockingQueue<>(2);  //һ��Ҫָ�����������
		
		
	}

	//���Զ�д��
	private void testReentrantReadWriteLock() 
	{
		/*
		 * ��Ҫ��
		 * �������̶߳�ȡ����һ���߳�д��Ļ����ö�д���Ƿǳ�����ģ�
		 */
		
		ReadWriteLock rwl = new ReadWriteLock();
		
		//����һ��д�߳�
		new Thread(new WriteThread(rwl)).start();
		
		
		//����3�����߳�  ���ﱣ֤�����ж��̶߳�ȡ����ֵ����һ����
		for(int i = 0 ; i < 3; i++)
		{
			new Thread(new ReadThread(rwl)).start();
		}
		

		
	}

	//���� trylock�ͳ�ʱ
	private void testTryLockandTimeout() 
	{
		/*
		 * try lock����true or false
		 * 
		 * tryLock��ͼ��������ɹ�����true,������������false.��������
		 * 
		 * ���Լӳ�ʱ����mylock.tryLock(100, TimeUnit.MILESECONDS)
		 * 
		 * ���г�ʱ��trylock�����жϵģ����׳�InterruptedException�쳣	 ����һ���ǳ����õ����ԣ���Ϊ���г����������
		 */
		
		
	}

	//�����ֲ߳̾�����
	private void testThreadLocal() 
	{
		/*
		 * ThreadLocal���ܶ�ط������̱߳��ر�����Ҳ��Щ�ط������̱߳��ش洢����ʵ��˼��ࡣ
		 * ���ܺܶ����Ѷ�֪��ThreadLocalΪ������ÿ���߳��ж�������һ����������ôÿ���߳̿��Է����Լ��ڲ��ĸ���������
		 * 
		 * ���������ݿ������ ����̼߳��������connection�Ļ�������Ҫͬ��connection������ͬ������󽵵�Ч�����ԣ�Ҫ���ֲ߳̾�����
		 * 
		 * get\set\remove\initialValue   initialValue������д
		 * �������get֮ǰ����Ҫ����set�����������ʵĻ���������дinitialValue()������
		 * 
		 * �����ThreadLocalʹ�ó���Ϊ ������� ���ݿ����ӡ�Session����ȡ�
		 * 
		 * ͬ��������Ϊ�˹�����Դ���߳�ͨ�ţ�ThreadLocal��Ϊ�˸�����Դ�Ĺ���
		 */
		
		
		String dateStamp = MyThreadLocal.dateFormat.get().format(new Date());
		System.out.println(dateStamp);
		
		int random = ThreadLocalRandom.current().nextInt(20);
		System.out.println(random);
	}
}


//�����ֲ߳̾�����
class MyThreadLocal
{
	
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
    
    //����set�Ͳ���ҪinitalValue
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


//���ڲ���TryLock  
class BankForTryLock
{
	private Lock lockBank = new ReentrantLock();
	
	public BankForTryLock()
	{
	}
	
	
	//transferͬʱֻ��һ���߳̽���
		public void transfer() throws InterruptedException //await�׳�
		{
			//tryLock��ͼ��������ɹ�����true,������������false.��������
			if(lockBank.tryLock()) //if(lockBank.tryLock(100, TimeUnit.MILLISECONDS))
				//Ҳ���Ե���lockBank.lockInterruptibly(); ��һ����ʱ��Ϊ���޵�trylock����
			{
				try
				{
					
				}
				finally
				{
					//�мǷ���finally��
					lockBank.unlock();
				}
			}
			
		}
}

//���Զ�д��
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
//���Զ�д��
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
//���Զ�д��
class ReadWriteLock
{
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//��ȡ����
	private Lock readlock = rwl.readLock();
	
	//��ȡд��
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
		//�������������ᷢ�ָ������̶߳�ȡ�������ǲ�ͬ��
		readlock.lock();
		System.out.println(Thread.currentThread().getName() + "have read data :" + num); 
		readlock.unlock();
	}

}

//������
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
                TimeUnit.SECONDS.sleep(1);//������˯��һ����,�������Կ��������ִ�й���  
            }  
        } catch (InterruptedException e) {  
        }  
    }  
}  
	
//������
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