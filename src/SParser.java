import java.util.HashMap;

public class SParser {
	enum Flag {IGNORE_CASE/*, LINE_BY_LINE*/}

	HashMap<String, Token> dict = new HashMap<>();

	public ParserResult parse(String pattern, String string, Flag[] flags, boolean greedy) throws InvalidPatternException {
		return parse(new Pattern(pattern, dict), string, flags, greedy);
	}

	public ParserResult parse(String pattern, String string, Flag[] flags) throws InvalidPatternException {
		return parse(pattern, string, flags, false);
	}

	public ParserResult parse(String pattern, String string, boolean greedy) throws InvalidPatternException {
		return parse(pattern, string, null, greedy);
	}

	public ParserResult parse(String pattern, String string) throws InvalidPatternException {
		return parse(pattern, string, null, false);
	}

	public ParserResult parse(Pattern pattern, String string, Flag[] flags, boolean greedy) {
		pattern.check(string, flags, greedy);
		return null;    //TODO
	}

	public ParserResult parse(Pattern pattern, String string, Flag[] flags) {
		return parse(pattern, string, flags, false);
	}

	public ParserResult parse(Pattern pattern, String string, boolean greedy) {
		return parse(pattern, string, null, greedy);
	}

	public ParserResult parse(Pattern pattern, String string) {
		return parse(pattern, string, null, false);
	}
}
