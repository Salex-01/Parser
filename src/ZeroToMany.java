public class ZeroToMany implements Token {
	Token nested;

	public ZeroToMany(Token n) {
		nested = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (nested != null) nested = nested.simplify();
		return nested == null ? null : this;
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		return true;
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		ParserResult pr = nested.checkAtBeginning(s, flags, greedy);
		return new ParserResult(true, pr.matchNumber, pr.capturedGroups);
	}
}