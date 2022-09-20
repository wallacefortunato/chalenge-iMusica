package challenge.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import challenge.validator.EmailNotRegistered;

@Entity
@Table(name = "TB_USERS_PLATFORM")
public class User implements Serializable {

	private static final long serialVersionUID = 1L; //@Wallace_Fortunato: Atributo de serialização para compatibilidade.

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; //@Wallace_Fortunato: Melhor padrão para identificadores.

	@Column(nullable = false, length = 130)
	private String name;

	@Column(nullable = false, unique = true, length = 130)
	private String email;

	@Column(nullable = false, length = 10) //@Wallace_Fortunato: Senhas de 10 digitos
	private String password;

	private boolean enabled = true;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	/*public User() {

	}

	public User(Long id, @NotBlank String name, @Email String email, @NotBlank String password, boolean enabled,
			Set<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.posts = posts;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}
