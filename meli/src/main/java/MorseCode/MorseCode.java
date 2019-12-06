package MorseCode;

import com.sun.org.apache.xpath.internal.operations.Equals;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Map;
import java.util.Set;

public class MorseCode {


    public BidiMap<String, String> bidiMap; //we know it is bijective so a bidimap is valid
    // we are working in an imperative environment so public is aloud

    public MorseCode(){

        bidiMap = new DualHashBidiMap<>();
        bidiMap.put(".-", "A");
        bidiMap.put("-...", "B");
        bidiMap.put("-.-.", "C");
        bidiMap.put("-..", "D");
        bidiMap.put(".", "E");
        bidiMap.put("..-.", "F");
        bidiMap.put("--.", "G");
        bidiMap.put("....", "H");
        bidiMap.put("..", "I");
        bidiMap.put(".---", "J");
        bidiMap.put("-.-", "K");
        bidiMap.put(".-..", "L");
        bidiMap.put("--", "M");
        bidiMap.put("-.", "N");
        bidiMap.put("---", "O");
        bidiMap.put(".--.", "P");
        bidiMap.put("--.-", "Q");
        bidiMap.put(".-.", "R");
        bidiMap.put("...", "S");
        bidiMap.put("-", "T");
        bidiMap.put("..-", "U");
        bidiMap.put("...-", "V");
        bidiMap.put(".--", "W");
        bidiMap.put("-..-", "X");
        bidiMap.put("-.--", "Y");
        bidiMap.put("--..", "Z");
        bidiMap.put("-----", "0");
        bidiMap.put(".----", "1");
        bidiMap.put("..---", "2");
        bidiMap.put("...--", "3");
        bidiMap.put("....-", "4");
        bidiMap.put(".....", "5");
        bidiMap.put("-....", "6");
        bidiMap.put("--...", "7");
        bidiMap.put("---..", "8");
        bidiMap.put("----.", "9");
        bidiMap.put(" ", " ");
        bidiMap.put(".-.-.-", ".");


    }


    //code might look repetitive but their edge cases are too different to be modularized and coding an aux function just to initialize vars is too much


    public String parse2Human(String toParse){
        StringBuilder builder = new StringBuilder(); //this is where the real string is going to be stored

        boolean dontCheck = false; //if there is a space after a space we need to parse it as a space in the result
        StringBuilder token = new StringBuilder(); //this is used to get token by token

        int index = 0;

        while (toParse.charAt(index) == ' '){
            index++;
        }

        for ( ; index < toParse.length() - 1; index++){


            if (!dontCheck && toParse.charAt(index) == ' '){

                dontCheck = true;
                builder.append(bidiMap.get(token.toString())); //puts the corresponding char on the string builder

                token.delete(0, token.length()); //clear the token to start a new one

                index++;



            }

            token.append(toParse.substring(index, index + 1));

            dontCheck = false;
        }

        if(!(token.equals("") || token.equals("EOF") || token.equals(" ")) )
        builder.append(bidiMap.get(token.toString())); //last input





        return builder.toString();


    }







    private String parse2Morse(String toParse){
        StringBuilder builder = new StringBuilder(); //this is where the real string is going to be stored
        StringBuilder token = new StringBuilder(); //this is used to get token by token
        for (int index = 0 ; index < toParse.length(); index++){
            builder.append(bidiMap.inverseBidiMap().get(toParse.substring(index, index + 1))); //could not find a better way to get the string from an index
            if(index != toParse.length() - 1) // we do not want a space between end and last symbol
            builder.append(" "); //after every letter there is a space

        }

        return builder.toString();
    }


    public String translate2Human(String toDecode){

        Double min = findMinInt(toDecode);

        String morse = parseBits2Morse(min, toDecode);
        String result;
        result = parse2Human(morse);

        return result;
    }




    public String translate2Morse(String toDecode){

        String aux = toDecode.toUpperCase();

        String result;
        result = parse2Morse(aux);


        return result;
    }



    private String parseBits2Morse(Double unit, String bits){

        StringBuilder builder = new StringBuilder();
        int index = 0;

        StringBuilder token = new StringBuilder();


        while (index < bits.length() - 1){

            token.append(bits.substring(index, index + 1));

            if(bits.charAt(index) != bits.charAt(index + 1)){

                builder.append(parseBits2Token(token.length(), unit, bits.charAt(index)));
                token.delete(0, token.length());

            }



            index++;
        }

        if(!token.equals(""))
        builder.append(parseBits2Token(token.length(), unit, bits.charAt(index)));


        return builder.toString();

    }


    private String parseBits2Token(Integer bitsNum, Double unit, char num){



        // we have to work on intervals
        //we are taking the half between the

        if(num == '0'){
            if(bitsNum <= unit * 2){ // we have to take a decision on where to make the cut as we took the min interval, it is better to take a high cut
                return "";
            }
            else if(bitsNum <= unit * 5){
                return " ";
            }
            else {
                return "  ";
            }
        }
        else {
            if(bitsNum <= unit * 2){
                return ".";
            }
            else {
                return "-";
            }
        }

    }









    // we need to find the length of a time unit and we take the minimum because it scales to fast if we take "the maximum of the minimums"
    // it has a weakness if user only inputs dots or dashes we have to choose which one we are referring to
    private Double findMinInt(String code){


        int min0 = minimumIntFromBit('0', code);

        int min1 = minimumIntFromBit('1', code);




        Double avgMin = (Double.valueOf(min0) + Double.valueOf(min1))/2;


        return avgMin;





    }


    private Integer minimumIntFromBit(char bit, String code){

        Integer min = 1000000000; //we put an absurd number to compare with the first length

        int length;

        int index = 0;

        char op;
        if(bit == '0'){
            op = '1';
        }
        else {
            op = '0';
        }



        while (code.charAt(index) != bit){
            index ++;
        }

        length = index; //we need to do this the first time because of indexes in arrays


        while (index < code.length() - 1){ // we will check the last interval outside this while sentence

            if(code.charAt(index) == op){

                length = index - length;

                min = (min < length) ? min : length;
                while (index <code.length() && code.charAt(index) != bit){
                    index++;
                }

                length = index;
            }
            else {
                index++;
            }
        }

        if(code.charAt(code.length() - 1) == bit){

            length = index - length + 1;
            min = (min < length) ? min : length;
        }


        return min;


    }





}
