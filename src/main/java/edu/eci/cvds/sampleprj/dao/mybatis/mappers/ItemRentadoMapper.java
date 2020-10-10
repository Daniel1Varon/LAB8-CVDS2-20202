package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;


public interface ItemRentadoMapper {
    public List<ItemRentado> getItemsRentados();

    public ItemRentado getItemRentado(int id);

    public void addItemRentado(int cliid,int itemid,Date fechaini,Date fechafin);
}