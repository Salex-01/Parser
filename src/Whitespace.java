import java.util.HashMap;

public class Whitespace implements Token {
	static HashMap<Object, Whitespace> i = new HashMap<>();
	boolean matched;

	private Whitespace(boolean m) {
		matched = m;
	}

	@Override
	public Token simplify() {
		return this;
	}

	static Token getInstance(boolean mode) {
		return i.computeIfAbsent(mode, o -> new Whitespace(mode));
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) {
		int i = 0;
		for (char c : s.toCharArray()) {
			if (matched == (c == ' ' || c == '\t' || c == '\n' || c == '\r'))
				return new ParserResult(true, 1, c + "", null, i);
			i++;
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) {
		if (s.isEmpty()) return new ParserResult(false, 0, null, null, -1);
		boolean ok = matched == (s.charAt(0) == ' ' || s.charAt(0) == '\t' || s.charAt(0) == '\n' || s.charAt(0) == '\r');
		return new ParserResult(ok, ok ? 1 : 0, ok ? s.charAt(0) + "" : null, null, ok ? 0 : -1);
	}
}