import java.util.LinkedList;

public class ParserResult {
	int matchNumber;
	LinkedList<String> capturedGroups;

	ParserResult(int n) {
		matchNumber = n;
	}

	@SuppressWarnings("unchecked")
	ParserResult(LinkedList<String> s) {
		capturedGroups = (LinkedList<String>) s.clone();
	}

	@SuppressWarnings("unchecked")
	ParserResult(int n, LinkedList<String> s) {
		matchNumber = n;
		capturedGroups = (LinkedList<String>) s.clone();
	}
}
