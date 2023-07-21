package	edu.Roma42.services;
import	edu.Roma42.models.User;
import	edu.Roma42.repositories.UsersRepository;
import	edu.Roma42.exceptions.AlreadyAuthenticatedException;
import	org.junit.jupiter.api.Test;
import static	org.junit.jupiter.api.Assertions.assertTrue;
import static	org.junit.jupiter.api.Assertions.assertFalse;
import static	org.junit.jupiter.api.Assertions.assertThrows;
import static	org.mockito.Mockito.mock;
import static	org.mockito.Mockito.doThrow;
import static	org.mockito.Mockito.when;
import static	org.mockito.Mockito.times;
import static	org.mockito.Mockito.verify;
import static	org.mockito.Mockito.verifyNoMoreInteractions;
import static	org.mockito.Mockito.never;

public class	UsersServiceImplTest {
	
	@Test
	public void	checkCorrectLoginPassword() {
		UsersServiceImpl	usersServ;
		UsersRepository		usersRepoMock;
		User				gpanico;
		User				adistef;
		User				mpaterno;

		gpanico = new User(new Long(1), "gpanico", "1234", false);
		adistef = new User(new Long(2), "adi-stef", "1234", true);
		mpaterno = new User(new Long(3), "mpaterno", "1234", false);
		usersRepoMock = mock(UsersRepository.class);
		when(usersRepoMock.findByLogin("gpanico")).thenReturn(gpanico);
		when(usersRepoMock.findByLogin("adi-stef")).thenReturn(adistef);
		when(usersRepoMock.findByLogin("mpaterno")).thenReturn(mpaterno);
		doThrow(new RuntimeException("")).when(usersRepoMock).update(mpaterno);
		usersServ = new UsersServiceImpl(usersRepoMock);
		assertThrows(AlreadyAuthenticatedException.class, () -> usersServ.authenticate("adi-stef", "1234"));
		assertTrue(usersServ.authenticate("gpanico", "1234"));
		assertFalse(usersServ.authenticate("mpaterno", "1234"));
		verify(usersRepoMock).findByLogin("gpanico");
		verify(usersRepoMock).findByLogin("adi-stef");
		verify(usersRepoMock).findByLogin("mpaterno");
		verify(usersRepoMock, times(1)).update(gpanico);
		verify(usersRepoMock, never()).update(adistef);
		verify(usersRepoMock, times(1)).update(mpaterno);
		verifyNoMoreInteractions(usersRepoMock);
	}

	@Test
	public void	checkIncorrectLogin() {
		UsersServiceImpl	usersServ;
		UsersRepository		usersRepoMock;
		User				gpanico;

		gpanico = new User(new Long(1), "gpanico", "1234", false);
		usersRepoMock = mock(UsersRepository.class);
		when(usersRepoMock.findByLogin("gpanico")).thenThrow(new RuntimeException(""));
		usersServ = new UsersServiceImpl(usersRepoMock);
		assertFalse(usersServ.authenticate("gpanico", "1234"));
		verify(usersRepoMock).findByLogin("gpanico");
		verify(usersRepoMock, never()).update(gpanico);
		verifyNoMoreInteractions(usersRepoMock);
	}

	@Test
	public void	checkIncorrectPassword() {
		UsersServiceImpl	usersServ;
		UsersRepository		usersRepoMock;
		User				gpanico;

		gpanico = new User(new Long(1), "gpanico", "4321", false);
		usersRepoMock = mock(UsersRepository.class);
		when(usersRepoMock.findByLogin("gpanico")).thenReturn(gpanico);
		usersServ = new UsersServiceImpl(usersRepoMock);
		assertFalse(usersServ.authenticate("gpanico", "1234"));
		verify(usersRepoMock).findByLogin("gpanico");
		verify(usersRepoMock, never()).update(gpanico);
		verifyNoMoreInteractions(usersRepoMock);

	}
}
