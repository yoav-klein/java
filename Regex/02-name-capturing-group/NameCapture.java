import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameCapture {

    public static void main(String[] args){
        Pattern p = Pattern.compile("/tenants/(?<tenantId>.*)");
        Matcher match = p.matcher("/tenants/fdsafatr34343");

        if(match.find()) {
            System.out.println(match.group("tenantId"));
            System.out.println("Found!!");
        }

    }
}