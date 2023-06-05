import java.util.Arrays;

public class MyHashMap {
    private Node[] data;
    private int size;

    public MyHashMap() {
        data = new Node[16];
    }

    public Object put(Integer key , Integer value) {
        int hash = key.hashCode();
        int index = hash % data.length;

        Node node = data[index];
        while (node != null && node.getKey() != key) {
            node = node.getNext();
        }

        if (node == null) {
            node = new Node(key, value);
            node.setNext(data[index]);
            data[index] = node;
            size++;

            if (size >= data.length * 0.75) {
                resize();
            }
        } else {
            node.setValue(value);
        }

        return value;
    }

    public Object get(Integer key) {
        int hash = key.hashCode();
        int index = hash % data.length;

        Node node = data[index];
        while (node != null && node.getKey() != key) {
            node = node.getNext();
        }

        if (node == null) {
            return null;
        } else {
            return node.getValue();
        }
    }

    public Object remove(Integer key) {
        int hash = key.hashCode();
        int index = hash % data.length;

        Node node = data[index];
        Node prev = null;
        while (node != null && node.getKey() != key) {
            prev = node;
            node = node.getNext();
        }

        if (node == null) {
            return null;
        } else {
            Object value = node.getValue();

            if (prev == null) {
                data[index] = node.getNext();
            } else {
                prev.setNext(node.getNext());
            }
            size--;

            return value;
        }
    }

    public Object replace(Integer key, Integer v) {
        int hash = key.hashCode();
        int index = hash % data.length;

        Node node = data[index];
        while (node != null && node.getKey() != key) {
            node = node.getNext();
        }

        if (node == null) {
            return null;
        } else {
            node.setValue(v);
            return v;
        }
    }

    public int size() {
        return size;
    }

    public boolean containsKey(Integer key) {
        int hash = key.hashCode();
        int index = hash % data.length;

        Node node = data[index];
        while (node != null && node.getKey() != key) {
            node = node.getNext();
        }

        return node != null;
    }

    public boolean containsValue(Integer v) {
        for (int i = 0; i < data.length; i++) {
            Node node = data[i];
            while (node != null) {
                if (node.getValue() == v) {
                    return true;
                }
                node = node.getNext();
            }
        }

        return false;
    }

    private void resize() {
        Node[] oldData = data;
        data = new Node[data.length * 2];
        Arrays.fill(data, null);

        for (int i = 0; i < oldData.length; i++) {
            Node oldNode = oldData[i];
            while (oldNode != null) {
                Node newNode = new Node(oldNode.getKey(), oldNode.getValue());

                int index = newNode.getKey().hashCode() % data.length;
                newNode.setNext(data[index]);
                data[index] = newNode;

                oldNode = oldNode.getNext();
            }
        }
    }

    private class Node {
        private Integer key;
        private Integer value;
        private Node next;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}