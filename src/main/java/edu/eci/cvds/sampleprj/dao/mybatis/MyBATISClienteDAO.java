package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public class MyBATISClienteDAO implements ClienteDAO{

	@Inject
	private ClienteMapper clienteMapper;

	@Override
	public Cliente load(long id) throws PersistenceException {
		try{
			return clienteMapper.consultarCliente(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el cliente "+id,e);
		}
	}
	
	@Override
	public List<Cliente> load() throws PersistenceException {
		try{
			return clienteMapper.consultarClientes();
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar los clientes",e);
		}
	}
	
	@Override
	public void save(Cliente cl) throws PersistenceException{
		try{
			clienteMapper.insertarCliente(cl);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el cliente "+cl.toString(),e);
		}        

	}

	@Override
	public void vetar(long doc, int est) throws PersistenceException {
		try{
			clienteMapper.vetarCliente(doc, est);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al vetar el cliente "+doc,e);
		}  
	}
 }