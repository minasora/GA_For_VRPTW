import java.io.IOException;

/**
 * @author minasora
 * @date 2019/10/7 16: 16
 * @description
 */
public class Main {

    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello World!");
        Conf.readInstance();
        GA_Strategy.genetic_algoritm();
    }
}
