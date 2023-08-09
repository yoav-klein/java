
class Months {
    public static String numberToString(int monthNum) {
        String res;
        switch(monthNum) {
            case 1: res = "January";
                break;
            case 2: res = "February";
                break;
            case 3: res = "March";
                break;
            case 4: res = "April";
                break;
            case 5: res = "May";
                break;
            case 6: res = "June";
                break;
            case 7: res = "July";
                break;
            case 8: res = "August";
                break;
            case 9: res = "September";
                break;
            case 10: res = "October";
                break;
            case 11: res = "November";
                break;
            case 12: res = "December";
                break;
            default: res = null;
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        int monthNum = 22;
        String res = numberToString(monthNum);
        System.out.println(res == null ? "Invalid" : res);
    }
}