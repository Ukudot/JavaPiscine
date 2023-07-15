* DESCRIPTION *
This is a simple program to print with ASCII a BMP black and white image

* COMPILATION *
javac --release 8 -d ./target/edu.42Roma.printer src/java/edu.42Roma.printer/*/*.java

* EXECUTION *
cp -r src/resources target
java -cp ./target/edu.42Roma.printer app.App <white_char> <black_char>

* ARCHIVE ASSSEMBLY *
javac --release 8 -d ./target/edu.42Roma.printer src/java/edu.42Roma.printer/*/*.java
cp -r src/resources target
jar -cfmv target/images-to-chars-printer.jar ./src/manifest.txt target/resources/it.bmp -C target/edu.42Roma.printer .

* ARCHIVE STARTUP *
java -jar target/images-to-chars-printer.jar <white_char> <black_char>

* ARCHIVE DECOMPRESS *
cp target/images-to-chars-printer.jar <new folder> ;cd <new folder>
jar -xf images-to-chars-printer.jar
~if you want to run the program launch the next command~
java app.App

* INFO PARAMS *
white_char: Character used to represent white color
black_char: Character used to represent black color
