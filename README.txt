Just morse translation, no server tests from Main Class:

File: Bidimap

Considerations taken into account

- For space we decided it would be three spaces in a row.
- It was taken the floor to clasify each interval of bits.
- For time unit it was considered that the average of the shortest interval of '0' and '1' was appropiated.
- It was used a bidimensional map to store the equivalences as function keys -> values is bijective and saves space that is not significant in this case but could be bigger if more characters are coded.



API RUNNING ON SPRING BOOT:

File: meli

Run application to deploy on localhost. Port can be changed inside file application.properties inside resources folder. By default it is set on port 8080.

Api can be tested with google chrome plugin rest client:

To translate from human language to morse code:

Method: POST

Request URL: http://localhost:8080/translate/2Human

HEADER

Header name: Content type
Header value: application/json

BODY

Body content type: application/json
Editor view: Raw input
Body: {"text": ".... --- .-.. .-   -- . .-.. .."}

SUBMIT input

or use curl command as in the example with requested URL --> http://localhost:8080/translate/2Human


API RUNNING ON CLOUD FOUNDRY:

To test we need to do the same test as in local server but changing URL to http://meli.cfapps.io/translate/2Human and without needing to run application.







