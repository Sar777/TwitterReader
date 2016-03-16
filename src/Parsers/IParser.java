package Parsers;

public interface IParser<A,B> {
	public B parse(A data);
}
