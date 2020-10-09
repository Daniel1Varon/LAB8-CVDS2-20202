package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {
	public void save(TipoItem ti) throws PersistenceException;

	public TipoItem load(int ti) throws PersistenceException;
	
	public List<TipoItem> load() throws PersistenceException;
}
