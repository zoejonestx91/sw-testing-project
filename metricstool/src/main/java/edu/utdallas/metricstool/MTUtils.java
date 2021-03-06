package edu.utdallas.metricstool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MTUtils {
	
	private static BufferedWriter out;
	
	public static void init() {
		 try {
			out = new BufferedWriter(new FileWriter("metrics.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("[METRICSTOOL] Unable to access metrics.csv. Cannot write metrics.");
		}
	}
	
	public static void finish() {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write(String s) {
		try {
			out.write(s);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	// example usage
//	public static void main(String[] a) {
//		for (String s : internalNamesReferenced("Ljava/test/LongSomething;[[ILjava/util/HashMap<Ljava/lang/Integer;Ljava/lang/Boolean;>;Z[Ljava/lang/String;IJ"))
//			System.out.println(s);
//	}
	
	// Extracts the next proper argument from a desc-style set of arguments
	public static String nextArg(String args) {
		int count = 0;
		char c = args.charAt(0);
		if (c == 'L') {
			for (int j = 1; j < args.length(); j++) {
				char d = args.charAt(j);
				if (d == ';' && count == 0) {
					return args.substring(0, j + 1);
				} else if (d == '<') {
					count++;
				} else if (d == '>') {
					count--;
				} else {
					continue;
				}
			}
		} else if (c == '[') {
			return "[" + nextArg(args.substring(1));
		} else {
			return String.valueOf(c);
		}
		return null;
	}
	
	// Extracts the arguments in a description
	public static ArrayList<String> parseDescArgs(String desc) {
		ArrayList<String> names = new ArrayList<String>();
		desc = desc.substring(desc.indexOf('(') + 1, desc.indexOf(')'));
		while (!desc.isEmpty()) {
			String next = nextArg(desc);
			names.add(next);
			desc = desc.substring(next.length());
		}
		return names;
	}
	
	// Converts an argument from the description format to the qualified format
	public static String descFormatToQualified(String name) {
		if (name == null || name.equals(""))
			return "";
		int count = 0;
		String newName = "";
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c == '<') {
				count++;
				newName += c;
			} else if (c == '>') {
				count--;
				newName += c;
			} else if (c == ';') {
				if (count > 0 && name.charAt(i + 1) != '>')
					newName += ',';
			} else if (c == '/') {
				newName += '.';
			} else if (c == 'L') {
				if (!(i == 0 || name.charAt(i - 1) == '<' || name.charAt(i - 1) == ';'))
					newName += c;
			} else {
				newName += c;
			}
		}
		return newName;
	}
	
	// Determines the full set of qualified names referenced by a single argument
	public static HashSet<String> internalNamesReferenced(String desc) {
		HashSet<String> names = new HashSet<String>();
		int objSem = 0;
		int braSem = 0;
		for (int i = 0; i < desc.length(); i++) {
			char c = desc.charAt(i);
			if (objSem == 0 && c == 'L') {
				objSem++;
				int sem = 0;
				for (int j = i + 1; j < desc.length(); j++) {
					char d = desc.charAt(j);
					if (d == ';' && sem == 0) {
						String name = desc.substring(i, j + 1);
						names.add(descFormatToQualified(name));
						break;
					} else if (d == 'L' && (desc.charAt(j - 1) == ';' || desc.charAt(j - 1) == '<')) {
						sem++;
					} else if (d == ';' && sem != 0) {
						sem--;
					}
				}
			} else if (braSem == 0 && c == '<') {
				braSem++;
				int sem = 0;
				for (int j = i + 1; j < desc.length(); j++) {
					char d = desc.charAt(j);
					if (d == '>' && sem == 0) {
						names.addAll(internalNamesReferenced(desc.substring(i + 1, j)));
						break;
					} else if (d == '<') {
						sem++;
					} else if (d == '>' && sem != 0) {
						sem--;
					}
				}
			} else if (c == ';' && desc.charAt(i - 1) != '>') {
				objSem--;
			} else if (c == '>') {
				braSem--;
			}
		}
		return names;
	}

	public static void write(int i) {
		write(String.valueOf(i));
	}

	public static void write(double d) {
		write(String.valueOf(d));
	}
}
	