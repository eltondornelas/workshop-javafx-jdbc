package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	public List<Department> findAll() {
		//MOCK -> só retorna dados de mentira, não é dados do banco de dados
		List<Department> list = new ArrayList<>();
		
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Electronics"));
		
		return list;
		
	}
	
	
}
