package utils;

import android.graphics.Color;

public class Utils {

	public static int getColorInt(String colorCode) {
		int c = 0;
		if (colorCode != null) {
			c = Color.parseColor(colorCode);

		}
		return c;

	}
}
