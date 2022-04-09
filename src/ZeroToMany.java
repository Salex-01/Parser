public class ZeroToMany implements Token {
	Token nested;

	public ZeroToMany(Token n) {
		nested = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (nested != null) nested = nested.simplify();
		return nested == null ? null : new NTimes(nested, 0, Long.MAX_VALUE);
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr) throws InvalidPatternException {
		return new NTimes(nested, 0, Long.MAX_VALUE).search(s, flags, greedy, pr);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr) throws InvalidPatternException {
		return new NTimes(nested, 0, Long.MAX_VALUE).match(s, flags, greedy, pr);
	}
}