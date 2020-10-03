package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
	
	public Cliente load(int cl) throws PersistenceException;
}
