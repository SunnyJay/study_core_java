import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/*线程安全的集合
 * 注意基本概念：callable\runnable是共享变量的   而thread不共享
 */

public class TestChapter14_3 implements Chapter
{

	@Override
	public void test() 
	{
	
/*		//测试Concurrent集合 重要!
		testConcurrent();*/
		
/*		//测试CopyWriteArrayList
		testCopyWrite();*/
		
/*		//测试Collections同步包装器  不建议使用
		testCollectionsWrapper();*/
		
/*		//测试callable 与 future
		testCallableAndFutuer();*/
		
		//测试执行器 Executor 和 线程池
		testExecutor();
		
	}

	//测试执行器 Executor 和 线程池  重要！
	private void testExecutor() 
	{
		/*
		 * 使用线程池的两个理由：
		 * 		创建大量生命周期很短的线程、限制并发线程数目
		 * 注意任务的概念：
		 *      fixed中，把任务分配给线程池，若任务数多余空闲数，任务会在队列中排队。（想象那个图）
		 * 		single中，只有一个线程，并且任务是一个一个提交给线程的。
		 * 
		 * 执行器 Executors有很多静态工厂方,他们都构造返回一个ThreadPoolExecutor类，它实现了ExecutorService接口 可以用多态
		 *   newCachedThreadPool 没有可用的空闲线程可用时立即创建一个线程 线程空闲60秒终止
		 *   newFixedThreadPool 固定大小  空闲线程会一直保留
		 *   newSingleThreadPool
		 *   newScheduledThreadPool
		 *   newSingleScheduledThreadPool
		 *   
		 * submit(task) shutdown
		 * 重点注意shutdown: 
		 *    用完一个线程池时，调用shutdown。该方法启动该池的关闭序列。
		 *    被关闭的执行器不再接受新的任务。
		 *    所有任务完成后，线程池中的线程死亡。
		 *    还可以调用shutdownNow,可以取消尚未开始的所有任务并试图中断正在运行的线程
		 */
		
		ExecutorService te1 = Executors.newCachedThreadPool(); //系统会根据需要创建线程
		
		ExecutorService te2 = Executors.newFixedThreadPool(20); //若提交的任务多余空闲线程数，则把得不到服务的任务放进队列
		
		ExecutorService te3 = Executors.newSingleThreadExecutor(); //退化为1的newFixedThreadPool
		
		ExecutorService te4= Executors.newCachedThreadPool(); //
		
		try {
		int ret = ((ThreadPoolExecutor) te2).getLargestPoolSize();
		System.out.println(ret);
		Callable<Integer> call = new Call();
		
		//生成三个future1代表三个线程任务，三个future1共享call的num，num可以一次累加 
		FutureTask<Integer> future1 = new FutureTask<Integer>(call);
		FutureTask<Integer> future2 = new FutureTask<Integer>(call);
		FutureTask<Integer> future3= new FutureTask<Integer>(call);
		
		te2.submit(future1);//任务 task就是一个runnable线程
		System.out.println(future1.get()); //45
		
		te2.submit(future2);
		System.out.println(future2.get()); // 90
		te2.submit(future3);
		System.out.println(future3.get()); //135
		
		te2.submit(future1); //future1只管自己的 所以仍然是45
		System.out.println(future1.get()); //45
		
		te2.shutdown();//关闭接收 然后完成已提交的任务
		
/*		//关闭后提交不顶用   不再接受 会抛出异常
		FutureTask<Integer> future4= new FutureTask<Integer>(call);
		te2.submit(future4);
		System.out.println(future4.get());*/
		
		//te1.shutdownNow();//取消尚未开始的所有任务并试图中断正在运行的线程
	}
		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private void testCallableAndFutuer() 
	{
		/*
		 * 重要！  Callable是泛型 你需要指定类型  重写call方法
		 * Callable<V>    FutureTask<V>
		 * Callable提供call方法并生成返回值，FutureTask用于获得该返回值
		 * 
		 * FutureTask的参数是Callable
		 * 
		 * FutureTask就相当于一个代理，你通过它去获得Callable的结果.当然FutureTask是个task，要用thread包装
		 * FutureTask有get cancel等方法 
		 */
		
		Callable<Integer> call = new Call();
		FutureTask<Integer> future = new FutureTask<Integer>(call);
		new Thread(future).start();
		
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}

	//测试Collections同步包装器
	private void testCollectionsWrapper() 
	{
		/*
		 * 同步的集合包装器 synchronizedMap 和 synchronizedList
		 * 
		 * 如果你要迭代 你仍然需要使用Synchronized修饰
		 */
		
		//其实就是代理对象
		List<String> synchArrayList = Collections.synchronizedList(new ArrayList<String>());
		
		//仍然需要锁定
		synchronized (synchArrayList)
		{
			Iterator iter = synchArrayList.iterator();
		}
		
	}

	//测试CopyWrite容器  COW
	private void testCopyWrite() 
	{
		/*
		 * CopyOnWriteArrayList
		 * CopyOnWriteArraySet
		 * 
		 * 1.通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，
		 * 		复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
		 * 2.读写分离 读操作不加锁 
		 * 
		 * 3.核心思想是：CopyOnWriteArrayList的核心思想是利用高并发往往是读多写少的特性，对读操作不加锁，对写操作，先复制一份新的集合，在新的集合上面修改，
		 * 然后将新集合赋值给旧的引用，并通过volatile 保证其可见性，当然写操作的锁是必不可少的了。
		 * 
		 * 4.COW缺点占用内存 而且不保证实时一致性，只保证最终一致性
		 */
		
	}

	//测试Concurrent集合
	private void testConcurrent() 
	{
		/*
		 * ConcurrentHashMap 映射表    
		 * ConcurrentSkipListSet 有序集
		 * ConcurrentLinkedQueue 队列
		 * ConcurrentSkipListMap 映射表
		 */
		
		/*
		 * ConcurrentHashMap 可高效支持大量的读者和写者 默认情况下支持16个同时写，如果多了就阻塞。如果需要同时多余16，可以构造器指定。
		 * ConcurrentHashMap 有必要深入理解 锁分段技术
		 * put putIfAbsent remove replace
		 * ConcurrentHashMap的putIfAbsent用来做缓冲相当不错，多线程安全的
		 * 
		 * 重要！：ConcurrentMap能够保证每一次调用（例如一次putIfAbsent）都是原子操作，不受多线程影响，但并不保证多次调用之间也是原子操作。
		 * 
		 * 注意区别：ConcurrentMap是原子操作，但是你的调用不是原子操作
		 */
		
		//ConcurrentHashMap()、ConcurrentHashMap(初始容量)、ConcurrentHashMap(初始容量、负载因子、线程估计数)
		ConcurrentHashMap<Integer,String> ch = new ConcurrentHashMap<>(); //默认初始
		ch.put(1,"1"); //一次操作是线程安全的 原子性
		ch.put(1,"4"); //更新
		ch.putIfAbsent(1,"5"); //不会更新 因为1本身存在
		
		String ret = ch.get(1);
		System.out.println(ret); //输出4
		
		ret = ch.putIfAbsent(2,"5");
		System.out.println(ret); //返回先前关联的值 如果一开始不存在 返回null
		
		ch.remove(1);
		ret = ch.get(1);
		System.out.println(ret); //1被remove了
		
		ch.replace(2, "5", "6");
		System.out.println(ch);
		
		/*
		 * 下面是ConcurrentLinkedQueue 非阻塞队列
		 * add和offer是一样的  直接调用了offer
		 */
		
		ConcurrentLinkedQueue<Integer> cl = new ConcurrentLinkedQueue<>();
		cl.add(4); //返回boolean
		cl.add(2);
		cl.add(8);
		cl.offer(6);
		int retInt = cl.peek(); //只看对头
		System.out.println(retInt);
		System.out.println(cl);
		
		retInt= cl.poll(); //又看又取
		System.out.println(retInt);
		System.out.println(cl);
		
		
		/*
		 * 当然，ConcurrentSkipListMap 的key是有序的。
		 * 1.ConcurrentSkipListMap 支持更高的并发。ConcurrentSkipListMap 的存取时间是log（N），和线程数几乎无关。
		 * 		也就是说在数据量一定的情况下，并发的线程越多，ConcurrentSkipListMap越能体现出他的优势
		 * 2.在非多线程的情况下，应当尽量使用TreeMap。此外对于并发性相对较低的并行程序可以使用Collections.synchronizedSortedMap将TreeMap进行包装，也可以提供较好的效率。
		 *      对于高并发程序，应当使用ConcurrentSkipListMap，能够提供更高的并发度。
		 * 3.所以在多线程程序中，如果需要对Map的键值进行排序时，请尽量使用ConcurrentSkipListMap，可能得到更好的并发度。  
		 * 
		 *  方法和ConcurrentHashMap一样
		 */
		ConcurrentSkipListMap<Integer,String> cs = new ConcurrentSkipListMap<>();
		cs.put(6,"121"); //一次操作是线程安全的 原子性
		cs.put(6,"ddd"); //更新
		cs.putIfAbsent(6,"5"); //不会更新 因为1本身存在
		cs.put(3,"aa");
		cs.put(5,"fd");
		cs.put(5,"dfg");
		System.out.println(cs); //3 5 6 排序
		
		
		/*
		 * ConcurrentSkipListSet与他们类似 
		 * 有序
		 */
		ConcurrentSkipListSet<Integer> css = new ConcurrentSkipListSet<>();
		css.add(5);
		css.add(1);
		css.add(5); //没变化 返回false
		css.add(9);
		css.add(2);
		css.pollFirst();
		System.out.println(css);//2 5 9
		
	}

}

//测试Callable
class Call implements Callable<Integer>
{
	public  int sum = 0; //多个Future可以共享sum 与runnable类型
	@Override
	public Integer call() throws Exception 
	{
		for(int i = 0; i < 10 ; i++)
		{
			sum += i;
		}
		return sum;
	}
	
}


