package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;

public class MyBATISItemDAO implements ItemDAO{

	@Inject
	private ItemMapper itemMapper;    

	@Override
	public void save(Item it) throws PersistenceException{
		try{
			itemMapper.insertarItem(it);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item "+it.toString(),e);
		}        

	}

	@Override
	public Item load(int id) throws PersistenceException {
		try{
			return itemMapper.consultarItem(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}
	
	@Override
	public List<Item> load() throws PersistenceException {
		try{
			return itemMapper.consultarItems();
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar los items",e);
		}


	}

	@Override
	public void actualizarTarifa(int id, long tf) throws PersistenceException {
		try{
			itemMapper.actualizarTarifaItem(id,tf);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al actualizar el item",e);
		}

	}
	
 }