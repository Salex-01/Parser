import java.util.LinkedList;

public class ParserResult {
	boolean ok;
	int matchSize;
	String value;
	LinkedList<ParserResult> capturedGroups;
	int position;

	@SuppressWarnings("unchecked")
	ParserResult(boolean b, int size, String v, LinkedList<ParserResult> groups, int pos) {
		ok = b;
		matchSize = size;
		value = v;
		capturedGroups = (groups == null ? null : (LinkedList<ParserResult>) groups.clone());
		position = pos;
	}
}