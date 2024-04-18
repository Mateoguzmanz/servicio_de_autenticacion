package com.example.servicio_de_autenticacion;

import com.example.servicio_de_autenticacion.entity.User;
import com.example.servicio_de_autenticacion.repository.UserRepository;
import com.example.servicio_de_autenticacion.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private AuthService authService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLoadUserByUsername() {
		String email = "test@example.com";
		User user = new User();
		user.setEmail(email);
		user.setPassword("testPassword");

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		UserDetails userDetails = authService.loadUserByUsername(email);

		assertNotNull(userDetails);
		assertEquals(email, userDetails.getUsername());
		assertEquals(user.getPassword(), userDetails.getPassword());
		assertTrue(userDetails.getAuthorities().isEmpty());

		verify(userRepository, times(1)).findByEmail(email);
	}

	@Test
	void testLoadUserByUsername_UserNotFound() {
		String email = "nonexistent@example.com";

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(email));

		verify(userRepository, times(1)).findByEmail(email);
	}
}
