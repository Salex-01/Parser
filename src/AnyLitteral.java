import java.util.ArrayList;

public class AnyLitteral implements Token {
	boolean inverted;
	Character[] litterals;

	public AnyLitteral(boolean i, ArrayList<Character> l) {
		inverted = i;
		litterals = l.toArray(new Character[0]);
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return litterals.length == 0 ? null : litterals.length == 1 ? new Litteral(litterals[0] + "") : this;
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		for (int i = 0; i < s.length(); i++) {
			boolean found = false;
			for (Character c : litterals) {
				if (s.charAt(i) == c) {
					found = true;
					break;
				}
			}
			if (found == inverted) {
				return new ParserResult(true, 1, s.charAt(i) + "", null, i);
			}
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		for (Character c : litterals) {
			if (s.charAt(0) == c) {
				return new ParserResult(true, 1, s.charAt(0) + "", null, 0);
			}
		}
		return new ParserResult(false, 0, null, null, -1);
	}

	@Override
	public boolean takeOneMore(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}

	@Override
	public boolean giveOneBack(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}
}