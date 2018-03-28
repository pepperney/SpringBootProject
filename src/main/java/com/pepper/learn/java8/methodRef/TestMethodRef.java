package com.pepper.learn.java8.methodRef;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.pepper.learn.java8.stream.Employee;


public class TestMethodRef {
	
	
	/**
	 * 一、方法引用：
	 * 若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用 （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
	 * 格式：
	 * 1. 对象的引用 :: 实例方法名
	 * 2. 类名 :: 静态方法名
	 * 3. 类名 :: 实例方法名
	 * 注意： 
	 * ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
	 * ②若Lambda的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
	 */
	@Test
	public void test_method_ref(){
		PrintStream ps = System.out;
		Consumer<String> con = (str) -> ps.println(str);
		con.accept("Hello World!");
		System.out.println("--------------------------------");
		
		Consumer<String> con2 = ps::println;
		con2.accept("Hello Java8!");
		
		Consumer<String> con3 = System.out::println;
		con3.accept("Hello JDK8!");
	}
	
	
	/**
	 * 方法引用一
	 * 对象的引用 :: 实例方法名
	 */
	@Test
	public void test_method_ref_1(){
		Employee emp = new Employee(101, "张三", 18, 9999.99);
		
		Supplier<String> sup = () -> emp.getName();
		System.out.println(sup.get());
		System.out.println("----------------------------------");
		
		Supplier<String> sup2 = emp::getName;
		System.out.println(sup2.get());
		
		BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
		System.out.println(fun.apply(1.5, 22.2));
		System.out.println("--------------------------------------------------");
		
		BiFunction<Double, Double, Double> fun2 = Math::max;
		System.out.println(fun2.apply(1.2, 1.5));
		
	}
	
	
	/**
	 * 方法引用二：
	 * 类名 :: 静态方法名
	 */
	
	@Test
	@SuppressWarnings("unused")
	public void test_method_ref_2(){
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

		Comparator<Integer> com2 = Integer::compare;
		
		Integer a = 12;
		Integer b = 3;
		System.out.println(a.compareTo(b));
	}
	
	
	/**
	 * 方法引用三
	 * 类名 :: 实例方法名
	 */
	@Test
	public void test_method_ref_3(){
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		System.out.println(bp.test("abcde", "abcde"));
		System.out.println("-----------------------------------------");
		
		BiPredicate<String, String> bp2 = String::equals;
		System.out.println(bp2.test("abc", "abc"));
		System.out.println("-----------------------------------------");
		
		
		Function<Employee, String> fun = (e) -> e.show();
		System.out.println(fun.apply(new Employee()));
		System.out.println("-----------------------------------------");
		
		Function<Employee, String> fun2 = Employee::show;
		System.out.println(fun2.apply(new Employee()));
		
	}
	
	
	/**
	 * 二.构造器引用
	 * 格式--- 类名 :: new 
	 * 注意： 构造器的参数列表，需要与函数式接口中参数列表保持一致！  
	 */
	@Test
	@SuppressWarnings("unused")
	public void test_constrctor_ref(){
		Function<String, Employee> fun = Employee::new;
		BiFunction<String, Integer, Employee> fun2 = Employee::new;
		
		Supplier<Employee> sup = () -> new Employee();
		System.out.println(sup.get());
		
		System.out.println("------------------------------------");
		
		Supplier<Employee> sup2 = Employee::new;
		System.out.println(sup2.get());
	}
	
	
	/**
	 * 三.数组引用
	 * 格式：类型[] :: new;
	 */
	@Test
	public void test_array_ref(){
		Function<Integer, String[]> fun = (args) -> new String[args];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		System.out.println("--------------------------");
		
		Function<Integer, Employee[]> fun2 = Employee[] :: new;
		Employee[] emps = fun2.apply(20);
		System.out.println(emps.length);
	}
	
}