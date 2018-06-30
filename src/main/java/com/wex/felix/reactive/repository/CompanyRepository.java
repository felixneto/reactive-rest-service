package com.wex.felix.reactive.repository;


import com.wex.felix.reactive.model.Company;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyRepository {

	Mono<Company> getCompany(int id);

	Flux<Company> allCompanies();

	Mono<Void> saveCompany(Mono<Company> company);

}