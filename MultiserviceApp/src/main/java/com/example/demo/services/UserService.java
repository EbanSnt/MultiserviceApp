package com.example.demo.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	// Metodo que devuelve todos los usuarios
	public List<Users> allUsers() {
		return userRepo.findAll();
	}
	// Metodo que permite modicar un usuario
	public Users saveUser(Users user) {
		return userRepo.save(user);
	}
	// Metodo que permite eliminar un usuario
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	// Devuelve un usuario
	public Users user(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	/*Método que registre a un nuevo usuario*/
	public Users register(Users newUser, BindingResult result) {
		
		//Comparar las contraseñas
		String password = newUser.getPassword();
		String confirm = newUser.getConfirm();
		if(!password.equals(confirm)) {
			//SI no son iguales
			//path, clave, mensaje
			result.rejectValue("confirm", "Matches", "Password and confirmation don't match");
		}
		
		//Revisar que el email no esté registrado
		String email = newUser.getEmail();
		Users userExist = userRepo.findByEmail(email); //Objeto de User o null
		if(userExist != null) {
			//El correo ya está registrado
			result.rejectValue("email", "Unique", "E-mail already exists");
		}
		
		//Si existe error, regreso null
		if(result.hasErrors()) {
			return null;
		} else {
			//NO HAY ERRORES
			//Hashear contraseña
			String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
			newUser.setPassword(passHash); //Establecemos el password hasheado
			return userRepo.save(newUser);
		}
		
	}
	
	/*Método que revisa que los datos sean correctos para Iniciar Sesión*/
	public Users login(String email, String password) {
		//Revisamos que el correo exista en BD
		Users userTryingLogin = userRepo.findByEmail(email); //Objeto User o NULL
		
		if(userTryingLogin == null) {
			return null;
		}
		
		//Comparar las contraseñas
		//BCrypt.checkpw(Contra NO encriptada, Contra SI encriptada) -> True o False
		if(BCrypt.checkpw(password, userTryingLogin.getPassword())) {
			return userTryingLogin;
		} else {
			return null;
		}
		
		
	}
}
