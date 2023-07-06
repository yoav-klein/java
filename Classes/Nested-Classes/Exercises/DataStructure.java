

public class DataStructure {
    
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];
    
    // getter for the SIZE
    public int size() {
        return SIZE;
    }

    public int get(int index) {
        return arrayOfInts[index];
    }


    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    // subquestion 1: Add a method that takes an DataStructureIterator
    // and performs the same function as the printEven method
    public void print(DataStructureIterator iter) {
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
    
    // subquestion 3: Lambda expressions
    public void print(java.util.function.Function<Integer, Boolean> iter) {
        for(int i = 0; i < SIZE; ++i) {
            if(iter.apply(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }
        System.out.println();
    } 
    

    public void printEven() {
        
        // Print out values of even indices of the array
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    
    interface DataStructureIterator extends java.util.Iterator<Integer> { } 

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface
    
    private class EvenIterator implements DataStructureIterator {
        
        // Start stepping through the array from the beginning
        private int nextIndex = 0;
        
        public boolean hasNext() {
            
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }        
        
        public Integer next() {
            
            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
            
            // Get the next even element
            nextIndex += 2;
            return retValue;
        }

    }
    
    public static void main(String s[]) {
        
        // Fill the array with integer values and print out only
        // values of even indices
        DataStructure ds = new DataStructure();
        ds.printEven();

        // subquestion 1
        DataStructure.EvenIterator ei = ds.new EvenIterator();
        ds.print(ei);

        // subquestion 2 - call the print() method so it prints odd numbers
        // using anonymous class
        // NOTE that we had to add the get and size methods in order to do this.
        // this is because from this main method, since its static, you don't have
        // access to the arrayOfInts.
        ds.print(
            new DataStructure.DataStructureIterator() {
                private int nextIndex = 1;
                public boolean hasNext() { 
                    return (nextIndex <= ds.size() - 1);
                }
                public Integer next() {
                    int retValue = ds.get(nextIndex);
                    nextIndex += 2;
                    return retValue;
                }
            }
        );

        // subquestion 3 - create another print method that takes java.util.function.Function<Integer, Boolean>
        // and performs the same functionality as print(DataStructureIterator i)
        // use Labmda functions to have it print all the odd numbers, and then the even numbers

        // print even
        ds.print(index -> {
            if (index % 2 == 0) return Boolean.TRUE;
            return Boolean.FALSE;
        });
        // print odd
        ds.print(index -> {
            if (index % 2 == 0) return Boolean.FALSE;
            return Boolean.TRUE;
        });
        
    }
}