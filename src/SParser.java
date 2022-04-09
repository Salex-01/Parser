import java.util.HashMap;

public class SParser {
	enum Flag {
		IGNORE_CASE(1),
		LINE_BY_LINE(2);

		final int v;

		Flag(int value) {
			this.v = value;
		}
	}

	HashMap<String, Token> dict = new HashMap<>();

	public ParserResult parse(String[] patterns, String string, Flag[] flags, boolean greedy) throws InvalidPatternException {
		Pattern[] p = new Pattern[patterns.length];
		for (int i = 0; i < patterns.length; i++) {
			p[i] = new Pattern(patterns[i], dict);
		}
		return parse(p, string, flags, greedy);
	}

	public ParserResult parse(String[] patterns, String string, Flag[] flags) throws InvalidPatternException {
		return parse(patterns, string, flags, false);
	}

	public ParserResult parse(String[] patterns, String string, boolean greedy) throws InvalidPatternException {
		return parse(patterns, string, null, greedy);
	}

	public ParserResult parse(String[] patterns, String string) throws InvalidPatternException {
		return parse(patterns, string, null, false);
	}

	public ParserResult parse(Pattern[] patterns, String string, Flag[] flags, boolean greedy) {
		patterns[0].search(string, flags, greedy);
		return null;    //TODO
	}

	public ParserResult parse(Pattern[] patterns, String string, Flag[] flags) {
		return parse(patterns, string, flags, false);
	}

	public ParserResult parse(Pattern[] patterns, String string, boolean greedy) {
		return parse(patterns, string, null, greedy);
	}

	public ParserResult parse(Pattern[] patterns, String string) {
		return parse(patterns, string, null, false);
	}
}