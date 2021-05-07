import interfacePackage.MyFirstInterface;
import interfacePackage.MySecondInterface;

public class TestClass {
    @AutoInjectable
    private MyFirstInterface field1;
    @AutoInjectable
    private MySecondInterface field2;

    public String example1;
    public String example2;

    public String getExample1(){
        return example1;
    }

    public String getExample2(){
        return example2;
    }

    public void testMethod(){
        example1=field1.doSomething();
        example2=field2.doAnotherThing();
    }

}
