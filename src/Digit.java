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
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) {
		int i = 0;
		for (char c : s.toCharArray()) {
			if ((matched == (c >= '0' && c <= '9')))
				return new ParserResult(true, 1, c + "", null, i);
			i++;
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) {
		if (s.isEmpty()) return new ParserResult(false, 0, null, null, -1);
		boolean ok = (matched == (s.charAt(0) >= '0' && s.charAt(0) <= '9'));
		return new ParserResult(ok, ok ? 1 : 0, s.charAt(0) + "", null, 0);
	}

	@Override
	public boolean takeOneMore(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}

	@Override
	public boolean giveOneBack(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}
}