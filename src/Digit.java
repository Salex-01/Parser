import java.util.HashMap;

public class Digit implements Token {
	static HashMap<Object, Digit> i = new HashMap<>();
	boolean matched;

	private Digit(boolean m) {
		matched = m;
	}

	@Override
	public Token simplify() {
		return this;
	}

	static Token getInstance(Boolean mode) {
		return i.computeIfAbsent(mode, o -> new Digit(mode));
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((matched && (c >= '0' && c <= '9')) || (!matched && (c < '0' || c > '9'))) {
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
		while ((matched && (c >= '0' && c <= '9')) || (!matched && (c < '0' || c > '9'))) {
			i++;
			if (i < s.length()) {
				c = s.charAt(i);
			} else {
				break;
			}
		}
		return new ParserResult(i);
	}
}