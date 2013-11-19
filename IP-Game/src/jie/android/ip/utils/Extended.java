package jie.android.ip.utils;

public class Extended {

	public static class Pair<T, U> {
		private final T first;
		private final U second;
		
		public Pair(T first, U second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Pair) {
				Pair<T, U> right = (Pair<T, U>)(obj);
				if (right.first.equals(first) && right.second.equals(second)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int hashCode() {
			return (first != null ? first.hashCode() : 0) ^ (second != null ? second.hashCode() : 0);
		}
		
		public final T first() {
			return first;
		}
		
		public final U second() {
			return second;
		}		
	}
	
}
