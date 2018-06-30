package com.wex.felix.reactive.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.wex.felix.reactive.handlers.CompanyHandler;
import com.wex.felix.reactive.repository.CompanyRepository;
import com.wex.felix.reactive.repository.DummyCompanyRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Configuration
public class CompanyRouter {
	@Bean
	public RouterFunction<ServerResponse> route(CompanyHandler companyHandler) {

		return RouterFunctions.nest(RequestPredicates.path("/company"),
				RouterFunctions.nest(RequestPredicates.accept(APPLICATION_JSON),
						RouterFunctions.route(RequestPredicates.GET("/{id}"), companyHandler::getCompany)
						.andRoute(RequestPredicates.method(HttpMethod.GET), companyHandler::listCompanies)
							).andRoute(RequestPredicates.POST("/").and(RequestPredicates.contentType(APPLICATION_JSON)), companyHandler::createCompany));
	}
	@Bean
	public CompanyRepository getCompanyBean() {
		return new DummyCompanyRepository();
	}

}
