//A node stored in the creatures arraylist. Has a "name" and an int data value.
public class VVBNode {
	public int data;
	public String name = "";
	public VVBNode(int data, String name) {
		this.data = data;
		this.name = name;
	}
	public VVBNode(VVBNode copy) {
		this.data = copy.data;
		this.name = new String(copy.name);
	}
}
