package Reports.Generators;

public interface IReportsGenerator<A, B> {
	public B generate(A setting);
}
