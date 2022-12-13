public class Any implements Token {
	static Any instance = null;

	static Token getInstance() {
		if (instance == null) instance = new Any();
		return instance;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return this;
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) {
		if (s.isEmpty()) return new ParserResult(false, 0, null, null, -1);
		return new ParserResult(true, 1, s.charAt(0) + "", null, 0);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) {
		if (s.isEmpty()) return new ParserResult(false, 0, null, null, -1);
		return new ParserResult(true, 1, s.charAt(0) + "", null, 0);
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