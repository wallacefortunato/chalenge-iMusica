package challenge.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private LocalDateTime date;

	@Column(nullable = false, length = 130)
	private String title;

	@Column(nullable = false, length = 300)
	private String text;

	@Column(nullable = false, length = 130)
	private User user;
	
	public Post() {
	
	}

	public Post(Long id, Date date, @NotBlank String title, @NotBlank String text, User user) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.text = text;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", date=" + date + ", title=" + title + ", text=" + text + ", user=" + user + "]";
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
