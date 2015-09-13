package junit.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.geneframework.orm.util.TableReader;
import org.junit.Test;

public class TestReflect {

	@Test
	public void getImplClasses() {
		Class c = TableReader.class.getDeclaringClass();
		System.out.println(TableReader.class.toGenericString());
		// Object[] o = TableReader.class.getAnnotatedSuperclass();
		// for (int i = 0; i < o.length; i++) {
		// System.out.println(o[i]);
		// }
		System.out.println(c);
		System.out.println(TableReader.class.getCanonicalName());
		System.out.println(TableReader.class.getPackage());
		Package pag = TableReader.class.getPackage();
		System.out.println(pag.getClass());
	}

	@Test
	public void getsubClass() throws Exception {
		try {
			System.out.println("接口实现类：");
			for (Class<?> c : getAllAssignedClass(TableReader.class)) {
				System.out.println(c.getName());
				TableReader t = (TableReader) c.newInstance();
				System.out.println(t.getSuffixs());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Class<?>> getAllAssignedClass(Class<?> cls)
			throws IOException, ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls)) {
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	/**
	 * 取得当前类路径下的所有类
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getClasses(Class<?> cls) throws IOException,
			ClassNotFoundException {
		String pk = cls.getPackage().getName();
		String path = pk.replace('.', '/');
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();
		URL url = classloader.getResource(path);
		return getClasses(new File(url.getFile()), pk);
	}

	/**
	 * 迭代查找类
	 * 
	 * @param dir
	 * @param pk
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> getClasses(File dir, String pk)
			throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!dir.exists()) {
			return classes;
		}
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				classes.addAll(getClasses(f, pk + "." + f.getName()));
			}
			String name = f.getName();
			if (name.endsWith(".class")) {
				classes.add(Class.forName(pk + "."
						+ name.substring(0, name.length() - 6)));
			}
		}
		return classes;
	}

}
