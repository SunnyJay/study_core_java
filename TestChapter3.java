public class TestChapter3 implements Chapter
{
	@Override
	public void test()
	{
/*		//测试变量名 变量名除了_ 还可以用$开头 之前不知道
		int $num = 3;
		System.out.println($num);*/

/*		//测试二进制整数
		int binaryInt = 0b1101; //int binaryInt = 34_34_34;
		System.out.println(binaryInt);*/
		
		
/*		//测试局部变量不初始化直接使用
		int b;
		System.out.println(b);*/
		
/*		//测试静态导入
		System.out.println(Math.PI);*/
		
/*		//测试强制转换 舍入
        double a = 9.64;
		int b = (int) a; //直接截取
		int c = (int) Math.round(a); //四舍五入 round返回long  round圆、约
		System.out.println(b + "  " + c); //9    10
*/	
	
/*		//测试subString
		String name = "sunnanjun";
		String xing = name.substring(0, 3);
		System.out.println(xing); //sun
*/		
		
/*		//测试==
		String  a = "sunnanjun";
		String  b = "sunnanjun";
		String  c = "jack";
		System.out.println(a==b?"true":"false"); //保存同一个字符串，所以是true
		System.out.println(a==c?"true":"false");*/
		
/*		//测试StringBuilder
		StringBuilder sb = new StringBuilder();
		sb.append("sun");
		sb.append("nan");
		String name = sb.toString();
		System.out.println(name);*/
		
/*		//测试Scanner Console
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String word = sc.next();
		System.out.println(name);
		System.out.println(word);
		
		Console cs = System.console();
		String userName = cs.readLine("UserName");
		char password[] = cs.readPassword("Password");
		
		System.out.println(userName);*/
		
/*		//测试printf
		int a = 343;
		double b = 33.66;
		System.out.printf("孙楠军%5d说到底%4f", a, b);*/
		
/*		//测试能否同名
		int a;
		{
			int a = 4;
		}
		
		//测试case标签是否能是字符串 1.7可以
		String name = "sunnanjun";
		switch(name)
		{
		case "jack":
		case "sunnanjun":
		{
			System.out.println("yes!");
			break;
		}
		default:break;
		}*/
		
/*		//测试大数值  valueOf方法
		BigInteger a = BigInteger.valueOf(500);
		BigInteger b = BigInteger.valueOf(78);
		System.out.println(a);
		BigInteger c = a.add(b);
		System.out.println(c);
		c = a.multiply(b);
		System.out.println(c);*/
		
/*		//测试数组
		int[] array1 = {23, 45, 454};
		System.out.println(array1.length);
		int[] array2 = new int[]{23, 45, 454};
		System.out.println(array1==array2?"true":"false");
		int[] ss = new int[0];
		System.out.println(ss.length);
		
		int[] array3 = Arrays.copyOf(array1, array1.length);
		System.out.println(array3.length);*/
		
/*		//测试Arrays sort\binarySearch
		int[] array1 = {234, 45, 454};
		Arrays.sort(array1);
		for(int a:array1)
		{
			System.out.println(a);
		}
		
		int index = Arrays.binarySearch(array1, 234);
		System.out.println(index);*/
	
		
	}
}
