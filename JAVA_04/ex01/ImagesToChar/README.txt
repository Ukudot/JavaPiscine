* DESCRIPTION *
This is a simple program to print with ASCII a BMP black and white image

* IMPORTANT *
The only files accepted are black and white binary BMP

* COMPILATION *
javac -d ./target/edu.42Roma.printer src/java/edu.42Roma.printer/app/App.java src/java/edu.42Roma.printer/logic/*.java

* EXECUTION *
java -cp ./target/edu.42Roma.printer App <white_char> <black_char>

* INFO PARAMS *
white_char: Character used to represent white color
black_char: Character used to represent black color

* CREATE JAR *
~You have to compile before launch the next command~
cp -r src/resources target
jar -cfmv target/images-to-chars-printer.jar ./src/manifest.txt -C target/edu.42Roma.printer . -C target/resources .

* EXECUTE FROM JAR *
java -jar target/images-to-chars-printer.jar <white_char> <black_char>

* INFO PARAMS *
white_char: Character used to represent white color
black_char: Character used to represent black color
