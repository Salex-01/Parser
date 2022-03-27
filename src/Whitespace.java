public class Whitespace implements Token {
	static Whitespace i;

	@Override
	public Token simplify() {
		return this;
	}

	static Token getInstance() {
		if (i == null) {
			i = new Whitespace();
		}
		return i;
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) {
		return s.contains(" ") || s.contains("\t") || s.contains("\n") || s.contains("\r");
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) {
		if (s.isEmpty()) {
			return new ParserResult(0);
		}
		int i = 0;
		char c = s.charAt(0);
		while (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
			i++;
			if (i < s.length()) {
				c = s.charAt(i);
			} else {
				break;
			}
		}
		return new ParserResult(1);
	}
}