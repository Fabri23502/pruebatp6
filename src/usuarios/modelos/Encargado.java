    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import pedidos.modelos.Pedido;
import java.util.List;
import pedidos.modelos.GestorPedidos;
/*6. A la clase Encargado: hacerla subclase de Usuario y realizar las modificaciones que considere
necesarias.*/
public class Encargado extends Usuario {

      public Encargado(String correo, String apellido, String nombre, String clave,String claverepetida, Perfil perfil){
        super(correo,  apellido ,  nombre,  clave, claverepetida, perfil);
    }
    @Override
    public void mostrar() {
        System.out.println("Encargado: " + this.verApellido() + ", " + this.verNombre());
        super.mostrar(); // Llama al método mostrar de la superclase
    }

    @Override
    public List<Pedido> verPedidos(){
        GestorPedidos gp = GestorPedidos.crear();
        return gp.verPedidos(); 
    }

}
