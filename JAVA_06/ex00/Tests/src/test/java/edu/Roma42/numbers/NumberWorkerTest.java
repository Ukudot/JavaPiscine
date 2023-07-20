package edu.Roma42.numbers;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class	NumberWorkerTest {
    @ParameterizedTest
	@ValueSource(ints = {29, 11, 617, 491})
    public void isPrimeForPrimes(int number)
    {
        assertTrue(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
	@ValueSource(ints = {3242, 27, 611361, 495})
    public void isPrimeForNotPrimes(int number)
    {
        assertFalse(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
	@ValueSource(ints = {-3242, 0, 1, -1213})
    public void isPrimeForIncorrectNumbers(int number)
    {
        assertThrows(IllegalNumberException.class, () -> {
			new NumberWorker().isPrime(number);
		});
    }

    @ParameterizedTest
	@CsvFileSource(resources = "/data.csv")
    public void checkDigitsSum(int number, int sum)
    {
		int	actual;

		actual = new NumberWorker().digitsSum(number);
        assertEquals(sum, actual);
    }
}
