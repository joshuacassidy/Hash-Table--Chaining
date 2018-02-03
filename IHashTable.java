public interface IHashTable<Key, Value> {
    public int getSize();
    public boolean isEmpty();
    public Value remove(Key key);
    public Value get(Key key);
    public void put(Key key, Value value);
    public void resize(int newCapacity);
}
