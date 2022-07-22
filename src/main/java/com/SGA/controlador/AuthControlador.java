package com.SGA.controlador;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import com.SGA.servicio.AuthService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SGA.dto.LoginDto;
import com.SGA.dto.RecuperarContraseñaDto;
import com.SGA.dto.RegistroDto;
import com.SGA.entidades.Contratista;
import com.SGA.entidades.Correo;
import com.SGA.entidades.Municipio;
import com.SGA.entidades.Persona;
import com.SGA.entidades.Rol;
import com.SGA.entidades.TipoDocumento;
import com.SGA.entidades.Usuario;
import com.SGA.repositorio.MunicipioRepository;
import com.SGA.repositorio.RolRepository;
import com.SGA.repositorio.UsuarioRepositorio;
import com.SGA.seguridad.JWTAuthResponseDto;
import com.SGA.seguridad.JwtTokenProvider;
import com.SGA.servicio.PersonaService;
import com.SGA.servicio.TipoDocumentoService;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private AuthService authService;

	@PostMapping("iniciarSesion")
	public ResponseEntity<JWTAuthResponseDto> authenticateUser(@RequestBody LoginDto login){
		return  authService.authenticateUser(login);
	}

	@PostMapping("registrar") 
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDto registroDto){
		if(usuarioRepositorio.existsByUsername(registroDto.getUsername())){
			return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		if(usuarioRepositorio.existsByEmail(registroDto.getEmail())){
			return new ResponseEntity<>("Esta dirección de correo ya existe", HttpStatus.BAD_REQUEST);
		}
		Persona unaPersona = new Persona();
		Optional<TipoDocumento> idTipoDocumento =tipoDocumentoService.findById(registroDto.getUnaPersona().getUnTipoDocumento().getIdTipoDocumento());
		unaPersona.setUnTipoDocumento(idTipoDocumento.get());
		unaPersona.setNumeroDocumento(registroDto.getUnaPersona().getNumeroDocumento());
		unaPersona.setNombre(registroDto.getUnaPersona().getNombre());
		unaPersona.setApellido(registroDto.getUnaPersona().getApellido());
		unaPersona.setFechaNacimiento(registroDto.getUnaPersona().getFechaNacimiento());
		Municipio unMunicipio =  municipioRepository.getById(registroDto.getUnaPersona().getLugarDeNacimiento().getIdMunicipio());
		unaPersona.setLugarDeNacimiento(unMunicipio);
		unaPersona.setTelefono(registroDto.getUnaPersona().getTelefono());
		unaPersona.setDireccion(registroDto.getUnaPersona().getDireccion());
		unaPersona.setFechaCreacion(new Date().toString());
		unaPersona.setBarrio(registroDto.getUnaPersona().getBarrio());
		unaPersona.setFechaModificacion(new Date().toString());
		unaPersona.setEstado("A");
		Persona persona = personaService.save(unaPersona);
		Usuario usuario = new Usuario();
		usuario.setUnaPersona(persona);
		usuario.setUsername(registroDto.getUsername());
		usuario.setEmail(registroDto.getEmail());
		usuario.genearPassword();
		String contraseña =usuario.getPassword();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		//Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		Optional<Rol> roles = rolRepository.findById(registroDto.getUnRol().getId());
		usuario.setRoles(Collections.singleton(roles.get()));
		usuarioRepositorio.save(usuario);
		enviarNuevoPasswordCorreoElectronico(usuario,contraseña);
		return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);
	}

	private void enviarNuevoPasswordCorreoElectronico(Usuario unUsuario,String password){
		String asunto = " Credenciales de acceso - Usuario de acceso a Fundación Mía ''Construyendo Futuro'' ";
		String mensaje =
				"<br><a>Las credenciales de acceso al software Fundación Mia ''Construyendo Futuro'' </a>"
					+ "<a>se realizaron con exito.</a>"
					+ "<br><b>       </b>"
					+ "<br><h2>Los datos de acceso del usuario(a) " +unUsuario.getUnaPersona().getNombre() +" "+unUsuario.getUnaPersona().getApellido()+" son: </h2>"
					+ "<br><b>       </b>"
					+ "<br><b>Usuario: </b>" + unUsuario.getUsername()
					+ "<br><b>Correo: </b>" + unUsuario.getEmail()
					+ "<br><b>Contraseña: </b>" + password
					+ "<br><a>Una vez ingrese el usuario con la  contraseña, se sugiere realizar el cambio por </a>"
					+ "<a>una contraseña de fácil acceso .   <a>"
					+ "<br>"
					+ "<hr>"
					+ "<br><b>       </b>"
					+ "<br><b>Fundación Mía ''Construyendo futuro'' 2022 </b>"
					+ "<br><br><img src = 'https://i.ibb.co/2dPrLBQ/leidy-gomez-logo-mia-1.png' with='300px' height='220px'/>";

		Correo.enviarCorreo(unUsuario.getEmail(), asunto, mensaje);
	}
	
	
	@PutMapping("recuperar")
	public ResponseEntity<?> recuperarContrasena(@RequestBody RecuperarContraseñaDto recuperarDto){
		try {
			Optional<Usuario> saveUser = usuarioRepositorio.findByUsernameOrEmail(recuperarDto.getUsername(), recuperarDto.getEmail());
			Usuario usuario = saveUser.get();
			usuario.setPassword("");
			usuario.genearPassword();
			String contraseña =usuario.getPassword();
			usuario.setPassword(passwordEncoder.encode(contraseña));
			usuarioRepositorio.save(usuario);
			recuperarContraseña(usuario, contraseña);
			return new ResponseEntity<>("contraseña actualizada", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("algo mal", HttpStatus.BAD_REQUEST);
		}
	}

	private void recuperarContraseña(Usuario unUsuario, String password){
		String asunto = " Nuevas credenciales de acceso - Usuario de acceso a FundaciÃ³n MÃ­a ''Construyendo Futuro'' ";
		String mensaje =
			"<br><a>Las nuevas credenciales de acceso al software FundaciÃ³n Mia ''Construyendo Futuro'' </a>"
				+ "<a>se realizaron con exito.</a>"
				+ "<br><b>       </b>"
				+ "<br><b>       </b>"
				+ "<br><b>Usuario: </b>" + unUsuario.getUsername()
				+ "<br><b>Correo: </b>" + unUsuario.getEmail()
				+ "<br><b>ContraseÃ±a: </b>" + password
				+ "<br><a>Una vez ingrese el usuario con la  contraseÃ±a, se sugiere realizar el cambio por </a>"
				+ "<a>una contraseÃ±a de fÃ¡cil acceso .   <a>"
				+ "<br>"
				+ "<hr>"
				+ "<br><b>       </b>"
				+ "<br><b>FundaciÃ³n MÃ­a ''Construyendo futuro'' 2022 </b>"
				+ "<br><br><img src = 'https://i.ibb.co/2dPrLBQ/leidy-gomez-logo-mia-1.png' with='300px' height='220px'/>";
		Correo.enviarCorreo(unUsuario.getEmail(), asunto, mensaje);
	}
	
}
