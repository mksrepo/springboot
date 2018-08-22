package io.pivotal.workshop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.pivotal.workshop.domain.Snippet;

@Repository
public class SnippetRepository {

	@SuppressWarnings("serial")
	private List<Snippet> snippets = new ArrayList<Snippet>() {
		{
			add(new Snippet("JavaScript: Hello World", "console.log('Hello World!');"));
			add(new Snippet("HTML: Hello World", "<html><body><h1>Hello World</h1></body></html>"));
			add(new Snippet("Bash: Hello World", "echo \"Hello World\""));
			add(new Snippet("Python: Hello World", "print \"Hello World\""));
		}
	};

	public Snippet save(Snippet snippet) {
		assert snippet.getTitle() != null;
		assert snippet.getCode() != null;

		Snippet _snippet = new Snippet(snippet.getTitle(), snippet.getCode());

		this.snippets.add(_snippet);
		return _snippet;
	}

	public List<Snippet> findAll() {
		return snippets;
	}

	public Snippet findById(String id) {
		Optional<Snippet> result = snippets.stream().filter(snippet -> snippet.getId().equals(id)).findFirst();

		return result.get();
	}
}