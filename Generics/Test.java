/*class Node {

    public Object data;

    public Node(Object data) { this.data = data; }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}*/

class Node<T> {

    public T data;

    public Node(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}


class MyNode extends Node<Integer> {

    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

class Test {
    public static void main(String[] args) {
        //MyNode mn = new MyNode(4);
        //Node n = mn;
        //mn.setData("hello");
        //n.setData("Hello");
        //mn.setData(10);

        String s = "Hello";
        Integer i = (Integer)s;
    }
}