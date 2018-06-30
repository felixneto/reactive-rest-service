package com.wex.felix.reactive.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

	private final String name;

	private final String cnpj;

	public Company(@JsonProperty("name") String name, @JsonProperty("cnpj") String cnpj) {
		this.name = name;
		this.cnpj = cnpj;
	}

	public String getName() {
		return this.name;
	}

	public String getCnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return "Company{" +
				"name='" + name + '\'' +
				", cnpj=" + cnpj +
				'}';
	}
}