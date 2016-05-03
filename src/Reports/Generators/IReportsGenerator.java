package Reports.Generators;

/**
 * The Interface IReportsGenerator.
 *
 * @param <A> the report setting
 * @param <B> the report type
 */
public interface IReportsGenerator<A, B> {
	
	/**
	 * Generate report
	 *
	 * @param setting the setting report generator
	 * @return the report type
	 */
	public B generate(A setting);
}
