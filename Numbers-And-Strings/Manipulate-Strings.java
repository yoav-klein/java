
class Manipulate {

    static public void main(String[] args) {
        // substring
        String s = "My name is John Doe, it's great..";

        System.out.println(s.substring(3, 7));
        System.out.println(s.substring(21));

        // split
        String filePath = "/home/ubuntu/yoav.txt";
        String[] parts = filePath.split("/");

        for(int i = 0; i < parts.length; ++i) {
            System.out.println(parts[i]);
        }

        // trim
        String s1 = "   This is my house   ";
        System.out.println(s1.trim());
        

        // indexOf
        // get the index of the first /
        int index = filePath.indexOf('/');
        System.out.println(index);

        // get the index of the last /
        index = filePath.lastIndexOf('/');
        System.out.println(index);

        // get index of string
        String heystack = "Well, my name is Yoav, Yoav Klein";
        String needle = "Yoav";

        System.out.println(heystack.indexOf(needle));

        // replace - replaces a sequence with a sequence
        System.out.println(heystack.replaceFirst("Yoav", "Dikla"));
        
        // replaceFirst and replaceAll - works with regex
        System.out.println(heystack.replaceFirst("Yo..", "Dikla"));
        System.out.println(heystack.replaceAll("Yoav", "Dikla"));
        


    }

}