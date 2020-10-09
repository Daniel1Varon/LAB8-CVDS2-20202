package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;

public class MyBATISTipoItemDAO implements TipoItemDAO{

	@Inject
	private TipoItemMapper tipoItemMapper;    

	@Override
	public void save(TipoItem ti) throws PersistenceException{
		try{
			tipoItemMapper.addTipoItem(ti.getID(),ti.getDescripcion());
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el tipo item "+ti.toString(),e);
		}        

	}

	@Override
	public TipoItem load(int id) throws PersistenceException {
		try{
			return tipoItemMapper.getTipoItem(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el tipo item "+id,e);
		}


	}
	
	@Override
	public List<TipoItem> load() throws PersistenceException {
		try{
			return tipoItemMapper.getTiposItems();
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar los tipos items",e);
		}


	}
 }