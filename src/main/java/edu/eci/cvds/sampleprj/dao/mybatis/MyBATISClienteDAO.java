package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO{

	@Inject
	private ClienteMapper clienteMapper;

	@Override
	public Cliente load(int id) throws PersistenceException {
		try{
			return clienteMapper.consultarCliente(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el cliente "+id,e);
		}


	}
 }