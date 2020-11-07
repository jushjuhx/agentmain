import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("start");
        System.out.println(ClassLoader.getSystemClassLoader());
        while (true) {
            Account account = new Account();
            account.username = "123";
            System.out.println(account.username);
            try {
                Method m = Class.forName("Dynamic").getMethod("showInfo");
                System.out.println(Class.forName("Dynamic").getClassLoader());
                m.invoke(null);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            Thread.sleep(3000);
        }
    }
}
