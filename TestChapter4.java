/*对象和类*/

public class TestChapter4 implements Chapter 
{
	public static int i;
	public TestChapter4()
	{
		System.out.println("运行构造函数");
	}
	
	{
		System.out.println("运行静态块1");
	}
	
	{
		System.out.println("运行静态块2");
	}
	
	@Override
	public void test()
	{
/*		//测试Calendar SimpleDateFormat  一定要记住
		//GregorianCalendar gcal = new GregorianCalendar();
		//System.out.println(GregorianCalendar.DAY_OF_MONTH);
		Calendar cl = Calendar.getInstance();
		
		System.out.println(cl.get(Calendar.YEAR));
		System.out.println(cl.get(Calendar.MONTH)); //月份是从0开始计数的，所以此处进行加1  
		System.out.println(cl.get(Calendar.DATE));
		System.out.println(cl.get(Calendar.HOUR_OF_DAY)); //HOUR_OF_DAY24小时     HOUR 12小时
		
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
