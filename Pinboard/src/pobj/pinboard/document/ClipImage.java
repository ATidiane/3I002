package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ClipImage extends AbstractClip implements Clip {

	private Image img;
	private File filename;
	
	public ClipImage(double left, double top, File filename) {
		super(left, top, 0, 0, null);
		this.filename = filename;
		
		img = new Image("file://" + filename.getAbsolutePath());
		double right = img.getWidth() + left;
		double bottom = img.getHeight() + top;
		setGeometry(left, top, right, bottom);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(img, getLeft(), getTop());
	}

	@Override
	public Clip copy() {
		return new ClipImage(getLeft(), getTop(), filename);
	}

}
