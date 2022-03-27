import java.util.HashMap;

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
			return new ParserResult(0);
		}
		int i = 0;
		char c = s.charAt(0);
		while (matched == Character.isLetter(c)) {
			i++;
			if (i < s.length()) {
				c = s.charAt(i);
			} else {
				break;
			}
		}
		return new ParserResult(1);
	}
}