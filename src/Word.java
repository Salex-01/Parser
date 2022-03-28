import java.util.HashMap;
import java.util.LinkedList;

public class Word implements Token {
	static HashMap<Object, Word> i = new HashMap<>();
	boolean matched;

	private Word(boolean m) {
		matched = m;
	}

	@Override
	public Token simplify() {
		return this;
	}

	static Token getInstance(boolean mode) {
		return i.computeIfAbsent(mode, o -> new Word(mode));
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (matched == Character.isLetter(c)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) {
		if (s.isEmpty()) {
			return new ParserResult(false, 0, null);
		}
		int i = 0;
		char c = s.charAt(0);
		LinkedList<String> result = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		while (matched == Character.isLetter(c)) {
			sb.append(c);
			i++;
			if (i < s.length()) {
				c = s.charAt(i);
			} else {
				break;
			}
		}
		result.add(sb.toString());
		return new ParserResult(true, i, result);
	}
}