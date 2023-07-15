package	logic;
import	java.util.List;
import	java.util.Vector;
import	java.io.File;
import	java.io.IOException;
import	java.awt.image.BufferedImage;
import	java.awt.Color;
import	javax.imageio.ImageIO;

public class	Image {
	private	BufferedImage	img;
	private char			white;
	private char			black;
	private int				height;
	private int				width;

	public	Image(char white, char black, String path) throws InvalidInputException {
		this.white = white;
		this.black = black;
		this.img = null;
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new InvalidInputException(e.getMessage());
		}
		if (this.img.getType() != BufferedImage.TYPE_BYTE_BINARY) {
			throw new InvalidInputException("Invalid image type");
		}
		this.height = this.img.getHeight();
		this.width = this.img.getWidth();
	}

	public void	print() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.img.getRGB(j, i) == Color.BLACK.getRGB()) {
					System.out.print(this.black);
				} else {
					System.out.print(this.white);
				}
			}
			System.out.println();
		}
	}
}
