import java.io.*;

class ReaderTwoStreams {
    static final String dataFile = "objects.bin";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    
        ObjectInputStream is1 = null;
        ObjectInputStream is2 = null;
        try {
            is1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
            is2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

            try {
                MyNum mn1 = (MyNum)is1.readObject();
                System.out.println(mn1);

                MyNum mn2 = (MyNum)is2.readObject();
                System.out.println(mn2);
                
                assert mn1 == mn2;

            }
            catch(EOFException eof) {}
        }
        finally {
            if(is1 != null) {
                is1.close();
            }
            if(is2 != null) {
                is2.close();
            }
        }

    }
}