import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;

/**
 * �쳣 �����ԡ���־�͵���
 *
 */
public class TestChapter11  implements Chapter
{

	@Override
	public void test() 
	{
/*		//����finally�е�return
		int i = testFinallyReturn();
		System.out.println(i); //3
*/	
/*		//����assert
		testAssert();*/
		
		//���� Thread.dumpStack()
		testDumpStack();
		
	}
	
	private void testDumpStack() 
	{
		//ֻҪ����������ܲ�����ջ����
		Thread.dumpStack();
	}

	/**
	 * javaĬ���ǹر�assert�ģ���eclipse����Ҫ��
	 * VM arguments�ı����м��϶��Կ����ı�־:-enableassertions ����-ea �Ϳ�����
	 */
	private void testAssert() 
	{
		int i = 3;
		assert i == 3;	
		
		//���ʽΨһ��Ŀ���ǲ�����Ϣ�ַ���
		assert i == 4:"sd"; //sd�������쳣
	}

	/**
	 * �����쳣
	 * @throws FileNotFoundException
	 * @throws EOFException
	 */
	void testException() throws FileNotFoundException,Exception,Throwable/*,ArrayIndexException //��Ҫ��δ����쳣*/
	{
		/*
		 * ����쳣���ö���
		 * ��Ҫ�׳�δ����쳣������ʲôArrayIndexException NullPointerException IndexOutOfBoundsException
		 */
		int a =6;
		if(a < 10)
			throw new EOFException();//ע��new
		
		try
		{
			a += 1;
		}
		catch(Exception e) //������catch(Exception1 | Exception2 e)
		{
			//e.printStackTrace();
			//�ٴ��׳��쳣
			throw new ServerException("down:" + e.getMessage());
		}
		
		
		//��װԭʼ�쳣 
		//ǿ�ҽ����ʹ�����ַ�ʽ, �׳��߼��쳣��������ʧԭʼ��Ϣ
		try
		{
			a += 1;
		}
		catch(Exception e) //������catch(Exception1 | Exception2 e)
		{

			Throwable se = new ServerException("down:");
			se.initCause(e);
			throw se;
		}
		
		try
		{
			a += 1;
		}
		catch(Exception e) //��ʱ������ֱ���׳������κθı�
		{
			throw e;
		}
		
	}
	
	/**
	 * ����finally
	 */
	void testFinally()
	{
		InputStream in = System.in; 
		
		//��һ��
		//���ַ�ʽ��Ҫ��close�ٽ���trycatch, ����������ܸ���
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
		
		//�ڶ��� ǿ�ҽ������ַ�ʽ �������
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
		
		//������ JDK 7�ķ�ʽ��������Դ��trycatch ���Բ�������ʾclose Ҳ�Ƽ�ʹ��
		//try(InputStream in2 = System.in) //����ͬʱ������Դ �ֺż�� //try(InputStream in2 = System.in;InputStream in3 = System.in)
		try(InputStream in2 = System.in;InputStream in3 = System.in)
		{
			//try���˳���ʱ����Զ�����close
		}
		catch(Exception e)
		{
			//
		}
	}
	
	/**
	 * ����finally�е�return
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
