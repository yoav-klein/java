import java.io.*;

class Reader {
    static final String dataFile = "objects.bin";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    
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