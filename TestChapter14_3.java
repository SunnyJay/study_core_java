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

/*�̰߳�ȫ�ļ���
 * ע��������callable\runnable�ǹ��������   ��thread������
 */

public class TestChapter14_3 implements Chapter
{

	@Override
	public void test() 
	{
	
/*		//����Concurrent���� ��Ҫ!
		testConcurrent();*/
		
/*		//����CopyWriteArrayList
		testCopyWrite();*/
		
/*		//����Collectionsͬ����װ��  ������ʹ��
		testCollectionsWrapper();*/
		
/*		//����callable �� future
		testCallableAndFutuer();*/
		
		//����ִ���� Executor �� �̳߳�
		testExecutor();
		
	}

	//����ִ���� Executor �� �̳߳�  ��Ҫ��
	private void testExecutor() 
	{
		/*
		 * ʹ���̳߳ص��������ɣ�
		 * 		���������������ں̵ܶ��̡߳����Ʋ����߳���Ŀ
		 * ע������ĸ��
		 *      fixed�У������������̳߳أ��������������������������ڶ������Ŷӡ��������Ǹ�ͼ��
		 * 		single�У�ֻ��һ���̣߳�����������һ��һ���ύ���̵߳ġ�
		 * 
		 * ִ���� Executors�кܶྲ̬������,���Ƕ����췵��һ��ThreadPoolExecutor�࣬��ʵ����ExecutorService�ӿ� �����ö�̬
		 *   newCachedThreadPool û�п��õĿ����߳̿���ʱ��������һ���߳� �߳̿���60����ֹ
		 *   newFixedThreadPool �̶���С  �����̻߳�һֱ����
		 *   newSingleThreadPool
		 *   newScheduledThreadPool
		 *   newSingleScheduledThreadPool
		 *   
		 * submit(task) shutdown
		 * �ص�ע��shutdown: 
		 *    ����һ���̳߳�ʱ������shutdown���÷��������óصĹر����С�
		 *    ���رյ�ִ�������ٽ����µ�����
		 *    ����������ɺ��̳߳��е��߳�������
		 *    �����Ե���shutdownNow,����ȡ����δ��ʼ������������ͼ�ж��������е��߳�
		 */
		
		ExecutorService te1 = Executors.newCachedThreadPool(); //ϵͳ�������Ҫ�����߳�
		
		ExecutorService te2 = Executors.newFixedThreadPool(20); //���ύ�������������߳�������ѵò������������Ž�����
		
		ExecutorService te3 = Executors.newSingleThreadExecutor(); //�˻�Ϊ1��newFixedThreadPool
		
		ExecutorService te4= Executors.newCachedThreadPool(); //
		
		try {
		int ret = ((ThreadPoolExecutor) te2).getLargestPoolSize();
		System.out.println(ret);
		Callable<Integer> call = new Call();
		
		//��������future1���������߳���������future1����call��num��num����һ���ۼ� 
		FutureTask<Integer> future1 = new FutureTask<Integer>(call);
		FutureTask<Integer> future2 = new FutureTask<Integer>(call);
		FutureTask<Integer> future3= new FutureTask<Integer>(call);
		
		te2.submit(future1);//���� task����һ��runnable�߳�
		System.out.println(future1.get()); //45
		
		te2.submit(future2);
		System.out.println(future2.get()); // 90
		te2.submit(future3);
		System.out.println(future3.get()); //135
		
		te2.submit(future1); //future1ֻ���Լ��� ������Ȼ��45
		System.out.println(future1.get()); //45
		
		te2.shutdown();//�رս��� Ȼ��������ύ������
		
/*		//�رպ��ύ������   ���ٽ��� ���׳��쳣
		FutureTask<Integer> future4= new FutureTask<Integer>(call);
		te2.submit(future4);
		System.out.println(future4.get());*/
		
		//te1.shutdownNow();//ȡ����δ��ʼ������������ͼ�ж��������е��߳�
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
		 * ��Ҫ��  Callable�Ƿ��� ����Ҫָ������  ��дcall����
		 * Callable<V>    FutureTask<V>
		 * Callable�ṩcall���������ɷ���ֵ��FutureTask���ڻ�ø÷���ֵ
		 * 
		 * FutureTask�Ĳ�����Callable
		 * 
		 * FutureTask���൱��һ��������ͨ����ȥ���Callable�Ľ��.��ȻFutureTask�Ǹ�task��Ҫ��thread��װ
		 * FutureTask��get cancel�ȷ��� 
		 */
		
		Callable<Integer> call = new Call();
		FutureTask<Integer> future = new FutureTask<Integer>(call);
		new Thread(future).start();
		
        try {
            Thread.sleep(5000);// ������һЩ����
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}

	//����Collectionsͬ����װ��
	private void testCollectionsWrapper() 
	{
		/*
		 * ͬ���ļ��ϰ�װ�� synchronizedMap �� synchronizedList
		 * 
		 * �����Ҫ���� ����Ȼ��Ҫʹ��Synchronized����
		 */
		
		//��ʵ���Ǵ������
		List<String> synchArrayList = Collections.synchronizedList(new ArrayList<String>());
		
		//��Ȼ��Ҫ����
		synchronized (synchArrayList)
		{
			Iterator iter = synchArrayList.iterator();
		}
		
	}

	//����CopyWrite����  COW
	private void testCopyWrite() 
	{
		/*
		 * CopyOnWriteArrayList
		 * CopyOnWriteArraySet
		 * 
		 * 1.ͨ�׵�����ǵ�������һ���������Ԫ�ص�ʱ�򣬲�ֱ������ǰ������ӣ������Ƚ���ǰ��������Copy��
		 * 		���Ƴ�һ���µ�������Ȼ���µ����������Ԫ�أ������Ԫ��֮���ٽ�ԭ����������ָ���µ�������
		 * 2.��д���� ������������ 
		 * 
		 * 3.����˼���ǣ�CopyOnWriteArrayList�ĺ���˼�������ø߲��������Ƕ���д�ٵ����ԣ��Զ���������������д�������ȸ���һ���µļ��ϣ����µļ��������޸ģ�
		 * Ȼ���¼��ϸ�ֵ���ɵ����ã���ͨ��volatile ��֤��ɼ��ԣ���Ȼд���������Ǳز����ٵ��ˡ�
		 * 
		 * 4.COWȱ��ռ���ڴ� ���Ҳ���֤ʵʱһ���ԣ�ֻ��֤����һ����
		 */
		
	}

	//����Concurrent����
	private void testConcurrent() 
	{
		/*
		 * ConcurrentHashMap ӳ���    
		 * ConcurrentSkipListSet ����
		 * ConcurrentLinkedQueue ����
		 * ConcurrentSkipListMap ӳ���
		 */
		
		/*
		 * ConcurrentHashMap �ɸ�Ч֧�ִ����Ķ��ߺ�д�� Ĭ�������֧��16��ͬʱд��������˾������������Ҫͬʱ����16�����Թ�����ָ����
		 * ConcurrentHashMap �б�Ҫ������� ���ֶμ���
		 * put putIfAbsent remove replace
		 * ConcurrentHashMap��putIfAbsent�����������൱�������̰߳�ȫ��
		 * 
		 * ��Ҫ����ConcurrentMap�ܹ���֤ÿһ�ε��ã�����һ��putIfAbsent������ԭ�Ӳ��������ܶ��߳�Ӱ�죬��������֤��ε���֮��Ҳ��ԭ�Ӳ�����
		 * 
		 * ע������ConcurrentMap��ԭ�Ӳ�����������ĵ��ò���ԭ�Ӳ���
		 */
		
		//ConcurrentHashMap()��ConcurrentHashMap(��ʼ����)��ConcurrentHashMap(��ʼ�������������ӡ��̹߳�����)
		ConcurrentHashMap<Integer,String> ch = new ConcurrentHashMap<>(); //Ĭ�ϳ�ʼ
		ch.put(1,"1"); //һ�β������̰߳�ȫ�� ԭ����
		ch.put(1,"4"); //����
		ch.putIfAbsent(1,"5"); //������� ��Ϊ1�������
		
		String ret = ch.get(1);
		System.out.println(ret); //���4
		
		ret = ch.putIfAbsent(2,"5");
		System.out.println(ret); //������ǰ������ֵ ���һ��ʼ������ ����null
		
		ch.remove(1);
		ret = ch.get(1);
		System.out.println(ret); //1��remove��
		
		ch.replace(2, "5", "6");
		System.out.println(ch);
		
		/*
		 * ������ConcurrentLinkedQueue ����������
		 * add��offer��һ����  ֱ�ӵ�����offer
		 */
		
		ConcurrentLinkedQueue<Integer> cl = new ConcurrentLinkedQueue<>();
		cl.add(4); //����boolean
		cl.add(2);
		cl.add(8);
		cl.offer(6);
		int retInt = cl.peek(); //ֻ����ͷ
		System.out.println(retInt);
		System.out.println(cl);
		
		retInt= cl.poll(); //�ֿ���ȡ
		System.out.println(retInt);
		System.out.println(cl);
		
		
		/*
		 * ��Ȼ��ConcurrentSkipListMap ��key������ġ�
		 * 1.ConcurrentSkipListMap ֧�ָ��ߵĲ�����ConcurrentSkipListMap �Ĵ�ȡʱ����log��N�������߳��������޹ء�
		 * 		Ҳ����˵��������һ��������£��������߳�Խ�࣬ConcurrentSkipListMapԽ�����ֳ���������
		 * 2.�ڷǶ��̵߳�����£�Ӧ������ʹ��TreeMap��������ڲ�������Խϵ͵Ĳ��г������ʹ��Collections.synchronizedSortedMap��TreeMap���а�װ��Ҳ�����ṩ�Ϻõ�Ч�ʡ�
		 *      ���ڸ߲�������Ӧ��ʹ��ConcurrentSkipListMap���ܹ��ṩ���ߵĲ����ȡ�
		 * 3.�����ڶ��̳߳����У������Ҫ��Map�ļ�ֵ��������ʱ���뾡��ʹ��ConcurrentSkipListMap�����ܵõ����õĲ����ȡ�  
		 * 
		 *  ������ConcurrentHashMapһ��
		 */
		ConcurrentSkipListMap<Integer,String> cs = new ConcurrentSkipListMap<>();
		cs.put(6,"121"); //һ�β������̰߳�ȫ�� ԭ����
		cs.put(6,"ddd"); //����
		cs.putIfAbsent(6,"5"); //������� ��Ϊ1�������
		cs.put(3,"aa");
		cs.put(5,"fd");
		cs.put(5,"dfg");
		System.out.println(cs); //3 5 6 ����
		
		
		/*
		 * ConcurrentSkipListSet���������� 
		 * ����
		 */
		ConcurrentSkipListSet<Integer> css = new ConcurrentSkipListSet<>();
		css.add(5);
		css.add(1);
		css.add(5); //û�仯 ����false
		css.add(9);
		css.add(2);
		css.pollFirst();
		System.out.println(css);//2 5 9
		
	}

}

//����Callable
class Call implements Callable<Integer>
{
	public  int sum = 0; //���Future���Թ���sum ��runnable����
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


