public class HashTable implements IHashTable{

    private HashItem[] hashTable;

    private int size;
    private int capacity;

    public HashTable() {
        this.capacity = 10;
        this.hashTable = new HashItem[capacity];
    }

    public HashTable(int capacity) {
        this.hashTable = new HashItem[capacity];
        this.capacity = capacity;
    }


    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    @Override
    public int get(int key) {
        int generatedArrayIndex = hash(key);
        HashItem hashItem = hashTable[generatedArrayIndex];
        while (hashItem != null && hashItem.getKey() != key) {
            hashItem = hashItem.getNext();
        }
        if (hashItem.getKey() == key) {
            return hashItem.getValue();
        } else {
            throw new HashItemNotFoundException("Hash Item could not be found");
        }
    }

    @Override
    public void put(int key, int value) {
        int hashArrayIndex = hash(key);
        HashItem hashItem = hashTable[hashArrayIndex];
        while (hashItem != null) {
            if (hashItem.key == (key)) {
                hashItem.value = value;
                return;
            }
            hashItem = hashItem.getNext();
        }
        size++;
        hashItem = hashTable[hashArrayIndex];
        HashItem newItem = new HashItem(key, value);
        newItem.next = hashItem;
        hashTable[hashArrayIndex] = newItem;

        if (size >= capacity * 0.75) {
            resize(2*capacity);
        }
    }

    @Override
    public int remove(int key) {
        int hashArrayIndex = hash(key);
        HashItem hashItem = hashTable[hashArrayIndex];
        HashItem prev = null;
        while (hashItem != null && hashItem.key != (key)) {
            prev = hashItem;
            hashItem = hashItem.next;
        }

        if (hashItem == null) {
            throw new HashItemNotFoundException("Hash item could not be found");
        }
        size--;

        if (prev != null) {
            prev.next = hashItem.next;
        } else {
            hashTable[hashArrayIndex] = hashItem.next;
        }

        return hashItem.value;
    }

    public String toString() {
        String table = "Table size: " + getSize() + "\n";
        for (HashItem i: hashTable) {
            boolean itemIsPresent=false;
            if (i != null) {
                while (i != null) {
                    itemIsPresent=true;
                    table+= i.getKey() + " " + i.getValue() +" | ";
                    i = i.getNext();
                }
            }
            table+= itemIsPresent ? "\n": "";
        }
        return table;
    }

    @Override
    public void resize(int newCapacity) {
        capacity = newCapacity;
        HashItem[] cloneTable = new HashItem[newCapacity];
        for (HashItem i: hashTable) {
            if (i != null) {
                while (i != null) {
                    int hashArrayIndex = hash(i.getKey());
                    if( cloneTable[hashArrayIndex] == null) {
                        cloneTable[hashArrayIndex] = new HashItem(i.getKey(),i.getValue());
                    } else {
                        HashItem hashItem = cloneTable[hashArrayIndex];
                        while (hashItem.getNext() != null) {
                            hashItem = hashItem.getNext();
                        }
                        hashItem.setNext(new HashItem(i.getKey(),i.getValue()));
                    }
                    i = i.getNext();
                }
            }
        }
        hashTable = cloneTable;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
