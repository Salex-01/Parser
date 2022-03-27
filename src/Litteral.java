public class Litteral implements Token {
	String value;

	public Litteral(Character[] characters) {
		StringBuilder sb = new StringBuilder();
		for (Character c : characters) {
			sb.append(c);
		}
		value = sb.toString();
	}

	public Litteral(String s) {
		value = s;
	}

	@Override
	public Token simplify() {
		return value.isEmpty() ? null : this;
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) {
		for (SParser.Flag f : flags) {
			if (f == SParser.Flag.IGNORE_CASE) {
				return s.toLowerCase().contains(value.toLowerCase());
			}
		}
		return s.contains(value);
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) {
		int i = 0;
		String v = value;
		for (SParser.Flag f : flags) {
			if (f == SParser.Flag.IGNORE_CASE) {
				s = s.toLowerCase();
				v = value.toLowerCase();
				break;
			}
		}
		while (s.substring(i * v.length()).startsWith(v)) i++;
		return new ParserResult(1);
	}
}