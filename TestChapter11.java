import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;

/**
 * 异常 、断言、日志和调试
 *
 */
public class TestChapter11  implements Chapter
{

	@Override
	public void test() 
	{
/*		//测试finally中的return
		int i = testFinallyReturn();
		System.out.println(i); //3
*/	
/*		//测试assert
		testAssert();*/
		
		//测试 Thread.dumpStack()
		testDumpStack();
		
	}
	
	private void testDumpStack() 
	{
		//只要调用这个就能产生堆栈跟踪
		Thread.dumpStack();
	}

	/**
	 * java默认是关闭assert的，在eclipse中需要打开
	 * VM arguments文本框中加上断言开启的标志:-enableassertions 或者-ea 就可以了
	 */
	private void testAssert() 
	{
		int i = 3;
		assert i == 3;	
		
		//表达式唯一的目的是产生消息字符串
		assert i == 4:"sd"; //sd将传给异常
	}

	/**
	 * 测试异常
	 * @throws FileNotFoundException
	 * @throws EOFException
	 */
	void testException() throws FileNotFoundException,Exception,Throwable/*,ArrayIndexException //不要抛未检查异常*/
	{
		/*
		 * 多个异常间用逗号
		 * 不要抛出未检查异常，比如什么ArrayIndexException NullPointerException IndexOutOfBoundsException
		 */
		int a =6;
		if(a < 10)
			throw new EOFException();//注意new
		
		try
		{
			a += 1;
		}
		catch(Exception e) //还可以catch(Exception1 | Exception2 e)
		{
			//e.printStackTrace();
			//再次抛出异常
			throw new ServerException("down:" + e.getMessage());
		}
		
		
		//包装原始异常 
		//强烈建议多使用这种方式, 抛出高级异常，而不丢失原始信息
		try
		{
			a += 1;
		}
		catch(Exception e) //还可以catch(Exception1 | Exception2 e)
		{

			Throwable se = new ServerException("down:");
			se.initCause(e);
			throw se;
		}
		
		try
		{
			a += 1;
		}
		catch(Exception e) //有时候你想直接抛出不做任何改变
		{
			throw e;
		}
		
	}
	
	/**
	 * 测试finally
	 */
	void testFinally()
	{
		InputStream in = System.in; 
		
		//第一种
		//这种方式需要对close再进行trycatch, 看起来程序很复杂
		try
		{
			in.read();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//第二种 强烈建议这种方式 清晰简洁
		try
		{
			try
			{
				in.read();
			}
			finally
			{
				in.close();
			}
			
		}
		catch(Exception e)
		{
			
		}
		
		//第三种 JDK 7的方式，即带资源的trycatch 可以不用再显示close 也推荐使用
		//try(InputStream in2 = System.in) //可以同时几个资源 分号间隔 //try(InputStream in2 = System.in;InputStream in3 = System.in)
		try(InputStream in2 = System.in;InputStream in3 = System.in)
		{
			//try块退出的时候会自动调用close
		}
		catch(Exception e)
		{
			//
		}
	}
	
	/**
	 * 测试finally中的return
	 */
	@SuppressWarnings("finally")
	int  testFinallyReturn()
	{
		try
		{
			return 1;
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			return 3;
		}
	}
	

}
