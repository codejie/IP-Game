package jie.android.ip.utils;

public class Holder<T> {
	
	private T value;
	
	public Holder(T value) {
		this.setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
