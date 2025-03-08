package model;

public enum Rating {
	ONE, TWO, THREE, FOUR, FIVE;

	// @pre rating >= 1, rating <= 5
	public static Rating getRating(int rating) {
		if (rating == 5) {
			return Rating.FIVE;
		}
		else if (rating == 4) {
			return Rating.FOUR;
		}
		else if (rating == 3) {
			return Rating.THREE;
		}
		else if (rating == 2) {
			return Rating.TWO;
		}
		else return Rating.ONE;
	}
}
