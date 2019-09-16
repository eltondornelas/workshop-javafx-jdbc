package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll() {
		return dao.findAll();
		//vai no banco de dados buscar os departamentos
	}	
	
	public void saveOrUpdate(Department obj) {
		if (obj.getId() == null) {
			//significa que está inserindo um novo departamento
			dao.insert(obj);			
		}
		else {
			dao.update(obj);
		}
	}
}
