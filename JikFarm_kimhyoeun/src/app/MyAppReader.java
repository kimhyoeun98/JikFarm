package app;

import java.util.Scanner;

public class MyAppReader {

	Scanner sc = new Scanner(System.in);
	
	public String readString(String message) {
		System.out.print(message);
		return sc.nextLine().strip();
	}
	
	public int readInt(String message) {
	    while (true) { // 올바른 값이 입력될 때까지 반복
	        try {
	            System.out.print(message);
	            return Integer.parseInt(sc.nextLine().strip());
	        } catch (NumberFormatException e) {
	            System.out.println("오류: 숫자만 입력해주세요.");
	        }
	    }
	}
}