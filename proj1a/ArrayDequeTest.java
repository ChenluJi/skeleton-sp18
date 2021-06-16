import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    static ArrayDeque<Integer> ad = new ArrayDeque();
    @Test
    public void testAddGet() {
        int N = 9;
        for (int i = 0; i < N; i++) {
            ad.addLast(i);
        }
//        int get7 = ad.get(7);
        assertEquals(java.util.Optional.of(7), java.util.Optional.of(ad.get(7)));
    }
    @Test
    public void testResize() {
        int N = 17;
        for (int i = 0; i < N; i++) {
            ad.addLast(i);
        }
        int first = ad.removeFirst();
        int last = ad.removeLast();
        assertNotNull(first);
        assertNotNull(last);
    }
    @Test
    public void testRemoveFill() {
        int N = 9;
        for (int i = 0; i < N; i++) {
            ad.addFirst(i);
        }
        for (int i = 0; i < N; i++) {
            ad.removeFirst();
        }
//        for (int i = 0; i < N; i++) {
//            ad.addFirst(i);
//        }
    }
}
