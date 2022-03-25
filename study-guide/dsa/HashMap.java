public class HashMap {
    public LinkedList[] map;

    public HashMap(int size) {
        this.map = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.map[i] = new LinkedList();
        }
    }

    public int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode += Character.codePointAt(key, i);
        }
        hashCode %= this.map.length;
        return hashCode;
    }

    public void assign(String key, int val) {
        int index = hash(key);
        LinkedList list = this.map[index];
        if (list.head == null)
            list.head = new LinkedListNode(key, val);
        LinkedListNode cur = list.head;
        while (cur.getNext() != null) {
            if (cur.getKey() == key) {
                cur.setKeyVal(key, val);
            }
            cur = cur.getNext();
        }
        cur.setNext(new LinkedListNode(key, val));
    }

    public int retrieve(String key) {
        int index = hash(key);
        LinkedListNode cur = this.map[index].head;
        while (cur != null) {
            if (cur.getKey() == key) {
                return cur.getVal();
            }
            cur = cur.getNext();
        }
        return -1;
    }
}
