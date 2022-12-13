import java.util.ArrayList;
import java.util.List;

public class Range implements Token {
	char firstEnd;
	char secondEnd;
	boolean inverted;

	public Range(List<Token> tokens, char second, boolean i) throws InvalidPatternException {
		if (tokens.size() == 0) {
			throw new InvalidPatternException();
		}
		Token lastToken = tokens.get(tokens.size() - 1);
		if (lastToken instanceof Litteral) {
			String val = ((Litteral) lastToken).value;
			firstEnd = val.charAt(val.length() - 1);
			val = val.substring(0, val.length() - 1);
			if (val.length() == 0) {
				tokens.remove(tokens.size() - 1);
			} else {
				((Litteral) lastToken).value = val;
			}
		} else throw new InvalidPatternException();
		if (firstEnd > second) {
			secondEnd = firstEnd;
			firstEnd = second;
		} else {
			secondEnd = second;
		}
		inverted = i;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		ArrayList<Character> a = new ArrayList<>();
		for (char c = firstEnd; c <= secondEnd; c++) {
			a.add(c);
		}
		return new AnyLitteral(inverted, a);
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ArrayList<Character> a = new ArrayList<>();
		for (char c = firstEnd; c <= secondEnd; c++) {
			a.add(c);
		}
		return new AnyLitteral(inverted, a).search(s, flags, pr, offset, lineOffset);
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ArrayList<Character> a = new ArrayList<>();
		for (char c = firstEnd; c <= secondEnd; c++) {
			a.add(c);
		}
		return new AnyLitteral(inverted, a).match(s, flags, pr, offset, lineOffset);
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