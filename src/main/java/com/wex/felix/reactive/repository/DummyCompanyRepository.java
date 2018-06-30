package com.wex.felix.reactive.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.wex.felix.reactive.model.Company;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DummyCompanyRepository implements CompanyRepository {

	private final Map<Integer, Company> company = new HashMap<>();

	public DummyCompanyRepository() {
		company.put(1, new Company("John Doe", "06299777000185"));
		company.put(2, new Company("Jane Doe", "53838002000110"));
	}

	@Override
	public Mono<Company> getCompany(int id) {
		return Mono.justOrEmpty(company.get(id));
	}

	@Override
	public Flux<Company> allCompanies() {
		return Flux.fromIterable(company.values());
	}

	@Override
	public Mono<Void> saveCompany(Mono<Company> companyMono) {
		return companyMono.doOnNext(person -> {
			int id = company.size() + 1;
			company.put(id, person);
			System.out.format("Saved %s with id %d%n", person, id);
		}).thenEmpty(Mono.empty());
	}
}