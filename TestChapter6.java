import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*接口与内部类*/
public class TestChapter6 implements Chapter
{

	@Override
	public void test() 
	{
		
/*		//测试克隆
		testClone();*/
		
/*		//测试回调
		Animal a = new Dog(); //用接口实现回调
		testCallBack(a);*/
		
/*		//测试内部类
		testInnerClass();*/
		
/*		//测试匿名内部类  接口形式
		testAnonymousClass(new Animal(){
			@Override
			public void eat()
			{
				System.out.println("eat!");
			}
		}
		);
		
		//测试匿名内部类  父类形式
		testAnonymousClass(new Anony("sun"){ //注意传参
			@Override
			public void say()
			{
				System.out.println("i am child of Anony!");
			}
		}
		);*/
		
/*		//测试静态内部类
		testStaticAnony();*/
		
/*		//测试动态代理
		testProxy();*/
		
		testProxyAop();
		
	}
	
	//测试接口形式匿名类
	void testAnonymousClass(Animal a) 
	{
		a.eat();
	}
	
	//测试父类形式匿名类
	void testAnonymousClass(Anony a) 
	{
		a.say();
	}

	//测试静态内部类
	void testStaticAnony() 
	{
		AStatic as6 = new AStatic();
		as6.anum = 7;
		
		//静态内部类不通过外部实例就可以创建对象；与类变量可以通过类名访问相似
		AStatic.B6 bs6 = new AStatic.B6(33); //因为是静态的，所以可以直接类.内部类  就和普通的静态成员调用一样  如果不是静态的就不行
		bs6.say();
		

		A6 a6 = new A6();
		a6.anum = 7;
		
		//A6.B6 b6 = new A6.B6(); //常规内部类需要通过外部类的实例才能创建对象，与实例变量需要通过对象来访问相似
		A6.B6 b6 = a6.new B6(); //这样是可以的
		
	}
	
	//测试克隆
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
	
	//测试回调
	void testCallBack(Animal a)
	{
		a.eat();
	}
	
	//测试内部类
	void testInnerClass()
	{
		A6 a6 = new A6();
		a6.anum = 7;
		
		A6.B6 b6 = a6.new B6(); //注意写法OuterObject.new  InnerClass()
		
		System.out.println(b6.bnum);
		
		String name = b6.getClass().getName(); //查看b6的类名字，你可以发现它是A6$B拼接起来的
		System.out.println(name);
	}
	
	//测试动态代理
	void testProxy()
	{
		//1.先生成一个handler MyInvocationHandler里面的invoke是主要逻辑方法
		InvocationHandler handler = new MyInvocationHandler();
		
		//2.生成一个代理对象p
		Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
		
		//3.调用方法
		p.say();
		p.sleep();
	}
	
	//测试Aop 往一个对象中插入方法，当然这个对象继承接口
	void testProxyAop()
	{
		//先生成一个handler
		Person man = new Man();
		MyAopInvocationHandler handler = new MyAopInvocationHandler();
		handler.setTarget(man);//传入你要插入方法的源对象
		
		Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
		p.say();//一次执行三个方法！
		p.sleep();//包括上面总共6个方法
	}
	

}


//用于测试克隆
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
		//默认克隆是浅拷贝 直接调用super.clone();
		return super.clone();
	}

	public Student6()
	{
		
	}
}

//用于测试回调
interface Animal
{
	//默认是public
	void eat();
}

//用于测试回调
class Dog implements Animal
{

	@Override
	public void eat() 
	{
		System.out.println("Dog eat...");
		
	}
}



//测试内部类
class A6
{
	int anum;
	
	public class B6
	{
		int bnum;
		
		//有个隐藏的成员outer，表示外围类引用 
		//会默认生成一个构造器，参数是外围类对象，然后把对象赋值给outer引用
		//当然并不叫outer，可用OuterClass.this得到outer。
		
		public B6()
		{
			//通过A6.this 可以得到隐藏的外围类引用
			bnum = A6.this.anum;
		}
	}
}

//测试匿名内部类
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

//测试静态内部类
class AStatic
{
	public int anum;
	public static String name;
	
	//B6属于AStatic类本身，而不属于AStatic的对象
	//所以B6不能访问AStatic的实例对象的成员，但可以访问静态成员
	public static class B6
	{
		int bnum;
		
		
		public B6(int bnum)
		{
			this.bnum = bnum;
		}
		
		public void say()
		{
			System.out.println(AStatic.name);//可以访问外部类的静态成员 不能访问实例成员
			System.out.println("I am  b6 " + bnum);
		}
		
	}
}
//测试动态代理和AOP
interface Person
{
	 void say();
	 void sleep();
}
	
//测试AOP
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

//测试动态代理
class MyInvocationHandler implements InvocationHandler
{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("正在执行的方法： " + method);
		if(args != null)
		{
			for(Object val:args)
			{
				System.out.println(val);
			}
		}
		else
		{
			System.out.println("该方法没有实参");
		}
		return null;
	}
}

//测试Aop
class MyAopInvocationHandler implements InvocationHandler
{
	//被代理的对象要传进来，因为method的invoke方法要用
	private Object target;
	
	public void setTarget(Object target)
	{
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//第一个方法
		System.out.println("模拟插入方法1");
		
		//执行自带方法
		method.invoke(target, args);
		
		//第二个方法
		System.out.println("模拟插入方法2");
		
		return null;
	}
}
