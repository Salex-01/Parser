public class Main {
	public static void main(String[] args) throws InvalidPatternException {
		ParserResult res = new SParser().parse(args[1], args[2]);
	}
}