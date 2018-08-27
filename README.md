# testpac4j

Simple Project to try PAC4J With JAX-RS

**Endpoints**

* GET `http://localhost:8080/testpac4j/rest/auth` (Use IpAuthenticator client to generate an IpProfile who returns a JWT Token in the response, change IpRegExAtuehticator in ConfigSecurity to match your IP).
* GET `http://localhost:8080/testpac4j/rest/auth/profile` (Get the authenticated user passing the JWT Token. Use ParameterClient (with token param) or Header Client (with Auth header) with the JWT Token to get access to this resource).
