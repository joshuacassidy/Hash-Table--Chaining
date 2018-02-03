public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.put(4,300);
        hashTable.put(4,200);
        hashTable.put(5,300);
        hashTable.put(6,300);
        hashTable.get(4);
        hashTable.remove(5);
        System.out.println(hashTable);

    }
}
