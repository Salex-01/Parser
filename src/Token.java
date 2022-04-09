import java.util.List;

public interface Token {
	Token simplify() throws InvalidPatternException;

	ParserResult search(String s, SParser.Flag flags, boolean greedy,ParserResult pr) throws InvalidPatternException;

	ParserResult match(String s, SParser.Flag flags, boolean greedy,ParserResult pr) throws InvalidPatternException;
}