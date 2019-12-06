import org.apache.commons.collections4.BidiMap;

public class Main {




    public static void main(String[] args) {
        Bidimap aux = new Bidimap();
        StringBuilder hello = new StringBuilder();
        hello.append("hello");
        //System.out.println(aux.bidiMap.get(""));
        int[] ind = {0};

        //System.out.println(aux.findMinInt("001111000"));

        System.out.println(aux.translate2Human("000000001101101100111000001111110001111110011111100000001110111111110111011100000001100011111100000111111001111110000000110000110111111110111011100000011011100000000000"));

    }



}
