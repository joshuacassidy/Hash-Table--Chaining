public class HashTable<Key, Value> implements IHashTable<Key, Value>{

    private HashItem<Key, Value>[] hashTable;

    private int size, capacity;

    public HashTable() {
        this.capacity = 10;
        this.hashTable = new HashItem[capacity];
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.hashTable = new HashItem[capacity];
    }


    private int hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public Value get(Key key) {
        int generatedArrayIndex = hash(key);
        HashItem<Key, Value> hashItem = hashTable[generatedArrayIndex];
        while (hashItem != null && hashItem.getKey() != key) {
            hashItem = hashItem.getNext();
        }
        if (hashItem.getKey().equals(key)) {
            return hashItem.getValue();
        } else {
            throw new HashItemNotFoundException("Hash Item could not be found");
        }
    }


    public void put(Key key, Value value) {
        int hashArrayIndex = hash(key);
        HashItem hashItem = hashTable[hashArrayIndex];
        while (hashItem != null) {
            if (hashItem.getKey() == (key)) {
                hashItem.setValue(value);
                return;
            }
            hashItem = hashItem.getNext();
        }
        size++;
        hashItem = hashTable[hashArrayIndex];
        HashItem<Key, Value> newItem = new HashItem<>(key, value);
        newItem.next = hashItem;
        hashTable[hashArrayIndex] = newItem;

        if (size >= capacity * 0.75) {
            resize(2*capacity);
        }
    }

    @Override
    public Value remove(Key key) {
        int hashArrayIndex = hash(key);
        HashItem<Key, Value> hashItem = hashTable[hashArrayIndex];
        HashItem prev = null;
        while (hashItem != null && hashItem.key != (key)) {
            prev = hashItem;
            hashItem = hashItem.getNext();
        }

        if (hashItem == null) {
            throw new HashItemNotFoundException("Hash item could not be found");
        }
        size--;

        if (prev != null) {
            prev.next = hashItem.getNext();
        } else {
            hashTable[hashArrayIndex] = hashItem.getNext();
        }
        if (size <= capacity /3) {
            resize(capacity/2);
        }
        return hashItem.getValue();
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

    public void resize(int newCapacity) {
        capacity = newCapacity;
        HashItem[] cloneTable = new HashItem[newCapacity];
        for (HashItem<Key, Value> i: hashTable) {
            if (i != null) {
                while (i != null) {
                    int hashArrayIndex = hash(i.getKey());
                    if( cloneTable[hashArrayIndex] == null) {
                        cloneTable[hashArrayIndex] = new HashItem<>(i.getKey(),i.getValue());
                    } else {
                        HashItem hashItem = cloneTable[hashArrayIndex];
                        while (hashItem.getNext() != null) {
                            hashItem = hashItem.getNext();
                        }
                        hashItem.setNext(new HashItem<>(i.getKey(),i.getValue()));
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

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
