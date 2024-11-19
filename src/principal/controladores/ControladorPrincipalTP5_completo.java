/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import Interfaces.IGestorPedidos;
import Interfaces.IGestorProductos;
import Interfaces.IGestorUsuarios;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.GestorProductos;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.Empleado;
import usuarios.modelos.Encargado;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author Mariana
 */
public class ControladorPrincipalTP5_completo {
    public static void main(String[] args) {
        IGestorProductos gp = GestorProductos.crear();
        IGestorUsuarios gu=GestorUsuarios.crear();
        IGestorPedidos gPed = GestorPedidos.crear();
        
        System.out.println("#####PRODUCTOS#####");
        /*CREAR PRODUCTO*/
        System.out.println(gp.crearProducto(1, "Producto1", Categoria.ENTRADA, Estado.DISPONIBLE, 1.0f));
        System.out.println(gp.crearProducto(2, "Producto2", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 2.0f));
        System.out.println(gp.crearProducto(3, "Plato3", Categoria.POSTRE, Estado.DISPONIBLE, 3.0f));
        System.out.println(gp.crearProducto(3, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f));
        //producto repetido, mismo codigo
        System.out.println(gp.crearProducto(0, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f));
        //código inválido vale 0
        System.out.println(gp.crearProducto(4, null, Categoria.POSTRE, Estado.DISPONIBLE, 4.0f));
        //sin descripción -- nula
        System.out.println(gp.crearProducto(4, "", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f));
        //descripción inválida  -- cadena vacia
        System.out.println(gp.crearProducto(4, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 0.0f));
        //precio inválido  -- precio en 0
        System.out.println(gp.crearProducto(4, "Producto4", null, Estado.DISPONIBLE, 4.0f));
        //sin categoría 
        System.out.println(gp.crearProducto(4, "Producto4", Categoria.POSTRE, null, 4.0f));
        //sin estado

        /*RECUPERAR LOS PRODUCTOS CON EL METODO MENU*/
        System.out.println("######## PRODUCTOS CON MENU ########");
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println("######## PRODUCTOS POR CODIGO ########");
        /*OBTENER UN PRODUCTO MEDIANTE EL CODIGO*/
        Producto unProducto1 = gp.obtenerProducto(1);
        Producto unProducto2 = gp.obtenerProducto(2);
        
        System.out.println("######## PRODUCTOS DE UNA CATEGORIA ########");
        /*SUBCONJUNTO DE PRODUCTOS DE UNA CATEGORIA*/
        List<Producto> productosBuscados = gp.verProductosPorCategoria(Categoria.POSTRE);
        System.out.println("Productos filtrados por categoría");
        System.out.println("===================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();

        System.out.println("######## PRODUCTOS POR DESCRIPCION ########");
        /*BUSCAR PRODUCTO POR DESCRIPCION*/
        productosBuscados = gp.buscarProductos("Prod");
        System.out.println("Productos buscados por descripcion");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
      
        System.out.println(gp.modificarProducto(unProducto1, 100, "Producto11", 10.0f, Categoria.PLATO_PRINCIPAL, Estado.NO_DISPONIBLE));
        //se le cambia la descripción, precio, categoría y estado
        //el código, por más que se lo pasa, no se modifica
        productosBuscados = gp.buscarProductos("Producto11");
        System.out.println("Productos buscados");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println(gp.modificarProducto(unProducto1, 100, "", 10.0f, Categoria.PLATO_PRINCIPAL, Estado.NO_DISPONIBLE));
//    
        System.out.println("#####USUARIOS#####");
    
        System.out.println(gu.crearUsuario("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1", "claveCliente1", Perfil.CLIENTE));
        System.out.println(gu.crearUsuario("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2", "claveCliente2", Perfil.CLIENTE));
        System.out.println(gu.crearUsuario("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3", "claveCliente3", Perfil.CLIENTE));
        System.out.println(gu.crearUsuario("cliente3@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
//        cliente duplicado
        System.out.println(gu.crearUsuario(null, "claveCliente4", "ApellidoCliente4", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
//        sin correo
        System.out.println(gu.crearUsuario("", "claveCliente4", "ApellidoCliente4", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
        //correo inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", null, "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
        //sin apellido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
        //apellido inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", null, "claveCliente4", Perfil.CLIENTE));
        //sin nombre
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", "", "claveCliente4", Perfil.CLIENTE));
        //nombre inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4", "claveCliente4", null));
        //sin perfil
        System.out.println(gu.crearUsuario("cliente4@bar.com", null, "ApellidoCliente4", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
        //sin clave
        System.out.println(gu.crearUsuario("cliente4@bar.com", "", "ApellidoCliente4", "NombreCliente4", "claveCliente4", Perfil.CLIENTE));
        //clave inválida
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4", null, Perfil.CLIENTE));
        //sin repetir la clave
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4", "", Perfil.CLIENTE));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("cliente4@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4", "claveCliente44", Perfil.CLIENTE));
        //sin coincidir las claves
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Cliente) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();
        
        System.out.println(gu.crearUsuario("empleado1@bar.com", "claveEmpleado1", "ApellidoEmpleado1", "NombreEmpleado1", "claveEmpleado1", Perfil.EMPLEADO));
        System.out.println(gu.crearUsuario("empleado2@bar.com", "claveEmpleado2", "ApellidoEmpleado2", "NombreEmpleado2", "claveEmpleado2", Perfil.EMPLEADO));
        System.out.println(gu.crearUsuario("empleado3@bar.com", "claveEmpleado3", "ApellidoEmpleado3", "NombreEmpleado3", "claveEmpleado3", Perfil.EMPLEADO));
        System.out.println(gu.crearUsuario("empleado3@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //empleado duplicado
        System.out.println(gu.crearUsuario(null, "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //sin correo
        System.out.println(gu.crearUsuario("", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //correo inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", null, "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //sin apellido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //apellido inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", null, "claveEmpleado4", Perfil.EMPLEADO));
        //sin nombre
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "", "claveEmpleado4", Perfil.EMPLEADO));
        //nombre inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", null));
        //sin perfil
        System.out.println(gu.crearUsuario("empleado4@bar.com", null, "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //sin clave
        System.out.println(gu.crearUsuario("empleado4@bar.com", "", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado4", Perfil.EMPLEADO));
        //clave sin especificar
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", null, Perfil.EMPLEADO));
        //sin repetir clave        
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "", Perfil.EMPLEADO));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("empleado4@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4", "claveEmpleado44", Perfil.EMPLEADO));
        //sin coincidir las claves
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Empleado) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        System.out.println(gu.crearUsuario("encargado1@bar.com", "claveEncargado1", "ApellidoEncargado1", "NombreEncargado1", "claveEncargado1", Perfil.ENCARGADO));
        System.out.println(gu.crearUsuario("encargado2@bar.com", "claveEncargado2", "ApellidoEncargado2", "NombreEncargado2", "claveEncargado2", Perfil.ENCARGADO));
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado3", "ApellidoEncargado3", "NombreEncargado3", "claveEncargado3", Perfil.ENCARGADO));
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //encargado duplicado
        System.out.println(gu.crearUsuario(null, "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //sin correo
        System.out.println(gu.crearUsuario("", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //correo inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", null, "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //sin apellido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //apellido inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", null, "claveEncargado4", Perfil.ENCARGADO));
        //sin nombre
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "", "claveEncargado4", Perfil.ENCARGADO));
        //nombre inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", null));
        //sin perfil
        System.out.println(gu.crearUsuario("encargado3@bar.com", null, "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //sin clave
        System.out.println(gu.crearUsuario("encargado3@bar.com", "", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado4", Perfil.ENCARGADO));
        //clave sin especificar
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", null, Perfil.ENCARGADO));
        //sin repetir clave        
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "", Perfil.ENCARGADO));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4", "claveEncargado44", Perfil.ENCARGADO));
        //sin coincidir las claves

        System.out.println("Encargados");
        System.out.println("==========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Encargado) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();
                
        System.out.println("Menu");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();

        System.out.println("Obtener usuarios y productos");
        System.out.println("=========");
        Usuario unCliente1 = gu.obtenerUsuario("cliente1@bar.com");
        Producto prod1 = gp.obtenerProducto(1);
        Producto prod2 = gp.obtenerProducto(2);
        
        System.out.println("=========");
        System.out.println("Crear pedidos");
        ArrayList<ProductoDelPedido> productosDelPedido1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(unProducto1, 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(unProducto2, 2); 
        LocalDateTime fechaYHora = LocalDateTime.now();
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        if (!productosDelPedido1.contains(pdp1))
            productosDelPedido1.add(pdp1);
        if (!productosDelPedido1.contains(pdp2))
            productosDelPedido1.add(pdp2);
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido1, (Cliente)unCliente1));
        
        Usuario unCliente2 = gu.obtenerUsuario("cliente2@bar.com");
        ArrayList<ProductoDelPedido> productosDelPedido2 = new ArrayList<>();
        ProductoDelPedido pdp3 = new ProductoDelPedido(unProducto1, 10);
        ProductoDelPedido pdp4 = new ProductoDelPedido(unProducto2, 20);
        ProductoDelPedido pdp5 = new ProductoDelPedido(unProducto1, 30);
        //producto repetido        
        if (!productosDelPedido2.contains(pdp3))
            productosDelPedido2.add(pdp3);
        if (!productosDelPedido2.contains(pdp4))
            productosDelPedido2.add(pdp4);
        if (!productosDelPedido2.contains(pdp5))
            productosDelPedido2.add(pdp5);
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido2, (Cliente)unCliente2));

        System.out.println(gPed.crearPedido(null, hora, productosDelPedido2, (Cliente)unCliente1));
        //sin fecha
        System.out.println(gPed.crearPedido(fecha, null, productosDelPedido2, (Cliente)unCliente1));
        //sin hora
       ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        System.out.println(gPed.crearPedido(fecha, hora, null, (Cliente)unCliente1));
        //sin productos
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido3, (Cliente)unCliente1));
        //sin productos
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido2, null));
//        //sin cliente
        System.out.println("=======");
        System.out.println("Pedidos");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
        }
        System.out.println("=======");
        
        System.out.println("Hay pedidos con un producto");
        Producto prod= gp.obtenerProducto(2);
        System.out.println(gPed.hayPedidosConEsteProducto(prod));
        System.out.println("=======");
        
        System.out.println("Hay pedidos con un cliente");
        Usuario unCliente3= gu.obtenerUsuario("cliente3@bar.com");
        System.out.println(gPed.hayPedidosConEsteCliente((Cliente)unCliente3));
        System.out.println("=======");
        
        System.out.println("Hay pedidos con un cliente");
        System.out.println(gPed.hayPedidosConEsteCliente((Cliente)unCliente1));
        System.out.println("=======");
        
        System.out.println("Obtener el pedido y agregarle al cliente 1");        
        Pedido unPedido1=gPed.obtenerPedido(1);
        ((Cliente)unCliente1).agregarPedido(unPedido1);

        ((Cliente)unCliente1).agregarPedido(unPedido1);
        //pedido repetido
        System.out.println("=======");
        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
                   System.out.println("can elem: "+unCliente1.verPedidos().size());
        for(Pedido p : unCliente1.verPedidos()) { 
            p.mostrar();
        }
        System.out.println();        

        Usuario unEmpleado3 = gu.obtenerUsuario("empleado3@bar.com");
        System.out.println("Todos los pedidos (consultados por un empleado)");
        for(Pedido p : unEmpleado3.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
                
        Usuario unEncargado3 = gu.obtenerUsuario("encargado3@bar.com");
        System.out.println("Todos los pedidos (consultados por un encargado)");
        for(Pedido p : unEncargado3.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println("Cancelar el pedido de unCliente1");
        ((Cliente)unCliente1).cancelarPedido(unPedido1);
        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
        for(Pedido p : unCliente1.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println("Ver si existe un usuario");
        System.out.println(gu.existeEsteUsuario(unCliente1));
//        Usuario unCliente10 = new Cliente("cliente10@bar.com", "claveCliente10", "ApellidoCliente10", "NombreCliente10");
        //usuario inexistente
        System.out.println("Ver si existe un usuario");
//        System.out.println(gu.existeEsteUsuario(unCliente10));
           
        System.out.println("Usuarios");
        System.out.println("========");
        for(Usuario u : gu.verUsuarios()) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
        
        List<Usuario> usuariosBuscados = gu.buscarUsuarios("ApellidoCliente");
        System.out.println("Usuarios filtrados");
        System.out.println("==================");
        for(Usuario u : usuariosBuscados) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println(gPed.existeEstePedido(unPedido1));
        Pedido unPedido10 = gPed.obtenerPedido(10);
        //pedido inexistente
        System.out.println(gPed.existeEstePedido(unPedido10));
        
        Pedido unPedido2 = gPed.obtenerPedido(2);
        System.out.println(gPed.cambiarEstado(unPedido2));
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();  
        
        System.out.println(gPed.cancelarPedido(unPedido2));
        //no se puede por el estado del pedido
        System.out.println(gPed.cancelarPedido(unPedido1));
//        //sí se puede
  
        System.out.println("\n\nPedidos");
        System.out.println("=======");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
        }
        System.out.println();  
        
    }
}
