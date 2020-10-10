package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {
	
	private static final int MULTA_DIARIA=5000;
	
   @Inject
   private ItemDAO itemDAO;
   private ClienteDAO clienteDAO;
   private ItemRentadoDAO itemRentadoDAO;
   private TipoItemDAO tipoItemDAO;
   

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
	   return MULTA_DIARIA;
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
		   return clienteDAO.load(docu);
	   } catch (PersistenceException ex) {
		   throw new UnsupportedOperationException("Error al consultar el cliente "+docu,ex);
	   }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
	   try {
		   return clienteDAO.load(idcliente).getRentados();
       } catch(PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar items del cliente "+idcliente,ex);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
		   return clienteDAO.load();
       } catch(PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes",ex);
       }
   }
   
   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
	   try {
		   return itemDAO.load();
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los items",ex);
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   try {
		   ItemRentado it=itemRentadoDAO.load(iditem);
           LocalDate fechaMinimaEntrega=it.getFechafinrenta().toLocalDate();
           LocalDate fechaEntrega=fechaDevolucion.toLocalDate();
           long diasRetraso = ChronoUnit.DAYS.between(fechaMinimaEntrega, fechaEntrega);
           return diasRetraso*MULTA_DIARIA;
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar la multa",ex);
       }
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
		   return tipoItemDAO.load(id);
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item "+id,ex);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
	   try {
		   return tipoItemDAO.load();
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los tipos items",ex);
       }
   }

   @Override
   @Transactional
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		   Calendar calendar=Calendar.getInstance();
		   calendar.setTime(date);
		   calendar.add(Calendar.DAY_OF_YEAR, numdias);
		   Date datef = (Date) calendar.getTime();
		   ItemRentado ir=new ItemRentado((int)docu,item,date,datef);
		   itemRentadoDAO.save(ir);
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar alquiler a cliente",ex);
       }
   }

   @Override
   @Transactional
   public void registrarCliente(Cliente cl) throws ExcepcionServiciosAlquiler {
	   try {
		   clienteDAO.save(cl);
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar cliente",ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		   return itemDAO.load(iditem).getTarifaxDia()*numdias;
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el costo",ex);
       }
   }

   @Override
   @Transactional
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
	   try {
		   itemDAO.actualizarTarifa(id, tarifa);
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el costo",ex);
       }
   }
   @Override
   @Transactional
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
	   try {
		   itemDAO.save(i);
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar item",ex);
       }
   }

   @Override
   @Transactional
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
	   try {
		   if(estado==false) {
			   clienteDAO.vetar(docu, 0);
		   }
		   else {
			   clienteDAO.vetar(docu, 1);
		   }
		   
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar item",ex);
       }
   }
}