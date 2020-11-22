package annotationExam;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Deprecated // 다른 것으로 대체되었으니 더이상 사용하지말라고 권고
@SuppressWarnings("1111") // 유효지하지 않는 애너테이션은 무시, 경고메세지 억제
@TestInfo(testBy = "aaa" , testDate = @DateTime(yymmdd="160101", hhmmss="235959") )
public class AnnotationExam {

	public static void main(String[] args) {
		
		// AnnotationExam 객체를 생성, Class.class는 모든 클래스에 대한 정보(메서드, 변수)를 가지고 있다
		// 클래스 로더에 의해 메모리에 올라갈 때 클래스에 관한 정보를 객체에 담는 데 이를 클래스 객체라고 한다
		// 접근할 때 클래스이름.class로 접근한다
		Class<AnnotationExam> cls = AnnotationExam.class;
		
		TestInfo anno = cls.getAnnotation(TestInfo.class); // getAnnotations() :  모든 어노테이션에 관한 정보를 배열로 받는다
		System.out.println("anno.testedBy() = " + anno.testBy()); //anno.testedBy() = aaa
		System.out.println(anno.testType());  //FIRST
		System.out.println("anno.testDate().yymmdd() = " + anno.testDate().yymmdd()); //anno.testDate().yymmdd() = 160101
		System.out.println("anno.testDate().hhmmss() = " + anno.testDate().hhmmss()); //anno.testDate().hhmmss() = 235959
		
		for (String str : anno.testTools()) {
			System.out.println(str); //JUnit
		}
		
		System.out.println();
		
		Annotation[] annoArr = cls.getAnnotations();
		
		for (Annotation a : annoArr) {
			System.out.println(a);
			//@java.lang.Deprecated()
			//@annotationExam.TestInfo(count=1, testType=FIRST, testTools=[JUnit], testBy=aaa, 
			//testDate=@annotationExam.DateTime(yymmdd=160101, hhmmss=235959))

		}
		
	} // main

}


// RUNTIME : 클래스에 존재, 실행시 사용가능 	SOURCE : 소스파일에 존재, 클래스파일 X
@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo{
	// 디폴트값이 없으면 어노테이션을 붙일 때 값을 무조건 지정해줘야한다.
	int count() 			default 1;
	String testBy();
	String[] testTools() 	default "JUnit"; // 요소값이 하나인 경우에는 괄호를 생략가능
	TestType testType() 	default TestType.FIRST;
	DateTime testDate();
	
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime{
	String yymmdd();
	String hhmmss();
}


enum TestType{ FIRST, FINAL}


@Retention(RetentionPolicy.RUNTIME)
@interface testParem{
	String value();
}

// 어노테이션의 요소가 한 개이고 요소명칭이 value이면 값을 지정할 때 요소명을 생략할 수 있다
@testParem("test")
class testValue{
	
}