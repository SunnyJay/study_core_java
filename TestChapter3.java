public class TestChapter3 implements Chapter
{
	@Override
	public void test()
	{
/*		//���Ա����� ����������_ ��������$��ͷ ֮ǰ��֪��
		int $num = 3;
		System.out.println($num);*/

/*		//���Զ���������
		int binaryInt = 0b1101; //int binaryInt = 34_34_34;
		System.out.println(binaryInt);*/
		
		
/*		//���Ծֲ���������ʼ��ֱ��ʹ��
		int b;
		System.out.println(b);*/
		
/*		//���Ծ�̬����
		System.out.println(Math.PI);*/
		
/*		//����ǿ��ת�� ����
        double a = 9.64;
		int b = (int) a; //ֱ�ӽ�ȡ
		int c = (int) Math.round(a); //�������� round����long  roundԲ��Լ
		System.out.println(b + "  " + c); //9    10
*/	
	
/*		//����subString
		String name = "sunnanjun";
		String xing = name.substring(0, 3);
		System.out.println(xing); //sun
*/		
		
/*		//����==
		String  a = "sunnanjun";
		String  b = "sunnanjun";
		String  c = "jack";
		System.out.println(a==b?"true":"false"); //����ͬһ���ַ�����������true
		System.out.println(a==c?"true":"false");*/
		
/*		//����StringBuilder
		StringBuilder sb = new StringBuilder();
		sb.append("sun");
		sb.append("nan");
		String name = sb.toString();
		System.out.println(name);*/
		
/*		//����Scanner Console
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String word = sc.next();
		System.out.println(name);
		System.out.println(word);
		
		Console cs = System.console();
		String userName = cs.readLine("UserName");
		char password[] = cs.readPassword("Password");
		
		System.out.println(userName);*/
		
/*		//����printf
		int a = 343;
		double b = 33.66;
		System.out.printf("��骾�%5d˵����%4f", a, b);*/
		
/*		//�����ܷ�ͬ��
		int a;
		{
			int a = 4;
		}
		
		//����case��ǩ�Ƿ������ַ��� 1.7����
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
		
/*		//���Դ���ֵ  valueOf����
		BigInteger a = BigInteger.valueOf(500);
		BigInteger b = BigInteger.valueOf(78);
		System.out.println(a);
		BigInteger c = a.add(b);
		System.out.println(c);
		c = a.multiply(b);
		System.out.println(c);*/
		
/*		//��������
		int[] array1 = {23, 45, 454};
		System.out.println(array1.length);
		int[] array2 = new int[]{23, 45, 454};
		System.out.println(array1==array2?"true":"false");
		int[] ss = new int[0];
		System.out.println(ss.length);
		
		int[] array3 = Arrays.copyOf(array1, array1.length);
		System.out.println(array3.length);*/
		
/*		//����Arrays sort\binarySearch
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
