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
	public ParserResult search(String s, SParser.Flag flags, boolean greedy,ParserResult pr) {
		String nValue = value;
		String nS = s;
		if ((flags.v & SParser.Flag.IGNORE_CASE.v) != 0) {
			nS = s.toLowerCase();
			nValue = value.toLowerCase();
		}
		for (int i = 0; i < nS.length() - nValue.length() + 1; i++) {
			boolean ok = true;
			for (int j = 0; j < nValue.length(); j++) {
				if (nS.charAt(i + j) != nValue.charAt(j)) {
					ok = false;
					break;
				}
			}
			if (ok) return new ParserResult(true, value.length(), s.substring(i, i + value.length()), null, i);
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy,ParserResult pr) {
		String nValue = value;
		String nS = s;
		if ((flags.v & SParser.Flag.IGNORE_CASE.v) != 0) {
			nS = s.toLowerCase();
			nValue = value.toLowerCase();
		}
		boolean ok = true;
		for (int i = 0; i < nValue.length(); i++) {
			if (nS.charAt(i) != nValue.charAt(i)) {
				ok = false;
				break;
			}
		}
		return new ParserResult(ok, ok ? value.length() : 0, ok ? s.substring(0, value.length()) : null, null, ok ? 0 : -1);
	}
}