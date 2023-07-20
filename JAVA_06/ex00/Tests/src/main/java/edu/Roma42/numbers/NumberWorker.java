package edu.Roma42.numbers;

class	IllegalNumberException extends RuntimeException {
	public	IllegalNumberException(String errorMessage) {
		super(errorMessage);
	}
}

public class	NumberWorker {
	public boolean	isPrime(int number) {
		if (number <= 1) {
			throw new IllegalNumberException("Number less or equal than 1 are illegal");
		} else if (number == 2) {
			return (true);
		} else if (number % 2 == 0) {
			return (false);
		}
		for (int i = 3; i < number / i; i += 2) {
			if (number % i == 0) {
				return (false);
			}
		}
		return (true);
	}

	public int	digitsSum(int number) {
		int	result;
		
		result = 0;
		number = number > 0 ? number : -number;
		while (number / 10 != 0) {
			result += number % 10;
			number /= 10;
		}
		result += number;
		return (result);
	}
}
