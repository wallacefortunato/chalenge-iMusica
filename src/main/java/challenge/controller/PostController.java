package challenge.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import challenge.dto.DtoPost;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import challenge.model.Post;
import challenge.repository.PostRepository;
import challenge.security.IAuthenticationFacade;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/chalenge-posts")
public class PostController {

	@Autowired
	private PostRepository repository;
	
	@Autowired
    IAuthenticationFacade authenticationFacade;
	
	@PostMapping("/posts")
	public ResponseEntity<Object> newPost(@Valid @RequestBody DtoPost dtoPost) {

		var post = new Post();
		BeanUtils.copyProperties(dtoPost, post);
		post.setDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
	}
	
	/*@GetMapping("/posts")
	public Iterable<Post> listPost() {
		return repository.findAll();
	}*/

	@GetMapping("/posts")
	public ResponseEntity<Page<Post>> listPost(@PageableDefault(page = 0, size = 10, sort = "posts", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
	}
	
	@GetMapping("/post/{title}")
	public List<Post> getPostByTitle(@PathVariable("title") String title) {
		return repository.findByName(title);
	}

	@PutMapping("/{title}")
	public ResponseEntity<Object> updatePost(@PathVariable(value = "title") String title, @RequestBody @Valid DtoPost dtoPost) {
		Optional<Post> postOptional = repository.findByName(title);
		if (!postOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
		}

		var postModel = new Post();
		BeanUtils.copyProperties(dtoPost, postModel);
		postModel.setTitle(postOptional.get().getTitle());
		postModel.setText(postOptional.get().getText());

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postModel));
	}
	@DeleteMapping("/posts/{title}")
	public void deletePost(@PathVariable("title") String nome) {
		List<Post> var = repository.findByName(nome);
	}
}
