import org.apache.commons.collections4.BidiMap;

public class Main {




    public static void main(String[] args) {
        Bidimap aux = new Bidimap();
        StringBuilder hello = new StringBuilder();
        hello.append("hello");
        //System.out.println(aux.bidiMap.get(""));
        int[] ind = {0};

        //System.out.println(aux.findMinInt("001111000"));

        System.out.println(aux.translate2Human("10100000001011100011101110000000101110000000101011101000101000101010001010101"));

    }



}
