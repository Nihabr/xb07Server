package model.user;

import java.sql.ResultSet;

import model.QueryBuild.QueryBuilder;

public class AuthenticateUser {

	private ResultSet resultSet;

	private QueryBuilder qb;

	// Metoden faar email og password fra switchen (udtrukket fra en json) samt en boolean der skal saettes til true hvis det er serveren der logger paa, og false hvis det er en klient
	/**
	 * Allows the client to log in
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @return
	 * @throws Exception
	 */
//	public int authenticate(String email, String password, boolean isAdmin) throws Exception {
		public int authenticate(String email, String password, boolean isAdmin) throws Exception {

		String[] keys = {"userid", "email", "active", "password"};
		String[] fields = {"active"};
		String[] values = {"1"};
		qb = new QueryBuilder();

		// Henter info om bruger fra database via querybuilder
		resultSet = qb.selectFrom(keys, "users").where("email", "=", email).ExecuteQuery();

		// Hvis en bruger med forespurgt email findes
		if (resultSet.next()){

			qb.update("users", fields, values).where("email", "=", email).Execute();
				// Hvis passwords matcher
				if(resultSet.getString("password").equals(password))
				{	


					String[] key = {"type"};
					
					
					resultSet = qb.selectFrom(key, "roles").where("email", "=", email).ExecuteQuery();
					// Hvis brugeren baade logger ind og er registreret som admin, eller hvis brugeren baade logger ind og er registreret som bruger
//					if((resultSet.getString("type").equals("admin") && isAdmin) || (resultSet.getString("type").equals("user") && !isAdmin))
//					{
					resultSet.last();
					if((resultSet.getString("type").equals("admin")) || (resultSet.getString("type").equals("user")) == true)
						{
							
						
						
						
							System.out.println(0);
						return 0; // returnerer "0" hvis bruger/admin er godkendt
					} else {
						System.out.println(4);
						return 4; // returnerer fejlkoden "4" hvis brugertype ikke stemmer overens med loginplatform
					}
				} else {
					System.out.println(3);
					return 3; // returnerer fejlkoden "3" hvis password ikke matcher
				}
			} else {
				return 2; // returnerer fejlkoden "2" hvis bruger er sat som inaktiv
			}

	}
}