package Controllers;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateTST {
	public static Trie trieObject;
	public static String destinationPath = "C:\\Study\\Applied Computing topics\\apache-tomcat-8.5.47\\apache-tomcat-8.5.47\\webapps\\Computing-Project\\WEB-INF\\classes\\textfile";

	public static void initTrie() {
		if (trieObject == null) {
			System.out.println("Creating Trie for first time");
			try {
				trieObject = new Trie();
				File folder = new File(destinationPath);
				File[] listOfFiles = folder.listFiles();
				StringTokenizer stringToken = null;
				String tkn;
				String x ="";
				//iteration of list of files
				for (File f : listOfFiles) {
					if (f.isFile()) {
						In in = new In(f);
						String textFiles = in.readAll();
						Scanner scanner = new Scanner(textFiles);
						if (scanner.hasNext()) {
							x = scanner.nextLine().replaceAll("[^a-zA-Z0-9]+", " ");
						}
						stringToken = new StringTokenizer(x, " ");

						while (stringToken.hasMoreTokens()) {

							tkn = stringToken.nextToken();
							trieObject.insert(tkn.toLowerCase());
						}
					}
					System.out.println("Trie Initialized!");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Trie already Initialized");
		}
	}
}
