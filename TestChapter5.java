public class TestChapter5 implements Chapter 
{
	@Override
	public void test()
	{
/*		//测试重写
		B b = new B();
		b.fun();*/
		
/*		//测试强制转换
		A a = new B();
		if(a instanceof B)
		{
			B bb = (B) a;
			bb.fun();
			System.out.println("强制转换结束");
			
			//调用子类方法
			bb.funB();
		}*/
		
/*		//测试hashCode和equals
		Student a = new Student("A", 22);
		Student b = new Student("A", 22);
		Student c = new Student("A", 23);
		System.out.println(a.equals(b)?"true":"false");
		System.out.println(a == b ?"true":"false"); //==是无法重写的！
		System.out.println(a.equals(c)?"true":"false");*/
		
/*		//测试泛型数组
		ArrayList<Integer> list = new ArrayList<Integer>(10);  //容量是10 但是大小size是0
		System.out.println(list.size());
		list.ensureCapacity(100); //指定容量
		list.add(3);list.add(5);list.add(2);
		list.trimToSize(); //调整容量到大小
		
		System.out.println(list.get(2)); //不能用[]访问，只能用get
		
		//测试toArray
		Integer[] array = new Integer[list.size()];
		list.toArray(array);
		for(int a:array)
		{
			System.out.println(a);
		}*/
	

	}
}


//测试继承
class A
{
	public final int a = 3; //可以继承 但不能修改 不能改变语义
	public int b = 3;
	public void fun()
	{
		System.out.println("A:"+b);
	}
	
	public void fun2()
	{
		System.out.println("A:"+"fun2");
	}
	
	public final void fun3() //可以继承 但不能覆盖
	{
		System.out.println("A:"+"fun3");
	}
}
class B extends A
{
	@Override
	public void fun() //必须是public权限，不能低于父类中的级别 想想多态
	{
		System.out.println("B:"+b); //继承来的b
		System.out.println(a);
		System.out.println(b);
		b = 4;
		System.out.println(b);
		fun3();
		fun2();
	}
	
	public void funB()
	{
		System.out.println("B自己的方法");
	}
}

/*//final类不能继承 不能扩展
final class C
{
	
}
class D extends C
{
	
}*/


//测试hashCode equals
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
		//先判断是否是本对象
		if(obj == this)
			return true;
		//再判断是否同类
		if(obj instanceof Student)
		{
			//然后比较域
			Student temp = (Student) obj;
			if((temp.age == this.age) && (temp.name.equals(this.name)) )
				return true;
		}
		return false;
	}
}
