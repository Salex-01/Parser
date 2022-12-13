import java.util.LinkedList;
import java.util.List;

public class NTimes implements Token {
	Token nested;
	long min;
	long max;
	boolean greedy;

	public NTimes(Token n, long mi, long ma, boolean g) throws InvalidPatternException {
		if (ma < mi || ma < 0 || mi < 0) throw new InvalidPatternException();
		this.nested = n;
		this.min = mi;
		this.max = ma;
		greedy = g;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (min == 1 && max == 1) return nested;
		if (max <= 0) return null;
		return this;
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ParserResult r = nested.search(s, flags, pr, offset, lineOffset);
		if (r.ok) {
			ParserResult result = new ParserResult(true, r.matchSize, r.value, new LinkedList<>(List.of(r)), r.position);
			return getParserResult(s, flags, greedy, result, pr, offset, lineOffset);
		} else {
			if (min == 0) {
				return new ParserResult(true, 0, "", new LinkedList<>(), r.position);
			}
			return r;
		}
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ParserResult r = nested.match(s, flags, pr, offset, lineOffset);
		if (r.ok) {
			ParserResult result = new ParserResult(true, r.matchSize, r.value, new LinkedList<>(List.of(r)), 0);
			return getParserResult(s, flags, greedy, result, pr, offset, lineOffset);
		} else {
			if (min == 0) {
				return new ParserResult(true, 0, "", new LinkedList<>(), 0);
			}
			return r;
		}
	}

	private ParserResult getParserResult(String s, int flags, boolean greedy, ParserResult result, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ParserResult tmp = nested.match(s.substring(result.position + result.matchSize), flags, pr, offset, lineOffset);
		long count = 1;
		// TODO Condition sur greedy + feedback
		while (tmp.ok && count < max) {
			count++;
			result.matchSize += tmp.matchSize;
			result.value += tmp.value;
			result.capturedGroups.addLast(tmp);
			tmp = nested.match(s.substring(result.position + result.matchSize), flags, pr, offset, lineOffset);
		}
		boolean ok = count >= min;
		return new ParserResult(ok, ok ? result.matchSize : 0, ok ? result.value : null, ok ? result.capturedGroups : null, ok ? result.position : -1);
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