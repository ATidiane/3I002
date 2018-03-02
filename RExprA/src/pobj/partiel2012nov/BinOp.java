package pobj.partiel2012nov;

public class BinOp {
	private final Expression fg, fd;
	
	public BinOp(Expression fg, Expression fd) {
		this.fg = fg;
		this.fd = fd;
	}
	
	public Expression getFg() {
		return fg;
	}
	
	public Expression getFd() {
		return fd;
	}

	@Override
	public String toString() {
		return "BinOp [fg=" + fg + ", fd=" + fd + "]";
	}
	
}
