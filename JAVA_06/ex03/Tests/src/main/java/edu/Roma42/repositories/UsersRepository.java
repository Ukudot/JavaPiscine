package edu.Roma42.repositories;
import	edu.Roma42.models.User;

public interface	UsersRepository {
	User	findByLogin(String Login);
	void	update(User user);
}
