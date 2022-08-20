package lab5;

public class Recusrion {

	public void subsetSum(int[] array,int index, int sum) {
		/*
		 * Exercise 1
		 * Given set s, print all possible sums of all its subsets.
		 * index and sum start 0
		 */

		if(index == array.length) {
			System.out.print(sum+" ");
			return;
		}

		index++;
		subsetSum(array, index, sum);//dont include current element with sum
		subsetSum(array, index, (sum+array[index-1]));//include current element with sum

	}

	public void noConsecutive1(int k,String s) {
		/*
		 * Exercise 2
		 * Given an integer k, generate all possible binary strings without consecutive 1s
		 * s starts ""
		 */
		if(k==0) {
			System.out.print(s+" ");
			return;}
		else {
			k--;
			if(s.equals("")) {
				noConsecutive1(k, "0");
				noConsecutive1(k, "1");
			}else
				if(s.charAt(s.length()-1)=='1') {
					noConsecutive1(k, s+"0");
				}else {
					noConsecutive1(k, s+"0");
					noConsecutive1(k, s+"1");
				}

		}

	}
	public void substringWithSameChar(String s,int start,int end) {
		/*
		 * Exercise 3
		 * Given a String s, return all possible substrings that start and end with the same letter.
		 * start and end start at 0
		 */

		if(s.equals(""))
			return;
		if(end==s.length() && start<s.length()-1) {
			substringWithSameChar(s, start+1, start+1);
			return;
		}
		if(end==s.length())
			return;
		if(s.charAt(start)==s.charAt(end))
			System.out.print(s.substring(start, (end+1))+" ");

		substringWithSameChar(s, start, end+1);
	}
	public void sumToK(int k, String formula) {
		/*
		 * Exercise 4
		 * Given an integer k, print all possible combinations of non-increasing sequences whose sum equals k.
		 * This function gives all possible solutions with the rotations of elements so 1 2 1 and 1 1 2
		 * formula starts ""
		 */
		if(k==0) {
			System.out.println(formula);
			return;
		}
		for (int i = 1; i <= k; i++) {
			if(formula.length()>0)
				sumToK((k-i), formula+" "+i);
			else
				sumToK((k-i), i+"");

		}
	}
	public void sumToK2(int k, String formula) {
		/*
		 * Exercise 4
		 * Given an integer k, print all possible combinations of non-increasing sequences whose sum equals k.
		 * This function gives all possible solutions with the rotations of elements so 1 2 1 and 1 1 2
		 * formula starts ""
		 */
		if(k==0) {
			System.out.println(formula);
			return;
		}
		String[] a = formula.split(" ");
		for (int i = 1; i <= k; i++) {
			if(!formula.equals("")) {
				//				System.out.println(a[a.length-1]+"))");
				if(Integer.parseInt(a[a.length-1])<=i)
					sumToK2((k-i), formula+" "+i);
			}
			else
				sumToK2((k-i), formula+i);

		}
	}

	public int countOccurence(String s1, String s2, int count) { 
		/*
		 * Exercise 5
		 * Given two strings s1 and s2, print how many times s2 appears in s1
		 * count starts at 0
		 */
		if(s1.length()<s2.length() || s1.equals("")||s2.contentEquals(""))
			return count;
		if(s1.substring(0, s2.length()).contentEquals(s2))
			return countOccurence(s1.substring(s2.length()-1), s2, count+1);
		else 
			return countOccurence(s1.substring(s2.length()-1), s2, count);
	}
	
	
	public String removeDuplicates(String s ,int index) {
		/*
		 * Exercise 6
		 * Given  a string, remove any consecutive duplicate characters.
		 * index starts 0
		 */
		if(s.contentEquals("") || index>=s.length()-1)
			return s;
		if(s.charAt(index)==s.charAt(index+1)) {
			s=s.substring(0, index) + s.substring(index+1);
			return removeDuplicates(s, index);
		}else {
			return removeDuplicates(s, index+1);
		}
	}
	
	
	public static void main(String[] args) {
		Recusrion r = new Recusrion();
		int[] a = {1,2,3};
		r.subsetSum(a, 0,0);
		System.out.println();
		r.noConsecutive1(4, "");
		r.substringWithSameChar("abcab", 0, 0);
		r.sumToK2(4, "");
		System.out.println();
		System.out.println(r.countOccurence("banana", "na", 0));
		System.out.println(r.removeDuplicates("aabbccdef", 0));
	}

}
