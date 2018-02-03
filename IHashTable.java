public interface IHashTable {
    public int getSize();
    public boolean isEmpty();
    public int remove(int key);
    public int get(int key);
    public void put(int key, int value);
    public void resize(int newCapacity);
}
