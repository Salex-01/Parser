public class Ending implements Token {
	static Ending ending;

	static Ending getInstance() {
		if (ending == null) {
			ending = new Ending();
		}
		return ending;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return this;
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		int pos = s.indexOf('\n');
		return new ParserResult(true, 0, null, null, ((flags & SParser.Flag.LINE_BY_LINE.v) != 0) ? (pos > -1 ? pos : s.length()) : s.length());
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		boolean ok = (s.length() == 0 || (((flags & SParser.Flag.LINE_BY_LINE.v) != 0) && s.charAt(0) == '\n'));
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