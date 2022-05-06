public interface Token {
	Token simplify() throws InvalidPatternException;

	ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;

	ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;
}