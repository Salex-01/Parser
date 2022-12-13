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
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) {
		int i = 0;
		for (char c : s.toCharArray()) {
			if (matched == Character.isLetter(c)) return new ParserResult(true, 1, c + "", null, i);
			i++;
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public ParserResult match(String s, int flags,ParserResult pr, long offset, long lineOffset) {
		if (s.isEmpty()) return new ParserResult(false, 0, null, null, -1);
		boolean ok = matched == Character.isLetter(s.charAt(0));
		return new ParserResult(ok, ok ? 1 : 0, ok ? s.charAt(0) + "" : null, null, ok ? 0 : -1);
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