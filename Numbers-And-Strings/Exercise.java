
class Exercise {
    static public void computeInitials() {
        String fullName = "Yoav Klein";

        String[] words = fullName.split(" ");
        System.out.format("Initials: %c.%c.", words[0].charAt(0), words[1].charAt(0));
    }

    static public void main(String[] args) {
        computeInitials();
    }
}