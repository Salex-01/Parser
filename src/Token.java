public interface Token {
	Token simplify() throws InvalidPatternException;

	boolean check(String s,SParser.Flag[] flags, boolean greedy) throws InvalidPatternException;

	ParserResult checkAtBeginning(String s,SParser.Flag[] flags, boolean greedy) throws InvalidPatternException;
}