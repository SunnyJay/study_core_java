import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestChapter5_2  implements Chapter{

	@Override
	public void test() {
		//testBox();
		//kebiancanshu(34,45,465,6);
		
/*		//测试枚举
		Color c = Color.BLACK; //记住写法
		Color[] array = Color.values(); //返回所有实例
		for(Color color:array)
		{
			System.out.println(color); //toString返回字符串实例
		}
		System.out.println(c.getDept()); 
		
		Color cc1 = Color.valueOf("BLACK"); //方法一 返回指定值的实例
		System.out.println(cc1);
		Color cc2 = Enum.valueOf(Color.class, "RED"); //方法二 返回指定值的实例
		System.out.println(cc2);*/
		
		//测试反射
		fanshe();
		
		
		
		
	}
	
	//测试包装类  Integer.valueOf(3) 
	void testBox()
	{
		//Character Void Byte Boolean
		Character c = new Character('c');
		Integer a = new Integer(3);
		System.out.println(Integer.valueOf(3));
		System.out.println(Integer.valueOf("3445"));  //返回Integer对象
		
		int aa = Integer.parseInt("345");
		System.out.println(aa);
	}
	
	//测试可变参数
	void kebiancanshu(int... args)
	{
		System.out.println(args[3]);
		int sum = 0;
		for(int a:args)
		{
			sum += a;
		}
		System.out.println(sum);
	}
	
	//测试反射
	void fanshe()
	{
		Class cls = Teacher.class;//或者 对象.getClass();
//		//另一种获取方式
//		Teacher teacher = new Teacher("sun", 24);
//		teacher.getClass();
		
//		System.out.println(cls.getName());
		
	
		try {
			//测试forName
/*			Class t = Class.forName("Teacher"); //使用forName生成Class对象
			Teacher tt = (Teacher) t.newInstance();  //然后用Class对象生成类
			tt.setAge(23);
			System.out.println(tt.getAge());*/
			
			
/*			//测试Filed[]
			Field[] filedsPublic = cls.getFields(); //返回共有方法 Teacher没有，所以数组是空的
			Field[] fileds = cls.getDeclaredFields();
			System.out.println(fileds.length);
			for(Field f:fileds)
			{
				System.out.println(f.getName());
			}*/
			
			//利用反射获得某个私有属性
			Teacher teacher = new Teacher("nanjun", 55);
			Class tcl = teacher.getClass();
			Field f = tcl.getDeclaredField("age"); //注意和getDeclaredFileds()的区别
			f.setAccessible(true); //这样才可以访问私有属性
			Integer age = (Integer)f.get(teacher);//获得属性的句柄
			System.out.println(age);//打印属性值
			
			f.set(teacher, 23);//更改属性值
			age = (Integer)f.get(teacher);
			System.out.println(age);
			
			//modifiers方法
			int id = f.getModifiers();
			System.out.println(Modifier.isStatic(id)?"true":"false"); //是否是静态 false 
			System.out.println(Modifier.isPrivate(id)?"true":"false"); //是否是私有 true
			
			//利用反射获得方法
			Method m = tcl.getMethod("getAge");
			System.out.println("利用反射获取" +m.invoke(teacher)); //用Invoke调用方法
			
			//Array类
			Object obj = Array.newInstance(Teacher.class, 3);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}


//用于测试反射
class Teacher
{
	private String name;
	private int age;
	
	public Teacher()
	{
	}
	
	public Teacher(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String getName()
	{
		return this.name;
	}
}



enum Color
{
	RED(3),BLUE(5),BLACK(2);
	
	private int dept;
	private Color(int dept) //枚举不能new
	{
		this.dept = dept;
	}
	
	public int getDept()
	{
		return this.dept;
	}
}
