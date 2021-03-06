package pl.piomin.samples.springboot.kubernetes.controller;

import java.util.Optional;
import java.util.Set;

import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;
import pl.piomin.samples.springboot.kubernetes.repository.PersonRepository;
import pl.piomin.samples.springboot.kubernetes.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private PersonRepository repository;
	@Autowired
	private PersonService service;

	PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public Person add(@RequestBody Person person) {
		return repository.save(person);
	}

	@PostMapping("/random")
	public Set<Person> add() {
		Person p1 = new Person();
		p1.setAge(1);
		p1.setFirstName("X");
		p1.setLastName("X");
		p1.setGender(Gender.MALE);
		Person p2 = new Person();
		p2.setAge(2);
		p2.setFirstName("Y");
		p2.setLastName("Y");
		p2.setGender(Gender.FEMALE);
		return service.doIt(p1, p2);
	}

	@PutMapping
	public Person update(@RequestBody Person person) {
		return repository.save(person);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		repository.deleteById(id);
	}

	@GetMapping
	public Iterable<Person> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Person> findById(@PathVariable("id") String id) {
		return repository.findById(id);
	}

	@GetMapping("/first-name/{firstName}/last-name/{lastName}")
	public Set<Person> findByFirstNameAndLastName(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return repository.findByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/age-greater-than/{age}")
	public Set<Person> findByAgeGreaterThan(@PathVariable("age") int age) {
		return repository.findByAgeGreaterThan(age);
	}

	@GetMapping("/age/{age}")
	public Set<Person> findByAge(@PathVariable("age") int age) {
		return repository.findByAge(age);
	}

}
