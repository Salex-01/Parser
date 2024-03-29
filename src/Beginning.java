public class Beginning implements Token {
	static Beginning beginning;

	static Beginning getInstance() {
		if (beginning == null) {
			beginning = new Beginning();
		}
		return beginning;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return this;
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		boolean ok = (offset == 0 || ((flags & SParser.Flag.LINE_BY_LINE.v) != 0 && (lineOffset == 0 || s.contains("\n"))));
		return new ParserResult(ok, 0, null, null, ok ? (lineOffset == 0 ? 0 : s.indexOf('\n') + 1) : -1);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		boolean ok = (offset == 0 || ((flags & SParser.Flag.LINE_BY_LINE.v) != 0 && lineOffset == 0));
		return new ParserResult(ok, 0, null, null, ok ? 0 : -1);
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