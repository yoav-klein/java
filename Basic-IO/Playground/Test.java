import java.io.*;

class MyNum implements Serializable {
    private int a;

    MyNum(int data) {
        this.a = data;
    }

    @Override
    public String toString() {
        return String.format("%d", a);
    }
}

class MyString implements Serializable {
    private String a;

    MyString(String data) {
        this.a = data;
    }

    @Override
    public String toString() {
        return this.a;
    }
}

class A implements Serializable {
    private int a;

    A(int data) {
        this.a = data;
    }

    @Override
    public String toString() {
        return String.format("%d", a);
    }
}


class Writer {
    static final String dataFile = "objects.bin";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

            // writing the same object twice to the same stream
            MyNum mn = new MyNum(120);
            os.writeObject(mn);
            os.writeObject(mn);
            os.writeObject(new MyString("Yoav"));

        } finally {
            if(os != null) {
                os.close();
            }
        }
    
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
            
            try {
                MyNum mn1 = (MyNum)is.readObject();
                System.out.println(mn1);

                MyNum mn2 = (MyNum)is.readObject();
                System.out.println(mn2);
                
                assert mn1 == mn2;

                MyString s = (MyString)is.readObject();
                System.out.println(s);
            }
            catch(EOFException eof) {}
        }
        finally {
            if(is != null) {
                is.close();
            }
        }

    }
}