public class OneOrMany implements Token {
	Token nested;
	boolean greedy;

	public OneOrMany(Token n, boolean g) {
		nested = n;
		greedy = g;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (nested != null) nested = nested.simplify();
		return nested == null ? null : new NTimes(nested, 1, Long.MAX_VALUE, greedy);
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 1, Long.MAX_VALUE, greedy).search(s, flags, pr, offset, lineOffset);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return new NTimes(nested, 1, Long.MAX_VALUE, greedy).match(s, flags, pr, offset, lineOffset);
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