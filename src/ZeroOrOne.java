public class ZeroOrOne implements Token {
	Token nested;
	boolean greedy;

	public ZeroOrOne(Token n, boolean g) {
		nested = n;
		greedy = g;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (nested != null) nested = nested.simplify();
		return nested == null ? null : new NTimes(nested, 0, 1, greedy);
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 0, 1, greedy).search(s, flags, pr, offset, lineOffset);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 0, 1, greedy).match(s, flags, pr, offset, lineOffset);
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