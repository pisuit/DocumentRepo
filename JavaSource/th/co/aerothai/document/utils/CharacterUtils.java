package th.co.aerothai.document.utils;

public class CharacterUtils {
	public static String Unicode2ASCII(String unicode) {
		StringBuffer ascii = new StringBuffer(unicode);
		int code;
		for (int i = 0; i < unicode.length(); i++) {
			code = (int) unicode.charAt(i);
			if ((0xE01 <= code) && (code <= 0xE5B))
				ascii.setCharAt(i, (char) (code - 0xD60));
		}
		return ascii.toString();
	}
}
