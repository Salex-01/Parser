import java.util.LinkedList;

public class ParserResult {
	boolean ok;
	int matchNumber;
	LinkedList<String> capturedGroups;

	@SuppressWarnings("unchecked")
	ParserResult(boolean ok, int n, LinkedList<String> s) {
		matchNumber = n;
		capturedGroups = s == null ? null : (LinkedList<String>) s.clone();
	}
}
