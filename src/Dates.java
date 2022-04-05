public class Dates {
    private int day;
    private int month;
    private int year;

    //can be change later to suit the corresponding era
    static int MAX_YEAR = 9999;
    static int MIN_YEAR = 1800;

    //check for leap year
    static boolean isLeapYear(int year) {
        //the year is leap (366 days instead of 365)
        //is when it is divisible to 4 AND NOT to 100
        //OR it can be divisible to 400
        return (((year % 4 == 0) && year % 100 != 0)
                || year % 400 == 0);
    }
    //return true for valid date
    static boolean isValidDate(int day, int month, int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            System.out.println("A year must be in range of 1800 to 9999!");
            return false;
        }
        if (month < 1 || month > 12) {
            System.out.println("A month must be in range of 1 to 12!");
            return false;
        }
        if (day < 1 || day > 31) {
            System.out.println("A date must at least be in range of 1 to 31!");
            return false;
        }

        //check for the 2nd month for leap year
        if (month == 2) {
            if (isLeapYear(year)) {
                if (day > 29){
                    System.out.println("A leap-year 2nd month must at least be in range of 1 to 29!");
                }
                return (day <= 29);
            }
            else {
                if (day > 28){
                    System.out.println("A non-leap-year 2nd month must at least be in range of 1 to 28!");
                }
                return (day <= 28);
            }
        }

        //since months with 31 and the 2nd month has been dealt with
        //moving to the 30s
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30){
                System.out.println("The month '\"'" + Integer.toString(month) + "'\"' must at least be in range of 1 to 30!");
            }
            return (day <= 30);
        }
        else {
            //System.out.println("Oh no...");
            return (day < 31);
        }

    }


    public Dates(){
        day = 01;
        month = 01;
        year = MIN_YEAR;
    }
    /*public Dates(int month, int day, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }*/

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public  boolean setDate(int month, int day, int year){
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
            return true;
        }
        return false;
    }


    //to print out the date in mm/dd/yy format
    @Override
    public String toString() {
        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }
    /*
    //to print out the date in dd/mm/yy format
    @Override
    public String toString() {
        return (Integer.toString(day) + '/' + Integer.toString(month) + '/' + Integer.toString(year));
    }
    */
}
