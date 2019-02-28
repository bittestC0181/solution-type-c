package problem03;

public class MyStack {
	
	private int count = 0;
	private int size;
	private String[] buffer;

	public MyStack( int size ) {
		this.size = size;
		buffer = new String[this.size];
	}
	
	public void push(String item) {
		if ( buffer.length == count ) {
			buffer = resizing();
		}
		buffer[count++] = item;
	}

	public String pop() {
		if ( isEmpty() ) 
			return null;
		else {
			return buffer[--count];
		}
	}

	public boolean isEmpty() {
		if ( count == 0 ) return true;
		return false;
	}
	
	public int size() {
		return this.size;
	}
	
	public String[] resizing() {
		int newSize = this.size*2;
		String[] tmp = new String[newSize];
		for ( int i = 0; i<this.size; i++ ) {
			tmp[i] = buffer[i];
		}
		this.size = newSize;
		return tmp;
	}
	
}