package sim;

import api.cte.TypeConfiguration;

public class TestTypeConfiguration {
	public static void main(String[] args) {
		System.out.println(TypeConfiguration.size);
		TypeConfiguration.getConfig();
		System.out.println(TypeConfiguration.types);
		System.out.println(TypeConfiguration.size);
		System.out.println(TypeConfiguration.get("musee"));
		System.out.println(TypeConfiguration.get("église"));
	}
}
