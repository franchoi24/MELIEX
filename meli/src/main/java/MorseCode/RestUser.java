package MorseCode;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/translate")
public class RestUser {

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/2Morse",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    MorseResponse toMorse(@RequestBody MorseResponse string) {
        MorseCode morseCode = new MorseCode();
        return new MorseResponse(morseCode.translate2Morse(string.getText()));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/2Human",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    MorseResponse toHuman(@RequestBody MorseResponse string) {
        MorseCode morseCode = new MorseCode();
        return new MorseResponse(morseCode.parse2Human(string.getText()));
    }


}



