public class ZeroOrOne implements Token {
	Token nested;

	public ZeroOrOne(Token n) {
		nested = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (nested != null) nested = nested.simplify();
		return nested == null ? null : new NTimes(nested, 0, 1);
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 0, 1).search(s, flags, greedy, pr, offset, lineOffset);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 0, 1).match(s, flags, greedy, pr, offset, lineOffset);
	}
}