package vn.hue.kienletrung.springboot.FORM;

public class Form {
	private long fromId;
	private long toId;
	private double ammount;
	
	public Form(long fromId, long toId, double ammount) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.ammount = ammount;
	}
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	
	

}
