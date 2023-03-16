package four;

import java.io.Closeable;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

class Chair {
    public void sit() throws IllegalArgumentException{
        System.out.println("!!!");
        throw new RuntimeException();
    }
}




public class Bottle extends Chair{
    private static void sortAndSer(String... args) {
        var one = args[0];
        Arrays.sort(args);
        System.out.println(one);
        System.out.println(Arrays.toString(args));
        var zeta = new int[][]{};
    }
    public static void main(String... args) {
//        var linux = new String[] {"lunux", "mac", "windows"};
//        var mac = new String[] {"mac", "lunux", "windows"};
//        var search = Arrays.binarySearch(linux, "lunux");
//        System.out.println(search);
//        var mismatch1 = Arrays.mismatch(linux, mac);
//        var mismatch2 = Arrays.mismatch(mac, mac);
//        HashSet<Object> set = new HashSet<Object>();


        List.of(1,2,3,4).stream().parallel()
                .forEach(System.out::println);
        var stream = Stream.of(1,2,3);
//        System.out.println(stream.fi);
        var list = new ArrayList<>();
        list.add(new String());
//        list.add(String::new);
    }

}






