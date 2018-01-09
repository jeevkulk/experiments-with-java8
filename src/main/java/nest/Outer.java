package nest;

public class Outer {

    public static void main(String[] args) {

        //Outer can be left out - any of the below works
        //StaticInner staticInner = new StaticInner();
        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.method1();
        staticInner.method2();
        staticInner.method3();
        staticInner.method4();

        //Accessing static methods of a static nested class
        StaticInner.method3();
        StaticInner.method4();

        //Outer can be left out - any of the below works
        //StaticInner.PrivateStaticInner privateStaticInner = new StaticInner.PrivateStaticInner();
        Outer.StaticInner.PrivateStaticInner privateStaticInner = new Outer.StaticInner.PrivateStaticInner();
        privateStaticInner.method1();
        privateStaticInner.method2();

        //Accessing classes nested in nested static inner class
        StaticInner.PrivateInner privateInner = new StaticInner().new PrivateInner();
        privateInner.method1();
        privateInner.method2();

        //Accessing classes nested in nested inner class
        Outer.Inner inner = new Outer().new Inner();
        inner.method1();
        inner.method2();

        //Any of the below works while referring to nest inner class of nested class
        //Inner.PrivateInner privateInner1 = new Outer().new Inner().new PrivateInner();
        Outer.Inner.PrivateInner privateInner1 = new Outer().new Inner().new PrivateInner();
        privateInner1.method1();
        privateInner1.method2();
    }

    public static class StaticInner {
        public void method1() {
            System.out.println(this+": public method1 called");
        }

        private void method2() {
            System.out.println(this+": private method2 called");
        }

        public static void method3() {
            System.out.println("StaticInner: public static method3 called");
        }

        private static void method4() {
            System.out.println("StaticInner: private static method4 called");
        }

        private static class PrivateStaticInner {
            public void method1() {
                System.out.println(this+": public method1 called");
            }

            private void method2() {
                System.out.println(this+": private method2 called");
            }
        }

        private class PrivateInner {
            public void method1() {
                System.out.println(this+": public method1 called");
            }

            private void method2() {
                System.out.println(this+": private method2 called");
            }
        }
    }

    public class Inner {
        public void method1() {
            System.out.println(this+": public method1 called");
        }

        private void method2() {
            System.out.println(this+": private method2 called");
        }

        private class PrivateInner {
            public void method1() {
                System.out.println(this+": public method1 called");
            }

            private void method2() {
                System.out.println(this+": private method2 called");
            }
        }
    }
}
