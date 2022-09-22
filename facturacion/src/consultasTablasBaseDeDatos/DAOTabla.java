package consultasTablasBaseDeDatos;

import java.util.List;
import java.util.Optional;

import entidades.Cliente;

public interface DAOTabla<T> {
    
    Optional<T> get(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(Cliente c, String nombre, String email);
    
    void delete(T t);

}