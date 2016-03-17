import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestChapter5_2  implements Chapter{

	@Override
	public void test() {
		//testBox();
		//kebiancanshu(34,45,465,6);
		
/*		//����ö��
		Color c = Color.BLACK; //��סд��
		Color[] array = Color.values(); //��������ʵ��
		for(Color color:array)
		{
			System.out.println(color); //toString�����ַ���ʵ��
		}
		System.out.println(c.getDept()); 
		
		Color cc1 = Color.valueOf("BLACK"); //����һ ����ָ��ֵ��ʵ��
		System.out.println(cc1);
		Color cc2 = Enum.valueOf(Color.class, "RED"); //������ ����ָ��ֵ��ʵ��
		System.out.println(cc2);*/
		
		//���Է���
		fanshe();
		
		
		
		
	}
	
	//���԰�װ��  Integer.valueOf(3) 
	void testBox()
	{
		//Character Void Byte Boolean
		Character c = new Character('c');
		Integer a = new Integer(3);
		System.out.println(Integer.valueOf(3));
		System.out.println(Integer.valueOf("3445"));  //����Integer����
		
		int aa = Integer.parseInt("345");
		System.out.println(aa);
	}
	
	//���Կɱ����
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
	
	//���Է���
	void fanshe()
	{
		Class cls = Teacher.class;//���� ����.getClass();
//		//��һ�ֻ�ȡ��ʽ
//		Teacher teacher = new Teacher("sun", 24);
//		teacher.getClass();
		
//		System.out.println(cls.getName());
		
	
		try {
			//����forName
/*			Class t = Class.forName("Teacher"); //ʹ��forName����Class����
			Teacher tt = (Teacher) t.newInstance();  //Ȼ����Class����������
			tt.setAge(23);
			System.out.println(tt.getAge());*/
			
			
/*			//����Filed[]
			Field[] filedsPublic = cls.getFields(); //���ع��з��� Teacherû�У����������ǿյ�
			Field[] fileds = cls.getDeclaredFields();
			System.out.println(fileds.length);
			for(Field f:fileds)
			{
				System.out.println(f.getName());
			}*/
			
			//���÷�����ĳ��˽������
			Teacher teacher = new Teacher("nanjun", 55);
			Class tcl = teacher.getClass();
			Field f = tcl.getDeclaredField("age"); //ע���getDeclaredFileds()������
			f.setAccessible(true); //�����ſ��Է���˽������
			Integer age = (Integer)f.get(teacher);//������Եľ��
			System.out.println(age);//��ӡ����ֵ
			
			f.set(teacher, 23);//��������ֵ
			age = (Integer)f.get(teacher);
			System.out.println(age);
			
			//modifiers����
			int id = f.getModifiers();
			System.out.println(Modifier.isStatic(id)?"true":"false"); //�Ƿ��Ǿ�̬ false 
			System.out.println(Modifier.isPrivate(id)?"true":"false"); //�Ƿ���˽�� true
			
			//���÷����÷���
			Method m = tcl.getMethod("getAge");
			System.out.println("���÷����ȡ" +m.invoke(teacher)); //��Invoke���÷���
			
			//Array��
			Object obj = Array.newInstance(Teacher.class, 3);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}


//���ڲ��Է���
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
	private Color(int dept) //ö�ٲ���new
	{
		this.dept = dept;
	}
	
	public int getDept()
	{
		return this.dept;
	}
}
