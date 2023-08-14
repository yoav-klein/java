
class ReverseCharSequence implements java.lang.CharSequence {
    private final String s;

    public ReverseCharSequence(String s) {
        int len = s.length();
        String tmp = "";
        for(int i = len-1; i >= 0; --i) {
            tmp = tmp + s.charAt(i);
        }
        this.s = tmp;
    }

    public char charAt(int index) {
        return this.s.charAt(index);
    }

    public int length() {
        return this.s.length();
    }

    public CharSequence subSequence(int start, int end) {
        return this.s.substring(start, end);
    }

    public String toString() {
        return (String)this.s;
    }

    public static void main(String[] args) {
        ReverseCharSequence rcs = new ReverseCharSequence("Toyota Corolla");

        System.out.println("Length: " + rcs.length());
        System.out.println("Substring: 2, 5: " + rcs.subSequence(2, 5));
        System.out.println("toString: " + rcs.toString());
        System.out.println("charAt 3: " + rcs.charAt(3));
    }
}