import classPackage.MyFirstClass;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		TestClass example = (TestClass)(new Injector()).inject(new TestClass());
		example.testMethod();
		System.out.println("field1 " + example.getExample1());
		System.out.println("field2 " + example.getExample2());
    }
}
