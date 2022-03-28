public class GroupCatcher implements Token {
	Token nested;

	public GroupCatcher(Token n) {
		nested = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return null;	//TODO
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		return false;	//TODO
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		return null;	//TODO
	}
}
