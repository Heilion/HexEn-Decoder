import java.math.*;

public class Decoder {

	public static void main(String[] args) {
		System.out.println(translateTextToHex("definitely not the message"));
		System.out.println(translateHexToText("214c9726856640e7d0144140d2ce61394"));
	}

	static public String translateHexToText(String str) {
		String hexBinaryString = hexStringToBinary(str);
		return binaryToText(hexBinaryString);
	}
	
	static public String translateTextToHex(String str) {
		String textBinaryString = textToBinaryString(str);
		return binaryToHexString(textBinaryString);
	}
	
	static public String hexStringToBinary(String str) {
		String binaryString = "";
		for (int i = 0; i < str.length(); i++) {
			binaryString += hexCharToBinaryString(str.charAt(i));
		}
		return binaryString;
	}

	static public String binaryToText(String binary) {
		String textString = "";
		for (int i = 0; i < binary.length(); i += 5) {
			int charInd = 0;
			for (int j = 0; j < 5; j++) {
				try {
					charInd += (binary.charAt(i + j) - 48)
							* (int) (Math.pow(2, 4 - j));
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}
			textString += (charInd == 0) ? ' ' : (char) (96 + charInd);
		}
		return textString;
	}

	static public String hexCharToBinaryString(char c) {
		int charIndex = (c > 96) ? c - 87 : c - 48;
		String str = "";
		for (int i = 3; i >= 0; i--) {
			if (charIndex / (int) (Math.pow(2, i)) == 1) {
				str += "1";
				charIndex -= (int) (Math.pow(2, i));
			} else {
				str += "0";
			}
		}
		return str;
	}

	static public String textToBinaryString(String text) {
		String binaryString = "";
		for (int i = 0; i < text.length(); i++) {
			binaryString += textCharToBinaryString(text.charAt(i));
		}
		return binaryString;
	}

	static public String textCharToBinaryString(char c) {
		int charIndex = (c == ' ') ? 0 : (c - 97) + 1;
		String binaryString = "";
		for (int i = 4; i >= 0; i--) {
			if (charIndex / (int) (Math.pow(2, i)) == 1) {
				binaryString += "1";
				charIndex -= (int) (Math.pow(2, i));
			} else {
				binaryString += "0";
			}
		}
		return binaryString;
	}

	static public String binaryToHexString(String binaryString) {
		String hexString = "";
		for (int i = 0; i < binaryString.length(); i += 4) {
			int binaryInd = 0;
			for (int j = 0; j < 4; j++) {
				try {
					if (binaryString.charAt(i + j) == '1') {
						binaryInd += (int) (Math.pow(2, 3 - j));
					}
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}
			hexString += binaryToHex(binaryInd);
		}
		return hexString;
	}

	static public char binaryToHex(int binaryInd) {
		return (binaryInd < 10) ? (char) (binaryInd + 48)
				: (char) (binaryInd + 87);
	}
}
