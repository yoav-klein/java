import java.io.*;

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

    }
}