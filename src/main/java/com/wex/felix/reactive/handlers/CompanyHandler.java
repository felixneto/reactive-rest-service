package com.wex.felix.reactive.handlers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.wex.felix.reactive.model.Company;
import com.wex.felix.reactive.repository.CompanyRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyHandler {

	@Autowired
	private CompanyRepository repository;

	public Mono<ServerResponse> getCompany(ServerRequest request) {
		int companyId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Company> companyMono = this.repository.getCompany(companyId);
		return companyMono
				.flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
				.switchIfEmpty(notFound);
	}


	public Mono<ServerResponse> createCompany(ServerRequest request) {
		Mono<Company> company = request.bodyToMono(Company.class);
		return ServerResponse.ok().build(this.repository.saveCompany(company));
	}

	public Mono<ServerResponse> listCompanies(ServerRequest request) {
		Flux<Company> company = this.repository.allCompanies();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(company, Company.class);
	}

}