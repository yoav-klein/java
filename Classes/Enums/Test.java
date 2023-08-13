enum Day {
    SUNDAY, MONDAY, TUESDAY,
    WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

class Test {
    Day day;
    
    public Test(Day day) {
        this.day = day;
    }
    
    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println("Mondays are bad.");
                break;
                    
            case FRIDAY:
                System.out.println("Fridays are better.");
                break;
                         
            case SATURDAY: case SUNDAY:
                System.out.println("Weekends are best.");
                break;
                        
            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
    }
    
    public static void main(String[] args) {
        Test firstDay = new Test(Day.MONDAY);
        firstDay.tellItLikeItIs();
        Test thirdDay = new Test(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        Test fifthDay = new Test(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        Test sixthDay = new Test(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        Test seventhDay = new Test(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }
}