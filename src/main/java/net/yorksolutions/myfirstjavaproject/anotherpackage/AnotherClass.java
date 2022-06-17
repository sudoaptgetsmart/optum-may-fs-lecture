package net.yorksolutions.myfirstjavaproject.anotherpackage;

import net.yorksolutions.myfirstjavaproject.MyFirstJavaProjectApplication;

public class AnotherClass {
    AnotherClass()
    {
        final var c = new MyFirstJavaProjectApplication();
        final var d = new MyFirstJavaProjectApplication();
        final var i = c.p;
        final var j = d.p;
        final var k = MyFirstJavaProjectApplication.s;
        final var l = MyFirstJavaProjectApplication.s;

        MyFirstJavaProjectApplication.main(null);
    }
}
