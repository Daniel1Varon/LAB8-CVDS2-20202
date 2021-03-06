package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO{

	@Inject
	private ItemRentadoMapper itemRentadoMapper;   

	@Override
	public void save(ItemRentado ir) throws PersistenceException {
		// TODO Auto-generated method stub
		try{
			itemRentadoMapper.addItemRentado(ir.getId(),ir.getItemId(),ir.getFechainiciorenta(),ir.getFechafinrenta());
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item rentado "+ir.toString(),e);
		}
	}

	@Override
	public ItemRentado load(int ir) throws PersistenceException {
		try{
			return itemRentadoMapper.getItemRentado(ir);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item rentado "+ir,e);
		}
	}
 }