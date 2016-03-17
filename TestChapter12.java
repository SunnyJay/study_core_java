import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型程序设计
 *
 */
public class TestChapter12 implements Chapter
{

	@Override
	public void test() 
	{
/*		//测试简单的泛型类
		testSimpleFanxing();*/
		
/*		//测试泛型方法
		testGenericFunction();*/
		
/*		//测试原始类
		testRawType();*/
		
/*		//测试桥方法 不是很清楚 遗留
		testBridge();*/
		
/*		//测试泛型中的继承
		testExtends();*/
		
		//测试泛型中的通配符
		testTongpeifu();
	}

	//测试泛型中的通配符 重要
	private void testTongpeifu() 
	{
		// ArrayList<? extends Cat> 是 ArrayList<Cat>的父类  因为Cat是Cat的本身
		ArrayList<? extends Cat> a1 = new ArrayList<Cat>(); //多态
		
		// ArrayList<? extends Cat> 是 ArrayList<RedCat>的父类  因为Cat是RedCat的父类
		ArrayList<? extends Cat> a2 = new ArrayList<RedCat>(); //多态
		
		
		
		//ArrayList<? super RedCat>是ArrayList<Cat>的父类  因为<? super RedCat>包含了cat
		ArrayList<? super RedCat> a3 = new ArrayList<Cat>(); //多态
		
		//ArrayList<? super RedCat>是ArrayList<Object>的父类  因为<? super RedCat>包含了Obejct
		ArrayList<? super RedCat> a4 = new ArrayList<Object>(); //多态
		
		//ArrayList<? super RedCat>是ArrayList<RedCat>的父类  因为<? super RedCat>包含了Redcat
		ArrayList<? super RedCat> a5 = new ArrayList<RedCat>(); //多态
		
	}

	//测试泛型中的继承
	private void testExtends() 
	{
		//redcat是cat的子类，但是ArrayList<RedCat>不是ArrayList<Cat>的子类
		ArrayList<Cat> a1 = new ArrayList();
		//ArrayList<RedCat> a2 = a1; //重要！不能直接赋值 但是数组可以
		Cat[] cat = new Cat[3];
		Cat[] RedCat = cat; //可以直接赋值 记住！
		
		ArrayList a3 = a1; //可以直接赋值 因为永远可以转换为一个原始类型
		
		
		ArrayList<Cat> a4= new ArrayList(); 
		List<Cat> a5 = a4; //ArrayList<T>是List<T>的子类 所以ArrayList<Cat>是List<Cat>的子类 注意与上面的区别
	}
	
	

	
	
	//测试泛型方法擦出中遇到的桥方法
	//擦除方法时，会可能导致该方法与继承来的仿佛冲突
	//冲突时编译器自动生成桥方法
	private void testBridge() 
	{
		Bridge1 b2 = new Bridge2(); //多态
		b2.testBridge_fun1("jack"); //
	}
	
	

	//测试原始类
	//无论何时定义一个泛型类，都将自动提供一个原始类型(raw type)
	//原始类型就是删除类型形参，并替换为第一个限定类型（无限定类型替换为object）
	//注意类型擦出的概念
	private void testRawType() 
	{
	}
	
	
	

	//测试简单的泛型类
	private void testSimpleGeneric() 
	{
		Teacher12<String> t1 = new Teacher12<String>();
		Teacher12_2<String, Integer> t2 = new Teacher12_2<String, Integer>();
	}
	
	//测试泛型方法
	private void testGenericFunction() 
	{
		this.<Integer>genericFunction1(63);	//调用时加上泛型，但是泛型是可以省略的，如下所示	
		genericFunction1(63); //大多数情况下可以省略，但是传入多个不同类型的参数时注意不要产生歧义，
		
		genericFunction2("sun");//String实现了Comparable接口
		//genericFunction2(new Rectangle());//Rectangle没有实现 所以报错
	}
	
	//测试原始类
	
	
	
	//泛型方法1
	private <T> void genericFunction1(T arg) 
	{
		System.out.println(arg);
	}
	
	
	//泛型方法2  限定T必须是Comparable的子类，即实现Comparable接口
	private <T extends Comparable> void genericFunction2(T arg) 
	{
		System.out.println(arg);
	}
	
	//泛型方法3 
	//限定类用&分割，逗号用来分隔类型变量
	//限定中可以有多个接口，但是最多一个类！而且放在第一个
	private <T extends Comparable & Serializable> void genericFunction3(T arg) 
	{
		System.out.println(arg);
	}
}



//一个简单的泛型类
class Teacher12<T>
{
	private T name;
	public T getName()
	{
		return name;
	}
	
	public void setName(T name)
	{
		this.name = name;
	}
}

//两个类型参数的泛型类
class Teacher12_2<T,U>
{
	private T name;
	private U age;
	public T getName()
	{
		return name;
	}
	public U getAge()
	{
		return age;
	}
	public void setName(T name)
	{
		this.name = name;
	}
}

//用于测试原始类
//T无限顶，所以直接用Object替换
class Teacher12_3<T>
{
	private T name;
	public T getName()
	{
		return name;
	}
	
	public void setName(T name)
	{
		this.name = name;
	}
}

//用于测试原始类
//T有限顶，所以用第一个Comparable替换。
class Teacher12_4<T extends Comparable & Serializable>
{
	private T name;
	public T getName()
	{
		return name;
	}
	public void setName(T name)
	{
		this.name = name;
	}
}

//用于桥方法
class Bridge1
{
	//父类方法
	public  Object  testBridge_fun1(Object arg) 
	{
	System.out.println("b1");
		return arg;
	}
}

//用于桥方法
class Bridge2 extends Bridge1
{
		
	//子类方法
	//擦除后会和Bridge1的方法冲突
	public <T extends Comparable> T testBridge_fun1(T arg) 
	{
		System.out.println("b2");
		return arg;
	}
}

//用于测试继承在泛型中的应用


class Cat 
{
	
}

class RedCat extends Cat
{
	
}


