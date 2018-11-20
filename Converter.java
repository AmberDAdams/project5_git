package project5;

import java.util.Arrays;
import java.util.ArrayList;

public class Converter {
	private Converter(){}
	
	private static Character[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	private static ArrayList<Character> hexList = new ArrayList<Character>(Arrays.asList(hexArray));
	
	public static int binaryToDecimal(String binary) {
		if (!binary.substring(0,2).equals("0b")) return 0;
		if (binary.length()>33 || binary.length()<3) return 0;
		return binaryToDecimalHelper(binary.substring(2));
	}
	
	public static int binaryToDecimalHelper(String binary) {
		if (binary.length()==1) return (binary.charAt(0) - '0');
		String nextBinary = binary.substring(1);
		return  binaryToDecimalHelper(nextBinary) +  (binary.charAt(0) - '0') * ( 1 << nextBinary.length());
	}
	
	public static String binaryToHex(String binary) {
		if (!binary.substring(0,2).equals("0b")) return null;
		if (binary.length()>33 || binary.length()<3) return null;
		for (int i = 2; i < binary.length(); i++){
			char thisChar = binary.charAt(i);
			if (thisChar != '1' && thisChar !='0')
				return null;
		}
		binary = binary.substring(2);
		while (binary.length()%4!=0) 
			binary = "0" + binary;
		return "0x"+binaryToHexHelper(binary);
	}
	
	private static String binaryToHexHelper(String binary) {
		if (binary.length()==0) return "";
		return binaryToHexHelper(binary.substring(0,binary.length()-4)) + hexArray[(char) binaryToDecimalHelper(binary.substring(binary.length()-4))];
	}
	
	public static String decimalToBinary(int decimal) {
		return "0b"+decimalToBinaryHelper(decimal);
	}
	
	public static String decimalToBinaryHelper(int decimal) {
		if (decimal==0) return "";
		return decimalToBinaryHelper(decimal/2)+decimal%2;
	}
	
	public static String hexToBinary(String hex) {
		if (!hex.substring(0,2).equals("0x")) return null;
		return "0b"+hexToBinaryHelper(hex.substring(2));
	}
	
	public static String hexToBinaryHelper(String hex) {
		if (hex.length()==0) return "";
		String binary = decimalToBinaryHelper(hexList.indexOf(hex.charAt(0)));
		while (binary.length()%4!=0) 
			binary = "0" + binary;
		return binary + hexToBinaryHelper(hex.substring(1));
	}	
	
}