package runOutOfMemory;


public class RunOutOfMemory {

    public static void main(String[] args) {
        RunOutOfMemoryHelper r = new RunOutOfMemoryHelper();
        System.out.println(r.run());
    }
}

class RunOutOfMemoryHelper {
    String run() {
        String error = "";
        int count = 999999997;
        Boolean bool = true;
        while(bool) {
            count *= 10;
            try {
                StringBuilder sb = new StringBuilder(count);
                for(int i = 0; i < count; i++) {
                    sb.append('*');
                }
                if(count % 1000 == 0) {
                    System.out.println(count);
                }
            } catch (OutOfMemoryError e) {
                error = e.toString();
                return error + ". Ran out of memory with " + count + " characters in the string";
            }
        }
        return "should never end up here";
    }
}
