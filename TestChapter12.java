import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ͳ������
 *
 */
public class TestChapter12 implements Chapter
{

	@Override
	public void test() 
	{
/*		//���Լ򵥵ķ�����
		testSimpleFanxing();*/
		
/*		//���Է��ͷ���
		testGenericFunction();*/
		
/*		//����ԭʼ��
		testRawType();*/
		
/*		//�����ŷ��� ���Ǻ���� ����
		testBridge();*/
		
/*		//���Է����еļ̳�
		testExtends();*/
		
		//���Է����е�ͨ���
		testTongpeifu();
	}

	//���Է����е�ͨ��� ��Ҫ
	private void testTongpeifu() 
	{
		// ArrayList<? extends Cat> �� ArrayList<Cat>�ĸ���  ��ΪCat��Cat�ı���
		ArrayList<? extends Cat> a1 = new ArrayList<Cat>(); //��̬
		
		// ArrayList<? extends Cat> �� ArrayList<RedCat>�ĸ���  ��ΪCat��RedCat�ĸ���
		ArrayList<? extends Cat> a2 = new ArrayList<RedCat>(); //��̬
		
		
		
		//ArrayList<? super RedCat>��ArrayList<Cat>�ĸ���  ��Ϊ<? super RedCat>������cat
		ArrayList<? super RedCat> a3 = new ArrayList<Cat>(); //��̬
		
		//ArrayList<? super RedCat>��ArrayList<Object>�ĸ���  ��Ϊ<? super RedCat>������Obejct
		ArrayList<? super RedCat> a4 = new ArrayList<Object>(); //��̬
		
		//ArrayList<? super RedCat>��ArrayList<RedCat>�ĸ���  ��Ϊ<? super RedCat>������Redcat
		ArrayList<? super RedCat> a5 = new ArrayList<RedCat>(); //��̬
		
	}

	//���Է����еļ̳�
	private void testExtends() 
	{
		//redcat��cat�����࣬����ArrayList<RedCat>����ArrayList<Cat>������
		ArrayList<Cat> a1 = new ArrayList();
		//ArrayList<RedCat> a2 = a1; //��Ҫ������ֱ�Ӹ�ֵ �����������
		Cat[] cat = new Cat[3];
		Cat[] RedCat = cat; //����ֱ�Ӹ�ֵ ��ס��
		
		ArrayList a3 = a1; //����ֱ�Ӹ�ֵ ��Ϊ��Զ����ת��Ϊһ��ԭʼ����
		
		
		ArrayList<Cat> a4= new ArrayList(); 
		List<Cat> a5 = a4; //ArrayList<T>��List<T>������ ����ArrayList<Cat>��List<Cat>������ ע�������������
	}
	
	

	
	
	//���Է��ͷ����������������ŷ���
	//��������ʱ������ܵ��¸÷�����̳����ķ·��ͻ
	//��ͻʱ�������Զ������ŷ���
	private void testBridge() 
	{
		Bridge1 b2 = new Bridge2(); //��̬
		b2.testBridge_fun1("jack"); //
	}
	
	

	//����ԭʼ��
	//���ۺ�ʱ����һ�������࣬�����Զ��ṩһ��ԭʼ����(raw type)
	//ԭʼ���;���ɾ�������βΣ����滻Ϊ��һ���޶����ͣ����޶������滻Ϊobject��
	//ע�����Ͳ����ĸ���
	private void testRawType() 
	{
	}
	
	
	

	//���Լ򵥵ķ�����
	private void testSimpleGeneric() 
	{
		Teacher12<String> t1 = new Teacher12<String>();
		Teacher12_2<String, Integer> t2 = new Teacher12_2<String, Integer>();
	}
	
	//���Է��ͷ���
	private void testGenericFunction() 
	{
		this.<Integer>genericFunction1(63);	//����ʱ���Ϸ��ͣ����Ƿ����ǿ���ʡ�Եģ�������ʾ	
		genericFunction1(63); //���������¿���ʡ�ԣ����Ǵ�������ͬ���͵Ĳ���ʱע�ⲻҪ�������壬
		
		genericFunction2("sun");//Stringʵ����Comparable�ӿ�
		//genericFunction2(new Rectangle());//Rectangleû��ʵ�� ���Ա���
	}
	
	//����ԭʼ��
	
	
	
	//���ͷ���1
	private <T> void genericFunction1(T arg) 
	{
		System.out.println(arg);
	}
	
	
	//���ͷ���2  �޶�T������Comparable�����࣬��ʵ��Comparable�ӿ�
	private <T extends Comparable> void genericFunction2(T arg) 
	{
		System.out.println(arg);
	}
	
	//���ͷ���3 
	//�޶�����&�ָ���������ָ����ͱ���
	//�޶��п����ж���ӿڣ��������һ���࣡���ҷ��ڵ�һ��
	private <T extends Comparable & Serializable> void genericFunction3(T arg) 
	{
		System.out.println(arg);
	}
}



//һ���򵥵ķ�����
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

//�������Ͳ����ķ�����
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

//���ڲ���ԭʼ��
//T���޶�������ֱ����Object�滻
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

//���ڲ���ԭʼ��
//T���޶��������õ�һ��Comparable�滻��
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

//�����ŷ���
class Bridge1
{
	//���෽��
	public  Object  testBridge_fun1(Object arg) 
	{
	System.out.println("b1");
		return arg;
	}
}

//�����ŷ���
class Bridge2 extends Bridge1
{
		
	//���෽��
	//��������Bridge1�ķ�����ͻ
	public <T extends Comparable> T testBridge_fun1(T arg) 
	{
		System.out.println("b2");
		return arg;
	}
}

//���ڲ��Լ̳��ڷ����е�Ӧ��


class Cat 
{
	
}

class RedCat extends Cat
{
	
}


