package org.rookie.test.jvm;  // PackageElement
@CheckGetter
class Foo {      // TypeElement
    @CheckGetter
    int a;           // VariableElement
    static int b;    // VariableElement
    Foo () {}        // ExecutableElement
    @CheckGetter
    void setA (      // ExecutableElement
                     int newA         // VariableElement
    ) {}
}
