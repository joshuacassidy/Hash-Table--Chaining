public class HashItem<Key, Value> {

    public Key key;
    public Value value;
    public HashItem next;

    public HashItem(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public HashItem getNext() {
        return next;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setNext(HashItem next) {
        this.next = next;
    }
}
