package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
	
	
	public Cliente load(long cl) throws PersistenceException;

	public List<Cliente> load() throws PersistenceException;
	
	public void save(Cliente cl) throws PersistenceException;
}
