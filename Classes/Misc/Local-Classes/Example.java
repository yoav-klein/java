class Example {
    static String regEx = "[^0-9]";

    public static void validatePhoneNumber(String number) {
        final int phoneNumLength = 10;

        // acceptable in Java 8 and later:
        // int phoneNumLength = 10;

        class PhoneNumber {
            private String formattedNumber = null;

            PhoneNumber(String s) {
                String cleanNumber = s.replaceAll(regEx, ""); // local classes can access the enclosing class's member regEx.
                if(cleanNumber.length() == phoneNumLength)
                    formattedNumber = cleanNumber;
                else 
                    formattedNumber = null;
            }

            public String getNumber() {
                return formattedNumber;
            }
        }

        PhoneNumber p = new PhoneNumber(number);

        if(p.getNumber() == null) 
            System.out.println("Invalid number");
        else
            System.out.println(p.getNumber());

    }

    public static void main(String[] args) {
        validatePhoneNumber("050-541-9589");
        validatePhoneNumber("050-5-4-1-95-89");
        validatePhoneNumber("05050505050505050");

    }
}