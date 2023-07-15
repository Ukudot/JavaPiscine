package	logic;
import	java.util.List;
import	java.util.Vector;
import	java.io.File;
import	java.io.IOException;
import	java.awt.image.BufferedImage;
import	java.awt.Color;
import	javax.imageio.ImageIO;
import	com.diogonunes.jcolor.Attribute;
import	com.diogonunes.jcolor.Ansi;

public class	Image {
	private	BufferedImage	img;
	private int				white[];
	private int				black[];
	private int				height;
	private int				width;

	public	Image(String white, String black, String path) throws InvalidInputException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		Color	wh;
		Color	bl;

		wh = (Color) Class.forName("java.awt.Color").getField(white).get(null);
		bl = (Color) Class.forName("java.awt.Color").getField(black).get(null);
		this.white = new int[3];
		this.black = new int[3];
		this.white[0] = wh.getRed();
		this.white[1] = wh.getGreen();
		this.white[2] = wh.getBlue();
		this.black[0] = bl.getRed();
		this.black[1] = bl.getGreen();
		this.black[2] = bl.getBlue();
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
					System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(this.black[0], this.black[1], this.black[2])));
				} else {
					System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(this.white[0], this.white[1], this.white[2])));
				}
			}
			System.out.println();
		}
	}
}
