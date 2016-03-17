import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*�ӿ����ڲ���*/
public class TestChapter6 implements Chapter
{

	@Override
	public void test() 
	{
		
/*		//���Կ�¡
		testClone();*/
		
/*		//���Իص�
		Animal a = new Dog(); //�ýӿ�ʵ�ֻص�
		testCallBack(a);*/
		
/*		//�����ڲ���
		testInnerClass();*/
		
/*		//���������ڲ���  �ӿ���ʽ
		testAnonymousClass(new Animal(){
			@Override
			public void eat()
			{
				System.out.println("eat!");
			}
		}
		);
		
		//���������ڲ���  ������ʽ
		testAnonymousClass(new Anony("sun"){ //ע�⴫��
			@Override
			public void say()
			{
				System.out.println("i am child of Anony!");
			}
		}
		);*/
		
/*		//���Ծ�̬�ڲ���
		testStaticAnony();*/
		
/*		//���Զ�̬����
		testProxy();*/
		
		testProxyAop();
		
	}
	
	//���Խӿ���ʽ������
	void testAnonymousClass(Animal a) 
	{
		a.eat();
	}
	
	//���Ը�����ʽ������
	void testAnonymousClass(Anony a) 
	{
		a.say();
	}

	//���Ծ�̬�ڲ���
	void testStaticAnony() 
	{
		AStatic as6 = new AStatic();
		as6.anum = 7;
		
		//��̬�ڲ��಻ͨ���ⲿʵ���Ϳ��Դ������������������ͨ��������������
		AStatic.B6 bs6 = new AStatic.B6(33); //��Ϊ�Ǿ�̬�ģ����Կ���ֱ����.�ڲ���  �ͺ���ͨ�ľ�̬��Ա����һ��  ������Ǿ�̬�ľͲ���
		bs6.say();
		

		A6 a6 = new A6();
		a6.anum = 7;
		
		//A6.B6 b6 = new A6.B6(); //�����ڲ�����Ҫͨ���ⲿ���ʵ�����ܴ���������ʵ��������Ҫͨ����������������
		A6.B6 b6 = a6.new B6(); //�����ǿ��Ե�
		
	}
	
	//���Կ�¡
	void testClone()
	{
		try 
		{
			Student6 s1 = new Student6("sun", 22);
			Student6 s2 = (Student6) s1.clone();
			System.out.println(s2.name + " " + s2.age);
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//���Իص�
	void testCallBack(Animal a)
	{
		a.eat();
	}
	
	//�����ڲ���
	void testInnerClass()
	{
		A6 a6 = new A6();
		a6.anum = 7;
		
		A6.B6 b6 = a6.new B6(); //ע��д��OuterObject.new  InnerClass()
		
		System.out.println(b6.bnum);
		
		String name = b6.getClass().getName(); //�鿴b6�������֣�����Է�������A6$Bƴ��������
		System.out.println(name);
	}
	
	//���Զ�̬����
	void testProxy()
	{
		//1.������һ��handler MyInvocationHandler�����invoke����Ҫ�߼�����
		InvocationHandler handler = new MyInvocationHandler();
		
		//2.����һ���������p
		Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
		
		//3.���÷���
		p.say();
		p.sleep();
	}
	
	//����Aop ��һ�������в��뷽������Ȼ�������̳нӿ�
	void testProxyAop()
	{
		//������һ��handler
		Person man = new Man();
		MyAopInvocationHandler handler = new MyAopInvocationHandler();
		handler.setTarget(man);//������Ҫ���뷽����Դ����
		
		Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
		p.say();//һ��ִ������������
		p.sleep();//���������ܹ�6������
	}
	

}


//���ڲ��Կ�¡
class Student6 implements Cloneable
{
	public String name;
	public int age;
	
	public Student6(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		//Ĭ�Ͽ�¡��ǳ���� ֱ�ӵ���super.clone();
		return super.clone();
	}

	public Student6()
	{
		
	}
}

//���ڲ��Իص�
interface Animal
{
	//Ĭ����public
	void eat();
}

//���ڲ��Իص�
class Dog implements Animal
{

	@Override
	public void eat() 
	{
		System.out.println("Dog eat...");
		
	}
}



//�����ڲ���
class A6
{
	int anum;
	
	public class B6
	{
		int bnum;
		
		//�и����صĳ�Աouter����ʾ��Χ������ 
		//��Ĭ������һ������������������Χ�����Ȼ��Ѷ���ֵ��outer����
		//��Ȼ������outer������OuterClass.this�õ�outer��
		
		public B6()
		{
			//ͨ��A6.this ���Եõ����ص���Χ������
			bnum = A6.this.anum;
		}
	}
}

//���������ڲ���
class Anony
{
	public String name;
	public Anony(String name)
	{
		this.name = name;
	}
	
	public void say()
	{
		System.out.println("My name is " + name);
	}
}

//���Ծ�̬�ڲ���
class AStatic
{
	public int anum;
	public static String name;
	
	//B6����AStatic�౾����������AStatic�Ķ���
	//����B6���ܷ���AStatic��ʵ������ĳ�Ա�������Է��ʾ�̬��Ա
	public static class B6
	{
		int bnum;
		
		
		public B6(int bnum)
		{
			this.bnum = bnum;
		}
		
		public void say()
		{
			System.out.println(AStatic.name);//���Է����ⲿ��ľ�̬��Ա ���ܷ���ʵ����Ա
			System.out.println("I am  b6 " + bnum);
		}
		
	}
}
//���Զ�̬�����AOP
interface Person
{
	 void say();
	 void sleep();
}
	
//����AOP
class Man implements Person
{

	@Override
	public void say() {
		
		System.out.println("hi");
	}

	@Override
	public void sleep() {
		System.out.println("zzzz");
		
	}
	
}

//���Զ�̬����
class MyInvocationHandler implements InvocationHandler
{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("����ִ�еķ����� " + method);
		if(args != null)
		{
			for(Object val:args)
			{
				System.out.println(val);
			}
		}
		else
		{
			System.out.println("�÷���û��ʵ��");
		}
		return null;
	}
}

//����Aop
class MyAopInvocationHandler implements InvocationHandler
{
	//������Ķ���Ҫ����������Ϊmethod��invoke����Ҫ��
	private Object target;
	
	public void setTarget(Object target)
	{
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//��һ������
		System.out.println("ģ����뷽��1");
		
		//ִ���Դ�����
		method.invoke(target, args);
		
		//�ڶ�������
		System.out.println("ģ����뷽��2");
		
		return null;
	}
}
