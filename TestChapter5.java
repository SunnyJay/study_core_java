public class TestChapter5 implements Chapter 
{
	@Override
	public void test()
	{
/*		//������д
		B b = new B();
		b.fun();*/
		
/*		//����ǿ��ת��
		A a = new B();
		if(a instanceof B)
		{
			B bb = (B) a;
			bb.fun();
			System.out.println("ǿ��ת������");
			
			//�������෽��
			bb.funB();
		}*/
		
/*		//����hashCode��equals
		Student a = new Student("A", 22);
		Student b = new Student("A", 22);
		Student c = new Student("A", 23);
		System.out.println(a.equals(b)?"true":"false");
		System.out.println(a == b ?"true":"false"); //==���޷���д�ģ�
		System.out.println(a.equals(c)?"true":"false");*/
		
/*		//���Է�������
		ArrayList<Integer> list = new ArrayList<Integer>(10);  //������10 ���Ǵ�Сsize��0
		System.out.println(list.size());
		list.ensureCapacity(100); //ָ������
		list.add(3);list.add(5);list.add(2);
		list.trimToSize(); //������������С
		
		System.out.println(list.get(2)); //������[]���ʣ�ֻ����get
		
		//����toArray
		Integer[] array = new Integer[list.size()];
		list.toArray(array);
		for(int a:array)
		{
			System.out.println(a);
		}*/
	

	}
}


//���Լ̳�
class A
{
	public final int a = 3; //���Լ̳� �������޸� ���ܸı�����
	public int b = 3;
	public void fun()
	{
		System.out.println("A:"+b);
	}
	
	public void fun2()
	{
		System.out.println("A:"+"fun2");
	}
	
	public final void fun3() //���Լ̳� �����ܸ���
	{
		System.out.println("A:"+"fun3");
	}
}
class B extends A
{
	@Override
	public void fun() //������publicȨ�ޣ����ܵ��ڸ����еļ��� �����̬
	{
		System.out.println("B:"+b); //�̳�����b
		System.out.println(a);
		System.out.println(b);
		b = 4;
		System.out.println(b);
		fun3();
		fun2();
	}
	
	public void funB()
	{
		System.out.println("B�Լ��ķ���");
	}
}

/*//final�಻�ܼ̳� ������չ
final class C
{
	
}
class D extends C
{
	
}*/


//����hashCode equals
class Student
{
	private String name;
	private int age;
	public Student(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return age % 100;
	}

	@Override
	public boolean equals(Object obj) {
		//���ж��Ƿ��Ǳ�����
		if(obj == this)
			return true;
		//���ж��Ƿ�ͬ��
		if(obj instanceof Student)
		{
			//Ȼ��Ƚ���
			Student temp = (Student) obj;
			if((temp.age == this.age) && (temp.name.equals(this.name)) )
				return true;
		}
		return false;
	}
}
