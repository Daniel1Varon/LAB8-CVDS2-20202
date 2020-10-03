package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ItemRentadoMapper {
    public List<TipoItem> getItemsRentados();

    public TipoItem getItemRentado(int id);
    
    public void addItemRentado(@Param("ir") ItemRentado ir);
}