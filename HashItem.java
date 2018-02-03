public class HashItem {

    public int key, value;
    public HashItem next;

    public HashItem(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public HashItem getNext() {
        return next;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(HashItem next) {
        this.next = next;
    }
}
