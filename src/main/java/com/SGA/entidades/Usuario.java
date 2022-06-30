package com.SGA.entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = { "username" }),
		        @UniqueConstraint(columnNames = { "email" })
        }
)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column
	private String username;

    @Column
	private String email;

    @Column
	private String password;
    
    @Transient
    private String confirmPassword;

    @ManyToMany
	private Set<Rol> roles = new HashSet<>();
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Persona")
	private Persona unaPersona;

    public void genearPassword() {
        String minusculas= "abcdefghijklmnopqrstuvwxyz";
        String mayusculas="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String dijitos="0123456789";
        String especiales="@#$%&?";
        String passwordGenerado="";
        for(int i=0; i<2; i++){
            Random aleatorio= new Random();
            int posmin= aleatorio.nextInt(minusculas.length());
            int posmay=aleatorio.nextInt(mayusculas.length());
            int posDigitos= aleatorio.nextInt(dijitos.length());
            int posEspeciales= aleatorio.nextInt(especiales.length());
            
            passwordGenerado+=minusculas.substring(posmin,posmin+1)+
                    mayusculas.substring(posmay,posmay+1)+
                    dijitos.substring(posDigitos,posDigitos+1)+
                    especiales.substring(posEspeciales,posEspeciales+1);
        }
        
        this.password = passwordGenerado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles +
                ", unaPersona=" + unaPersona +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && username.equals(usuario.username) && email.equals(usuario.email) && password.equals(usuario.password) && confirmPassword.equals(usuario.confirmPassword) && roles.equals(usuario.roles) && unaPersona.equals(usuario.unaPersona);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, confirmPassword, roles, unaPersona);
    }
}
