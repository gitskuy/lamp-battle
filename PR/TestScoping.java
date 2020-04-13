public class TestScoping {
	public static void main(String[] args) {
		ScopeExample scope = new ScopeExample();
		System.out.println(scope.getI());
		scope.firstMethod();
		System.out.println(scope.getI());
		scope.secondMethod(10);
		System.out.println(scope.getI());
	}
}