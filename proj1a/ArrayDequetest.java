import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequetest {
    static ArrayDeque<Integer> ad = new ArrayDeque();
    @Test
    public void TestAddGet() {
        int N = 9;
        for (int i = 0; i < N; i++) {
            ad.addLast(i);
        }
//        int get7 = ad.get(7);
        assertEquals(java.util.Optional.of(7), java.util.Optional.of(ad.get(7)));
    }
}
