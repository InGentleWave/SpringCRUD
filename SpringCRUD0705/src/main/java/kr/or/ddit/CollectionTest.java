package kr.or.ddit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionTest {

	public static void main(String[] args) {
//		list1();
//		list2();
//		set1();
//		set2();
		map1();
//		map2();
	}

	public static <T> void printList(int index, List<T> list) {
		System.out.println(index + ")-------------------------------");
		if (list.size() == 0) {
			System.out.println("list가 비어있습니다.");
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i + 1 + "번째 인자 : " + list.get(i));
			}
		}
	}
	public static <T> void printSet(int index, Set<T> set,String type) {
		System.out.println(index + ")-------------------------------");
		if (set.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<T> ite = set.iterator();
			while(ite.hasNext()) {
				switch(type) {
				case "n":
					int no = (int)ite.next();
					System.out.println(no);
					break;
				case "s":
					String str = (String)ite.next();
					System.out.println();
				}
			}
		}
	}

	public static void list1() {
		List<Object> list = new ArrayList<>();

		// ### COLLECTION TEST
		// ### Collection의 종류로는 List, Set, Map이 있다.
		// ### Collection의 종류를 자유자재로 활용할 수 있다면 당신에게 CRUD는 식은죽 먹기다!
		// ### 자! 그럼 Collection 관련 문제 100문제를 풀고 Collection 마스터가 되어보자!

		// List 문제
		// 1) 모든 타입의 데이터를 담을 List를 만들어주세요. (변수명은 list로 작성하세요.)

		// 2) list에 데이터 5개를 넣어주세요. (각각 다른 타입의 데이터를 넣어주세요.)
		list.add("1");
		list.add(2);
		list.add(true);
		list.add('A');
		String[] strArray = { "cat", "dog" };
		list.add(strArray);
		// 3) list에 등록된 데이터 모두를 출력해주세요.
		printList(3, list);
		// 4) list에 index가 짝수번째인 데이터 모두를 "짝수"라는 문자열로 변경해주세요.
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 0)
				continue;
			list.set(i, "짝수");
		}
		// 5) list에 등록된 데이터 모두를 출력해주세요.
		printList(5, list);
		// 6) list에 등록된 데이터 중, index 3번째에 해당하는 데이터를 삭제해주세요.
		list.remove(2);
		// 7) 현재 등록되어 있는 list의 사이즈를 출력해주세요.
		System.out.println("7)-------------------------------");
		System.out.println("list의 사이즈 : " + list.size());
		// 8) list의 "추가"라는 문자열 데이터를 추가해주세요.
		list.add("추가");
		// 9) list의 등록된 데이터 모두를 출력해주세요.
		printList(9, list);
		// 10) list의 index 3번째에 해당하는 값을 출력해주세요.
		System.out.println("10)-------------------------------");
		System.out.println("list index의 3번째에 해당하는 값 : " + list.get(2));
		// 11) list의 index가 홀수번째인 데이터를 null로 수정해주세요.
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 0) {
				list.set(i, null);
			}
		}

		// 12) list의 등록된 데이터 모두를 출력해주세요.
		printList(12, list);
		// 13) list의 데이터들 중, 두번째 null 데이터가 포함되어 있는 index를 출력해주세요.
		System.out.println("13)-------------------------------");
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null)
				j++;
			if (j == 2) {
				System.out.println("두번째 null 데이터가 포함된 index : " + i);
				break;
			}
		}
		// 14) list의 모든 데이터를 삭제해주세요.
		list.clear();
		// 15) list의 등록된 데이터 모두를 출력해주세요.
		printList(15, list);
		// 16) list의 사이즈를 출력해주세요.
		System.out.println("16)-------------------------------");
		System.out.println("list의 사이즈 : " + list.size());
		// 17) list의 데이터를 2개 추가해주세요.
		list.add("1번 데이터");
		list.add("2번 데이터");
		// 18) list의 index 0,2번째에 데이터를 넣어주세요.
		list.set(0, "인덱스 0의 데이터");
		list.add("");
		list.set(2, "인덱스 2의 데이터");
		// 19) list의 마지막 데이터를 삭제해주세요.
		list.remove(list.size() - 1);
		// 20) list의 등록된 데이터 모두를 출력해주세요.
		printList(20, list);
	}

	public static void list2() {
		// 21) list의 타입을 문자열만 담을 수 있는 ArrayList로 생성해주세요.
		List<String> list = new ArrayList<>();
		// 22) list의 5개의 데이터를 넣어주세요.
		for (int i = 0; i < 5; i++) {
			list.add("인덱스" + i + ", " + (i + 1) + "번 데이터");
		}
		// 23) list의 짝수번째 데이터를 '(짝수)'라는 문자와 합쳐서 넣어주세요.
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 1) {
				list.set(i, "(짝수)" + list.get(i));
			}
		}
		// ex ) (짝수)1, (짝수)3, (짝수)5
		// 24) list의 모든 데이터를 출력해주세요.
		printList(24, list);
		// 25) list의 index가 홀수번째인 데이터 모두를 삭제해주세요.
		for (int i = list.size() - 1; i >= 0; i--) {
			if (i % 2 == 0) {
				list.remove(i);
			} else {
				continue;
			}
		}
		// 26) list의 마지막 index의 데이터를 출력해주세요.
		System.out.println("26)--------------------------");
		System.out.println("list의 마지막 index"+(list.size()-1)+"의 데이터 : "+list.get(list.size()-1));
		// 27) list의 본인의 이름을 추가해주세요.
		list.add("김인섭");
		// 28) list의 본인 이름이 추가된 index를 출력해주세요.
		System.out.println("28)--------------------------");
		System.out.println("본인 이름이 포함된 인덱스 : "+list.indexOf("김인섭"));
		// 29) list의 모든 데이터를 삭제하고,
		// index 0번째는 '#본인의 이름'
		// index 1번째는 '#열심히 공부하자'
		// index 2번째는 '#열심히 연습하자' 를 추가해주세요.
		list.clear();
		list.add("#김인섭");
		list.add("#열심히 공부하자");
		list.add("#열심히 연습하자");
		// 30) list의 모든 데이터를 출력해주세요!
		printList(30,list);
		// 31) List는 인터페이스인가요? 클래스인가요? (출력문으로 정답을 출력하세요.)
		System.out.println("31)--------------------------");
		System.out.println("List는 인터페이스");
		// 32) List의 종류는 어떤것들이 있나요? (출력문으로 정답을 출력하세요.)
		System.out.println("32)--------------------------");
		System.out.println("List 종류 : ArrayList, LinkedList, Vector, Stack, CopyOnWriteArrayList");
		// 33) 문자열을 담을 수 있고 변수명 list1으로 설정된 List를 만들어주세요.
		// (변수 선언 및 초기화를 통한 인스턴스를 만들어주세요)
		List<String> list1 = new ArrayList<>();
		// 34) 33번에서 만든 list1에 306호 학생의 이름을 넣어주세요
		String names = "김경근 고준수 이하은 홍진호 신의용 오형진 김진영 윤미루 안주영 김예빈 추승완 서강빈 이재석 박영하 이우현 김인섭 김소희 김지후 송욱진 이유진 안철호 김형준 이상현";
		String[] nameArr = names.split(" ");
		for(int i=0;i<nameArr.length;i++) {
			list1.add(i+1+"번째 "+nameArr[i]);
		}
		// 35) list1에 담긴 이름 전부를 출력해주세요.
		printList(35, list1);
		// 36) list1에 담긴 이름들 중, index가 홀수번째인 이름에 '-홀수'라는 텍스트를 추가해주세요.
		// ex) 홍길동-홀수
		for(int i=0;i<list1.size();i++) {
			if(i%2==0) {
				list1.set(i, list1.get(i)+"-홀수");
			}
		}
		// 37) list1에 담긴 이름들 중, index가 짝수번째이고 index가 5이상인 이름의 '-짝수'라는 텍스트를 추가해주세요.
		// ex) 홍길동-짝수
		for(int i=0;i<list1.size();i++) {
			if(i%2==1&&i>5) {
				list1.set(i, list1.get(i)+"-짝수");
			}
		}
		// 38) list1에 index 4번재 학생의 이름을 '홍길동'으로 수정해주세요
		list1.set(3, "홍길동");
		// 39) list1에 index 1번재 학생의 이름을 아무것도 없는 null로 수정해주세요.
		list1.set(0, null);
		// 40) list1에 들어있는 모든 데이터를 출력해주세요.
		printList(40, list1);
		// 41) list1에 들어있는 데이터들 중, index 6번째에 해당하는 학생의 이름을 삭제해주세요.
		list1.remove(5);
		printList(41, list1);
		// 42) list1에 들어있는 데이터들 중, index 1번째에 해당하는 학생의 이름을 삭제해주세요.
		list1.remove(0);
		printList(42, list1);
		// 43) list1에 들어있는 데이터들 중, 첫번째 index에 해당하는 학생의 이름을 삭제하고, 마지막 index에 해당하는 학생의 이름을
		// 삭제해주세요.
		list1.remove(0);
		list1.remove(list1.size()-1);
		printList(43, list1);
		// 44) list1에 index 5번재에 새로운 학생의 이름인 '전학생'을 추가해주세요.
		list1.add("");
		printList(44, list1);
		for(int i=list1.size()-1;i>=5;i--) {
			list1.set(i, list1.get(i-1));
		}
		printList(44, list1);
		list1.set(4, "전학생");
		printList(44, list1);
		// 45) list1에 index 7번째에 새로운 학생의 이름인 '전학생2'를 추가해주세요.
		list1.add("");
		for(int i=list1.size()-1;i>=7;i--) {
			list1.set(i, list1.get(i-1));
		}
		list1.set(6, "전학생2");
		printList(45, list1);
		// 46) list1에 들어있는 모든 데이터를 출력해주세요.
		printList(46, list1);
	}
	public static void set1() {
		// Set 문제
		// 47) 숫자를 담을 수 있는 Set을 만들어주세요.
		Set<Integer> set1 = new HashSet<>(); 
		// 48) 1-9까지의 숫자를 추가해주세요.
		for(int i=1;i<=9;i++) set1.add(i);
		// 49) set에 담긴 모든 데이터를 출력해주세요.
		System.out.println("49)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					System.out.println(no);
			}
		}
		// 50) set에 담긴 데이터들 중, 짝수 값에 해당하는 값을 출력해주세요.
		System.out.println("50)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					if(no%2==0)	System.out.println(no);
			}
		}
		// 51) set에 담긴 데이터들 중, 홀수 값에 해당하는 값을 출력해주세요.
		System.out.println("51)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					if(no%2==1)System.out.println(no);
			}
		}
		// 52) set에 담긴 데이터들 중, 짝수 값에 +10을 더해주세요.
		System.out.println("52)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			List<Integer> ten = new ArrayList<>();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					System.out.println("no : "+no);
					if(no%2==0) {
						ite.remove();
						ten.add(no+10);
						System.out.println("+10");
					}
			}
			for(int num : ten) {
				set1.add(num);
			}
		}
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					System.out.println(no);
			}
		}
		// 53) set에 담긴 데이터 모두를 다 더한 값을 출력해주세요.
		System.out.println("53)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			int sum = 0;
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					sum += (int)ite.next();
					
			}
			System.out.println("set에 담긴 데이터 모두를 다 더한 값 : "+sum);
		}
		// 54) set에 담긴 데이터들 중, 홀수 값에 해당하는 데이터를 삭제해주세요.
		System.out.println("54)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					if(no%2==1) {
						ite.remove();
					}

			}
		}
		// 55) set에 담긴 데이터 모두를 출력해주세요.
		System.out.println("55)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					System.out.println(no);
			}
		}
		// 56) set에 담긴 데이터들 중, 5-10범위 안에 있는 데이터를 출력해주세요.
		System.out.println("56)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					if(no>=5&&no<=10) {
						System.out.println(no);
					}

			}
		}
		// 57) set에 담긴 데이터의 마지막 데이터를 삭제해주세요.
		System.out.println("57)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			int no = 0;
			while(ite.hasNext()) {
					no = (int)ite.next();
					System.out.println("no : "+no);
			}
			System.out.println("지워질 값 : "+no);
			set1.remove(no);
		}
		// 58) set에 담긴 데이터 모두를 출력해주세요.
		System.out.println("58)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<Integer> ite = set1.iterator();
			while(ite.hasNext()) {
					int no = (int)ite.next();
					System.out.println(no);
			}
		}
	}
	public static void set2() {
		// 59) set에 타입을 문자열을 담을 수 있는 Set으로 생성해주세요.
		Set<String> set2 = new HashSet<>();
		// 60) set에 306호 학생 이름 모두를 넣어주세요.
		String names = "김경근 고준수 이하은 홍진호 신의용 오형진 김진영 윤미루 안주영 김예빈 추승완 서강빈 이재석 박영하 이우현 김인섭 김소희 김지후 송욱진 이유진 안철호 김형준 이상현";
		String[] nameArr = names.split(" ");
		for(int i=0;i<nameArr.length;i++) {
			set2.add(i+1+"번째 "+nameArr[i]);
		}
		// 61) set에 넣은 학생이름 모두를 출력해주세요.
		System.out.println("61)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					System.out.println(str);
			}
		}
		// 62) set에 넣은 학생이름들 중, 본인 이름의 데이터를 '본인'으로 수정해주세요.
		System.out.println("62)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			boolean flag = false;
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.contains("김인섭")) {
						ite.remove();
						str="본인";
					}
					System.out.println(str);
			}
			if(flag) {
				set2.add("본인");
			}
		}
		// 63) set에 넣은 학생이름들 중, '김'씨로 시작하는 학생을 출력해주세요.
		System.out.println("63)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.split(" ")[1].startsWith("김")) {
						System.out.println(str);
					}
			}
		}
		// 64) set에 넣은 학생이름들 중, '김'씨로 시작하는 학생 데이터를 삭제해주세요.
		System.out.println("64)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.split(" ")[1].startsWith("김")) {
						ite.remove();
					}
			}
		}
		// 65) set에 넣은 학생이름들 중, '본인'을 삭제해주세요.
		System.out.println("65)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.equals("본인")) {
						ite.remove();
					}
			}
		}
		// 66) set에 담긴 모든 데이터를 출력해주세요.
		System.out.println("66)-------------------------------");
		if (set2.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set2.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					System.out.println(str);
			}
		}
		// 67) Set은 인터페이스인가요? 클래스인가요? (출력문으로 출력해주세요.)
		System.out.println("Set은 인터페이스!");
		// 68) Set의 종류는 어떤것들이 있나요? (출력문으로 출력해주세요.)
		System.out.println("Set에는 HashSet, TreeSet, LinkedHashSet이 있음");
		// 69) 문자열을 담을 수 있고 변수명 set1으로 설정된 Set을 만들어주세요.
		// (변수선언 및 초기화를 통한 인스턴스를 만들어주세요.)
		Set<String> set1 = new HashSet<>();
		// 70) 69번에서 만든 set1에 무지개색깔을 추가해주세요. (한글로)
		set1.add("빨");
		set1.add("주");
		set1.add("노");
		set1.add("초");
		set1.add("파");
		set1.add("남");
		set1.add("보");
		// 71) set1에 들어있는 모든 색깔을 출력해주세요.
		System.out.println("71)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					System.out.println(str);
			}
		}
		// 72) set1에 들어있는 모든 색깔을 출력 시, 파랑 색깔인 경우 'blue'를 출력해주세요.
		System.out.println("72)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.equals("파")) {
						str="blue";
					}
					System.out.println(str);
			}
		}
		// 73) set1에 들어있는 무지개 색깔 중, 빨강색의 값을 'red'로 수정해주세요.
		System.out.println("73)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			boolean flag = false;
			while(ite.hasNext()) {
					String str= (String)ite.next();
					if(str.equals("빨")) {
						ite.remove();
						flag = true;
					}
			}
			if(flag) {
				set1.add("red");
			}
		}
		// 74) set1에 들어있는 무지개 색깔 중, 초록색의 값을 'green'으로 수정해주세요.
		System.out.println("74)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			boolean flag = false;
			while(ite.hasNext()) {
				String str= (String)ite.next();
				if(str.equals("초")) {
					ite.remove();
					flag = true;
				}
			}
			if(flag) {
				set1.add("green");
			}
		}
		// 75) set1에 들어있는 무지개 색깔 중, 남색, 보라색을 삭제해주세요.
		System.out.println("75)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			while(ite.hasNext()) {
				String str= (String)ite.next();
				if(str.equals("남")||str.equals("보")) {
					ite.remove();
				}
			}
		}
		// 76) set1에 들어있는 모든 색깔을 출력해주세요.
		System.out.println("76)-------------------------------");
		if (set1.size() == 0) {
			System.out.println("set가 비어있습니다.");
		} else {
			// 출력
			Iterator<String> ite = set1.iterator();
			while(ite.hasNext()) {
					String str= (String)ite.next();
					System.out.println(str);
			}
		}
	}
	public static void map1() {
		// Map 문제
		// 77) Map은 인터페이스인가요? 클래스인가요? (출력문으로 출력해주세요.)
		System.out.println("Map은 인터페이스!");
		// 78) Map의 종류는 어떤것들이 있나요? (출력문으로 출력해주세요.)
		System.out.println("Map의 종류에는 HashMap, TreeMap, LinkedHashMap, HashTable, CurrentHashMap이 있음");
		// 79) 문자열을 담음 수 있고 변수명 map1으로 설정된 Map을 만들어주세요.
		Map<String,String> map1 = new HashMap<>();
		// 80) map1에 좋아하는 동물 5마리를 넣어주세요.
		map1.put("1번", "개");
		map1.put("2번", "고양이");
		map1.put("3번", "소");
		map1.put("4번", "닭");
		map1.put("5번", "돼지");
		// 81) map1에 좋아하는 동물 5마리 중, 2마리만 꺼내서 출력해주세요.
		System.out.println("81)-------------------------------");
		System.out.println(map1.get("1번"));
		System.out.println(map1.get("2번"));
		// 82) map1에 좋아하는 동물 5마리 중, 첫번째로 넣었던 동물을 '사육사'로 변경해주세요.
		map1.put("1번", "사육사");
		// 83) map1에 들어있는 동물 5마리 중, 좋아하는 우선순위가 가장 낮은 동물을 '본인의 이름'으로 변경해주세요.
		map1.put("5번", "김인섭");
		// 84) map1에 들어있는 모든 데이터를 출력해주세요. get() 활용
		System.out.println("84)-------------------------------");
		for(int i=1;i<6;i++) {
			System.out.println(map1.get(i+"번"));
		}
		// 85) map1에 들어있는 모든 데이터를 keySet()을 이용해서 출력해주세요.
		// 86) BoardVO 클래스를 만들어주세요.
		// BoardVO 클래스는 총 3가지의 필드를 가지고 있습니다.
		// 글번호 : boNo
		// 글제목 : boTitle
		// 글내용 : boContent
	}
	public static void map2() {
		// 87) BoardVO 타입의 데이터를 넣을 수 있는 Map타입의 변수명 map2를 만들어주세요.
		// 88) map2에 게시글 정보 3개를 넣어주세요.
		// 89) map2에 두번째로 넣은 게시글 정보를 꺼내서 출력해주세요.
		// 글번호 : 2
		// 글제목 : 제목입니다2
		// 글내용 : 내용입니다2
		// 90) map2에 두번재로 넣은 게시글 정보를 삭제해주세요.
		// 91) map2에 들어있는 모든 데이터를 entrySet()을 이용하여 출력해주세요.
		// 92) map2에 첫번째로 넣은 게시글정보의 제목을 "첫번째 게시글입니다"로 수정해주세요.
		// 93) map2에 방금 수정한 게시글 정보를 출력해주세요.
		// 94) map2에 마지막 게시글정보로 넣은 BoardVO의 내용을 '내용을 수정합니다'로 수정해주세요.
		// 95) map2에 마지막 게시글 정보를 출력해주세요.

	}
}
