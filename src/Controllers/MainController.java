package Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainController {
	public static String path = "C:\\Study\\Applied Computing topics\\apache-tomcat-8.5.47\\apache-tomcat-8.5.47\\webapps\\Computing-Project\\WEB-INF\\classes\\textfile";
	public HashMap<String, Integer> countWords(String string) throws IOException {
		int totalWordCount = 0;
		HashMap<String, Integer> hashMapReturn = new HashMap<String, Integer>();
		String stringWords = string;
		
		ArrayList<String> keyTokenSearch = new ArrayList<String>();
		StringTokenizer stringTokenSearch = new StringTokenizer(stringWords, " ");
	
		while (stringTokenSearch.hasMoreElements()) {
			keyTokenSearch.add(stringTokenSearch.nextToken());
		}

		File fileArray[] = new File(path).listFiles();
		ArrayList<String> keyArrayList = new ArrayList<String>();

		//iteration of files
		for (File f : fileArray) {
			if (f.isFile()) {
				totalWordCount = 0;
				keyArrayList = new ArrayList<String>();

				String stringOfPage = new String(Files.readAllBytes(Paths.get(f.toURI())));
				StringTokenizer stringToken = new StringTokenizer(stringOfPage, " ");
				while (stringToken.hasMoreElements()) {
					keyArrayList.add(stringToken.nextToken());
				}

				for (int Pages = 0; Pages < keyArrayList.size(); Pages++) {
					for (int words = 0; words < keyTokenSearch.size(); words++) {
						ArrayList<Integer> offset = searchPattern(keyTokenSearch.get(words).toCharArray(),
								keyArrayList.get(Pages).toCharArray());

						totalWordCount = totalWordCount + offset.size();
					}
				}
				hashMapReturn.put(f.getName().split("\\.")[0] + ".htm", totalWordCount);
			}
		}

		return hashMapReturn;
	}

	public static ArrayList<Integer> searchPattern(char[] pattern, char[] txt) {
		int x = pattern.length;
		int y = txt.length;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i <= y - x; i++) {
			int j;
			for (j = 0; j < x; j++) {
				if (txt[i + j] != pattern[j])
					break;
			}
			if (j == x) {
				answer.add(i);
				continue;
			}
		}
		return answer;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap sortedResult(HashMap hashData){
		List listObject = new LinkedList(hashData.entrySet());
		// Defined Custom Comparator here
		Collections.sort(listObject, new Comparator() {
			public int compare(Object object1, Object object2) {
				return ((Comparable) ((Map.Entry) (object1)).getValue()).compareTo(((Map.Entry) (object2)).getValue());
			}
		});

		Collections.reverse(listObject);
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		System.out.println();
		return sortedHashMap;
	}
	@SuppressWarnings("unchecked")
	public String getSpellCheck(String words) {
		String result = "";
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		// String[] matchingword;
		String string = "";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for(File f : listOfFiles)
			if (f.isFile()) {

				In in = new In(f);
				String textFiles = in.readAll();

				Scanner scanner = new Scanner(textFiles);
				if(scanner.hasNextLine()) {
					string = scanner.nextLine().replaceAll("[^a-zA-Z0-9]+", " ");
				}
				StringTokenizer stringToken = new StringTokenizer(string, " ");

				String token;

				while (stringToken.hasMoreTokens()) {
					token = stringToken.nextToken();
					String x = token.replaceAll("\"", " ");

					if (x != " ") {
						int check = minDistance(words, token);
						hashMap.put(token, check);
					}
				}
			}

		HashMap<Integer, String> map = sortedResult(hashMap);
		Object[] keys = map.keySet().toArray();
		for(int i=1;i<=5;i++) {
			result += keys[keys.length-i].toString() + " , "; 
		}
		return result;
	}
	public int minDistance(String word1, String word2) {
		int lenght1 = word1.length();
		int lenght2 = word2.length();

		// lenght1+1, lenght2+1, because finally return dp[lenght1][lenght2]
		int[][] dp = new int[lenght1 + 1][lenght2 + 1];

		for (int i = 0; i <= lenght1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= lenght2; j++) {
			dp[0][j] = j;
		}

		for (int i = 0; i < lenght1; i++) {
			char character1 = word1.charAt(i);
			for (int j = 0; j < lenght2; j++) {
				char character2 = word2.charAt(j);

				// if last two chars equal
				if (character1 == character2) {
					// update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
		return dp[lenght1][lenght2];
	}
}
