/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author 2106913
 */
public class ItemRentado implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int id_cli;
	private int id_item;
    private Item item;
    private Date fechainiciorenta;
    private Date fechafinrenta;
    
    @SuppressWarnings("null")
	public ItemRentado(int id, Item item, Date fechainiciorenta, Date fechafinrenta) {
        this.id = id;
        this.id_item = item.getId();
        this.id_cli=(Integer) null;
        this.item=item;
        this.fechainiciorenta = fechainiciorenta;
        this.fechafinrenta = fechafinrenta;
    }
    
    public ItemRentado(int id, int id_cli,int id_item, Date fechainiciorenta, Date fechafinrenta) {
        this.id = id;
        this.setId_cli(id_cli);
        this.setId_item(id_item);
        this.fechainiciorenta = fechainiciorenta;
        this.fechafinrenta = fechafinrenta;
    }

    public ItemRentado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getFechainiciorenta() {
        return fechainiciorenta;
    }

    public void setFechainiciorenta(Date fechainiciorenta) {
        this.fechainiciorenta = fechainiciorenta;
    }

    public Date getFechafinrenta() {
        return fechafinrenta;
    }

    public void setFechafinrenta(Date fechafinrenta) {
        this.fechafinrenta = fechafinrenta;
    }

    @Override
    public String toString() {
        return "ItemRentado{" + "id=" + id + ", item=" + item + ", fechainiciorenta=" + fechainiciorenta + ", fechafinrenta=" + fechafinrenta + '}';
    }

	public int getId_cli() {
		return id_cli;
	}

	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

    
    
}
