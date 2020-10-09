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
import java.util.Calendar;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   private ClienteDAO clienteDAO;
   private ItemRentadoDAO itemRentadoDAO;
   private TipoItemDAO tipoItemDAO;
   private ServiciosAlquilerItemsStub servicioAlquilerItemsStub;
   

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
	   return servicioAlquilerItemsStub.valorMultaRetrasoxDia(1);
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
		   return servicioAlquilerItemsStub.consultarMultaAlquiler(iditem,fechaDevolucion);
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
		   return servicioAlquilerItemsStub.consultarCostoAlquiler(iditem, numdias);
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el costo",ex);
       }
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
	   try {
		   servicioAlquilerItemsStub.actualizarTarifaItem(id, tarifa);
	   } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el costo",ex);
       }
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
	   try {
		   itemDAO.save(i);
	   } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar item",ex);
       }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       
   }
}