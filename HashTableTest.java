import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {


    HashTable<Integer, Integer> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>();
    }

    @org.junit.Test
    public void get() throws Exception {
        hashTable.put(1,121);
        assertEquals(121, ((int) hashTable.get(1)));
        hashTable.put(1,122);
        assertEquals(122, ((int) hashTable.get(1)));
        hashTable.put(2,123);
        assertEquals(123, ((int) hashTable.get(2)));
        hashTable.put(3,124);
        assertEquals(124, ((int) hashTable.get(3)));
        hashTable.put(5,125);
        assertEquals(125, ((int) hashTable.get(5)));
    }


    @org.junit.Test
    public void remove() throws Exception {
        hashTable.put(1,121);
        assertEquals(121, ((int) hashTable.remove(1)));
        hashTable.put(1,122);
        assertEquals(122, ((int) hashTable.remove(1)));
        hashTable.put(2,123);
        assertEquals(123, ((int) hashTable.remove(2)));
        hashTable.put(3,124);
        assertEquals(124, ((int) hashTable.remove(3)));
        hashTable.put(5,125);
        assertEquals(125, ((int) hashTable.remove(5)));
    }

    @org.junit.Test
    public void getSize() throws Exception {
        assertEquals(0, hashTable.getSize());
        hashTable.put(1,1);
        hashTable.put(1,2);
        hashTable.put(2,2);
        assertEquals(2, hashTable.getSize());
    }

    @org.junit.Test
    public void isEmptyOnCreation() throws Exception {
        assertEquals(true, hashTable.isEmpty());
        hashTable.put(1,1);
        assertEquals(false, hashTable.isEmpty());
    }

    @org.junit.Test
    public void isNotEmptyWhenItemAdded() throws Exception {
        hashTable.put(1,1);
        assertEquals(false, hashTable.isEmpty());
    }

    @Test
    public void resize() throws Exception {
        for (int i = 0; i < 8; i++) {
            hashTable.put(i,i);
        }
        assertEquals(8,hashTable.getSize());
        assertEquals(20,hashTable.getCapacity());

        for (int i = 0; i < 6; i++) {
            hashTable.remove(i);
        }
        assertEquals(2,hashTable.getSize());
        assertEquals(5,hashTable.getCapacity());
    }

}