* DESCRIPTION *
This is a simple program to print a BMP black and white image with other colors

* COMPILATION *
mkdir lib
curl https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar --output lib/JColor-5.5.1.jar
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar --output lib/jcommander-1.82.jar
javac --release 8 -cp "lib/JColor-5.5.1.jar:lib/jcommander-1.82.jar" -d ./target/edu.42Roma.printer src/java/edu.42Roma.printer/*/*.java

* EXECUTION *
cp -r src/resources target
jar -xf lib/JColor-5.5.1.jar com
mv com target
jar -xf lib/jcommander-1.82.jar com
mv com/beust target/com
rm -rf com
java -cp "target/edu.42Roma.printer/:target/" app.App --white=<color> --black=<color>

* ARCHIVE ASSSEMBLY *
mkdir lib
curl https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar --output lib/JColor-5.5.1.jar
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar --output lib/jcommander-1.82.jar
javac --release 8 -cp "lib/JColor-5.5.1.jar:lib/jcommander-1.82.jar" -d ./target/edu.42Roma.printer src/java/edu.42Roma.printer/*/*.java
cp -r src/resources target
jar -xf lib/JColor-5.5.1.jar com; mv com target; jar -xf lib/jcommander-1.82.jar com; mv com/beust target/com; rm -rf com
jar -cfmv target/images-to-chars-printer.jar ./src/manifest.txt -C . target/resources -C target com -C target/edu.42Roma.printer .

* ARCHIVE STARTUP *
java -jar target/images-to-chars-printer.jar --white=<color> --black=<color>

* ARCHIVE DECOMPRESS *
cp target/images-to-chars-printer.jar <new folder> ;cd <new folder>
jar -xf images-to-chars-printer.jar
~if you want to run the program launch the next command~
java app.App --white=<color> --black=<color>

* INFO PARAMS *
--white: Color used instead of white
--black: Color used instead of black

* COLOR ACCEPTED *
RED (red)
GREEN (green)
BLUE (blue)
MAGENTA (magenta)
CYAN (cyan)
YELLOW (yellow)
BLACK (black)
WHITE (white)
GRAY (gray)
DARK_GRAY (dark_gray)
LIGHT_GRAY (light_gray)
ORANGE (orange)
PINK (pink)
