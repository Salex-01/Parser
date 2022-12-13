import java.util.LinkedList;
import java.util.List;

public class GroupCatcher implements Token {
	Token nested;

	public GroupCatcher(Token n) {
		nested = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return null;    //TODO
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		ParserResult result = nested.search(s, flags, pr, offset, lineOffset);
		if (result.ok) {
			LinkedList<ParserResult> groups = new LinkedList<>(List.of(result));
			if (result.capturedGroups != null) {
				groups.addAll(result.capturedGroups);
			}
			return new ParserResult(true, result.matchSize, result.value, new LinkedList<>(List.of(result)), result.position);
		}
		return null;    //TODO
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return null;    //TODO
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