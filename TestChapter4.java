/*�������*/

public class TestChapter4 implements Chapter 
{
	public static int i;
	public TestChapter4()
	{
		System.out.println("���й��캯��");
	}
	
	{
		System.out.println("���о�̬��1");
	}
	
	{
		System.out.println("���о�̬��2");
	}
	
	@Override
	public void test()
	{
/*		//����Calendar SimpleDateFormat  һ��Ҫ��ס
		//GregorianCalendar gcal = new GregorianCalendar();
		//System.out.println(GregorianCalendar.DAY_OF_MONTH);
		Calendar cl = Calendar.getInstance();
		
		System.out.println(cl.get(Calendar.YEAR));
		System.out.println(cl.get(Calendar.MONTH)); //�·��Ǵ�0��ʼ�����ģ����Դ˴����м�1  
		System.out.println(cl.get(Calendar.DATE));
		System.out.println(cl.get(Calendar.HOUR_OF_DAY)); //HOUR_OF_DAY24Сʱ     HOUR 12Сʱ
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		System.out.println(df.format(cl.getTime()));*/
	}
	
	//test javadoc
	/**
	 * @author Nanjun.Sun
	 * @version 23.2
	 * @see com.java.sun#fun()
	 * @see "sunnanjun"
	 * @param a
	 * @param b
	 * @return sum of a and b
	 */
	int add(int a, int b)
	{
		return a + b;
	}
}
