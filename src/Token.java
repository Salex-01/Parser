public interface Token {
	Token simplify() throws InvalidPatternException;

	ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;

	ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;

	boolean takeOneMore(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;

	boolean giveOneBack(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException;
}