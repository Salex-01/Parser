import java.util.LinkedList;
import java.util.List;

public class NTimes implements Token {
	Token nested;
	long min;
	long max;

	public NTimes(Token n, long mi, long ma) throws InvalidPatternException {
		if (ma < mi || ma < 0 || mi < 0) throw new InvalidPatternException();
		this.nested = n;
		this.min = mi;
		this.max = ma;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (min == 1 && max == 1) return nested;
		if (max <= 0) return null;
		return this;
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr) throws InvalidPatternException {
		ParserResult r = nested.search(s, flags, greedy, pr);
		if (!r.ok) return r;
		ParserResult result = new ParserResult(true, r.matchSize, r.value, new LinkedList<>(List.of(r)), r.position);
		ParserResult tmp = nested.match(s.substring(result.position + result.matchSize), flags, greedy, pr);
		return getParserResult(s, flags, greedy, result, tmp, pr);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr) throws InvalidPatternException {
		ParserResult r = nested.match(s, flags, greedy, pr);
		if (!r.ok) return r;
		ParserResult result = new ParserResult(true, r.matchSize, r.value, new LinkedList<>(List.of(r)), 0);
		ParserResult tmp = nested.match(s.substring(result.matchSize), flags, greedy, pr);
		return getParserResult(s, flags, greedy, result, tmp, pr);
	}

	private ParserResult getParserResult(String s, SParser.Flag flags, boolean greedy, ParserResult result, ParserResult tmp, ParserResult pr) throws InvalidPatternException {
		long count = 1;
		while (tmp.ok && count < max) {
			count++;
			result.matchSize += tmp.matchSize;
			result.value = tmp.value;
			result.capturedGroups.addLast(tmp);
			tmp = nested.match(s.substring(result.position + result.matchSize), flags, greedy, pr);
		}
		boolean ok = count >= min;
		return new ParserResult(ok, ok ? result.matchSize : 0, ok ? result.value : null, ok ? result.capturedGroups : null, ok ? result.position : -1);
	}
}